package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import duke.command.CommandType;
import duke.dukeexception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;


/**
 * Represents the storage of taskList of this Duke robot.
 */
public class Storage {
    private File file;

    /**
     * Class construtor. Construct a storage according to the file path given.
     * If there is already a text file in the file path, then create corresponding object.
     * If there is not, create the directory and the text file.
     * Throws exception when file operation fails.
     * @param filepath A string represents the file path of the taskList.
     * @throws IOException Throws exception when error occurs.
     */
    public Storage(String filepath) throws IOException {
        String s = "./" + filepath.split("duke")[0];
        File f = new File(s);
        this.file = new File(s + "duke.txt");
        if (!this.file.exists()) {
            f.mkdir();
            this.file.createNewFile();
        }
    }
    /**
     * Update the task content in the file.
     * @param task Task that will be written in the file.
     * @throws IOException Throws exception when file operation fails.
     */
    public void writeToFile(Task task, FileWriter fw) throws IOException {
        if (task == null) {
            throw new IOException();
        }
        if (task.getStatusIcon().equals("X")) {
            fw.write(task.getStatusIcon() + " "
                    + task.getDescription() + "\n");
        } else {
            fw.write("Wait " + task.getDescription() + "\n");
        }
    }
    /**
     * Update the taskList content in the file.
     * @param t the corresponding list of task that will be written in the file.
     * @throws DukeException Throws exception when file operation fails.
     */
    public void updateFile(List<Task> t) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (int i = 0; i < t.size(); i++) {
                this.writeToFile(t.get(i), fw);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, something went wrong when updating the file.");
        }
    }

    /**
     * Load tasks from file and return a TaskList
     * @return A taskList containing previous added tasks, load from the corresponding file.
     */

    public TaskList loadTasks() throws DukeException {
        try {
            Scanner s = new Scanner(this.file);
            TaskList taskList = new TaskList();
            while (s.hasNextLine()) {
                String storedTaskString = s.nextLine();
                Task t = disposeTaskString(storedTaskString);
                taskList.getTaskList().add(t);
            }
            s.close();
            return taskList;
        } catch (Exception e) {
            throw new DukeException("Sorry, something went wrong when loading task.");
        }
    }
    /**
     * Delete the file that used to store data.
     */
    public void clear() {
        this.file.delete();
    }
    /**
     * Create task according to a line of description in the file.
     * @param storedTaskString the corresponding description of the task.
     * @throws DukeException Throws exception when file operation or creation fails.
     */
    public Task disposeTaskString(String storedTaskString) throws DukeException {
        String[] temp = storedTaskString.split(" ", 3);
        String status = temp[0];
        String type = temp[1];
        String fullDescription = temp[1] + " " + temp[2];
        CommandType c = CommandType.COMMAND_MAP.get(type);
        Task t = Task.createATask(fullDescription, c);
        t.markStatus(status);
        return t;
    }
}
