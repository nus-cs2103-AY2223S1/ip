package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the file where all the tasks are read or written into
 */
public class Storage {

    protected Path outputFile;

    /**
     * A constructor for the Storage class
     *
     * @param f The path of the output file to contain the list of tasks
     */
    public Storage(Path f) {
        this.outputFile = f;
    }

    /**
     * Updates or refreshes the list of tasks and synchronises it to the output file
     *
     * @param taskList The list of tasks recorded so far
     */
    public void refreshList(List<Task> taskList) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(this.outputFile);
            writer.write("");
            writer.flush();
            for (Task curTask : taskList) {
                if (curTask instanceof Event) {
                    writer.write("Event | " + curTask.getStatusNumber() + " | "
                            + curTask.getDescription() + " | "
                            + ((Event) curTask).getDatetime() + "\n");
                } else if (curTask instanceof Todo) {
                    writer.write("Todo | " + curTask.getStatusNumber() + " | "
                            + curTask.getDescription() + "\n");
                } else if (curTask instanceof Deadline) {
                    writer.write("Deadline | " + curTask.getStatusNumber() + " | "
                            + curTask.getDescription()
                            + " | " + ((Deadline) curTask).getDatetime() + "\n");
                }
            }
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Reads in all the tasks recorded from the output file
     *
     * @return The list of tasks
     * @throws IOException If the output file cannot be read
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> inputTasks = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(this.outputFile);
        String taskLine = reader.readLine();
        Task loadTask = null;
        while (taskLine != null) {
            String[] taskDetails = taskLine.split("\\s\\|\\s");
            switch (taskDetails[0]) {
            case "Event":
                loadTask = new Event(taskDetails[2], taskDetails[3]);
                break;
            case "Deadline":
                loadTask = new Deadline(taskDetails[2], taskDetails[3]);
                break;
            case "Todo":
                loadTask = new Todo(taskDetails[2]);
                // Fallthrough
            }

            if (taskDetails[1].equals("0")) {
                loadTask.markAsUndone();
            } else {
                loadTask.markAsDone();
            }
            inputTasks.add(loadTask);
            taskLine = reader.readLine();
        }
        return inputTasks;
    }
}
