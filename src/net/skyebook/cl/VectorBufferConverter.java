/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skyebook.cl;

import com.jme3.math.Vector3f;
import com.jme3.util.BufferUtils;
import java.nio.FloatBuffer;

/**
 *
 * @author Skye Book
 */
public class VectorBufferConverter {
    
    /**
     * 
     * @param vectors
     * @return 
     */
    public static FloatBuffer[] toFloatBuffers(Vector3f... vectors){
	FloatBuffer x = BufferUtils.createFloatBuffer(vectors.length);
	FloatBuffer y = BufferUtils.createFloatBuffer(vectors.length);
	FloatBuffer z = BufferUtils.createFloatBuffer(vectors.length);
	
	for(int i=0; i<vectors.length; i++){
	    Vector3f vector = vectors[i];
	    x.put(vector.x);
	    y.put(vector.y);
	    z.put(vector.z);
	}
	
	return new FloatBuffer[]{x, y, z};
    }
}
