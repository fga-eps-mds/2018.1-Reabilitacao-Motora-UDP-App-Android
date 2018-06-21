package com.eps.mds.reabilitacaomotora.view

import android.content.Context
import android.databinding.DataBindingUtil
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
import com.eps.mds.reabilitacaomotora.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), SensorEventListener {

    private var senSensorManager: SensorManager? = null
    private var senAccelerometer: Sensor? = null

    private lateinit var ipEditText: EditText
    private lateinit var portEditText: EditText
    private lateinit var startStopSwitch: Switch
    private lateinit var logTextView: TextView

    private val client = UDP_Client()

    private lateinit var body: Body

    private var time = 0.0

    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var sensorModel: com.eps.mds.reabilitacaomotora.model.Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ipEditText = findViewById(R.id.ipEditText)
        portEditText = findViewById(R.id.portEditText)
        startStopSwitch = findViewById(R.id.startStopSwitch)

        logTextView = findViewById(R.id.logTextView)
        logTextView.movementMethod = ScrollingMovementMethod()

        senSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        senAccelerometer = senSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        senSensorManager!!.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_GAME)


        body = Body()

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sensorModel = com.eps.mds.reabilitacaomotora.model.Sensor( senAccelerometer!!.name.toString(),
                                                                   senAccelerometer!!.vendor,
                                                                   senAccelerometer!!.version.toString(),
                                                             senAccelerometer!!.power.toString() + " mA",
                                                          senAccelerometer!!.resolution.toString() + " m/s²",
                                                      senAccelerometer!!.maximumRange.toString() + " m/s²"
        )

        mainBinding.sensor = sensorModel

    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        val mySensor = sensorEvent!!.sensor

        if (mySensor.type == Sensor.TYPE_ACCELEROMETER) {

            sensorModel.updateAxis(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2])
            mainBinding.sensor = sensorModel

            if(startStopSwitch.isChecked) {
                Log.d("SENSOR", "send")
                sendData(sensorModel.xAxis ,
                         sensorModel.yAxis,
                         sensorModel.zAxis)

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