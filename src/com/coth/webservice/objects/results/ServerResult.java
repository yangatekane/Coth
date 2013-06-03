package com.coth.webservice.objects.results;

/**
 * Created by yanga on 2013/05/26.
 */
import com.coth.webservice.objects.enums.ServiceOperationOutcome;
public class ServerResult {
    public ServiceOperationOutcome ServiceOperationOutcome;

    public boolean isSuccess() {
        return ServiceOperationOutcome == ServiceOperationOutcome.Success;
    }

    public boolean isWrongParams() {
        return ServiceOperationOutcome == ServiceOperationOutcome.WrongParams;
    }

    public boolean isNoDataFound() {
        return ServiceOperationOutcome == ServiceOperationOutcome.NoDataFound;
    }

    public String checkForErrorsAndGetMessage() {
        // TODO: Should return a resId
        //Override this for custom result validation and return custom messages for display in a error dialog
        return "";
    }
}
