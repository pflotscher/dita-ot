/*
 * @(#)CatalogUtils.java        1.0 2005-4-11
 *
 * 
 */
package org.dita.dost.util;


import java.io.File;
import java.util.HashMap;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;



/**
 * Class description goes here.
 * 
 * @version 1.0 2005-4-11
 * @author Zhang, Yuan Peng
 */

public class CatalogUtils {

    private static HashMap map=null;
    /**
     * 
     */
    private CatalogUtils() {
        super();
    }

    /**
     * Parse the catalog file.
     * 
     * @return
     * 
     */
    public static HashMap getCatalog(String ditaDir){
    	CatalogParser parser;
		XMLReader reader;
        String catalogFilePath;
    	
        if (map!=null){
            return map;
        }else{
            map = new HashMap();
            parser = new CatalogParser(map, ditaDir);
            try{
                if (System.getProperty(Constants.SAX_DRIVER_PROPERTY) == null){
                    //The default sax driver is set to xerces's sax driver
                    System.setProperty(Constants.SAX_DRIVER_PROPERTY,Constants.SAX_DRIVER_DEFAULT_CLASS);
                }
                reader = XMLReaderFactory.createXMLReader();
                reader.setContentHandler(parser);
                catalogFilePath = (ditaDir == null) ? Constants.FILE_NAME_CATALOG : ditaDir+File.separator+Constants.FILE_NAME_CATALOG;
                reader.parse(catalogFilePath);
            }catch (Exception e){
                e.printStackTrace(System.out);
            }
            
            return map;
        }
    }
    
    
}
