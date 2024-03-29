package org.mifos.mobile.ui.views;

import org.mifos.mobile.models.accounts.loan.LoanWithAssociations;
import org.mifos.mobile.ui.views.base.MVPView;

public interface LoanAccountsDetailView extends MVPView {
    /**
     * Should be called when loan account object can successfully accessed
     * from the server to display loan account details on the screen.
     *
     * @param loanWithAssociations object containing details of each loan account,
     *                    received from server.
     */
    void showLoanAccountsDetail(LoanWithAssociations loanWithAssociations);

    /**
     * Should be called if there is any error from client side in getting
     * loan account object from server.
     * <p>
     * Reason for error should be mentioned clearly to the user.
     *
     * @param message Error message to display showing reason of failure in getting
     *                loan account object
     */
    void showErrorFetchingLoanAccountsDetail(String message);
}
