kernel void vector3_distance(global const float *v1x, global const float *v1y, global const float *v1z, global const float *v2x, global const float *v2y, global const float *v2z, global float *distanceResult){
	unsigned int xid = get_global_id(0);
	
	// get the distance differences
	float distanceX = v1x[xid] - v2x[xid];
	float distanceY = v1y[xid] - v2y[xid];
	float distanceZ = v1z[xid] - v2z[xid];

	distanceResult[xid] = sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
}
