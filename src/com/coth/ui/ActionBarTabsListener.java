package com.coth.ui;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by yanga on 2013/06/03.
 */
class ActionBarTabsListener implements ActionBar.TabListener {

    private Fragment fragment;

    public ActionBarTabsListener(Fragment fragment) {
        this.fragment = fragment;
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //ft.add(R.id.fragment_container, fragment, null);
    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // some people needed this line as well to make it work:
        ft.remove(fragment);
    }
}