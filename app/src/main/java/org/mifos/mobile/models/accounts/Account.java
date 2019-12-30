package org.mifos.mobile.models.accounts;

import com.google.gson.annotations.SerializedName;

public abstract class Account {
    @SerializedName("id")
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
