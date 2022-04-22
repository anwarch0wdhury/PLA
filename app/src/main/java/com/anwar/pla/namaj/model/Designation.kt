package com.anwar.pla.namaj.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Designation (
    val abbreviated: String,
    val expanded: String
): Parcelable