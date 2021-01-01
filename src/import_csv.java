import java.io.*;
import java.util.Scanner;

class import_csv{
    private String path;
    private int nrow;

    public import_csv(String path, int nrow){
        this.path = path;
        this.nrow = nrow;
    }

    public double[] read_csv(){
        File file = new File(path);
        double[] rate = new double[nrow];
        int i = 0;
        try{
            Scanner inputStream = new Scanner(file);
            inputStream.next();
            double sum = 0;
            double n = 0;

            while (inputStream.hasNext()){
                String data = inputStream.next();
                String[] values = data.split(",");

                double rates = Double.parseDouble(values[1]);
                sum += rates;
                n += 1;
                rate[i] = rates;
                i+= 1;
            }
            inputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return rate;
    }
}