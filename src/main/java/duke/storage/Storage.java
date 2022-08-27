package duke.storage;

import duke.task.*;
import duke.utilities.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

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
            String taskCode = task.toString().substring(0,3);
            switch (taskCode) {
            case "[T]": {
                String output = "T|" + task.getStatusIcon() + "|" + task.getDescription();
                fileWriter.write(output);
                break;
            }
            case "[E]": {
                Event e = (Event) task;
                String output = "E|" + e.getStatusIcon() + "|" + e.getDescription() + "|" +
                        e.getStart() + "|" + e.getEnd();
                fileWriter.write(output);
                break;
            }
            case "[D]": {
                Deadline d = (Deadline) task;
                String output = "D|" + d.getStatusIcon() + "|" + d.getDescription() + "|" + d.getBy();
                fileWriter.write(output);
                break;
            }
            }

            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        Scanner s = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineComponents = line.split("\\|");

            String type = lineComponents[0];
            boolean doneStatus = lineComponents[1].equals("X");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            switch (type) {
                case "T":
                    Todo t = new Todo(lineComponents[2]);
                    t.setIsDone(doneStatus);
                    tasks.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(
                            lineComponents[2],
                            LocalDateTime.parse(lineComponents[3].trim(), dateFormat)
                    );
                    d.setIsDone(doneStatus);
                    tasks.add(d);
                    break;
                case "E":
                    Event e = new Event(
                            lineComponents[2],
                            LocalDateTime.parse(lineComponents[3].trim(), dateFormat),
                            LocalDateTime.parse(lineComponents[4].trim(), dateFormat)
                    );
                    e.setIsDone(doneStatus);
                    tasks.add(e);
                    break;
                default:
                    throw new DukeException("No tasks to read from storage!");
            }
        }

        s.close();

        return tasks;
    }
}
