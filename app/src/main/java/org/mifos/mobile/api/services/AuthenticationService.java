package org.mifos.mobile.api.services;

import org.mifos.mobile.api.ApiEndPoints;
import org.mifos.mobile.models.User;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface AuthenticationService {

    @POST(ApiEndPoints.AUTHENTICATION)
    Observable<User> authenticate(@Query("username") String username,
                                  @Query("password") String password);
}
