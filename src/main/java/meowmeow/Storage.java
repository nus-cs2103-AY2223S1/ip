package meowmeow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import meowmeow.events.Deadline;
import meowmeow.events.Event;
import meowmeow.events.Task;
import meowmeow.events.ToDo;

/**
 * Represents a storage object that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private static File saveFile;
    private String filePath = "./data/meowmeow.txt";
    private ArrayList<Task> taskList = new ArrayList<>(100);

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the save file exists.
     * @throws IOException if the file does not exist.
     */
    public void checkFileExists() throws IOException {
        //Create save file
        try {
            saveFile = new File(filePath);
            File parent = saveFile.getParentFile();

            if (!parent.exists()) {
                parent.mkdirs();
            }

            saveFile.createNewFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current task list into the save file.
     * @param taskList Task list to be saved.
     */
    public void save(ArrayList taskList) {
        this.taskList = taskList;
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(saveFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter finalPrintWriter = printWriter;

        taskList.forEach((t) -> {
            Task k = (Task) t;
            finalPrintWriter.println(k.getSaveData());
        });
        printWriter.close();
    }

    /**
     * Loads task list from the save file.
     * @param txt File to be loaded
     * @return ArrayList of tasks
     */
    public ArrayList<Task> parseSaveFile(File txt) {
        try {
            txt.getAbsolutePath();
            txt.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc;
        try {
            sc = new Scanner(txt);
            while (sc.hasNextLine()) {
                String text = sc.nextLine();

                String[] split = text.split(" \\| ");
                assert split.length > 0 : "Save file is empty";
                String firstChar = split[0];
                assert firstChar == "T" || firstChar == "D" || firstChar == "E" : "Save file is corrupted";

                switch (firstChar) {
                case "T":
                    addTodo(split);
                    break;

                case "D":
                    addDeadline(split);
                    break;

                case "E":
                    addEvent(split);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return taskList;
    }

    /**
     * Adds a todo task to the task list.
     * @param split String array of todo task
     */
    public void addTodo(String[] split) {
        String taskName = split[2];

        Task todo = new ToDo(taskName);
        taskList.add(todo);

        boolean isDone = Boolean.parseBoolean(split[1]);
        if (isDone) {
            todo.markAsDone();
        }
    }

    /**
     * Adds a deadline task to the task list.
     * @param split String array of deadline task
     */
    public void addDeadline(String[] split) {
        String taskName = split[2];

        LocalDateTime date = LocalDateTime.parse(split[3]);

        Task deadline = new Deadline(taskName, date);
        taskList.add(deadline);

        boolean isDone = Boolean.parseBoolean(split[1]);
        if (isDone) {
            deadline.markAsDone();
        }
    }

    /**
     * Adds an event task to the task list.
     * @param split String array of event task
     */
    public void addEvent(String[] split) {
        String taskName = split[2];

        String time = split[3];

        Task event = new Event(taskName, time);
        taskList.add(event);

        boolean isDone = Boolean.parseBoolean(split[1]);
        if (isDone) {
            event.markAsDone();
        }
    }

    /**
     * Checks if the save file exists and loads the task list from the save file if it exists.
     * @return ArrayList of tasks
     */
    public ArrayList<Task> load() {
        try {
            checkFileExists();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseSaveFile(saveFile);
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

}
