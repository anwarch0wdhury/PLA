package com.anwar.pla.namaj.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GregorianMonth (
    val number: Long,
    val en: String
): Parcelable