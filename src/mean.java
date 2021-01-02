import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class mean {
    private double[] input;
    private int n;

    public mean(double[] input, int n){
        this.input = input;
        this.n = n;
    }
    public double get_mean(){
        double mean = 0;
        for (int i = 0; i < n; i++)
            mean = mean + input[i];
        mean = mean / n;

        return mean;
    }
}
