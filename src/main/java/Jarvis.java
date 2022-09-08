import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;

public class Jarvis {
    public static void main(String[] args) throws JarvisException {
        
        String filePath = "data/taskList.txt";

        TaskList tasks;
        Storage storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }

        Parser parser = new Parser(tasks);

        parser.introduction();

        try {
            parser.readCommand();
            try {
                storage.write(tasks);
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            parser.farewell();
        }
        catch (JarvisException e) {
            System.out.println((e));
        }
    }
}
