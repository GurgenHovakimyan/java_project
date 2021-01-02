import java.util.Random;

import static java.lang.Math.pow;

class Monte_Carlo {
    private double today_price;
    private double mean;
    private double std;
    private int days;
    private int iterations;

    public Monte_Carlo(double today_price, double mean, double std, int days, int iterations){
        this.today_price = today_price;
        this.mean = mean;
        this.std = std;
        this.days = days;
        this.iterations = iterations;
    }

    public double[][] create_simulation(){
        double[][] simulations = new double[days][iterations];
        for(int k = 0; k <= iterations-1; k++){
            simulations[0][k] = today_price;
        }
        double drift = mean - pow(std, 2)/2;

        for(int i = 1; i <= simulations.length-1; i++){
            for(int j = 0; j <= simulations[i].length-1; j++){

                Random random_value = new Random();

                simulations[i][j] = simulations[i-1][j] * Math.exp(drift + random_value.nextGaussian()*std);
            }
        }
        return simulations;
    }
}
