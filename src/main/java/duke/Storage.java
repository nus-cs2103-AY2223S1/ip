package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class handles loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /** The file where tasks are loaded from and saved to. */
    protected File file;

    /**
     * The class constructor for Storage.
     *
     * @param filePath where tasks are saved to and loaded from.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Re-initializes a todo object from its string representation
     * in the saved file.
     *
     * @param input String of the task that was parsed.
     */
    private void loadTodo(String input, boolean isDone, TaskList list) {
        String[] taskType = input.split(" ", 2);
        Todo todo = new Todo(taskType[1]);
        if (isDone) {
            todo.loadDone();
        }
        list.add(todo);
    }

    /**
     * Re-initializes a deadline object from its string representation
     * in the saved file. Exception is never thrown as all deadline tasks that
     * is read from the file has been properly formatted.
     *
     * @param input String of the task that was parsed.
     */
    private void loadDeadline(String input, boolean isDone, TaskList list) {
        String[] taskType = input.split(" ", 2);
        String[] taskBy = taskType[1].split("/by ", 2);
        try {
            Deadline deadline = new Deadline(taskBy[0], taskBy[1]);
            if (isDone) {
                deadline.loadDone();
            }
            list.add(deadline);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Re-initializes an Event object from its string representation
     * in the saved file.
     *
     * @param input String of the task that was parsed.
     */
    private void loadEvent(String input, boolean isDone, TaskList list) {
        String[] taskType = input.split(" ", 2);
        String[] taskBy = taskType[1].split("/at ", 2);
        Event event = new Event(taskBy[0], taskBy[1]);
        if (isDone) {
            event.loadDone();
        }
        list.add(event);
    }

    /**
     * Reads all tasks in file and adds them to the TaskList. If no previous
     * file has been found, it returns the TaskList provided in param.
     *
     * @param list an empty TaskList for saved tasks to be re-written to.
     * @return TaskList with all saved tasks added to.
     */
    public TaskList loadFile(TaskList list) {
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String[] task = s.nextLine().split(" \\| ", -1);
                switch (task[0]) {
                case "T": // create event task
                    String taskString = String.format("todo %s", task[2]);
                    loadTodo(taskString, isLoadedTaskDone(task[1]), list);
                    break;
                case "D": // create deadline task
                    String deadlineString = String.format("deadline %s/by %s", task[2], task[3]);
                    loadDeadline(deadlineString, isLoadedTaskDone(task[1]), list);
                    break;
                case "E": // create event task
                    String eventString = String.format("event %s/at %s", task[2], task[3]);
                    loadEvent(eventString, isLoadedTaskDone(task[1]), list);
                    break;
                default:
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            return list;
        }
    }

    private boolean isLoadedTaskDone(String input) {
        return input.trim().equals("X");
    }

    /**
     * Writes all events on the TaskList to a text file. The directory and file
     * is created if it does not yet exist.
     *
     * @param filePath where tasks are saved to and loaded from.
     * @param inputList the TaskList where all tasks are to be saved.
     */
    public static void writeToFile(String filePath, TaskList inputList) {
        try {
            createDirectory(filePath);
            FileWriter fw = createFileWriter(filePath);
            Task[] taskArray = inputList.taskListToArray();
            for (int i = 0; i < taskArray.length; i++) {
                String fileTextInput = taskArray[i].formatFileText();
                fw.write(fileTextInput);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectory(String filePath) {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private static FileWriter createFileWriter(String filePath) throws IOException {
        File textFile = new File(filePath + "/tasks.txt");
        textFile.createNewFile();
        return new FileWriter(textFile);
    }
}
