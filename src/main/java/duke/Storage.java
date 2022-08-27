package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents database of Duke.
 * Persists tasks into duke.txt file.
 */
public class Storage {
    private static final String FILE = "./duke.txt";

    /**
     * Loads tasks from duke.txt file.
     * 
     * @return
     * @throws DukeException If duke.txt was not found or if there was an error
     *                       during processing.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            while (true) {
                try {
                    String taskString = br.readLine();
                    if (taskString == null) {
                        // EOF reached
                        br.close();
                        break;
                    }

                    // process each line into a task
                    String[] taskArray = taskString.split("\\|");
                    String taskType = taskArray[0].strip();
                    boolean isDone = taskArray[1].strip().equals("1");
                    String description = taskArray[2].strip();

                    switch (taskType) {
                    case "T":
                        tasks.add(new Todo(isDone, description));
                        break;
                    case "D":
                        String deadlineString = taskArray[3].strip();
                        LocalDate deadline = LocalDate.parse(deadlineString,
                                DateTimeFormatter.ofPattern("dd MMM yy"));
                        tasks.add(new Deadline(isDone, description, deadline));
                        break;
                    case "E":
                        String eventTimeString = taskArray[3].strip();
                        LocalDate eventTime = LocalDate.parse(eventTimeString,
                                DateTimeFormatter.ofPattern("dd MMM yy"));
                        tasks.add(new Event(isDone, description, eventTime));
                        break;
                    }
                } catch (IOException e) {
                    throw new DukeException(
                            "Error occured while processing duke.txt. Loading empty list of tasks instead.");
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("duke.txt was not found. Loading empty list of tasks instead.");
        }
        return tasks;
    }

    /**
     * Saves tasks to duke.txt file.
     * 
     * @param taskList The current tasks.
     */
    public void save(TaskList taskList) {
        try {
            List<Task> tasks = taskList.getTasks();
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));

            for (Task task : tasks) {
                // process each task into a line
                String taskString = task.toDbString();
                bw.write(taskString);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
