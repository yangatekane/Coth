package com.coth.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import com.coth.CApplication;
import com.coth.utils.ImageDownloader;
import com.coth.webservice.CothWebService;
import com.coth.webservice.objects.enums.ServiceOperationOutcome;

/**
 * Created by yanga on 2013/05/26.
 */
public class CActivity extends Activity {
    public boolean isActiveActivity;
    public Dialog lastCreatedDialog;

    @Override
    protected void onStart() {
        isActiveActivity = true;
        super.onStart();
    }

    @Override
    protected void onPause() {
        isActiveActivity = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        isActiveActivity = true;
        super.onResume();
    }
    public ImageDownloader getImageManager() {
        return ((CApplication) getApplication()).getImageManager();
    }

    public CothWebService getCws() {
        return ((CApplication) getApplication()).getCws();

    }

    public Dialog createErrorDialogWithMessage(int resId, ServiceOperationOutcome serviceOutcome){
        // Decide which server errors finishes the activity and which don't
        if (serviceOutcome == ServiceOperationOutcome.WrongParams) {
            return createDialogWithMessage(getString(resId), false);
        } else {
            return createDialogWithMessage(getString(resId), true);
        }
    }

    public Dialog createDialogWithMessage(int resId, final boolean finishActivity){
        return createDialogWithMessage(getString(resId), finishActivity);
    }

    // NOTE: This is the new way to create dialogs
    public Dialog createDialogWithMessage(String message, final boolean finishActivity){
        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(!finishActivity){
                            return;
                        }

                        CActivity.this.finish();
                    }
                });
        lastCreatedDialog = builder.create();
        return lastCreatedDialog;
    }

}
