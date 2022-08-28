package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filepath;
    Storage(String filepath) {
        this.filepath = filepath;
    }

    protected TaskList load() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(new File(filepath));
        while (s.hasNext()) {
            String taskText = s.nextLine();
            char taskType = taskText.charAt(3);
            if (taskType == 'T') {
                tasks.add(new Todo(taskText));
            } else if (taskType == 'D') {
                tasks.add(new Deadline(taskText));
            } else if (taskType == 'E') {
                tasks.add(new Event(taskText));
            }
        }
        return new TaskList(tasks);
    }

    protected void save(String taskList) {
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(taskList);
            fw.close();
        } catch (IOException err) {
            System.out.println(":( TaskList not saved: " + err.getMessage());
        }
    }
}
