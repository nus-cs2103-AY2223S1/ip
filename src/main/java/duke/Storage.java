package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class that saves and loads the tasks from/to local file.
 */
public class Storage {
    //File to store the list of tasks inputted by user
    private File listOfTasks;

    public Storage(String listOfTasks) {
        this.listOfTasks = new File(listOfTasks);
    }

    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> lst = new ArrayList<>();
        Scanner input = null;
        try {
            if (!listOfTasks.exists()) {
                System.out.println("OOPS! You do not have a file. Let me create one for you!");
                try {
                    listOfTasks.getParentFile().mkdirs();
                    listOfTasks.createNewFile();
                } catch (IOException e) {
                    System.out.println("OOPS! I have problem creating directory!");
                }
            }
            input = new Scanner(listOfTasks);
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS! I cannot read your file!");
        }
        while (input.hasNextLine()) {
            String taskInString = input.nextLine();
            String[] taskInArray = taskInString.split(" \\| ");
            String taskType = taskInArray[0];
            switch (taskType) {
                case "T" : {
                    Task task= new Todo(taskInArray[2]);
                    lst.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                    break;
                }
                case "D" : {
                    LocalDate date = LocalDate.parse(taskInArray[3]);
                    Task task = new Deadline(taskInArray[2], date);
                    lst.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                    break;
                }
                case "E" : {
                    Task task = new Event(taskInArray[2], taskInArray[3]);
                    lst.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                    break;
                }
                default:
                    throw new DukeException("OOPS!I cannot find a valid task type!");
            }
        }
        return lst;
    }

    public void save(TaskList tasklist) {
        try {
            FileWriter fw = new FileWriter(listOfTasks.getPath());
            for (Task task : tasklist.getTasks()) {
                fw.write(task.saveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("OOPS! I cant seem to save your tasks in the file!");
        }
    }
}
