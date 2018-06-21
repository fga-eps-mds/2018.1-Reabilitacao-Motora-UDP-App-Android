package com.eps.mds.reabilitacaomotora.model

class Arm {

    private var armX = 0.5
    private var armY = 0.5
    private var armZ = 0.5

    private var rotateArmX = 0.5
    private var rotateArmY = 0.5
    private var rotateArmZ = 0.5

    fun updateArm(armX: Double, armY: Double, armZ: Double) {
        this.armX = armX * 10
        this.armY = armY * 10
        this.armZ = armZ * 10
    }

    fun updateRotateArm(rotateArmX: Double, rotateArmY: Double, rotateArmZ: Double) {
        this.rotateArmX = rotateArmX * 10
        this.rotateArmY = rotateArmY * 10
        this.rotateArmZ = rotateArmZ * 10
    }

    fun getArmFormattedData(): String {
        return "$armX $armY $armZ $rotateArmX $rotateArmY $rotateArmZ"
    }

}