package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {
    private String fileName;

    public void save(TaskList taskList) {
        try {
            PrintWriter saveFile = new PrintWriter(fileName);
            for (int i = 0; i < taskList.size(); ++i) {
                saveFile.println(taskList.getTask(i).getEncoded());
            }
            saveFile.close();
        } catch (IOException e) {
            System.out.println("<couldn't save to file!>");
        }
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            Scanner saved = new Scanner(new File("tasks.txt"));
            while (saved.hasNextLine()) {
                try {
                    tasks.add(Task.fromEncoded(saved.nextLine()));
                } catch (DateTimeParseException ignored) {
                }
            }
            saved.close();
        } catch (FileNotFoundException ignored) {
        }

        return tasks;
    }

    public Storage(String fileName) {
        this.fileName = fileName;
    }
}
