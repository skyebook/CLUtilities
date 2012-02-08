/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skyebook.cl;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

/**
 *
 * @author Skye Book
 */
public class TestUtils {
    
    public static Vector3f[] createRandomVectorArray(int size){
        Vector3f[] vectors = new Vector3f[size];
	for (int i = 0; i < size; i++) {
	    Vector3f vector = new Vector3f(FastMath.rand.nextFloat(),
		    FastMath.rand.nextFloat(), FastMath.rand.nextFloat());
	    vectors[i] = vector;
	}
        return vectors;
    }
}
