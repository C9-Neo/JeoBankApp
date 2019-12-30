package org.mifos.mobile.presenters.base;

import org.mifos.mobile.ui.views.base.MVPView;

public interface Presenter<V extends MVPView> {

    void attachView(V mvpView);

    void detachView();

}
