package com.slowcloud.streak.config.auth;

public class TokenVerifyException extends Exception {

	private static final long serialVersionUID = -109289097198252974L;

	public TokenVerifyException() {
		super();
	}

	public TokenVerifyException(
			String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace
	) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TokenVerifyException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenVerifyException(String message) {
		super(message);
	}

	public TokenVerifyException(Throwable cause) {
		super(cause);
	}

}
