package com.pet.pup.petpupuserservice.constants;

public enum ErrorCodes {
	USER_DETAILS_REQUIRED("User Details Required", 898),
	USER_NAME_REQUIRED("User Name is Required.", 899),
	USER_NOT_FOUND("User Not Found.", 900),
	USER_ALREADY_EXIST("USer Already Exist",901);
	
	private final String errorMessage;
	private final Integer errorCode;
	
	ErrorCodes (String errorMessage, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}
	
}
