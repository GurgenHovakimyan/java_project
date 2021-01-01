import static java.lang.StrictMath.log;

class log_diff {
    private double[] vector;

    public log_diff(double[] vector){
        this.vector = vector;
    }

    public double[] calc_diff(){
        double[] difference = new double[vector.length];
        difference[0] = 0;
        for(int i = 1; i < vector.length; i++){
            difference[i] = log((vector[i]/vector[i-1]));
        }
        return difference;
    }
}
