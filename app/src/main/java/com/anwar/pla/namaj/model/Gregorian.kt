package com.anwar.pla.namaj.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gregorian (
    val date: String,
    val format: String,
    val day: String,
    val weekday: GregorianWeekday,
    val month: GregorianMonth,
    val year: String,
    val designation: Designation
): Parcelable