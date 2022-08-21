package dobby;

import dobby.commands.*;
import dobby.tasks.*;

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
    public static void load(DobbyList dl, String filePath) throws FileNotFoundException{
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String task;
            while ((task = br.readLine())!= null) {
                if (!task.equals("")){
                    dl.addTask(task);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}