/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.superconduits.core.util.generics;

/**
 *
 * @author bhaskar
 */
public interface Generator<T> {

    public T createNewInstance(String funcKey);
}
