package com.anwar.pla.namaj.remote

import com.anwar.pla.common.Constants
import com.anwar.pla.namaj.data.NamajResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NamajService {

    @GET("{date}?")
    suspend fun getNamajTiming(
        @Path("date") date              :String,
        @Query("latitude") latitude     : Double,
        @Query("longitude") longitude   : Double,
        @Query("method") method         : Int = Constants.NAMAJ_METHOD,

    ) : Response<NamajResponse>
}