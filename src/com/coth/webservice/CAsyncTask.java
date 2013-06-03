package com.coth.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.coth.ui.CActivity;
import com.coth.ui.R;
import com.coth.webservice.objects.enums.CothServiceException;
import com.coth.webservice.objects.enums.ServiceOperationOutcome;


import java.text.ParseException;

public abstract class CAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    final private String TAG = "KAsyncTask";
    public Context context;
    public ProgressDialog progressDialog;
    public Exception exception;
    private boolean hideProgressDialog;

    public CAsyncTask<Params, Progress, Result> setContext(Context context) {
        this.context = context;
        return this;
    }

    public CAsyncTask<Params, Progress, Result> hideProgressDialog() {
        this.hideProgressDialog = true;
        return this;
    }

    @Override
    protected Result doInBackground(Params... params) {
        try {
            return doClientBackground(params);
        } catch (Exception e) {
            exception = e;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Result result) {
        if (!hideProgressDialog) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                // nothing
            }
        }

        try {

            if (exception != null) {
                throw exception;
            }
            onClientPostExecute(result);

        } catch (CothServiceException e) {
            handleKoomaException(e);
        } catch (Exception e) {
            handleGeneralException(e);
        }
    }

    @Override
    protected void onPreExecute() {
        if (!hideProgressDialog) {
            progressDialog = ProgressDialog.show(context, "", "Contacting Server...", true);
        }
        try {
            onClientPreExecute();
        } catch (CothServiceException e) {
            handleKoomaException(e);
        } catch (Exception e) {
            handleGeneralException(e);
        }
    }

    public void handleKoomaException(CothServiceException e) {
        Log.e(TAG, e.getMessage(), e);
        CActivity kActivity = (CActivity)context;
        if (e.getSecondaryError() != 0) {
            kActivity.createErrorDialogWithMessage(e.getSecondaryError(), e.getErrorCode()).show();
            return;
        }

        if (e.getErrorCode() == ServiceOperationOutcome.AccessNotAllowed) {
            kActivity.createErrorDialogWithMessage(R.string.COTH_SERVICE_ERROR_ACCESSNOTALLOWED, ServiceOperationOutcome.AccessNotAllowed).show();
        } else if (e.getErrorCode() == ServiceOperationOutcome.FailedAuthentication) {
            kActivity.createErrorDialogWithMessage(R.string.COTH_SERVICE_ERROR_FAILEDAUTHENTICATION, ServiceOperationOutcome.FailedAuthentication).show();
        } else if (e.getErrorCode() == ServiceOperationOutcome.GeneralFailure) {
            kActivity.createErrorDialogWithMessage(R.string.COTH_SERVICE_ERROR_GENERALFAILURE, ServiceOperationOutcome.GeneralFailure).show();
        } else if (e.getErrorCode() == ServiceOperationOutcome.NoDataFound) {
            kActivity.createErrorDialogWithMessage(R.string.COTH_SERVICE_ERROR_NODATAFOUND, ServiceOperationOutcome.NoDataFound).show();
        } else if (e.getErrorCode() == ServiceOperationOutcome.NotImplemented) {
            kActivity.createErrorDialogWithMessage(R.string.COTH_SERVICE_ERROR_NOTIMPLEMENTED, ServiceOperationOutcome.NotImplemented).show();
        } else if (e.getErrorCode() == ServiceOperationOutcome.ServerException) {
            kActivity.createErrorDialogWithMessage(R.string.COTH_SERVICE_ERROR_SERVEREXCEPTION, ServiceOperationOutcome.ServerException).show();
        } else if (e.getErrorCode() == ServiceOperationOutcome.WrongParams) {
            kActivity.createErrorDialogWithMessage(R.string.COTH_SERVICE_ERROR_WRONGPARAMS, ServiceOperationOutcome.WrongParams).show();
        } else {
            kActivity.createErrorDialogWithMessage(R.string.COTH_SERVICE_ERROR_UNKNOWN, ServiceOperationOutcome.Unknown).show();
        }
    }

    public void handleGeneralException(Exception exception) {
        Log.e(TAG, exception.getMessage(), exception);
        CActivity kActivity = (CActivity)context;
        kActivity.createErrorDialogWithMessage(R.string.COTH_SERVICE_ERROR_GENERALFAILURE, ServiceOperationOutcome.GeneralFailure).show();
    }

    protected abstract Result doClientBackground(Params... params) throws CothServiceException, Exception;

    protected abstract void onClientPostExecute(Result result) throws CothServiceException, ParseException, Exception;

    protected abstract void onClientPreExecute() throws CothServiceException;


}

