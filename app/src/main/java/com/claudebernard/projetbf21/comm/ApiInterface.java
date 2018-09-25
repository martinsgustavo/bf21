package com.claudebernard.projetbf21.comm;

import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    //COACH

    @GET("/login/json?")
    Call<Coach> login();

    @GET("/coach")
    Call<List<Coach>> findAllCoaches();

    @GET("/coach/{id}")
    Call<Coach> findCoach();

    @POST("/coach")
    @FormUrlEncoded
    Call<Coach> saveCoach(@Body Coach coach);

    @PUT("/coach/{id}")
    Call<Coach> editCoach(@Path("id") String id, @Body Coach coach);

    @DELETE("/coach/{id}")
    Call<Coach> deleteCoach(@Path("id") String id);


    //CLIENT

    @GET("/client")
    Call<List<Client>> findAllClients();

    @GET("/client/{id}")
    Call<Client> findCient();

    @POST("/client")
    @FormUrlEncoded
    Call<Client> saveClient(@Body Client client);

    @PUT("/client/{id}")
    Call<Client> editClient(@Path("id") String id, @Body Client client);

    @DELETE("/client/{id}")
    Call<Client> deleteClient(@Path("id") String id);


    //FOOD

    @GET("/food")
    Call<List<Food>> findAllFoods();

    @GET("/food")
    Call<Food> findFood();

    @POST("/food")
    @FormUrlEncoded
    Call<Food> saveFood(@Body Food food);

    @PUT("/food/{id}")
    Call<Food> editFood(@Path("id") String id, @Body Food food);

    @DELETE("/food/{id}")
    Call<Food> deleteFood(@Path("id") String id);


//    //PLAN
//
//    @GET("/plan")
//    Call<List<Plan>> findAllPlan();
//
//    @GET("/plan")
//    Call<Plan> findPlan();
//
//    @POST("/plan")
//    @FormUrlEncoded
//    Call<Plan> savePlan(@Body Plan plan);
//
//    @PUT("/plan/{id}")
//    Call<Plan> editPlan(@Path("id") String id, @Body Plan plan);
//
//    @DELETE("/plan/{id}")
//    Call<Plan> deletePlan(@Path("id") String id);
}
