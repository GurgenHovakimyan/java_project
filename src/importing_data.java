import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class importing_data {
    public static void main(String[] args){
        String path = "C:\\Users\\Gurgen\\Desktop\\Master Degree\\1 course\\1 half\\Computer Science and programming in data science (Java)\\Monte_Carlo_Simulation\\USD3MTD156N.csv";
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values[1]));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Object[] records_array = records.toArray();
        System.out.println(records_array);
    }


}