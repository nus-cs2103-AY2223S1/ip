package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskStored = new ArrayList<>();
        File fileToLoad = new File(filePath);
        if (!fileToLoad.exists()) {
            File fileDirectory = new File("./data");
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
                fileToLoad.createNewFile();
                System.out.println("Creating new directory data and new file tasks.txt");
            } else {
                fileToLoad.createNewFile();
                System.out.println("Creating new file tasks.txt");
            }
        }

        Scanner sc = new Scanner(fileToLoad);
        while (sc.hasNextLine()) {
            taskStored.add(formatString(sc.nextLine()));
        }
        return taskStored;
    }

    public Task formatString(String stringStored) {
        // To isolate the string containing the task description
        String s1 = stringStored.substring(7);
        // To isolate the string containing the task status
        String s2 = stringStored.substring(0, 6);
        // To keep track if the task has been marked completed
        boolean isCompleted = s2.contains("[X]");

        if (s2.contains("[T]")) {
            return new Todo(s1, isCompleted);
        } else if (s2.contains("[D]")) {
            String[] separateBy = s1.split(" \\(by: ", 2);
            return new Deadline(separateBy[0], separateBy[1].substring(0, separateBy[1].length() - 1), isCompleted);
        } else {
            String[] separateAt = s1.split(" \\(at: ", 2);
            return new Event(separateAt[0], separateAt[1].substring(0, separateAt[1].length() - 1), isCompleted);
        }
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        String dataToStore = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == 0) {
                dataToStore = tasks.getTask(i).toString();
            } else {
                dataToStore = dataToStore + "\n" + tasks.getTask(i).toString();
            }
        }
        fileWriter.write(dataToStore);
        fileWriter.close();
    }
}
