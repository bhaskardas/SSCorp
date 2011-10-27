/**
 * Utility class for all of Super Sales Corporation.
 */

package com.superconduits.core.util;

import com.superconduits.core.util.generics.ObjectGenerator;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: bhaskardas
 * @Since: on 20 July 2010
 * @version: 1.0
 * @changeLog:
 */
public class SSCorpUtil {

    /**
     * 
     * @param key
     * @return
     */
    public static Object getObject(String key){
        return new ObjectGenerator().createNewInstance(key);
    }

    //TODO: Create a exception class and cover all these exceptions..
    /**
     * 
     * @param cls
     * @return
     */
    public static Object getObject(Class<?> cls){
        try {
            return cls.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(SSCorpUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SSCorpUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Sets the image paths for the images to be displayed on the web page.
     * @param contextPath
     * @param imageName
     * @return
     */
    public static String setImagePath(String[] pathVarNames, String imageName)
                                        throws Exception{
        StringBuffer imagePath = new StringBuffer();

        if(pathVarNames == null || imageName == null){
            //TODO: custom exception
            throw new Exception("No image path or name could be foumd to set " +
                                "as the image path.");
        }

        for(int i = 0; i < pathVarNames.length; i++){
            imagePath.append(pathVarNames[i]);
            imagePath.append(File.separator);
        }
        imagePath.append(imageName);

        return imagePath.toString();
    }
}
