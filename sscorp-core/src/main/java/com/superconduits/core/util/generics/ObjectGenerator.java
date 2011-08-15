/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.superconduits.core.util.generics;

import org.utilities.util.PropertiesUtil;

/**
 *
 * @author bhaskar
 */
public class ObjectGenerator implements Generator<Object>{

    @Override
    public Object createNewInstance(String funcKey) {

        try{
            return Class.forName(PropertiesUtil.readProperty("sscorp-object-factory-config", funcKey)).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    //TODO : Write test case
//    public static void main(String[] args){
//        System.out.println(new ObjectGenerator().createNewInstance("companyProfile"));
//    }
}
