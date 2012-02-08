kernel void vector3_distance(global const float *v1x,
global const float *v1y,
global const float *v1z,
global const float *v2x,
global const float *v2y,
global const float *v2z,
global float *distanceResult){
	unsigned int xid = get_global_id(0);
	distanceResult[xid] = v1z[xid];
}
