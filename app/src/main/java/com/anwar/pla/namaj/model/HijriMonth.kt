package com.anwar.pla.namaj.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HijriMonth (
    val number: Long,
    val en: String,
    val ar: String
): Parcelable