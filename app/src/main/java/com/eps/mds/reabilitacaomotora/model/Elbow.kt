package com.eps.mds.reabilitacaomotora.model

class Elbow {

    private var elbowX = "0.5"
    private var elbowY = "0.5"
    private var elbowZ = "0.5"

    private var rotateElbowX = "0.5"
    private var rotateElbowY = "0.5"
    private var rotateElbowZ = "0.5"


    fun setElbow(elbowX: String, elbowY: String, elbowZ: String) {
        this.elbowX = elbowX
        this.elbowY = elbowY
        this.elbowZ = elbowZ
    }

    fun setRotateElbow(rotateElbowX: String, rotateElbowY: String, rotateElbowZ: String) {
        this.rotateElbowX = rotateElbowX
        this.rotateElbowY = rotateElbowY
        this.rotateElbowZ = rotateElbowZ
    }

    fun getElbowFormattedData(): String {
        return "$elbowX $elbowY $elbowZ $rotateElbowX $rotateElbowY $rotateElbowZ"
    }

}