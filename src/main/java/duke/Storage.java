package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    private String pathName;
    private File file;
    private TaskList previousTaskList;

    public Storage(String pathName, TaskList previousTaskList) throws IOException {
        this.pathName = pathName;
        this.file = new File(pathName);
        this.previousTaskList = previousTaskList;

        if (!Files.exists(Path.of(pathName))) {
            file.createNewFile();
        }
    }

    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("taskList.txt", false);
        fw.write(this.previousTaskList.printList());
        fw.close();
    }

    public void loadUpData() throws IOException {
        Scanner sc = new Scanner(this.file);

        if(!sc.hasNextLine()) {
            return;
        }

        String header = sc.nextLine();

        while(sc.hasNextLine()) {
            String task = sc.nextLine();
            char type = task.charAt(3);
            char status = task.charAt(6);

            if (type == 'E') {
                int at = task.indexOf("(at:");
                String timingWithBracket = task.substring(at + 5, task.lastIndexOf(")"));
                String description = task.substring(9, at - 1) + " " + "/at" + timingWithBracket;

                this.previousTaskList.addTask(new Event(" " + task.substring(9, at - 1), timingWithBracket));
                this.writeToFile();

                if (status == 'X') {
                    this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
                    writeToFile();
                }

                this.previousTaskList.incrementIndex();
            } else if (type == 'D') {
                int by = task.indexOf("(by:");
                String timingWithBracket = task.substring(by + 5, task.lastIndexOf(")"));
                String description = task.substring(9, by - 1) + " " + "/by" + timingWithBracket;

                this.previousTaskList.addTask(new Deadline(" " + task.substring(9, by - 1),
                        timingWithBracket));
                this.writeToFile();

                if (status == 'X') {
                    this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
                    writeToFile();
                }

                this.previousTaskList.incrementIndex();
            } else {
                String description = task.substring(9);

                this.previousTaskList.addTask(new ToDo(" " + description));
                this.writeToFile();

                if (status == 'X') {
                    this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
                    writeToFile();
                }

                this.previousTaskList.incrementIndex();
            }
        }
    }
}

