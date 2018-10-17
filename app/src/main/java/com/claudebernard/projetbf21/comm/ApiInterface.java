package com.claudebernard.projetbf21.comm;

import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.model.Food;
import com.claudebernard.projetbf21.model.Login;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface ApiInterface {

    //LOGIN

    @POST("/login")
    Call<ResponseServer> login(@Body Login login);


    //COACH

    @GET("/coach/list")
    Call<ResponseServerArray> findAllCoaches();

    @GET
    Call<ResponseServer> findCoach(@Url String url);

    @POST("/coach")
    Call<ResponseServer> saveCoach(@Body Coach coach);

    @PUT("/coach")
    Call<ResponseServer> editCoach(@Body Coach coach);

    @DELETE
    Call<ResponseServer> deleteCoach(@Url String url);


    //CLIENT

    @GET("/client/list")
    Call<ResponseServerArray> findAllClients();

    @GET
    Call<ResponseServer> findClient(@Url String url);

    @POST("/client")
    Call<ResponseServer> saveClient(@Body Client client);

    @PUT("/client")
    Call<ResponseServer> editClient(@Body Client client);

    @DELETE
    Call<ResponseServer> deleteClient(@Url String url);

    //FOOD

    @GET("/food/list")
    Call<ResponseServerArray> findAllFoods();

    @GET
    Call<ResponseServer> findFood(@Url String url);

    @POST("/food")
    Call<ResponseServer> saveFood(@Body Food food);

    @PUT("/food")
    Call<ResponseServer> editFood(@Body Food food);

    @DELETE
    Call<ResponseServer> deleteFood(@Url String url);


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
