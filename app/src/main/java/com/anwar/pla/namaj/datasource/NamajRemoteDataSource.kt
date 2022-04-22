package com.anwar.pla.namaj.datasource

import com.anwar.pla.common.BaseDataSource
import com.anwar.pla.namaj.remote.NamajService
import javax.inject.Inject

class NamajRemoteDataSource @Inject constructor(
    private val namajService: NamajService
) : BaseDataSource() {
    suspend fun getNamajTiming(date: String,latitude: Double, longitude: Double) =
        getResult { namajService.getNamajTiming(date,latitude, longitude) }
}