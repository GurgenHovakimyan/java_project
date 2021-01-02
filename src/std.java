import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class std {
    private double[] input;
    private int n;

    public std(double[] input, int n){
        this.input = input;
        this.n = n;
    }
    public double get_std(){
        double mean = 0;
        for (int i = 0; i < n; i++)
            mean = mean + input[i];
        mean = mean / n;

        double std = 0 ;
        for (int i = 0; i < n; i++)
            std += pow((input[i] - mean), 2);
        std = sqrt(std/n);

        return std;

    }

}
