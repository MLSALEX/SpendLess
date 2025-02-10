package com.spendless.app

enum class RepeatFrequency(val displayName: String) {
    DOES_NOT_REPEAT("Does not repeat"),
    DAILY("Daily"),
    WEEKLY("Weekly on Monday"),
    MONTHLY("Monthly on the 14th"),
    YEARLY("Yearly on Feb 14th");

    fun toDisplayString(): String = displayName
}