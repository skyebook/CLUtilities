/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skyebook.cl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Skye Book
 */
public class KernelUtils {
    
    public static String loadSource(String sourceLocation) throws FileNotFoundException, IOException{
	BufferedReader reader = new BufferedReader(new FileReader(sourceLocation));
	StringBuilder source = new StringBuilder();
	while(reader.ready()){
	    source.append(reader.readLine());
	    source.append("\n");
	}
	
	return source.toString();
    }
}
