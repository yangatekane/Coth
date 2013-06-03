package com.coth.webservice.objects.enums;

/**
 * Created by yanga on 2013/05/26.
 */
public class CothServiceException extends Exception{

    private static final long serialVersionUID = 2270682897508419224L;


    ServiceOperationOutcome errorCode;
    int secondaryError;

    public int getSecondaryError() {
        return secondaryError;
    }

    public ServiceOperationOutcome getErrorCode() {
        return errorCode;
    }

    public CothServiceException(ServiceOperationOutcome errorCode, int se){
        super();
        this.secondaryError = se;
        this.errorCode = errorCode;
    }

    public CothServiceException(ServiceOperationOutcome errorCode){
        super();
        this.errorCode = errorCode;
    }
}
