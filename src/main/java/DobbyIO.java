import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;

public class DobbyIO {

    public static void save(DobbyList dl) throws IOException {

        File newFile = new File("./src/main/dobbyList.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile.getAbsoluteFile()));
        bw.write(dl.toPrint());
        bw.close();
    }
    public static void load(DobbyList dl) throws FileNotFoundException{
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/main/dobbyList.txt"));
            String task;
            while ((task = br.readLine())!= null) {
                dl.addTask(task);
            }
            br.close();
        } catch (FileNotFoundException e) {
            DobbyChat.noFileFound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}