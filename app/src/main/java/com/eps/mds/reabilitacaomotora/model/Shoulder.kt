package com.eps.mds.reabilitacaomotora.model

class Shoulder {

    private var shoulderX = "0.5"
    private var shoulderY = "0.5"
    private var shoulderZ = "0.5"

    private var rotateShoulderX = "0.5"
    private var rotateShoulderY = "0.5"
    private var rotateShoulderZ = "0.5"

    fun updateShoulder(shoulderX: String, shoulderY: String, shoulderZ: String) {
        this.shoulderX = shoulderX
        this.shoulderY = shoulderY
        this.shoulderZ = shoulderZ
    }

    fun updateRotateShoulder(rotateShoulderX: String, rotateShoulderY: String, rotateShoulderZ: String) {
        this.rotateShoulderX = rotateShoulderX
        this.rotateShoulderY = rotateShoulderY
        this.rotateShoulderZ = rotateShoulderZ
    }

    fun getShoulderFormattedData(): String {
        return "$shoulderX $shoulderY $shoulderZ $rotateShoulderX $rotateShoulderY $rotateShoulderZ"
    }

}