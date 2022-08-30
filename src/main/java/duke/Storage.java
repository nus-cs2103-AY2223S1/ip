package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        File file = new File(filePath);
        try {
            file.createNewFile();
            this.filePath = filePath;
        } catch (IOException e) {
            Ui.printErrorMessage(new DukeException("Error loading file"));
        }
    }

    /**
     * Loads the file into a tasklist.
     *
     * @return Tasklist containing all the files.
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine.startsWith("[T]")) {
                    tasks.add(Todo.parse(nextLine));
                } else if (nextLine.startsWith("[D]")) {
                    tasks.add(Deadline.parse(nextLine));
                } else if (nextLine.startsWith("[E]")) {
                    tasks.add(Event.parse(nextLine));
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void writeToFile(TaskList tasks) {
        try {
            Files.write(Paths.get(filePath), tasks.convertToStringList(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            Ui.printErrorMessage(new DukeException("Error writing to file"));
        }
    }

}
