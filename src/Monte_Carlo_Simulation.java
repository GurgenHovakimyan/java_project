import java.util.Random;

public class Monte_Carlo_Simulation {
   public static void main(String[] args){
    String path = "C:\\Users\\Gurgen\\Desktop\\Master Degree\\1 course\\1 half\\Computer Science and programming in data science (Java)\\Monte_Carlo_Simulation\\GOLDAMGBD228NLBM.csv";
    double[] data = new import_csv(path, 254).read_csv();
    double[] difference = new log_diff(data).calc_diff();
    double mean = new mean(difference, difference.length).get_mean();
    double std = new std(difference, difference.length).get_std();
    double[][] prediction= new Monte_Carlo(data[data.length-1],mean, std, 100, 1000).create_simulation();
    double[] test = new double[1000];
    for(int i = 0; i <= 999; i++){
     test[i] = prediction[99][i];
    }
    double test_mean = new mean(test, test.length).get_mean();
    System.out.println(test_mean);
    }

}
