package com.androiddiplomski.data.service;

import com.androiddiplomski.data.api.models.request.LoginRequest;
import com.androiddiplomski.data.api.models.response.LoginApiResponse;
import com.androiddiplomski.data.api.models.response.MovieApiResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.androiddiplomski.data.api.APIConstants.PATH_LOGIN;

public interface TemplateAPI {


    @POST("")
    Single<MovieApiResponse> movieInfo();

    @POST(PATH_LOGIN)
    Single<LoginApiResponse> loginUser(@Body LoginRequest loginRequest);

//    @Headers(CONTENT_TYPE_HEADER)
//    @POST(PATH_LOGIN)
//    Single<TypeSessionsApiResponse> login(@Body UserInformation userInformation);
//

//    @Headers({CONTENT_TYPE_HEADER, ACCEPT_HEADER})
//    @HTTP(method = "DELETE", path = PATH_CLAIM_DEVICE, hasBody = true)
//    Observable<Response<Void>> returnDevice(@Header("Authorization") String authorization, @Body User user,
//            @Path("person_id") long userId);

}
