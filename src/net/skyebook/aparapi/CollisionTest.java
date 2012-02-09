/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skyebook.aparapi;

import com.amd.aparapi.Kernel;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Skye Book
 */
public class CollisionTest {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            int size = 512;

            final Sphere[] spheres = new Sphere[size];
            for (int j = 0; j < size; j++) {
                spheres[j] = new Sphere(64, 64, 50);
            }

            System.out.println(spheres[0].getTriangleCount() + " Traingles");

            Kernel kernel = new Kernel() {

                @Override
                public void run() {
                    int gid = getGlobalId();
                    spheres[gid].createCollisionData();
                }
            };

            long start = System.currentTimeMillis();
            kernel.execute(size);
            System.out.println("Conversion in run " + i + " took " + kernel.getConversionTime() + "ms");
            System.out.println("Execution in run " + i + " took " + kernel.getAccumulatedExecutionTime() + "ms");
        }
    }
}
