package com.mobile.capstonedesign.http

import com.mobile.capstonedesign.dto.request.*
import com.mobile.capstonedesign.dto.response.CommentResponseDTO
import com.mobile.capstonedesign.dto.response.WritingDetailResponseDTO
import com.mobile.capstonedesign.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface HttpApi {

    /* Community */
    @GET("community/recent")
    fun getRecentAllWritings(): Observable<ResponseCommunityList>

    @GET("community/popular")
    fun getLikeAllWritings(): Observable<ResponseCommunityList>

    @GET("community/mypage/{user_id}")
    fun getUserAllWritings(@Path("user_id") user_id: Int): Observable<ResponseCommunityList>

    @GET("community/{board_no}")
    fun getWritingDetailByNo(@Path("board_no") board_no: Int): Observable<ResponseCommunityDetail>

    //    @Headers("Accept: application/json")
    @POST("community/")
    fun insertWriting(@Body writing: InsertWritingRequestDTO): Observable<ResponseCommunity>

    //    @Headers("Accept: application/json")
    @PUT("community/{board_no}")
    fun updateWriting(@Path("board_no") board_no: Int, @Body writing: UpdateWritingRequestDTO): Observable<ResponseCommunity>

    //    @Headers("Accept: application/json")
    @DELETE("community/{board_no}")
    fun deleteWriting(@Path("board_no") board_no: Int): Observable<ResponseCommunity>



    /* Comment */
    @GET("comment/{board_no}")
    fun getAllComments(@Path("board_no") board_no: Int): Observable<ResponseCommentList>

    @GET("comment/reply")
    fun getAllReplyComments(@Query("board_no") board_no: Int, @Query("comment_no") comment_no: Int): Observable<ResponseCommentList>

    @POST("comment/")
    fun insertComment(@Body comment: InsertCommentRequestDTO): Observable<ResponseComment>

    @POST("comment/reply/")
    fun insertCommentReply(@Body comment_reply: InsertCommentReplyRequestDTO): Observable<ResponseComment>

    @PUT("comment/{comment_no}")
    fun updateComment(@Path("comment_no") comment_no: Int, @Body comment: UpdateCommentRequestDTO): Observable<ResponseComment>



    /* Record */
    @GET("record/{user_id}")
    fun getAllRecords(@Path("user_id") user_id: Int): Observable<ResponseRecordList>

    @POST("record/")
    fun insertDrinkRecord(@Body record: InsertDrinkRecordRequestDTO): Observable<ResponseRecord>



    /* Naver Search Blog API */
    @GET("search/blog.json")
    fun getStopSmokeBlog(/*@HeaderMap headers : Map<String, String>,*/
                        @Header("X-Naver-Client-Id") clientId :String,
                        @Header("X-Naver-Client-Secret") clientSecret :String,
                        @Query("query") query: String,
                        @Query("start") start: Int,
                        @Query("display") display: Int): Observable<NaverSearchItemList>

    /* Sample */
    // this is test
    //    @GET("members/{idx}")
    //    fun getMembers(@Path("idx") idx: Int): Single<ArrayList<RecentWriting>>
}