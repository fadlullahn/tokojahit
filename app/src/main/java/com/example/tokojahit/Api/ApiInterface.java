package com.example.tokojahit.Api;

import com.example.tokojahit.Model.Baju.GetBaju;
import com.example.tokojahit.Model.Baju.PostPutDelBaju;
import com.example.tokojahit.Model.Desain.GetDesain;
import com.example.tokojahit.Model.Desain.PostPutDelDesain;
import com.example.tokojahit.Model.Kain.GetKain;
import com.example.tokojahit.Model.Kain.PostPutDelKain;
import com.example.tokojahit.Model.Login.Login;
import com.example.tokojahit.Model.Pesanan.GetPesanan;
import com.example.tokojahit.Model.Pesanan.PostPutDelPesanan;
import com.example.tokojahit.Model.Register.Register;
import com.example.tokojahit.Model.User.ResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.Part;


public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name,
            @Field("level") String level,
            @Field("email") String email,
            @Field("nowa") String nowa
    );


    @GET("user/retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("user/create.php")
    Call<ResponseModel> ardCreateData(
            @Field("name") String name,
            @Field("username") String username,
            @Field("level") String level,
            @Field("password") String password,
            @Field("email") String email,
            @Field("nowa") String nowa
    );

    @FormUrlEncoded
    @POST("user/delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("user/get.php")
    Call<ResponseModel> ardGetData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("user/update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("id") int id,
            @Field("name") String name,
            @Field("username") String username,
            @Field("level") String level,
            @Field("password") String password,
            @Field("email") String email,
            @Field("nowa") String nowa
    );


    @GET("kain.php")
    Call<GetKain> getKain();

    @Multipart
    @POST("kain.php")
    Call<PostPutDelKain> postKain(@Part MultipartBody.Part image,
                                     @Part("name") RequestBody name,
                                     @Part("price") RequestBody price,
                                     @Part("date") RequestBody date,
                                     @Part("flag") RequestBody flag);

    @Multipart
    @POST("kain.php")
    Call<PostPutDelKain> postUpdateKain(@Part MultipartBody.Part image,
                                           @Part("id") RequestBody id,
                                           @Part("name") RequestBody name,
                                           @Part("price") RequestBody price,
                                           @Part("date") RequestBody date,
                                           @Part("flag") RequestBody flag);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kain.php", hasBody = true)
    Call<PostPutDelKain> deleteKain(@Field("id") String id);






    @GET("desain.php")
    Call<GetDesain> getDesain();

    @Multipart
    @POST("desain.php")
    Call<PostPutDelDesain> postDesain(@Part MultipartBody.Part image,
                                    @Part("name") RequestBody name,
                                    @Part("price") RequestBody price,
                                    @Part("date") RequestBody date,
                                    @Part("flag") RequestBody flag);

    @Multipart
    @POST("desain.php")
    Call<PostPutDelDesain> postUpdateDesain(@Part MultipartBody.Part image,
                                        @Part("id") RequestBody id,
                                        @Part("name") RequestBody name,
                                        @Part("price") RequestBody price,
                                        @Part("date") RequestBody date,
                                        @Part("flag") RequestBody flag);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "desain.php", hasBody = true)
    Call<PostPutDelDesain> deleteDesain(@Field("id") String id);






    @GET("baju.php")
    Call<GetBaju> getBaju();

    @Multipart
    @POST("baju.php")
    Call<PostPutDelBaju> postBaju(@Part MultipartBody.Part image,
                                    @Part("name") RequestBody name,
                                    @Part("price") RequestBody price,
                                    @Part("date") RequestBody date,
                                    @Part("flag") RequestBody flag);

    @Multipart
    @POST("baju.php")
    Call<PostPutDelBaju> postUpdateBaju(@Part MultipartBody.Part image,
                                            @Part("id") RequestBody id,
                                            @Part("name") RequestBody name,
                                            @Part("price") RequestBody price,
                                            @Part("date") RequestBody date,
                                            @Part("flag") RequestBody flag);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "baju.php", hasBody = true)
    Call<PostPutDelBaju> deleteBaju(@Field("id") String id);





    @GET("pesanan")
    Call<GetPesanan> getPesanan();

    @Multipart
    @POST("pesanan")
    Call<PostPutDelPesanan> postPesanan(
            @Part MultipartBody.Part image,
            @Part("name") RequestBody name,
            @Part("price") RequestBody price,
            @Part("date") RequestBody date,
            @Part("flag") RequestBody flag,
            @Part("baju") RequestBody baju,
            @Part("kain") RequestBody kain,
            @Part("desain") RequestBody desain,
            @Part("lingkar_badan") RequestBody lingkar_badan,
            @Part("lingkar_pinggang") RequestBody lingkar_pinggang,
            @Part("panjang_dada") RequestBody panjang_dada,
            @Part("lebar_dada") RequestBody lebar_dada,
            @Part("panjang_punggung") RequestBody panjang_punggung,
            @Part("lebar_punggung") RequestBody lebar_punggung,
            @Part("lebar_bahu") RequestBody lebar_bahu,
            @Part("lingkar_leher") RequestBody lingkar_leher,
            @Part("tinggi_dada") RequestBody tinggi_dada,
            @Part("jarak_dada") RequestBody jarak_dada,
            @Part("lingkar_pangkal_lengan") RequestBody lingkar_pangkal_lengan,
            @Part("panjang_lengan") RequestBody panjang_lengan,
            @Part("lingkar_siku") RequestBody lingkar_siku,
            @Part("lingkar_pergelangan_tangan") RequestBody lingkar_pergelangan_tangan,
            @Part("lingkar_kerung_lengan") RequestBody lingkar_kerung_lengan,
            @Part("lingkar_panggul_1") RequestBody lingkar_panggul_1,
            @Part("lingkar_panggul_2") RequestBody lingkar_panggul_2,
            @Part("lingkar_rok") RequestBody lingkar_rok
    );

    @Multipart
    @POST("pesanan")
    Call<PostPutDelPesanan> postUpdatePesanan(
            @Part MultipartBody.Part image,
            @Part("id") RequestBody id,
            @Part("name") RequestBody name,
            @Part("price") RequestBody price,
            @Part("date") RequestBody date,
            @Part("flag") RequestBody flag,
            @Part("proses") RequestBody proses
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "pesanan", hasBody = true)
    Call<PostPutDelPesanan> deletePesanan(
            @Field("id") String id
    );

}
