/**
 * Top level controller exception class in the super sales corporation
 * application.
 */

package com.superconduits.web.exceptions;

/**
 *
 * @author bhaskardas
 */
public class SSCorpControllerException extends Exception{
	private static final long serialVersionUID = 1L;

	public SSCorpControllerException() {
    }

    public SSCorpControllerException(String message) {
        super(message);
    }
    
    public SSCorpControllerException(String message, Throwable e){
    	super(message, e);
    }
}