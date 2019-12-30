package org.mifos.mobile.ui.activities;

import android.os.Bundle;

import org.mifos.mobile.R;
import org.mifos.mobile.ui.activities.base.BaseActivity;
import org.mifos.mobile.ui.fragments.HelpFragment;



public class HelpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        setToolbarTitle(getString(R.string.help));
        showBackButton();
        replaceFragment(HelpFragment.newInstance(), false, R.id.container);
    }
}
