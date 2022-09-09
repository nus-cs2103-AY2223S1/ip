package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * class deals with an array of Tasks.
 */
public class TaskList {

    /**
     * Array to store Tasks.
     */
    private static final ArrayList<Task> taskArray = new ArrayList<>();

    /**
     * Constructor for class.
     *
     * @param data file object of data
     * @throws IOException file may not be found
     */
    public TaskList(File data) throws IOException {
        if (!data.createNewFile()) {
            createObject(data);
        }
    }

    /**
     * Getter for TaskArray.
     *
     * @return taskArray
     */
    public ArrayList<Task> getTaskArray() {
        return taskArray;
    }

    /**
     * Getter for item in array.
     *
     * @param index of item
     * @return Task object
     */
    public Task getItem(int index) {
        return taskArray.get(index - 1);
    }

    /**
     * creates a Duke.Task object from given File data.
     *
     * @param data file data to read from.
     */
    public void createObject(File data) throws FileNotFoundException {
        Scanner s = new Scanner(data);
        while (s.hasNextLine()) {
            String entry = s.nextLine();
            String[] inputs = entry.split("\\|", 4);
            for (int i = 0; i < inputs.length; i++) {
                inputs[i] = inputs[i].trim();
            }
            boolean completed = Objects.equals(inputs[1], "1");
            if (Objects.equals(inputs[0], "T")) {
                ToDo newToDo = new ToDo(inputs[2], true, completed, getSize());
                taskArray.add(newToDo);
            } else {
                if (Objects.equals(inputs[0], "D")) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime formattedDate = LocalDateTime.parse(inputs[3], format);
                    Deadlines newDeadline = new Deadlines(inputs[2], true, completed, formattedDate, getSize());
                    taskArray.add(newDeadline);
                } else if (Objects.equals(inputs[0], "E")) {
                    Events newEvent = new Events(inputs[2], true, completed, inputs[3], getSize());
                    taskArray.add(newEvent);
                }
            }
        }

    }

    /**
     * Gets the total size of array.
     *
     * @return int size
     */
    public int getSize() {
        return taskArray.size();
    }

    /**
     * markTasks applies the required action on the correct task ID.
     *
     * @param action to indicate mark/unmark
     * @param index  to indicate which task to apply action to
     */
    public String markTasks(String action, int index) {
        if (index > taskArray.size() || index < 1) {
            return "Invalid task ID!";
        } else if (Objects.equals(action, "mark")) {
            return taskArray.get(index - 1).mark();
        } else {
            return taskArray.get(index - 1).unMark();
        }
    }

    /**
     * removes task from array, and removes entry from data file.
     *
     * @param deletable id of the task to be deleted.
     */
    public void delete(Task deletable) {
        taskArray.remove(deletable);
    }

    /**
     * createTask handles task of child type Duke.ToDo, Duke.Deadlines and Duke.Events.
     *
     * @param input user input.
     * @throws ArrayIndexOutOfBoundsException       used to handle invalid inputs.
     * @throws DukeException.EmptyTaskException     Thrown when todo task is empty.
     * @throws DukeException.UnkownCommandException Thrown when command is unknown.
     */
    public Task createTask(String input, boolean init)
            throws DukeException.EmptyTaskException, DukeException.UnkownCommandException,
            DukeException.InvalidParameterException {

        String[] commands = input.split("/", 2);
        String[] inputArr = commands[0].split(" ", 2);

        if (Objects.equals(inputArr[0], "todo")) {
            try {
                if (!(inputArr[1].trim().length() > 0)) {
                    throw new DukeException.EmptyTaskException("OOPS! please include a name for your task!");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException.EmptyTaskException("OOPS! please include a name for your task!");
            }
            ToDo newToDo = new ToDo(inputArr[1], init, false, getSize());
            taskArray.add(newToDo);
            return newToDo;
        } else {
            if (Objects.equals(inputArr[0], "deadline")
                    || Objects.equals(inputArr[0], "event")) {
                try {
                    String[] date = commands[1].split(" ", 2);
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime formattedDate = LocalDateTime.parse(date[1], format);

                    if (Objects.equals(inputArr[0], "deadline")) {
                        if (Objects.equals(date[0].toLowerCase(), "by")) {
                            Deadlines newDeadline = new Deadlines(inputArr[1], init, false, formattedDate, getSize());
                            taskArray.add(newDeadline);
                            return newDeadline;
                        } else {
                            throw new DukeException.InvalidParameterException("Include '/by' followed by a date after!");

                        }
                    } else {
                        if (Objects.equals(date[0].toLowerCase(), "at")) {
                            Events newEvent = new Events(inputArr[1], init, false, date[1], getSize());
                            taskArray.add(newEvent);
                            return newEvent;
                        } else {
                            throw new DukeException.InvalidParameterException("Include '/at' followed by a date after!");
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException.InvalidParameterException("deadline/event requires date as a third parameter after /by or /at respectively!");
                } catch (DateTimeParseException e) {
                    throw new DukeException.InvalidParameterException("Please input in date time format 'yyyy-MM-dd HH:mm'");
                }
            } else if (Objects.equals(inputArr[0], "fixedtask")) {
                try {
                    String[] duration = commands[1].split(" ", 2);
                    if (Objects.equals(duration[0].toLowerCase(), "for")) {
                        FixedDurationTask newFixedTask = new FixedDurationTask(inputArr[1], init, false, duration[1], getSize());
                        taskArray.add(newFixedTask);
                        return newFixedTask;
                    } else {
                        throw new DukeException.InvalidParameterException("Include /for after fixedtask");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException.InvalidParameterException("fixedtask requires a third parameter after /for");
                }

            } else {
                throw new DukeException.UnkownCommandException("OOPS! Indicate todo/deadline/event before a task");
            }
        }
    }
}