package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.common.Messages;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Storage {
    private static final String FILE_PATH = "data/tasklist.txt";

    private void createFile(File file) throws DukeException {
        new File("data").mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException(Messages.MESSAGE_STORAGE_ERROR);
        }
    }

    private String formatTask(Task task) {
        String type = task.getType();
        String status = task.getStatusIcon();
        String description = task.getDescription();
        String date = task.getDate();
        return String.format("%s;%s;%s;%s", type, status, description, date) + System.lineSeparator();
    }

    public void save(TaskList taskList) throws DukeException {
        int numTasks = taskList.numTasks();
        FileWriter writer = null;

        try {
            writer = new FileWriter(FILE_PATH);
            for (int i = 0; i < numTasks; i++) {
                Task task = taskList.getTask(i);
                writer.write(formatTask(task));
            }
        } catch (IOException e) {
            throw new DukeException(Messages.MESSAGE_STORAGE_ERROR);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new DukeException(Messages.MESSAGE_STORAGE_ERROR);
                }
            }
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        createFile(file);
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] splitInputArray = line.split(";", 4);
                String type = splitInputArray[0];
                String status = splitInputArray[1];
                String description = splitInputArray[2];
                String date = splitInputArray[3];

                Task task;
                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D")) {
                    task = new Deadline(description, date);
                } else {
                    task = new Event(description, date);
                }
                tasks.add(task);
                task.changeStatus(status.equals("X"));
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException(Messages.MESSAGE_STORAGE_ERROR);
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
