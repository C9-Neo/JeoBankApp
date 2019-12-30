package org.mifos.mobile.models;

import com.google.gson.annotations.SerializedName;

public class UpdatePasswordPayload {

    @SerializedName("password")
    String password;

    @SerializedName("repeatPassword")
    String repeatPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
