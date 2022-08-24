package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Loads the task list from a file in the hard disk and updates any changes in the
 * task list in the same file
 */
public class Storage {

    private final String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    /**
     * Loads list of tasks previously saved in hard disk
     * If no previous file exists, create a new file
     * @return ArrayList of tasks that were loaded from hard disk
     * @throws IOException
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filename);
        file.createNewFile();
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String tempTask = fileScanner.nextLine();
            String[] loadedTask = tempTask.split("~~");
            switch (loadedTask[0]) {
            case "T":
                tasks.add(new Todo(loadedTask[2]));
                break;
            case "D":
                tasks.add(new Deadline(loadedTask[2], loadedTask[3]));
                break;
            case "E":
                tasks.add(new Event(loadedTask[2], loadedTask[3]));
                break;
            default:
                break;
            }
            if (loadedTask[1].equals("X")) {
                tasks.get(tasks.size() - 1).mark();
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to hard disk; updates any previous file with new task list
     * @param tasks TaskList used to update the file and save new tasks onto hard disk
     * @throws IOException
     */
    public void saveTasks(TaskList tasks) throws IOException {
        new FileWriter("tasks.txt").close();
        FileWriter fileWriter = new FileWriter("tasks.txt");
        for (Task task : tasks.getTasks()) {
            fileWriter.write(task.getLetterTag() + "~~" + task.getStatusIcon() + "~~"
                    + task.getDescription() + "~~" + task.getAdditionalInfo() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
