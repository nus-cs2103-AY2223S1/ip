package duke;

import duke.exception.DukeException;
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
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final static String FILEPATH = "data/Duke.txt";

    /**
     * Creates a Storage object.
     */
    public Storage() {
    }

    /**
     * Retrieves the list of tasks that are saved in the disk.
     *
     * @return An ArrayList of type Task that consists of all the given tasks that were previously saved.
     * @throws DukeException if an I/O error occurs when locating or creating the file with the given pathname.
     */
    protected ArrayList<Task> loadFromDisk() throws DukeException {

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
                String isDone = words[1];
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
                if (isDone.equals("1") && task != null) {
                    task.markAsDone();
                    tasks.add(task);
                }
            }
            reader.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks into the disk.
     *
     * @param tasks An ArrayList of type Task that consists of all the given tasks that were previously saved.
     * @throws DukeException if an I/O error occurs when locating or creating the file with the given pathname.
     */
    protected void saveToDisk(ArrayList<Task> tasks) throws DukeException {
        try {
            File file = new File(FILEPATH);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                if (task instanceof ToDo) {
                    writer.write("T|");
                    writer.write(task.getStatus() ? "1|" : "0|");
                    writer.write(task.getDescription());
                } else if (task instanceof Deadline) {
                    writer.write("D|");
                    writer.write(task.getStatus() ? "1|" : "0|");
                    writer.write(task.getDescription() + "|");
                    writer.write(((Deadline) task).getBy().format(DATE_TIME_FORMATTER));
                } else if (task instanceof Event) {
                    writer.write("E|");
                    writer.write(task.getStatus() ? "1|" : "0|");
                    writer.write(task.getDescription() + "|");
                    writer.write(((Event) task).getDuration().format(DATE_TIME_FORMATTER));
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }
}
