package com.anwar.pla.namaj.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Method (
    val id: Long,
    val name: String,
    val params: Params,
    val location: Location
): Parcelable