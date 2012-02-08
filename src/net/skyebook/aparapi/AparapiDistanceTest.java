/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skyebook.aparapi;

import com.amd.aparapi.Kernel;
import com.jme3.math.Vector3f;
import net.skyebook.cl.TestUtils;

/**
 *
 * @author Skye Book
 */
public class AparapiDistanceTest {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            int size = 15000000;
            final Vector3f[] one = TestUtils.createRandomVectorArray(size);
            final Vector3f[] two = TestUtils.createRandomVectorArray(size);

            Kernel kernel = new Kernel() {

                @Override
                public void run() {
                    int gid = getGlobalId();
                    one[gid].distance(two[gid]);
                }
            };

            long start = System.currentTimeMillis();
            kernel.execute(size);
            System.out.println("Execution in run " + i + " took " + kernel.getAccumulatedExecutionTime() + "ms");
        }
    }
}
