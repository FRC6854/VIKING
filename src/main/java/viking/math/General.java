package viking.math;

public class General {

    public double map(double a,double in_min,double in_max,double out_min,double out_max) {
        return((a - in_min)*(out_max-out_min)/(in_max-in_min) + out_min);}
    
        public double findx(double theta, double hyp) {return hyp*(Math.sin(Math.toRadians(theta)));}
        public double findy(double theta, double hyp) {return hyp*(Math.cos(Math.toRadians(theta)));}

        public double hypLength(double x, double y) {return Math.sqrt(x*x+y*y);}

        public double distToRotations(double radius, double distance){return distance/(2*pi*radius);}
        public double distToTicks(double radius, double distance, double ticksPerRotation){return (distance/(2*pi*radius))*ticksPerRotation;}
        public double velocity(double d1, double d2, double time){return (d2-d1)/time;}
        public double acceleration(double v1, double v2, double time){return v2-v1/time;}
        public double acceleration(double d1, double d2, double d3, double time){return ((d3-d2)-(d2-d1))/time;}
}
