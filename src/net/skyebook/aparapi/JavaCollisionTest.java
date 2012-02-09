/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skyebook.aparapi;

import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Skye Book
 */
public class JavaCollisionTest {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            int size = 512;

            final Sphere[] spheres = new Sphere[size];
            for (int j = 0; j < size; j++) {
                spheres[j] = new Sphere(64, 64, 50);
            }

            System.out.println(spheres[0].getTriangleCount() + " Traingles");

            long start = System.currentTimeMillis();
            for (int j = 0; j < size; j++) {
                spheres[j].createCollisionData();
            }
            System.out.println("Execution took " + (System.currentTimeMillis()-start) + "ms");
        }
    }
}
