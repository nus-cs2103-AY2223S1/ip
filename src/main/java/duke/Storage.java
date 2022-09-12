package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A Storage object that deals with loading and saving tasks. Whenever the task list updates, {@code saveToDisk} will be
 * executed and the new task will be saved into disk. Whenever the bot starts, {@code loadFromDisk} will be executed
 * and load all the tasks that were previously saved into the Chatbot.
 */
public class Storage {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String FILEPATH = "data/Duke.txt";

    /**
     * Creates a Storage object.
     */
    public Storage() {
    }

    /**
     * Retrieves the list of tasks that are saved in the disk. If specified file does not exist, it will create a new 
     * one.
     *
     * @return An ArrayList of type Task that consists of all the given tasks that were previously saved.
     */
    protected ArrayList<Task> loadFromDisk() {

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILEPATH);
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("[|]", 4);
                String keyword = words[0];
                boolean isDone = words[1].equals("1");
                String taskDetails = words[2];
                Task task = null;
                if (keyword.equals("T")) {
                    task = new ToDo(taskDetails);
                } else {
                    if (keyword.equals("D")) {
                        LocalDateTime dateTime = LocalDateTime.parse(words[3], DATE_TIME_FORMATTER);
                        task = new Deadline(taskDetails, dateTime);
                    } else if (keyword.equals("E")) {
                        LocalDateTime dateTime = LocalDateTime.parse(words[3], DATE_TIME_FORMATTER);
                        task = new Event(taskDetails, dateTime);
                    } else {
                        System.out.println("SoCCat cannot recognise the type of this task: " + keyword);
                    }
                }
                if (task != null) {
                    if (isDone) task.markAsDone();
                    tasks.add(task);
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks into the disk.
     *
     * @param tasks An ArrayList of type Task that consists of all the given tasks that were previously saved.
     */
    protected void saveToDisk(ArrayList<Task> tasks) {
        try {
            File file = new File(FILEPATH);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                String appendToEnd = task.getStatus() ? "1|" : "0|" + task.getDescription();
                if (task instanceof ToDo) {
                    writer.write("T|");
                    writer.append(appendToEnd);
                } else if (task instanceof Deadline) {
                    writer.write("D|");
                    writer.append(appendToEnd);
                    writer.append("|").append(((Deadline) task).getBy().format(DATE_TIME_FORMATTER));
                } else if (task instanceof Event) {
                    writer.write("E|");
                    writer.append(appendToEnd);
                    writer.append("|").append(((Event) task).getDuration().format(DATE_TIME_FORMATTER));
                } else {
                    System.out.println("SoCCat cannot recognise the type of this task and will not save it into disk.");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
