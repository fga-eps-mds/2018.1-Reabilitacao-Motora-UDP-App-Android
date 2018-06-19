package com.eps.mds.reabilitacaomotora.model

class Hand {

    private var handX = "0.5"
    private var handY = "0.5"
    private var handZ = "0.5"

    private var rotateHandX = "0.5"
    private var rotateHandY = "0.5"
    private var rotateHandZ = "0.5"

    fun updateHand(handX: String, handY: String, handZ: String) {
        this.handX = handX
        this.handY = handY
        this.handZ = handZ
    }

    fun updateRotateHand(rotateHandX: String, rotateHandY: String, rotateHandZ: String) {
        this.rotateHandX = rotateHandX
        this.rotateHandY = rotateHandY
        this.rotateHandZ = rotateHandZ
    }

    fun getHandFormattedData(): String {
        return "$handX $handY $handZ $rotateHandX $rotateHandY $rotateHandZ"
    }

}