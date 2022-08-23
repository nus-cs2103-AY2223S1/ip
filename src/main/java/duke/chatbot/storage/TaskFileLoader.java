package duke.chatbot.storage;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskFileLoader {
    private final File file;

    protected TaskFileLoader(File file) {
        this.file = file;
    }

    public TaskList getTaskList() {
        try {
            Scanner sc = new Scanner(file);
            TaskList taskList = new TaskList();

            while (sc.hasNext()) {
                taskList.add(TaskLineParser.parseLine(sc.nextLine()));
            }

            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return null;
        }
    }
}
