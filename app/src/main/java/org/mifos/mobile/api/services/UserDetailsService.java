package org.mifos.mobile.api.services;

import org.mifos.mobile.api.ApiEndPoints;
import org.mifos.mobile.models.UpdatePasswordPayload;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface UserDetailsService {

    @PUT(ApiEndPoints.USER)
    Observable<ResponseBody> updateAccountPassword(@Body UpdatePasswordPayload payload);
}
