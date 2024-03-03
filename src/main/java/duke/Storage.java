package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
        TaskList tl = new TaskList();
        String st = "";
        try {
            file = new File("dukes.txt");
            if (!file.exists()) {
                file.createNewFile();
                return st;
            }
            assert file.exists() : "File should exist";
            Scanner sc = new Scanner(file);
            if (!sc.hasNextLine()) {
                return st;
            }
            tl.getList().add(convertStringToTask(st));
            duke.addCount();
            st += st;
        } catch (Exception e) {
            return "File not found!";
        }
        return st;
    }

    private void loadInitial(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            printFileTask();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printFileTask() throws IOException {
        BufferedReader br = null;
        TaskList tl = new TaskList();
        String st;
        br = new BufferedReader(new FileReader(file));
        while (true) {
            if (!((st = br.readLine()) != null)) {
                break;
            }
            tl.getList().add(convertStringToTask(st));
            duke.addCount();
            System.out.println(st);
        }
    }

    /**
     * Returns the task list in the specified file.
     *
     * @return the task list in the specified file.
     */
    public TaskList load(String filepath) {
        try {
            TaskList tl = new TaskList();
            duke.setCount(0);
            BufferedReader br = null;
            file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
                return tl;
            }
            assert file.exists() : "File should exist";
            br = new BufferedReader(new FileReader(file));
            String st = "";
            return readFileLine(br, st, tl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private TaskList readFileLine(BufferedReader br, String st, TaskList tl)
            throws IOException {
        while (true) {
            if (!((st = br.readLine()) != null)) {
                break;
            }
            tl.getList().add(convertStringToTask(st));
            duke.addCount();
        }
        return tl;
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
        if (taskType == 'E' || taskType == 'D') {
            task = convertStringToTaskWithDate(s, taskType);
        }
        if (done == 'X') {
            task.setStatus("[X]");
        }
        task.setPriority(priority);
        return task;
    }

    private Task convertStringToTaskWithDate(String s, char taskType) {
        Parser p = new Parser();
        int firstDateIndex = s.indexOf('(');
        int lastDateIndex = s.indexOf(')');
        String name = s.substring(9, firstDateIndex - 1);
        String date = s.substring(firstDateIndex + 5, lastDateIndex);
        if (taskType == 'E') {
            return new Event(name, p.parseFileString(date));
        } else if (taskType == 'D') {
            return new Deadline(name, p.parseFileString(date));
        }
        return null;
    }
}
