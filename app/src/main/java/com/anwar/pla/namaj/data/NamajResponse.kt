package com.anwar.pla.namaj.data

import android.os.Parcelable
import com.anwar.pla.namaj.model.Data
import kotlinx.parcelize.Parcelize

@Parcelize
data class NamajResponse (
    val code: Long,
    val status: String,
    val data: Data
): Parcelable