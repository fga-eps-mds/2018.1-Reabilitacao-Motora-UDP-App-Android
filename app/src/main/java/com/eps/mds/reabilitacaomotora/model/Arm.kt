package com.eps.mds.reabilitacaomotora.model

class Arm {

    private var armX = "0.5"
    private var armY = "0.5"
    private var armZ = "0.5"

    private var rotateArmX = "0.5"
    private var rotateArmY = "0.5"
    private var rotateArmZ = "0.5"

    fun updateArm(armX: String, armY: String, armZ: String) {
        this.armX = armX
        this.armY = armY
        this.armZ = armZ
    }

    fun updateRotateArm(rotateArmX: String, rotateArmY: String, rotateArmZ: String) {
        this.rotateArmX = rotateArmX
        this.rotateArmY = rotateArmY
        this.rotateArmZ = rotateArmZ
    }

    fun getArmFormattedData(): String {
        return "$armX $armY $armZ $rotateArmX $rotateArmY $rotateArmZ"
    }

}