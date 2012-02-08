/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skyebook.cl;

import java.nio.FloatBuffer;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import static org.lwjgl.opencl.CL10.*;
import org.lwjgl.opencl.*;

public class OpenCLSum {

    static final String source =
              "kernel void "
            + "sum(global const float *a, "
            + "    global const float *b, "
            + "    global float *answer) { "
            + "  unsigned int xid = get_global_id(0); "
            + "  answer[xid] = a[xid] + b[xid];" 
            + "}"
            ;

    static final FloatBuffer a = toFloatBuffer(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    static final FloatBuffer b = toFloatBuffer(new float[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0});
    static final FloatBuffer answer = BufferUtils.createFloatBuffer(a.capacity());

    public static void main(String[] args) throws Exception {
        // initialization
        CL.create();
        CLPlatform platform = CLPlatform.getPlatforms().get(0);
        List<CLDevice> devices = platform.getDevices(CL_DEVICE_TYPE_GPU);
        CLContext context = CLContext.create(platform, devices, null, null, null);
        CLCommandQueue queue = clCreateCommandQueue(context, devices.get(0), CL_QUEUE_PROFILING_ENABLE, null);

        // allocation
        CLMem aMem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, a, null);
        clEnqueueWriteBuffer(queue, aMem, 1, 0, a, null, null);
        CLMem bMem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, b, null);
        clEnqueueWriteBuffer(queue, bMem, 1, 0, b, null, null);
        CLMem answerMem = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, answer, null);
        clFinish(queue);

        // program/kernel creation
        CLProgram program = clCreateProgramWithSource(context, source, null);
        Util.checkCLError(clBuildProgram(program, devices.get(0), "", null));
        // sum has to match a kernel method name in the OpenCL source
        CLKernel kernel = clCreateKernel(program, "sum", null);

        // execution
        PointerBuffer kernel1DGlobalWorkSize = BufferUtils.createPointerBuffer(1);
        kernel1DGlobalWorkSize.put(0, a.capacity());
        kernel.setArg(0, aMem);
        kernel.setArg(1, bMem);
        kernel.setArg(2, answerMem);
        clEnqueueNDRangeKernel(queue, kernel, 1, null, kernel1DGlobalWorkSize, null, null, null);

        // read the results back
        clEnqueueReadBuffer(queue, answerMem, 1, 0, answer, null, null);
        clFinish(queue);
        
        print(a);
        System.out.println("+");
        print(b);
        System.out.println("=");
        print(answer);

        // teardown
        clReleaseKernel(kernel);
        clReleaseProgram(program);
        clReleaseCommandQueue(queue);
        clReleaseContext(context);
        CL.destroy();
    }

    static FloatBuffer toFloatBuffer(float[] floats) {
        FloatBuffer buf = BufferUtils.createFloatBuffer(floats.length).put(floats);
        buf.rewind();
        return buf;
    }

    static void print(FloatBuffer buffer) {
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.print(buffer.get(i)+" ");
        }
        System.out.println("");
    }
}