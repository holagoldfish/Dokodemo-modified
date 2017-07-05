package com.zendaimoney.Dokodemo.exception;

/**
 * 未知异常，必须人工介入排查的异常，通常应该是底层抛出的无法处理的环境因素异常
 * 
 */
public class UnknownException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnknownException(String message) {
		super(message);
	}

	public UnknownException(Throwable cause) {
		super(cause);
	}

	public UnknownException(String message, Throwable cause) {
		super(message, cause);
	}
}
