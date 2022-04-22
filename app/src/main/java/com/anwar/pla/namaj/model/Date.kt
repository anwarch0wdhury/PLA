package com.anwar.pla.namaj.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Date (
    val readable: String,
    val timestamp: String,
    val hijri: Hijri,
    val gregorian: Gregorian
): Parcelable