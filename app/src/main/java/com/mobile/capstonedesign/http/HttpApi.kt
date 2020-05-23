package com.mobile.capstonedesign.http

import com.mobile.capstonedesign.dto.request.InsertWritingRequestDTO
import com.mobile.capstonedesign.dto.request.UpdateWritingRequestDTO
import com.mobile.capstonedesign.dto.response.WritingDetailResponseDTO
import com.mobile.capstonedesign.dto.response.WritingSimpleResponseDTO
import com.mobile.capstonedesign.model.Writing
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface HttpApi {

    /* Community */
    @GET("community/recent")
    fun getRecentAllWritings(): Observable<ArrayList<WritingSimpleResponseDTO>>

    @GET("community/like")
    fun getLikeAllWritings(): Observable<ArrayList<WritingSimpleResponseDTO>>

    @GET("community/mypage/{user_id}")
    fun getUserAllWritings(@Path("user_id") user_id: Int): Observable<ArrayList<WritingSimpleResponseDTO>>

    @GET("community/{board_no}")
    fun getWritingDetailByNo(@Path("board_no") board_no: Int): Observable<WritingDetailResponseDTO>

    //    @Headers("Accept: application/json")
    @POST("community/")
    fun insertWriting(@Body writing: InsertWritingRequestDTO): Observable<Boolean>

    //    @Headers("Accept: application/json")
    @PUT("community/{board_no}")
    fun updateWriting(@Path("board_no") board_no: Int, @Body writing: UpdateWritingRequestDTO): Observable<Boolean>

    //    @Headers("Accept: application/json")
    @DELETE("community/{board_no}")
    fun deleteWriting(@Path("board_no") board_no: Int): Observable<Boolean>

    /* Comment */
    @GET("comment/")
    fun getAllComments(): Observable<ArrayList<WritingSimpleResponseDTO>>

    /* Sample */
    // this is test
    //    @GET("members/{idx}")
    //    fun getMembers(@Path("idx") idx: Int): Single<ArrayList<RecentWriting>>
}