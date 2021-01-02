import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class Jarque_Bera {
    private double[] input;
    private int n;
    public Jarque_Bera(double[] input, int n){
        this.input = input;
        this.n = n;
    }
    public double get_JB_value(){
        double mean = 0;
        for (int i = 0; i < n; i++)
            mean = mean + input[i];
        mean = mean / n;

        double std = 0 ;
        for (int i = 0; i < n; i++)
            std = pow((input[i] - mean), 2);
        std = sqrt(std/n);

        double skewness = 0;

        for (int i = 0; i < n; i++)
            skewness = pow((input[i] - mean), 3);

        skewness = skewness/pow(std, 3);

        double kurtosis = 0;

        for (int i = 0; i < n; i++)
            kurtosis = pow((input[i] - mean), 4);

        kurtosis = kurtosis/pow(std, 4);

        double JB = (n / 6) * (pow(skewness,2) + 0.25*(pow((kurtosis-3),2)));
        System.out.println("Mean: " + mean);
        System.out.println("Std: " + std);
        return  JB;

    }
}
