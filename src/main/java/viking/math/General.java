package viking.math;

public class General {

    public double map(double a,double in_min,double in_max,double out_min,double out_max) {
        return((a - in_min)*(out_max-out_min)/(in_max-in_min) + out_min);}
    
        public double findx(double theta, double hyp) {return hyp*(Math.sin(Math.toRadians(theta)));}
        public double findy(double theta, double hyp) {return hyp*(Math.cos(Math.toRadians(theta)));}

        public double hyplength(double x, double y) {return Math.sqrt(x*x+y*y);}
}