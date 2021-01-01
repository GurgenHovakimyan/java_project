public class Monte_Carlo_Simulation {
   public static void main(String[] args){
    String path = "C:\\Users\\Gurgen\\Desktop\\Master Degree\\1 course\\1 half\\Computer Science and programming in data science (Java)\\Monte_Carlo_Simulation\\USD3MTD156N.csv";
    double[] data = new import_csv(path).read_csv();
    System.out.println(data[0]);
    }

}