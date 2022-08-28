package chatbot.storage;

import chatbot.exceptions.DukeException;
import chatbot.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The class is responsible for populating the chatbot with possible data
 * from previous sessions and saving the data of the current session just
 * before it ends.
 */
public class Storage {
    private static final String divider = " \\| ";
    private static final String root = System.getProperty("user.dir");
    private static final Path dataPath = Paths.get(root, "data", "data.txt");

    /**
     * Populates the todo list with tasks saved from previous session
     * shall there be one.
     *
     * @param todos The todo list to be populated.
     * @throws IOException
     * @throws DukeException
     */
    public void initData(TaskList todos) throws IOException, DukeException {
        if (Files.exists(dataPath)) {
            Scanner in = new Scanner(dataPath);
            while (in.hasNextLine()) {
                String[] nextTaskInfo = in.nextLine().split(divider);
                Task newTask;
                switch (nextTaskInfo[0]) {
                case "T":
                    newTask = todos.addTodo(nextTaskInfo[2]);
                    if (nextTaskInfo[1].equals("1")) {
                        newTask.mark();
                    }
                    break;
                case "D":
                    newTask = todos.addDeadline(nextTaskInfo[2], LocalDate.parse(nextTaskInfo[3]));
                    if (nextTaskInfo[1].equals("1")) {
                        newTask.mark();
                    }
                    break;
                case "E":
                    newTask = todos.addEvent(nextTaskInfo[2], LocalDate.parse(nextTaskInfo[3]));
                    if (nextTaskInfo[1].equals("1")) {
                        newTask.mark();
                    }
                    break;
                default:
                    throw DukeException.CORRUPTED_DATA;
                }
            }

            in.close();
        }
    }

    /**
     * Saves the tasks in the todo list to a file at a fixed location known to the chatbot.
     *
     * @param todos The TaskList containing all the tasks in the current chatbot session.
     * @throws IOException
     */
    public void saveData(TaskList todos) throws IOException {
        File targetFile = dataPath.toFile();
        if (targetFile.getParentFile().mkdirs()) {
            targetFile.createNewFile();
        }

        FileWriter out = new FileWriter(targetFile);
        for (Task task : todos.listTasks()) {
            out.write(task.save() + "\n");
        }

        out.close();
    }
}
