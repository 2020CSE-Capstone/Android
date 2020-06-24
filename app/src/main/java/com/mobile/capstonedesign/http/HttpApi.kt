package com.mobile.capstonedesign.http

import com.mobile.capstonedesign.dto.request.*
import com.mobile.capstonedesign.dto.response.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface HttpApi {

    /* Community */
    @GET("community/recent")
    fun getRecentAllWritings(): Observable<ResponseCommunityList>

    @GET("community/popular")
    fun getLikeAllWritings(): Observable<ResponseCommunityList>

    @GET("community/mypage/{user_id}")
    fun getUserAllWritings(@Path("user_id") user_id: Int): Observable<ResponseCommunityList>

    @GET("community/search/{key_word}")
    fun getSearchWritings(@Path("key_word") key_word: String): Observable<ResponseCommunityList>

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

    @DELETE("comment/{comment_no}")
    fun deleteComment(@Path("comment_no") comment_no: Int): Observable<ResponseComment>



    /* Record */
    @GET("record/drink/{user_id}")
    fun getAllDrinkRecords(@Path("user_id") user_id: Int): Observable<ResponseRecordList>

    @GET("record/smoke/{user_id}")
    fun getAllSmokeRecords(@Path("user_id") user_id: Int): Observable<ResponseRecordList>

    @POST("record/")
    fun insertDrinkRecord(@Body record: InsertDrinkRecordRequestDTO): Observable<ResponseRecord>

    @GET("record/drink/total/{user_id}")
    fun getTotalDrink(@Path("user_id") user_id: Int): Observable<ResponseRecordDrinkTotal>

    @GET("record/smoke/total/{user_id}")
    fun getTotalSmoke(@Path("user_id") user_id: Int): Observable<ResponseRecordSmokeTotal>

    @POST("record/drink/month/")
    fun getMonthDrinkRecords(@Body monthDrink: MonthRecordRequestDTO): Observable<ResponseMonthRecordsList>

    @POST("record/smoke/month/")
    fun getMonthSmokeRecords(@Body monthDrink: MonthRecordRequestDTO): Observable<ResponseMonthRecordsList>



    /* Naver Search Blog API */
    @GET("search/blog.json")
    fun getNaverSearchBlog(/*@HeaderMap headers : Map<String, String>,*/
                        @Header("X-Naver-Client-Id") clientId :String,
                        @Header("X-Naver-Client-Secret") clientSecret :String,
                        @Query("query") query: String,
                        @Query("start") start: Int,
                        @Query("display") display: Int): Observable<NaverSearchItemList>



    /* Authentication */
    @POST("user/login")
    fun login(@Body login: LoginRequestDTO): Observable<Response<ResponseLogin>>

    @POST("user/signup")
    fun signUpRequest(@Body signUp: SignUpRequestDTO): Observable<ResponseSignUp>

    @GET("user/email/{email}")
    fun isEmailOverlapCheck(@Path("email") email: String): Observable<ResponseBoolean>

    @GET("user/nickname/{nickname}")
    fun isNicknameOverlapCheck(@Path("nickname") nickname: String): Observable<ResponseBoolean>
}