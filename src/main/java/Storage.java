import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Storage {
    private String file;

    public Storage(String filePath) {
        file = filePath;
    }

    public boolean hasExisted() {
        return new File(file).isFile();
    }

    public TaskList load() {
        //todo
        TaskList tasks = new TaskList();
        try {
            FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine()) {
                if (sc.nextLine().startsWith("[T]")) {
                    tasks.add(Todo.parse(sc.nextLine()));
                } else if (sc.nextLine().startsWith("[D]")) {
                    tasks.add(Deadline.parse(sc.nextLine()));
                } else if (sc.nextLine().startsWith("[E]")) {
                    tasks.add(Event.parse(sc.nextLine()));
                }
            }
            sc.close();
        } catch (IOException e) {

        }
        return tasks;
    }

    public void writeToFile(TaskList tasks) {
        try {
            Files.write(Paths.get(file), tasks.convertToStringList(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
