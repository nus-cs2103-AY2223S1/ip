package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.tasks.DeadlinesTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;



/**
 * Class that manages the storage of tasks into the text file.
 */
public class Storage {
    private String directory;
    private final String fileName = "/duke.txt";

    /**
     * Default constructor of the Storage class.
     *
     * @param directory Location of the text file.
     */
    public Storage(String directory) {
        this.directory = directory;
    }

    /**
     * Returns the TaskList object represented in the text file.
     *
     * @return TaskList object.
     */
    public TaskList load() {
        File fileDir = new File(directory);
        fileDir.mkdir();
        File f = new File(directory + fileName);
        TaskList taskList = new TaskList();

        try {
            f.createNewFile();
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                taskList.add(parseLine(s.nextLine()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return taskList;
    }

    private Task parseLine(String entry) {
        String[] entryArray = entry.split(",");
        String taskType = entryArray[0];
        String name = entryArray[2];
        boolean isMarked = entryArray[1].equals("X");
        Task newTask;
        if (taskType.equals("T")) {
            newTask = new TodoTask(name);
        } else if (taskType.equals("D")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime date = LocalDateTime.parse(entryArray[3], formatter);
            newTask = new DeadlinesTask(name, date);
        } else {
            newTask = new EventTask(name, entryArray[3]);
        }

        if (isMarked) {
            newTask.markAsDone();
        }
        return newTask;
    }

    /**
     * Saves all tasks in the TaskList to the text file.
     *
     * @param tasks TaskList containing the list of all the tasks.
     */
    public void writeAll(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(directory + "/duke.txt");
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.get(i).toCsv() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
