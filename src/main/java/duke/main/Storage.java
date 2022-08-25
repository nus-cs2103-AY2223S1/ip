package duke.main;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Handle storing and retrieving of tasks from a file.
 */
public class Storage {
    private final File saveFile;

    /**
     * Constructor for creating new save file.
     * @param filePath Path where the saved file is located.
     * @throws DukeException When error occur creating file
     * @throws IOException When error occur creating file
     */
    public Storage(String filePath) throws DukeException, IOException {
        String [] path = filePath.split("/");
        File directory = new File(path[0]);
        // Check if directory exists
        if (!directory.exists()) {
            // Does not exist, create new directory
            if (!directory.mkdir()) {
                throw new DukeException("Error encountered when creating folder to store save file");
            }
        }

        File savedTasks = new File(filePath);
        // Check if File exists
        if (!savedTasks.exists()) {
            // Does not exist, create new file
            if (!savedTasks.createNewFile()) {
                throw new DukeException("Error encountered when creating new save file");
            }
        }

        this.saveFile = savedTasks;
    }

    /**
     * Retrieve the data from the save file.
     * @return ArrayList of Tasks.
     * @throws FileNotFoundException If save file is missing.
     * @throws DukeException If save file contains unreadable data.
     */
    public ArrayList<Task> loadInTasks() throws FileNotFoundException, DukeException {
        Scanner scanner = new Scanner(this.saveFile);
        ArrayList<Task> tasksArray = new ArrayList<Task>();
        // populate the taskArray
        while (scanner.hasNext()) {
            String cur = scanner.nextLine();
            String[] inputs = cur.split(" ", 3);
            switch (inputs[0]) {
                case "T": {
                    Task newTask = new Todo(inputs[2], (inputs[1].equals("1")));
                    tasksArray.add(newTask);
                    break;
                }
                case "E": {
                    String[] splits = inputs[2].split("/ ", 2);
                    Task newTask = new Event(splits[0], LocalDate.parse(splits[1]), inputs[1].equals("1"));
                    tasksArray.add(newTask);
                    break;
                }
                case "D": {
                    String[] splits = inputs[2].split("/ ", 2);
                    Task newTask = new Deadline(splits[0], LocalDate.parse(splits[1]), inputs[1].equals("1"));
                    tasksArray.add(newTask);
                    break;
                }
                default: {
                    throw new DukeException("Encountered error retrieving data from save file.");
                }
            }
        }
        return tasksArray;
    }

    /**
     * Store Tasks in taskArray in String format.
     * @param tasksArray ArrayList of tasks.
     * @return A String that represents the existing tasks.
     */
    public String convertToString(ArrayList<Task> tasksArray) {
        StringBuilder text = new StringBuilder();
        for (Task curTask : tasksArray) {
            if (curTask instanceof Todo) {
                String toAppend = convertTodo((Todo) curTask);
                text.append(toAppend);
            } else if (curTask instanceof Deadline) {
                String toAppend = convertDeadline((Deadline) curTask);
                text.append(toAppend);
            } else if (curTask instanceof Event) {
                String toAppend = convertEvent((Event) curTask);
                text.append(toAppend);
            }
        }
        return text.toString();
    }

    public String convertTodo(Todo todo) {
        String str = "";
        str += "T ";
        str += todo.isDone() ? "1 " : "0 ";
        str += todo.getDescription() + "\n";
        return str;
    }

    public String convertDeadline(Deadline deadline) {
        String str = "";
        str += "D ";
        str += deadline.isDone() ? "1 " : "0 ";
        str += deadline.getDescription() + "/ ";
        str += deadline.getBy() + "\n";
        return str;
    }

    public String convertEvent(Event event) {
        String str = "";
        str += "E ";
        str += event.isDone() ? "1 " : "0 ";
        str += event.getDescription() + "/ ";
        str += event.getAt() + "\n";
        return str;
    }


    /**
     * Updates the saveFile with the current duke.main.TaskList.
     * @param taskList The existing tasks.
     * @throws IOException If error encountered with FileWriter.
     */
    public void update(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.saveFile);
        String str = convertToString(taskList.getTasks());
        fw.write(str);
        fw.close();
    }
}
