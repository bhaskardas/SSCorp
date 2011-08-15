/*
 * Top level controller exception class in the super sales corporation
 * application.
 */

package com.superconduits.web.exceptions;

/**
 *
 * @author bhaskar
 */
public class SSCorpControllerException extends Exception{
    public SSCorpControllerException() {
    }

    public SSCorpControllerException(String message) {
        super(message);
    }
}
