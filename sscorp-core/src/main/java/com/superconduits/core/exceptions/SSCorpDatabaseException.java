/**
 * Top level database layer exception class in the super sales corporation
 * application.
 */

package com.superconduits.core.exceptions;

/**
 *
 * @author bhaskar
 */
public class SSCorpDatabaseException extends SSCorpException{
	private static final long serialVersionUID = 1L;

	public SSCorpDatabaseException() {
    }

    public SSCorpDatabaseException(String message) {
        super(message);
    }
    
    public SSCorpDatabaseException(String message, Throwable e){
    	super(message, e);
    }
}