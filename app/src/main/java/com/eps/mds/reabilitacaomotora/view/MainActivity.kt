package com.eps.mds.reabilitacaomotora.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorManager
import android.util.Log
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.eps.mds.reabilitacaomotora.R
import com.eps.mds.reabilitacaomotora.model.Body
import android.text.method.ScrollingMovementMethod
import com.eps.mds.reabilitacaomotora.controller.UDP_Client

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var senSensorManager: SensorManager? = null
    private var senAccelerometer: Sensor? = null

    private lateinit var ipEditText: EditText
    private lateinit var portEditText: EditText
    private lateinit var startStopSwitch: Switch

    private lateinit var sensorNameTextView: TextView
    private lateinit var sensorManufacturerTextView: TextView
    private lateinit var sensorVersionTextView: TextView
    private lateinit var sensorPowerTextView: TextView
    private lateinit var sensorResolutionTextView: TextView
    private lateinit var sensorMaximumReachTextView: TextView

    private lateinit var logTextView: TextView
    
    private lateinit var xAxisTextView: TextView
    private lateinit var yAxisTextView: TextView
    private lateinit var zAxisTextView: TextView

    private val client = UDP_Client()

    private lateinit var body: Body

    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ipEditText = findViewById(R.id.ipEditText)
        portEditText = findViewById(R.id.portEditText)
        startStopSwitch = findViewById(R.id.startStopSwitch)

        sensorNameTextView =  findViewById(R.id.sensorNameTextView)
        sensorManufacturerTextView =  findViewById(R.id.sensorManufacturerTextView)
        sensorVersionTextView =  findViewById(R.id.sensorVersionTextView)
        sensorPowerTextView =  findViewById(R.id.sensorPowerTextView)
        sensorResolutionTextView =  findViewById(R.id.sensorResolutionTextView)
        sensorMaximumReachTextView =  findViewById(R.id.sensorMaximumReachTextView)

        logTextView = findViewById(R.id.logTextView)
        logTextView.movementMethod = ScrollingMovementMethod()

        xAxisTextView = findViewById(R.id.xAxisTextView)
        yAxisTextView = findViewById(R.id.yAxisTextView)
        zAxisTextView = findViewById(R.id.zAxisTextView)

        senSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        senAccelerometer = senSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        senSensorManager!!.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_GAME)

        sensorNameTextView.text = senAccelerometer!!.name.toString()
        sensorManufacturerTextView.text = senAccelerometer!!.vendor
        sensorVersionTextView.text = senAccelerometer!!.version.toString()
        sensorPowerTextView.text = senAccelerometer!!.power.toString().plus(" mA")
        sensorResolutionTextView.text =  senAccelerometer!!.resolution.toString().plus(" m/s²")
        sensorMaximumReachTextView.text =  senAccelerometer!!.maximumRange.toString().plus(" m/s²")

        body = Body()
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        val mySensor = sensorEvent!!.sensor

        if (mySensor.type == Sensor.TYPE_ACCELEROMETER) {
            val x = sensorEvent.values[0]
            val y = sensorEvent.values[1]
            val z = sensorEvent.values[2]

            xAxisTextView.text = x.toString()
            yAxisTextView.text = y.toString()
            zAxisTextView.text = z.toString()

            if(startStopSwitch.isChecked) {
                Log.d("SENSOR", "send")
                sendData(x.toString() ,y.toString(), z.toString())
            }else {
                time = 0.0
            }

        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onPause() {
        super.onPause()
        senSensorManager!!.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        senSensorManager!!.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun sendData(x: String, y: String, z: String) {

        time = time.plus(0.2)
        body.arm!!.updateArm(x.toDouble(), y.toDouble(), z.toDouble())

        val udpData:String = time.toString() + " " + body.getFormattedUdpData() + " "

        client.setMessage(udpData)
        client.sendMessage(ipEditText.text.toString(), Integer.parseInt(portEditText.text.toString()))
    }
}