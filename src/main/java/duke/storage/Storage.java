package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.processor.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {
    protected final String directory = System.getProperty("user.home") + "/DukeData";
    protected final String filePath = directory + "/Duke.txt";

    public File initialize() throws DukeException {
        try {
            File parentDirectory = new File(directory);

            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }

            File dukeFile = new File(filePath);

            if (!dukeFile.exists()) {
                dukeFile.createNewFile();
            }

            return dukeFile;

        } catch (IOException e) {
            throw new DukeException("OOPS!! A new file cannot be created.");
        }
    }

    public ArrayList<Task> readFile(File dukeFile) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(dukeFile);
            while (sc.hasNextLine()) {
                String commands = sc.nextLine();
                String typeOfTask = commands.substring(1, 2);
                String marked = commands.substring(4, 5);
                String description = commands.substring(7);

                switch (typeOfTask) {
                case "T":
                    Task toDo = new ToDo(description);
                    if (marked == "X") {
                        toDo.markAsDone();
                    }
                    tasks.add(toDo);
                    break;
                case "D":
                    String[] deadlineDescription = description.split("by:");
                    String deadlineName = deadlineDescription[0]
                            .substring(0, deadlineDescription[0].length() - 1)
                            .trim();
                    String by = deadlineDescription[1].substring(1, deadlineDescription[1].length() - 1).trim();
                    LocalDate deadlineDate = LocalDate.parse(by);
                    Task deadline = new Deadline(deadlineName, deadlineDate);
                    if (marked == "X") {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    String[] eventDescription = description.split("at:");
                    String eventName = eventDescription[0]
                            .substring(0, eventDescription[0].length() - 1)
                            .trim();
                    String at = eventDescription[1].substring(0, eventDescription[1].length() - 1).trim();
                    Task event = new Event(eventName, at);
                    if (marked == "X") {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                default:
                    break;
                }
            }
            return tasks;

        } catch (IOException e) {
            throw new DukeException("OOPS!! Failed to read file.");
        }
    }

    public void writeAndSaveToFile(File dukeFile, TaskList tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(dukeFile);
            int numberOfTasks = tasks.getNumberOfTasks();
            for (int i = 0; i < numberOfTasks; i++) {
                Task task = tasks.getTask(i);
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!! Failed to write to file.");
        }
    }
}
