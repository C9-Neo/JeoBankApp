package org.mifos.mobile.ui.views;

import org.mifos.mobile.models.Charge;
import org.mifos.mobile.ui.views.base.MVPView;

import java.util.List;

public interface ClientChargeView extends MVPView {
    /**
     * Should be called if there is any error from client side in loading the client charges
     * from server.
     * Reason for error should be mentioned clearly to the user.
     *
     * @param message Error message to display showing reason of failure in loading
     *                charges of a particular client.
     */
    void showErrorFetchingClientCharges(String message);

    /**
     * Use to display List of charges for the respective client.
     *
     * @param clientChargesList List containing charges of a particular client
     */
    void showClientCharges(List<Charge> clientChargesList);
}
