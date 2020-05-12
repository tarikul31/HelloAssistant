package com.softmaticbd.helloassistant.service;

import com.softmaticbd.helloassistant.model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("posts")
    Call<List<Post>> getPosts();

    // todo for individual field ...............  ..............
    @GET("posts")
    Call<List<Post>> getPostsById(
            @Query("userId") Integer userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPostsById(@QueryMap Map<String,String> parameters);

    @POST("posts")
    Call<Post> createPosts(@Body Post post);

    // todo for individual field ............... .................

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPosts(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String body
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPosts(@FieldMap Map<String, String> fields);


}
