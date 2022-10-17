package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents a storage that handles saving of the task list into a file and loading from the file.
 */
public class Storage {

    private File listOfAllTasks;

    /**
     * Class constructor.
     *
     * @param listOfTasks String representation of file's path.
     */
    public Storage(String listOfTasks) {
        this.listOfAllTasks = new File(listOfTasks);
    }

    //Solution adapted from https://github.com/24Donovan24/ip/tree/d036dd6c7e3bdaace325d1a365de12c676f55340

    /**
     * Loads task list from the file.
     *
     * @return ArrayList of tasks loaded from a saved file if the file exists or a new ArrayList otherwise.
     * @throws IOException
     * @throws DukeException
     */
    public ArrayList<Task> load() throws IOException, DukeException{
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = null;
        if (!listOfAllTasks.exists()) {
            System.out.println("No existing file! Let me create a new one for you.");
            try {
                listOfAllTasks.getParentFile().mkdirs();
                listOfAllTasks.createNewFile();
            } catch (IOException e) {
                System.out.println("I encountered a problem!");
            }
        }
        try {
            sc = new Scanner(listOfAllTasks);
        } catch (FileNotFoundException e) {
            throw new DukeException("I cannot read your file");
        }
        while (sc.hasNextLine()) {
                String taskInFile = sc.nextLine();
                String[] taskInArray = taskInFile.split(" \\| "); //The \\ is really equivalent to a single \ (the first \ is required as a Java escape sequence in string literals).
                // It is then a special character in regular expressions which means "use the next character literally, don't interpret its special meaning"
                String taskType = taskInArray[0];
                assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E");
                switch (taskType) {
                case "T": {
                    Task task = new ToDo(taskInArray[2]);
                    list.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                    break;
                }

                case "D": {
                    Task task = new Deadline(taskInArray[2], taskInArray[3]);
                    list.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                    break;
                }

                case "E": {
                    Task task = new Event(taskInArray[2], taskInArray[3]);
                    list.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                    break;
                }
                default:
                    throw new DukeException("I cannot find a valid task type.");
                }
            }
            return list;
    }

    /**
     * Saves changes made to tasklist to the file.
     *
     * @param taskList TaskList object consisting of list of tasks.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(listOfAllTasks.getPath());
            for (Task task : taskList.getAllTasks()) {
                writer.write(task.saveString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException exception) {
            System.out.println("I cannot save your tasks in the file.");
        }
    }

}
