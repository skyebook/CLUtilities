/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skyebook.cl;

import com.jme3.math.Vector3f;

/**
 *
 * @author Skye Book
 */
public class JavaDistanceTest {
    
    public static void main(String[] args){
        int size = 25000000;
        Vector3f[] findDistanceFromBuffers = TestUtils.createRandomVectorArray(size);
	Vector3f[] vectors = TestUtils.createRandomVectorArray(size);
        
        float[] results = new float[size];
        
        long start = System.currentTimeMillis();
        for(int i=0; i<size; i++){
            results[i] = findDistanceFromBuffers[i].distance(vectors[i]);
        }
        System.out.println("Calculation took " + (System.currentTimeMillis()-start) + "ms");
    }
}
