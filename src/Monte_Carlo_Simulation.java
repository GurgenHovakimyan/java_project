import java.util.Random;
import processing.core.PApplet;

public class Monte_Carlo_Simulation<i> extends PApplet {
 public static void main(String[] args){
    PApplet.main("Monte_Carlo_Simulation");


    }
    public void settings() {
        size(1080, 720);
    }

    String path = "C:\\Users\\Minasyan\\Desktop\\java_project\\GOLDAMGBD228NLBM.csv";
    double[] data = new import_csv(path, 254).read_csv();
    double[] difference = new log_diff(data).calc_diff();
    double mean = new mean(difference, difference.length).get_mean();
    double std = new std(difference, difference.length).get_std();
    double[][] prediction= new Monte_Carlo(data[data.length-1],mean, std, 746, 10).create_simulation();
    double[] test = new double[1000];
    double test_mean = new mean(test, test.length).get_mean();
    // Creating random colors list for predictions.
    int[] colors = random_colors(prediction[0].length);

    // Method for random colors list creating.
    public int[] random_colors(int len){
     int[] colors = new int[len];
     for(int i = 0; i < colors.length; i++){
      colors[i] = color(random(200), random(200), random(200));
     }
     return colors;
    }

    // Getting max of an array.
    public static double getMax(double[] arr) {
     double max = arr[0];
     for (int i = 1; i < arr.length; i++) {
      if (arr[i] > max) {
       max = arr[i];
      }
     }
     return max;
    }

    // Getting min of an array.
    public static double getMin(double[] arr) {
     double min = arr[0];
     for (int i = 1; i < arr.length; i++) {
      if (arr[i] < min) {
       min = arr[i];
      }
     }
     return min;
    }

    // Getting min of a 1-dim array and a 2-dim array.
    public static double min_all(double[] arr, double[][] simulation){
     double[] mins = new double[simulation.length + 1];
     mins[0] = getMin(arr);
     for(int i = 1; i < simulation.length + 1; i++){
      mins[i] = getMin(simulation[i-1]);
     }
     return getMin(mins);
    }

    // Getting max of a 1-dim array and a 2-dim array.
    public static double max_all(double[] arr, double[][] simulation){
     double[] maxs = new double[simulation.length + 1];
     maxs[0] = getMax(arr);
     for(int i = 1; i < simulation.length + 1; i++){
      maxs[i] = getMax(simulation[i-1]);
     }
     return getMax(maxs);
    }

    // Normalising the given array with minmax.
    public static double[] minmax_norm_arr(double[] arr, double[][] simulation){
    double max_value = max_all(arr, simulation);
    double min_value = min_all(arr, simulation);
    double[] arr_norm = new double[arr.length];
     for(int i = 0; i < arr_norm.length; i++){
     arr_norm[i] = (arr[i] - min_value)/(max_value - min_value);
     }
     return arr_norm;
    }

    //Scaling the given array.
    public static double[] scaling_arr(double[] arr, double[][] simulation, double value){
     arr = minmax_norm_arr(arr, simulation);
     for(int i = 0; i < arr.length; i++){
      arr[i] = (1 - arr[i]) * value;
     }
     return arr;
    }

    // Normalising the given 2-dim array with minmax.
    public static double[][] minmax_norm_mat(double[] arr, double[][] simulation){
     double max_value = max_all(arr, simulation);
     double min_value = min_all(arr, simulation);
     double[][] mat_norm = new double[simulation.length][simulation[0].length];
     for(int i = 0; i < mat_norm.length; i++){
      for(int j = 0; j< mat_norm[0].length; j++){
       mat_norm[i][j] = (simulation[i][j] - min_value)/(max_value - min_value);
      }
     }
     return mat_norm;
    }

    // Scaling the given 2-dim array.
   public static double[][] scaling_mat(double[] arr, double[][] simulation, double value){
    simulation = minmax_norm_mat(arr, simulation);
    for(int i = 0; i < simulation.length; i++){
     for(int j = 0; j < simulation[0].length; j++){
      simulation[i][j] = (1 - simulation[i][j]) * value;
     }
    }
    return simulation;
   }

    public void draw() {
        background(255);
        // Graph.
        line(50,30,50,670);
        line(50,670,1070,670);
        line(55,35,50,30);
        line(45,35,50,30);
        line(1065,675,1070,670);
        line(1065,665,1070,670);
        textAlign(CENTER,CENTER);
        fill(0);
        textSize(25);
        text("Price", 50, 15);
        text("Time", 1045, 700);

        // Data
        for(int i = 0; i < data.length - 1; i++){
         line(i + 50, (float) scaling_arr(data, prediction, 620)[i] + 50, i + 51, (float) scaling_arr(data, prediction, 620)[i + 1] + 50);
        }
        // Dotted line.
        for(int i = (int) scaling_arr(data, prediction, 620)[data.length-1] + 50; i < 670; i = i + 10){
            line(data.length + 49,i, data.length + 49, i + 5);
        }
        fill(51, 171, 249);
        textSize(15);
        text("31.12.2020", data.length + 49, 685);
        text("31.12.2019",  49, 685);
        text("31.12.2021", data.length + 49 + 365, 685);
        text("26.09.2022", 1040, 685);
        ellipse(1050, 670, 4, 4);
        ellipse(data.length + 49 + 365, 670, 4, 4);
        ellipse(data.length + 49, 670, 4, 4);
        textSize(10);
        // Min Price.
        text((int) min_all(data, prediction), 35, 668);
        ellipse(50, 670, 4, 4);
        // Max Price.
        text((int) max_all(data, prediction), 35, 50);
        ellipse(50, 50, 4, 4);
        // Price at 31.12.2019.
        text((int) data[0], 35, (int) scaling_arr(data, prediction, 620)[0] + 48);
        ellipse(50, (int) scaling_arr(data, prediction, 620)[0] + 50, 4, 4);
        // Price at 31.12.2020.
        text((int) data[data.length - 1], 35, (int) scaling_arr(data, prediction, 620)[data.length - 1] + 50);
        for(int i = 50; i < data.length + 50; i = i + 10){
            line(i,(int) scaling_arr(data, prediction, 620)[data.length - 1] + 50, i + 5, (int) scaling_arr(data, prediction, 620)[data.length - 1] + 50);
        }
        ellipse(50, (int) scaling_arr(data, prediction, 620)[data.length - 1] + 50, 4, 4);

        text((int) (max_all(data, prediction) - min_all(data, prediction))/2, 35, 360);
        ellipse(50, 360, 4, 4);
        text((int) (max_all(data, prediction) - min_all(data, prediction))/4, 35, 515);
        ellipse(50, 515, 4, 4);
        text((int) (max_all(data, prediction) - min_all(data, prediction))*3/4, 35, 205);
        ellipse(50, 205, 4, 4);


        // Prediction.
        for(int j = 0; j < prediction[0].length; j++) {
         stroke(colors[j]);
         for (int i = 0; i < prediction.length - 1; i++) {
          line(data.length + i + 50, (float) scaling_mat(data, prediction, 620)[i][j] + 50, data.length + i + 51, (float) scaling_mat(data, prediction, 620)[i + 1][j] + 50);
         }
        }
        stroke(0);

//        println("min_all is " + min_all(data, prediction));
//        println("min of data is " + getMin(data));
//        for(int i = 0; i < prediction.length; i++){
//            println("min of prediction[" + i + "] is " + getMin(prediction[i]));
//        }


    }
}
