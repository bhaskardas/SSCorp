/*
 * Top level business exception class in super sales corporation application.
 */

package com.superconduits.core.exceptions;

/**
 *
 * @author bhaskar
 */
public class SSCorpBusinessException extends Exception{

    public SSCorpBusinessException() {
    }

    public SSCorpBusinessException(String message){
        super(message);
    }
}
