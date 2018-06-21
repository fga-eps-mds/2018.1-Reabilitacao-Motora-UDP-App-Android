package com.eps.mds.reabilitacaomotora.model



class Sensor {

    var name: String = ""
    var manufacturer: String = ""
    var version: String = ""
    var power: String = ""
    var resolution: String = ""
    var maximumReach: String = ""
    var xAxis: String = "0.0"
    var yAxis: String = "0.0"
    var zAxis: String = "0.0"

    constructor(name: String, manufacturer: String, version: String, power: String,
                resolution: String, maximumReach:String) {

        this.name = name
        this.manufacturer = manufacturer
        this.version = version
        this.power = power
        this.resolution = resolution
        this.maximumReach = maximumReach
    }


    fun updateAxis(xAxis: Float, yAxis: Float, zAxis: Float) {
        this.xAxis = xAxis.toString()
        this.yAxis = yAxis.toString()
        this.zAxis = zAxis.toString()
    }
}