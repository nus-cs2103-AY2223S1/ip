package Storage;

import TaskList.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DukeEncoder {
    public static void rewriteList(ArrayList<Task> workList) {
        try {
            Path file = Paths.get("src/main/List.txt");
            ArrayList<String> stringArrayList = new ArrayList<>();
            for (int i = 0; i < workList.size(); i++) {
                stringArrayList.add(workList.get(i).storedData());
            }
            Files.write(file, stringArrayList, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Cannot save the list!");
        }
    }
}
