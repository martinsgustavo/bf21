package com.claudebernard.projetbf21.comm;

import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.model.Food;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.claudebernard.projetbf21.model.FoodPlanBD;
import com.claudebernard.projetbf21.model.Login;
import com.claudebernard.projetbf21.model.PlanDays;
import com.claudebernard.projetbf21.model.PlanDaysBD;
import com.claudebernard.projetbf21.model.PlanMeals;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @GET("/client/list")
    Call<ResponseServerArray> findAllFoods();

    @GET
    Call<ResponseServer> findFood(@Url String url);

    @POST("/food")
    Call<ResponseServer> saveFood(@Body Food food);

    @PUT("/food")
    Call<ResponseServer> editFood(@Body Food food);

    @DELETE
    Call<ResponseServer> deleteFood(@Url String url);


    //PLAN

    @GET("/foodPlan/list")
    Call<ResponseServerArray> findAllPlans();

    @GET
    Call<ResponseServerArray> findAllPlansClient(@Url String url);

    @GET
    Call<ResponseServer> findPlan(@Url String url);

    @POST("/foodPlan")
    Call<ResponseServer> saveFoodPlan(@Body FoodPlanBD foodPlan);

    @POST("/foodPlan/{fP}/planDay/list")
    Call<ResponseServer> addDayToPlan(@Path("fP") Integer foodPlan, @Body PlanDaysBD planDays);

    @PUT("/foodPlan")
    Call<ResponseServer> editFoodPlan(@Body FoodPlan foodPlan);

    @DELETE
    Call<ResponseServer> deleteFoodPlan(@Url String url);


    //PLAN DAYS

    @GET("/foodPlan/{fP}/planDay/list")
    Call<ResponseServerArray> findAllPlanDaysByFoodPlan(@Path("fP") Integer foodPlan);

    @GET
    Call<ResponseServer> findPlanDayById(@Url String url);

    @POST("/foodPlan/{fP}/planDay/{pD}/planMeal/list")
    Call<ResponseServer> saveMealToDay(@Path("fP") Integer foodPlan, @Path("pD") Integer planDay, @Body PlanMeals planMeals);

    @DELETE
    Call<ResponseServer> deleteMealFromDay(@Url String url);


    //PLAN MEALS

    @POST("/foodPlan/{fP}/planDay/{pD}/planMeal/{pM}/food/list")
    Call<ResponseServer> saveFoodToMeal(@Path("fP") Integer foodPlan, @Path("pD") Integer planDay, @Path("pM") Integer planMeal, @Body Food food);

    @DELETE
    Call<ResponseServer> deletePlanMealById(@Url String url);

    @DELETE
    Call<ResponseServer> deleteFoodFromMeal(@Url String url);

}
