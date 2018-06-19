package com.eps.mds.reabilitacaomotora.model

class Udp {

    private var handX = "0.5"
    private var handY = "0.5"
    private var handZ = "0.5"

    private var rotateHandX = "0.5"
    private var rotateHandY = "0.5"
    private var rotateHandZ = "0.5"

    private var armX = "0.5"
    private var armY = "0.5"
    private var armZ = "0.5"

    private var rotateArmX = "0.5"
    private var rotateArmY = "0.5"
    private var rotateArmZ = "0.5"

    private var shoulderX = "0.5"
    private var shoulderY = "0.5"
    private var shoulderZ = "0.5"

    private var rotateShoulderX = "0.5"
    private var rotateShoulderY = "0.5"
    private var rotateShoulderZ = "0.5"

    private var elbowX = "0.5"
    private var elbowY = "0.5"
    private var elbowZ = "0.5"

    private var rotateElbowX = "0.5"
    private var rotateElbowY = "0.5"
    private var rotateElbowZ = "0.5"


    fun setHand(handX: String, handY: String, handZ: String) {
        this.handX = handX
        this.handY = handY
        this.handZ = handZ
    }

    fun setRotateHand(rotateHandX: String, rotateHandY: String, rotateHandZ: String) {
        this.rotateHandX = rotateHandX
        this.rotateHandY = rotateHandY
        this.rotateHandZ = rotateHandZ
    }

    fun setArm(armX: String, armY: String, armZ: String) {
        this.armX = armX
        this.armY = armY
        this.armZ = armZ
    }

    fun setRotateArm(rotateArmX: String, rotateArmY: String, rotateArmZ: String) {
        this.rotateArmX = rotateArmX
        this.rotateArmY = rotateArmY
        this.rotateArmZ = rotateArmZ
    }

    fun setShoulder(shoulderX: String, shoulderY: String, shoulderZ: String) {
        this.shoulderX = shoulderX
        this.shoulderY = shoulderY
        this.shoulderZ = shoulderZ
    }

    fun setRotateShoulder(rotateShoulderX: String, rotateShoulderY: String, rotateShoulderZ: String) {
        this.rotateShoulderX = rotateShoulderX
        this.rotateShoulderY = rotateShoulderY
        this.rotateShoulderZ = rotateShoulderZ
    }

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

}