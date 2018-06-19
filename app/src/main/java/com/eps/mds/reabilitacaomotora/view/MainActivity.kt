package com.eps.mds.reabilitacaomotora.view

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorManager
import android.os.Handler
import android.util.Log
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.eps.mds.reabilitacaomotora.R
import com.eps.mds.reabilitacaomotora.model.Body
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


class MainActivity : AppCompatActivity(), SensorEventListener {

    private var senSensorManager: SensorManager? = null
    private var senAccelerometer: Sensor? = null

    private var ipAddress: String = "192.168.0.27"
    private var port:Int = 5005

    private lateinit var ipEditText: EditText
    private lateinit var portEditText: EditText
    private lateinit var startStopSwitch: Switch

    private lateinit var sensorNameTextView: TextView
    private lateinit var sensorManufacturerTextView: TextView
    private lateinit var sensorVersionTextView: TextView
    private lateinit var sensorPowerTextView: TextView
    private lateinit var sensorResolutionTextView: TextView
    private lateinit var sensorMaximumReachTextView: TextView


    private lateinit var xAxisTextView: TextView
    private lateinit var yAxisTextView: TextView
    private lateinit var zAxisTextView: TextView

    private lateinit var body: Body

    private var time = 0


    @SuppressLint("SetTextI18n")
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

        xAxisTextView = findViewById(R.id.xAxisTextView)
        yAxisTextView = findViewById(R.id.yAxisTextView)
        zAxisTextView = findViewById(R.id.zAxisTextView)

        senSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        senAccelerometer = senSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        senSensorManager!!.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL)

        sensorNameTextView.text = senAccelerometer!!.name.toString()
        sensorManufacturerTextView.text = senAccelerometer!!.vendor
        sensorVersionTextView.text = senAccelerometer!!.version.toString()
        sensorPowerTextView.text = senAccelerometer!!.power.toString() + " mA"
        sensorResolutionTextView.text =  senAccelerometer!!.resolution.toString() + " m/s²"
        sensorMaximumReachTextView.text =  senAccelerometer!!.maximumRange.toString() + " m/s²"

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

    private fun sendMessage(message: String) {

        val handler = Handler()
        val thread = Thread(object : Runnable {

            lateinit var stringData: String

            override fun run() {

                var ds: DatagramSocket? = null
                try {
                    ds = DatagramSocket()
                    // IP Address below is the IP address of that Device where server socket is opened.
                    val serverAddr = InetAddress.getByName(ipEditText.text.toString())
                    var dp: DatagramPacket
                    dp = DatagramPacket(message.toByteArray(), message.length, serverAddr, port)
                    ds.send(dp)

                    val lMsg = ByteArray(1024)
                    dp = DatagramPacket(lMsg, lMsg.size)
                    ds.receive(dp)
                    stringData = String(lMsg, 0, dp.length)

                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    if (ds != null) {
                        ds.close()
                    }
                }

                handler.post(Runnable {
//                    val s = mTextViewReplyFromServer.getText().toString()
//                    if (stringData.trim { it <= ' ' }.length != 0)
//                        mTextViewReplyFromServer.setText(s + "\nFrom Server : " + stringData)
                })
            }
        })

        thread.start()
    }

    private fun sendData(x: String, y: String, z: String) {

        time = time.plus(0.2).toInt()
        body.arm!!.updateArm(x, y, z)

        val udpData:String = time.toString() + body.getFormattedUdpData()

        sendMessage(udpData)
    }
}

