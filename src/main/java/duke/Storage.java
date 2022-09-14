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
     *
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
            String taskType = loadedTask[0];
            String taskStatus = loadedTask[1];
            String taskDescription = loadedTask[2];
            String[] tagsList;
            switch (taskType) {
            case "T":
                tasks.add(new Todo(taskDescription));
                break;
            case "D":
                String deadline = loadedTask[3];
                tasks.add(new Deadline(taskDescription, deadline));
                break;
            case "E":
                String eventTime = loadedTask[3];
                tasks.add(new Event(taskDescription, eventTime));
                break;
            default:
                break;
            }
            if (taskStatus.equals("X")) {
                tasks.get(tasks.size() - 1).mark();
            }
            if (loadedTask.length == 5) {
                tagsList = loadedTask[4].split(" ");
                for (String tag : tagsList) {
                    tasks.get(tasks.size() - 1).addTag(tag);
                }
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to hard disk; updates any previous file with new task list
     *
     * @param tasks TaskList used to update the file and save new tasks onto hard disk
     * @throws IOException
     */
    public void saveTasks(TaskList tasks) throws IOException {
        new FileWriter("tasks.txt").close();
        FileWriter fileWriter = new FileWriter("tasks.txt");
        for (Task task : tasks.getTasks()) {
            fileWriter.write(task.getLetterTag() + "~~" + task.getStatusIcon() + "~~"
                    + task.getDescription() + "~~" + task.getAdditionalInfo() + "~~"
                    + task.getTagString() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
