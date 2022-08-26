import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    File directory;
    File taskList;
    FileWriter fileWriter;

    public Storage() {
        try {
            directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            taskList = new File("data/taskList.txt");
            if (!taskList.exists()) {
                taskList.createNewFile();
            }


        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void save(String taskList) {
        try {
            fileWriter = new FileWriter("data/taskList.txt");
            fileWriter.write(taskList);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
