package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Stores the data of the bot.
 */
public class Storage {

    private String filepath;
    private File file;
    private Duke duke = new Duke();

    /**
     * Constructs a storage space.
     *
     * @param filepath the file path for the file to be stored.
     */
    public Storage(String filepath) {
        this.file = new File(filepath);
        this.filepath = filepath;
    }

    /**
     * Loads the task in the file.
     *
     * @param file the file to be load.
     */
    public void loadTask(File file) {
        System.out.println("Loading tasks...");
        loadInitial(file);
    }

    /**
     * Loads the tasks in the file.
     *
     * @param file the file to be load.
     * @return the strings of the tasks.
     */
    public String loadTaskGui(File file) {
        String st = "Loading tasks...";
        st += loadInitialGui(file);
        return st;
    }

    private String loadInitialGui(File file) {
        try {
            TaskList tl = new TaskList();
            BufferedReader br = null;
            String st;
            if (!file.exists()) {
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader(file));
        while (true) {
            if (!((st = br.readLine()) != null)) {
                break;
            }
            tl.getList().add(convertStringToTask(st));
            duke.addCount();
            st += st;
        } return st;
        } catch (Exception e) {
        if (e instanceof FileNotFoundException) {
            throw new RuntimeException(e);
        } else if (e instanceof IOException) {
            return e.getMessage();
        } return e.getMessage();
        }
    }

    private void loadInitial(File file) {
        try {
            TaskList tl = new TaskList();
            BufferedReader br = null;
            String st;
            if (!file.exists()) {
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader(file));
            while (true) {
                if (!((st = br.readLine()) != null)) {
                    break;
                }
                tl.getList().add(convertStringToTask(st));
                duke.addCount();
                System.out.println(st);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the task list in the specified file.
     *
     * @param file the file that stores the task list.
     * @return the task list in the specified file.
     */
    public TaskList load(File file) {
        try {
            TaskList tl = new TaskList();
            duke.setCount(0);
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            String st;
            while (true) {
                if (!((st = br.readLine()) != null)) {
                        break;
                }
                tl.getList().add(convertStringToTask(st));
                duke.addCount();
            }
            return tl;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Adds task to a file.
     *
     * @param file the file that the tasks is being added to.
     * @param task the task to be added.
     */
    public void addTaskToFile(File file, Task task) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(task.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overwrites the task list in a file.
     *
     * @param file the file that needs to be overwritten.
     * @param taskList the new task list.
     */
    public void overwriteFile(File file, TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.close();
            fw = new FileWriter(file, true);
            for (Task task : taskList.getList()) {
                fw.write(task.toString());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts string representation of the task to task.
     *
     * @param s string representation of the task.
     * @return the task that given the description of the string.
     */
    public Task convertStringToTask(String s) {
        if (s.length() == 0) {
            return null;
        }
        char taskType = s.charAt(1);
        char done = s.charAt(4);
        char priority = Character.toUpperCase(s.charAt(7));
        Task task = null;
        if (taskType == 'T') {
            task = new Todo(s.substring(9));
        }
        Parser p = new Parser();
        if (taskType == 'E') {
            int firstDateIndex = s.indexOf('(');
            int lastDateIndex = s.indexOf(')');
            String name = s.substring(9, firstDateIndex - 1);
            String date = s.substring(firstDateIndex + 5, lastDateIndex);
            task = new Event(name, p.parseFileString(date));
        } else if (taskType == 'D') {
            int firstDateIndex = s.indexOf('(');
            int lastDateIndex = s.indexOf(')');
            String name = s.substring(9, firstDateIndex - 1);
            String date = s.substring(firstDateIndex + 5, lastDateIndex);
            task = new Deadline(name, p.parseFileString(date));
        }
        if (done == 'X') {
            task.setStatus("[X]");
        }
        task.setPriority(priority);
        return task;
    }
}
