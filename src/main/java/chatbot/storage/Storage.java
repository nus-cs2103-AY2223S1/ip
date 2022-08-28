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

public class Storage {
    private static final String DIVIDER = " \\| ";
    private static final String ROOT = System.getProperty("user.dir");
    private static final Path DATA_PATH = Paths.get(ROOT, "data", "data.txt");

    public void initData(TaskList todos) throws IOException, DukeException {
        if (Files.exists(DATA_PATH)) {
            Scanner in = new Scanner(DATA_PATH);
            while (in.hasNextLine()) {
                String[] nextTaskInfo = in.nextLine().split(DIVIDER);
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

    public void saveData(TaskList todos) throws IOException {
        File targetFile = DATA_PATH.toFile();
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
