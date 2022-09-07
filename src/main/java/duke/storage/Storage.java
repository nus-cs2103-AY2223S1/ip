package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Class that loads in file data and save file data of the program.
 */
public class Storage {

    private final File file;

    /**
     * Creates the Storage.
     * Checks if file and directory exists, if it does not, create one its place.
     *
     * @throws IOException If an error occurs when making the file.
     */
    public Storage() throws IOException {
        File directory = new File("data/");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File("data/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        this.file = file;
    }

    /**
     * Loads in the file data of previous Tasks when the program is first started.
     *
     * @return The ArrayList of Tasks from the saved file.
     * @throws DukeException If data in file has some errors.
     * @throws IOException If error occurs when reading the file.
     */
    public ArrayList<Task> loadFile() throws DukeException, IOException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String current = sc.nextLine();
            String[] info = current.split("\\|");
            String taskType = info[0];
            String description = info[2];
            boolean isDone = info[1].equals("1");
            switch (taskType) {
            case "T":
                Task addTodo = new Todo(description, isDone);
                tasks.add(addTodo);
                break;
            case "E":
                LocalDate atDate = LocalDate.parse(info[3]);
                Task addEvent = new Event(description, atDate, isDone);
                tasks.add(addEvent);
                break;
            case "D":
                LocalDate byDate = LocalDate.parse(info[3]);
                Task addDeadline = new Deadline(description, byDate, isDone);
                tasks.add(addDeadline);
                break;
            default:
                throw new IOException("Error in reading file");
                // Fallthrough
            }
        }

        return tasks;
    }

    /**
     * Updates the file according to current Tasks in program.
     *
     * @param tasks The list of Tasks currently in program.
     * @throws IOException If error occurs when creating FileWriter.
     */
    public void writeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks.getTasks()) {
            if (t instanceof Todo) {
                writeTodo(t, sb);
            } else if (t instanceof Deadline) {
                writeDeadline(t, sb);
            } else {
                writeEvent(t, sb);
            }
        }
        fw.write(sb.toString());
        fw.close();
    }

    private void writeTodo(Task t, StringBuilder sb) {
        sb.append("T")
                .append("|")
                .append(t.getIsDone() ? "1" : "0")
                .append("|")
                .append(t.getDescription())
                .append(System.lineSeparator());
    }

    private void writeDeadline(Task t, StringBuilder sb) {
        sb.append("D")
                .append("|")
                .append(t.getIsDone() ? "1" : "0")
                .append("|")
                .append(t.getDescription())
                .append("|")
                .append(((Deadline) t).getBy())
                .append(System.lineSeparator());
    }

    private void writeEvent(Task t, StringBuilder sb) {
        sb.append("E")
                .append("|")
                .append(t.getIsDone() ? "1" : "0")
                .append("|")
                .append(t.getDescription())
                .append("|")
                .append(((Event) t).getAt())
                .append(System.lineSeparator());
    }
}
