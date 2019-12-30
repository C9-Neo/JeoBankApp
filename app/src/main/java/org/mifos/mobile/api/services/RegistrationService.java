package org.mifos.mobile.api.services;

import org.mifos.mobile.api.ApiEndPoints;
import org.mifos.mobile.models.register.RegisterPayload;
import org.mifos.mobile.models.register.UserVerify;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RegistrationService {

    @POST(ApiEndPoints.REGISTRATION)
    Observable<ResponseBody> registerUser(@Body RegisterPayload registerPayload);

    @POST(ApiEndPoints.REGISTRATION + "/user")
    Observable<ResponseBody> verifyUser(@Body UserVerify userVerify);
}
