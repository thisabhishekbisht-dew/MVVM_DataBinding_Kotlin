package com.abhi.rxjava.network

import io.reactivex.Observable
import com.abhi.rxjava.model.BookListModel
import retrofit2.http.GET
import retrofit2.http.Query


interface RetroService {
    @GET("volumes")
    fun getBookListFromApi(@Query("q") query: String): Observable<BookListModel>
}