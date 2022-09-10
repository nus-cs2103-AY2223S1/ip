package seedu.duke;

import seedu.duke.Ui.Ui;
import seedu.duke.task.DeadlineTask;
import seedu.duke.task.EventTask;
import seedu.duke.task.ToDoTask;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

/**
 * Storage class for creating, saving and loading a file to contain the task list.
 */
public class Storage {

    public Storage() {}

    /**
     * Runs at launch. Retrieves the task list if it exists, otherwise creates one.
     * @return path to the list text file
     * @throws IOException
     */
    public Path createSave() throws IOException {
        Path file = null;
        try {
            file = Paths.get(".", "data");
            if (!Files.exists(file)) {
                Files.createDirectory(file);
            }
            file = Paths.get(".", "data", "list.txt");
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Saves the list as a text file in the given path.
     * @param list
     * @param file
     * @throws IOException
     */
    public void saveList(TaskList list, Path file) throws IOException {
        try {
            Files.write(file, list.toStringList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list from the file and puts its contents into a TaskList.
     * May move the scanning + parsing into Parser class at a later date.
     * @param file
     * @return TaskList containing the information from the file.
     * @throws IOException
     */
    public TaskList loadList(Path file) throws IOException {
        TaskList list = new TaskList();
        try {
            Scanner scanner = new Scanner(file);
            if (!scanner.hasNext()) {
                return list;
            }
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                boolean isDone = taskString.charAt(4) == 'X';
                switch (taskString.charAt(1)) {
                    case 'T': {
                        ToDoTask todo = new ToDoTask(taskString.substring(7, taskString.length()), isDone);
                        list.add(todo);
                        break;
                    }
                    case 'D': {
                        String[] taskDesc = taskString.substring(7, taskString.length() - 1).split(" \\(by: ");
                        DeadlineTask deadlineTask = new DeadlineTask(taskDesc[0], taskDesc[1], isDone);
                        list.add(deadlineTask);
                        break;
                    }
                    case 'E': {
                        String[] taskDesc = taskString.substring(7, taskString.length() - 1).split(" \\(on: ");
                        EventTask eventTask = new EventTask(taskDesc[0], taskDesc[1], isDone);
                        list.add(eventTask);
                        break;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
