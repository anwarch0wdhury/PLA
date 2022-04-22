package com.anwar.pla.namaj.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data (
    val timings: Timings,
    val date: Date,
    val meta: Meta
): Parcelable