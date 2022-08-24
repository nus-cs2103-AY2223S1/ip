package duke;

import duke.exceptions.DukeInvalidReadException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save() {
        String filePath = "data/tasks.txt";
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : TaskList.getTaskList()) {
                System.out.println(task.toString());
                fw.write(task.toStorageFormat());
                fw.write(System.lineSeparator());
            }
            fw.close();
            System.out.println("Saved task into local storage");
        } catch (IOException e) {
            System.out.println("Something's wrong, I can feel it. Its: " + e.getMessage());
        }
    }

    public void load() throws IOException {
        String directoryPath = "data";
        String filePath =  "data/tasks.txt";
        File directory = new File(directoryPath);
        File file = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
            file.createNewFile();
        }

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String taskString = sc.nextLine();
            String[] content = taskString.split(" \\| ", 0);
            char type = content[0].charAt(0);
            boolean isDone = content[1].charAt(0) == '1';
            String description = content[2];
            Task newTask;
            try {
                if (type == 'T') {
                    if (isDone) {
                        newTask = new ToDo(description, true);
                    } else {
                        newTask = new ToDo(description);
                    }
                } else if (type == 'D') {
                    String by = content[3];
                    if (isDone) {
                        newTask = new Deadline(description, true, by);
                    } else {
                        newTask = new Deadline(description, by);
                    }
                } else if (type == 'E') {
                    String at = content[3];
                    if (isDone) {
                        newTask = new Event(description, true, at);
                    } else {
                        newTask = new Event(description, at);
                    }

                } else {
                    throw new DukeInvalidReadException();
                }
                TaskList.getTaskList().add(newTask);
            } catch (DukeInvalidReadException e) {
                System.out.println(e.getMessage());
            }
        };

    }
}
