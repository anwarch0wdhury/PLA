package com.anwar.pla.namaj.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Params (
    val fajr: Long,
    val isha: Long
): Parcelable