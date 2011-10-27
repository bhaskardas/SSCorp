/**
 * Top level business exception class in super sales corporation application.
 */

package com.superconduits.core.exceptions;

/**
 *
 * @author bhaskar
 */
public class SSCorpBusinessException extends SSCorpException{
	private static final long serialVersionUID = 1L;

	public SSCorpBusinessException() {
    }

    public SSCorpBusinessException(String message){
        super(message);
    }
    
    public SSCorpBusinessException(String message, Throwable e){
    	super(message, e);
    }
}