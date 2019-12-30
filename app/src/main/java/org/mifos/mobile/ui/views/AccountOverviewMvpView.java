package org.mifos.mobile.ui.views;

import org.mifos.mobile.ui.views.base.MVPView;



public interface AccountOverviewMvpView extends MVPView {

    void showTotalLoanSavings(double totalLoan, double totalSavings);

    void showError(String message);
}
