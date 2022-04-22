package com.anwar.pla.namaj.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HijriWeekday (
    val en: String,
    val ar: String
): Parcelable