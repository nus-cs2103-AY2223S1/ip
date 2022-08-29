package duke;

import duke.exception.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {
    private static final String DIR = System.getProperty("user.dir");
    private final String filePath;

    public Storage (String filePath) {
        this.filePath = DIR + "/" + filePath;
    }

    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> dukeList = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] splitInput = input.split(Pattern.quote(" | "));
                String taskType = splitInput[0];
                String taskStatus = splitInput[1];
                String taskDescription = splitInput[2];

                boolean isComplete = taskStatus.equals("1");

                switch (taskType) {
                    case "T":
                        ToDo todo = new ToDo(taskDescription);
                        dukeList.add(todo);
                        break;

                    case "D":
                        String by = splitInput[3];
                        Deadline deadline = new Deadline(taskDescription, by);
                        dukeList.add(deadline);
                        break;

                    case "E":
                        String at = splitInput[3];
                        Event event = new Event(taskDescription, at);
                        dukeList.add(event);
                        break;

                    default:
                        break;
                }

                if (isComplete) {
                    int size = dukeList.size();
                    dukeList.get(size - 1).markAsDone();
                }
            }
        } else {
            File parent = new File(DIR + "/data");
            boolean isDirectoryCreated = parent.mkdir();
            boolean isFileCreated = file.createNewFile();
        }

        return dukeList;
    }

    public void save(TaskList tasks) throws IOException { // throws DukeIOException
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.getTask(i);
            String status = curr.getStatusIcon().equals("X") ? "1" : "0";
            if (curr instanceof ToDo) {
                writer.write("T | " + status + " | " + curr.getDescription() + "\n");
            } else if (curr instanceof Deadline) {
                writer.write("D | " + status + " | " + curr.getDescription() + " | "
                        + ((Deadline) curr).getBy().toString() + "\n");
            } else if (curr instanceof Event) {
                writer.write("E | " + status + " | " + curr.getDescription() + " | "
                        + ((Event) curr).getAt().toString() + "\n");
            }
        }
        writer.close();
    }
}
