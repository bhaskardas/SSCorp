/*
 * Top level database layer exception class in the super sales corporation
 * application.
 */

package com.superconduits.core.exceptions;

/**
 *
 * @author bhaskar
 */
public class SSCorpDatabaseException extends Exception{

    public SSCorpDatabaseException() {
    }

    public SSCorpDatabaseException(String message) {
        super(message);
    }
}
