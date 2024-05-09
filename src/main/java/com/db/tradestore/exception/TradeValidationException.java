package com.db.tradestore.exception;

public class TradeValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TradeValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TradeValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TradeValidationException(String message) {
		super(message);
	}

	public TradeValidationException(Throwable cause) {
		super(cause);
	}
}
