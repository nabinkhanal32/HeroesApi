package API;

import java.util.Map;

import models.Heroes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HeroesApi {

    @POST("heroes")
    Call<Void> addHero(@Body Heroes heroes);
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHero(@Field("name") String name, @Field("desc") String desc);

    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHero(@FieldMap Map<String,String> map);

}
