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
     * @return Todo object.
     */
    private Todo loadTodo(String input) {
        String[] taskType = input.split(" ", 2);
        Todo todo = new Todo(taskType[1]);
        return todo;
    }

    /**
     * Re-initializes a deadline object from its string representation
     * in the saved file. Exception is never thrown as all deadline tasks that
     * is read from the file has been properly formatted.
     *
     * @param input String of the task that was parsed.
     * @return Deadline object.
     */
    private Deadline loadDeadline(String input) {
        String[] taskType = input.split(" ", 2);
        String[] taskBy = taskType[1].split("/by ", 2);
        try {
            Deadline deadline = new Deadline(taskBy[0], taskBy[1]);
            return deadline;
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Re-initializes an Event object from its string representation
     * in the saved file.
     *
     * @param input String of the task that was parsed.
     * @return Event object.
     */
    private Event loadEvent(String input) {
        String[] taskType = input.split(" ", 2);
        String[] taskBy = taskType[1].split("/at ", 2);
        Event event = new Event(taskBy[0], taskBy[1]);
        return event;
    }

    /**
     * Reads all tasks in file and adds them to the TaskList. If no previous
     * file has been found, it returns the tasklist provided in param.
     *
     * @param list an empty tasklist for saved tasks to be re-written to.
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
                    Todo newTodo = loadTodo(taskString);
                    if (task[1].trim().equals("X")) {
                        newTodo.loadDone();
                    }
                    list.add(newTodo);
                    break;
                case "D": // create deadline task
                    String deadlineString = String.format("deadline %s/by %s", task[2], task[3]);
                    Deadline newDeadline = loadDeadline(deadlineString);
                    if (task[1].trim().equals("X")) {
                        newDeadline.loadDone();
                    }
                    list.add(newDeadline);
                    break;
                case "E": // create event task
                    String eventString = String.format("event %s/at %s", task[2], task[3]);
                    Event newEvent = loadEvent(eventString);
                    if (task[1].trim().equals("X")) {
                        newEvent.loadDone();
                    }
                    list.add(newEvent);
                    break;
                default:
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            return list;
        }
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
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File textFile = new File(filePath + "/tasks.txt");
            textFile.createNewFile();
            FileWriter fw = new FileWriter(textFile);

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
}
