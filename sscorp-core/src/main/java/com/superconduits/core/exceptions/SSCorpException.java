package com.superconduits.core.exceptions;

/**
 * 
 * @author bhaskardas
 *
 */
public class SSCorpException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public SSCorpException() {
		super();
	}
	
	/**
	 * 
	 * @param message
	 */
	public SSCorpException(String message){
		super(message);
	}
	
	/**
	 * 
	 * @param message
	 * @param e
	 */
	public SSCorpException(String message, Throwable e){
		super(message, e);
	}
}