package com.claudebernard.projetbf21.comm;

import com.claudebernard.projetbf21.model.ResponseServer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    //LOGIN

    @GET("/login/json?")
    Call<ResponseServer> login();


    //COACH

    @GET("/coach/list")
    Call<ResponseServer> findAllCoaches();

    @GET("/coach?idCoach={id}")
    Call<ResponseServer> findCoach(@Path("id") Integer id);

//    @POST("/coach")
//    @FormUrlEncoded
//    Call<Coach> saveCoach(@Body Coach coach);
//
//    @PUT("/coach/{id}")
//    Call<Coach> editCoach(@Path("id") String id, @Body Coach coach);
//
//    @DELETE("/coach/{id}")
//    Call<Coach> deleteCoach(@Path("id") String id);


    //CLIENT

    @GET("/client/list")
    Call<ResponseServer> findAllClients();

    @GET("/client?idClient={id}")
    Call<ResponseServer> findClient(@Path("id") Integer id);


//    @POST("/client")
//    @FormUrlEncoded
//    Call<Client> saveClient(@Body Client client);
//
//    @PUT("/client")
//    Call<Client> editClient(@Body Client client);
//
//    @DELETE("/client?idClient={id}")
//    Call<Client> deleteClient(@Path("id") int id);


    //FOOD

    @GET("/food/list")
    Call<ResponseServer> findAllFoods();

    @GET("/food?idFood={id}")
    Call<ResponseServer> findFood(@Path("id") Integer id);

//    @POST("/food")
//    @FormUrlEncoded
//    Call<Food> saveFood(@Body Food food);
//
//    @PUT("/food/{id}")
//    Call<Food> editFood(@Path("id") String id, @Body Food food);
//
//    @DELETE("/food/{id}")
//    Call<Food> deleteFood(@Path("id") String id);


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
