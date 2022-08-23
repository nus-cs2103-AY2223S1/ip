package duke.storage;

import duke.task.*;
import duke.utilities.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private final File file;

    public Storage(String filePath) throws IOException {
        String[] parts = filePath.split("/");
        File folder = new File(parts[0]);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        this.file = file;
    }

    public void writeTasksToStorage(TaskList taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getTasks();
        FileWriter fileWriter = new FileWriter(this.file);
        for (Task task : tasks) {
            if (task instanceof Todo) {
                String rep = "T|" + task.getDoneStatus() + "|" + task.getDescription();
                fileWriter.write(rep);
            } else if (task instanceof Event) {
                Event e = (Event) task;
                String rep = "E|" + e.getDoneStatus() + "|" + e.getDescription() + "|" +
                        e.getStart() + "|" + e.getEnd();
                fileWriter.write(rep);
            } else if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                String rep = "D|" + d.getDoneStatus() + "|" + d.getDescription() + "|" + d.getBy();
                fileWriter.write(rep);
            }

            fileWriter.write(System.lineSeparator());
        }

        fileWriter.close();
    }

    public ArrayList<Task> readTasksFromStorage() throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] lineComponents = line.split("\\|");

            String type = lineComponents[0];
            boolean doneStatus = lineComponents[1].equals("X");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            switch (type) {
            case "T":
                Todo t = new Todo(lineComponents[2]);
                t.setDoneStatus(doneStatus);
                tasks.add(t);
                break;
            case "D":
                Deadline d = new Deadline(lineComponents[2], LocalDateTime.parse(lineComponents[3], dateFormat));
                d.setDoneStatus(doneStatus);
                tasks.add(d);
                break;
            case "E":
                Event e = new Event(
                        lineComponents[2],
                        LocalDateTime.parse(lineComponents[3], dateFormat),
                        LocalDateTime.parse(lineComponents[4], dateFormat)
                );
                e.setDoneStatus(doneStatus);
                tasks.add(e);
                break;
            default:
                throw new DukeException("No tasks to read from storage!");
            }
        }

        sc.close();

        return tasks;
    }
}
