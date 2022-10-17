package hazell.entities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hazell.Storage;
import hazell.exceptions.HazellException;
import hazell.exceptions.NoSuchTask;

/**
 * A class that stores Tasks.
 */
public class TaskList {
    private final List<Task> store;
    private Storage storage;

    public TaskList() {
        this.store = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.store = tasks;
    }

    // Quick workaround method
    public void setStorage(Storage storage) {
        this.storage = storage;
    }


    /**
     * Retrieves a specified task using an index (1-indexed).
     *
     * @param index The index
     * @return The `index`-th task
     */
    public Task getTask(int index) throws NoSuchTask {
        try {
            return this.store.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new NoSuchTask(this.size());
        }
    }

    private String getSummary() {
        return String.format("Now you have %d tasks in the list.", this.store.size());
    }

    /**
     * Puts a new task into the store.
     *
     * @param task The task to be added
     * @return The response to be shown by the bot
     */
    public String addTask(Task task) {
        this.store.add(task);
        try {
            this.saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format("Got it. I've added this task:\n\t%s", task.toString())
                + "\n"
                + this.getSummary();
    }

    /**
     * Deletes a task in the store.
     *
     * @param index The index for which the (index)-th task to delete
     * @return A response string
     * @throws NoSuchTask If task cannot be found
     */
    public String deleteTask(int index) throws NoSuchTask {
        Task task;
        try {
            task = this.store.remove(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new NoSuchTask(this.size());
        }
        try {
            this.saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format("Noted. I've removed this task:\n\t%s", task.toString())
                + "\n"
                + this.getSummary();
    }

    /**
     * Marks a task in the store as done.
     *
     * @param index The index for which the (index)-th task to mark
     * @return A response string
     * @throws NoSuchTask If task cannot be found
     */
    public String markTaskAsDone(int index) throws NoSuchTask {
        Task task = getTask(index);
        task.markAsDone();
        try {
            this.saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format("Nice! I've marked this task as done:\n\t%s", task);
    }

    /**
     * Marks a task in the store as undone.
     *
     * @param index The index for which the (index)-th task to mark
     * @return A response string
     * @throws NoSuchTask If task cannot be found
     */
    public String markTaskAsUndone(int index) throws NoSuchTask {
        Task task = getTask(index);
        task.markAsUndone();
        try {
            this.saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format("OK, I've marked this task as not done yet:\n\t%s", task);
    }

    /**
     * Postpones or changes the time associated with this task.
     *
     * @param index The index of the task, in the order of this store
     * @param time The new time to be changed to
     * @return The bot response
     * @throws NoSuchTask If there is no task corresponding to this index
     */
    public String postponeTimeSensitiveTask(int index, String time) throws HazellException {
        Task task = getTask(index);
        if (!(task instanceof TimeSensitiveTask)) {
            return "This task is not time-sensitive!";
        }

        TimeSensitiveTask tsTask = (TimeSensitiveTask) task;
        tsTask.postpone(time);
        try {
            this.saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format("OK, I've postponed this task:\n\t%s", task);
    }

    /**
     * Returns a Path object pointing to the file used to store Hazell data locally.
     * Before doing so, it ensures that the folder exists.
     *
     * @return Path to file used by Hazell chatbot
     * @throws IOException When error getting file
     */
    private static Path getAndInitialiseFilePath() throws IOException {
        String currentDir = System.getProperty("user.dir");
        Path dataDir = Paths.get(currentDir, "data");
        if (!Files.exists(dataDir)) {
            Files.createDirectory(dataDir);
        }
        return Paths.get(currentDir, "data", "hazell.txt");
    }

    public void saveToFile() throws IOException {
        this.storage.store(this.store);
    }

    /**
     * Finds tasks with descriptions matching `keyword`.
     *
     * @param keyword The search string
     * @return Response string
     */
    public String findMatchingTasks(String keyword) {
        List<Task> output = new ArrayList<>();
        for (Task task : store) {
            if (task.getDescription().matches(keyword)) {
                output.add(task);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:");
        for (Task task : output) {
            sb.append("\n");
            sb.append(task.toString());
        }
        return sb.toString();
    }

    /**
     * Lists detailed information about all tasks in this store.
     *
     * @return String Details
     */
    @Override
    public String toString() {
        if (this.size() == 0) {
            return "You have no tasks, please create one!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.store.size(); i++) {
            Task task = null;
            try {
                task = this.getTask(i);
            } catch (NoSuchTask e) {
                // This block will never be executed as we are looping within the size of store.
            }
            sb.append(String.format("%d. %s", i + 1, task));
            if (i != this.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public int size() {
        return this.store.size();
    }
}
