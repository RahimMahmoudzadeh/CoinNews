package com.rahim.coinnews.core.utils.extentian

fun Number.roundToTwoDecimalPlaces(): String {
    return String.format("%.2f", this)
}