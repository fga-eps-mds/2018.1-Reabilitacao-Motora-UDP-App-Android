package com.eps.mds.reabilitacaomotora.model

class Body {

    var hand: Hand? = null
    var arm: Arm? = null
    var elbow: Elbow? = null
    var shoulder: Shoulder? = null

    init {
        hand = Hand()
        arm = Arm()
        elbow = Elbow()
        shoulder = Shoulder()
    }

    fun getFormattedUdpData(): String {
        return hand!!.getHandFormattedData() + " " + arm!!.getArmFormattedData() + " " +
                elbow!!.getElbowFormattedData() + " " + shoulder!!.getShoulderFormattedData()
    }

}