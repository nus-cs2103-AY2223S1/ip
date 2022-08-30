package Duke.DukeHandlers;

import Duke.DukeExceptions.BadFormatException;
import Duke.DukeExceptions.BadTaskOperationException;
import Duke.DukeExceptions.DukeException;
import Duke.DukeExceptions.EmptyDescException;
import Duke.DukeTasks.Deadline;
import Duke.DukeTasks.Event;
import Duke.DukeTasks.Task;
import Duke.DukeTasks.Todo;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Decoder {

    /**
     * Creates a Task given the name, date and type of
     * task: Event, Todo or Deadline, indicated by a tag.
     *
     * @param word the name of the task.
     * @param date the date of the task in YYYY-MM-DD format.
     * @param tag A character indicating the type of task.
     * @return a new Task.
     */
    private static Task makeTask(String word, String date, char tag) {
        Task newTask;
        if (tag == 'D') {
            newTask = new Deadline(word, date);
        } else if (tag == 'E') {
            newTask = new Event(word, date);
        } else {
            newTask = new Todo(word);
        }
        return newTask;
    }

    /**
     * Creates a Task given the name, date and type of
     * task: Event, Todo or Deadline, indicated by a tag.
     *
     * @param word the name of the task.
     * @param date the date of the task in YYYY-MM-DD format.
     * @param tag A character indicating the type of task.
     * @param isFinished whether a task is completed
     * @return a new Task.
     */
    private static Task makeTask(String word, String date, char tag, boolean isFinished) {
        Task newTask;
        if (tag == 'D') {
            newTask = new Deadline(word, date);
        } else if (tag == 'E') {
            newTask = new Event(word, date);
        } else {
            newTask = new Todo(word);
        }

        if (isFinished) {
            newTask.markAsDone();
        }
        return newTask;
    }

    /**
     * Returns task depending on user input.
     *
     * @param word the input read from command line.
     * @return a new Task.
     * @throws DukeException if input is not proper.
     */
    public static Task handleTasks(String word) throws DukeException {
        String[] splitted = word.split(" ", 2);
        if (splitted.length < 2) {
            throw new EmptyDescException(splitted[0]);
        }

        if (splitted[0].equals("todo")) {
            return makeTask(splitted[1], null, 'T');
        } else {
            String[] stringAndDate;
            if (splitted[0].equals("deadline")) {
                stringAndDate = splitted[1].split("/by");
            } else {
                stringAndDate = splitted[1].split("/at");
            }

            if (stringAndDate.length < 2) {
                throw new BadFormatException("incorrect format", splitted[0]);
            }

            if (splitted[0].equals("deadline")) {
                parseLD(stringAndDate[1]);
                return makeTask(stringAndDate[0], stringAndDate[1], 'D');
            } else {
                return makeTask(stringAndDate[0], stringAndDate[1], 'E');
            }
        }
    }

    /**
     * Returns a Task in txt form.
     *
     * @param word the string form of a task stored
     *             in tasklist.txt.
     * @return a new Task.
     */
    public static Task parseFromFile(String word) {
        String[] splitted = word.split(",");

        System.out.println(" ");
        if (splitted[0].equals("T")) {
            return makeTask(splitted[2], null, 'T', Boolean.parseBoolean(splitted[1]));
        }
        if (splitted[0].equals("D")) {
            return makeTask(splitted[2], splitted[splitted.length - 1], 'D', Boolean.parseBoolean(splitted[1]));
        }
        if (splitted[0].equals("E")) {
            return makeTask(splitted[2], splitted[splitted.length - 1], 'E', Boolean.parseBoolean(splitted[1]));
        }
        return null;
    }

    /**
     * Returns the integer of the task of be deleted.
     *
     * @param word the name of the task.
     * @param len the total number of tasks.
     * @return an integer indicating task to be deleted.
     * @throws DukeException if input is bad.
     */
    public static int handleDelete(String word, int len) throws DukeException {
        String[] deleteTasks = word.split(" ");

        if (deleteTasks.length != 2) {
            throw new BadTaskOperationException("delete", "delete");
        }
        if (!isValidNum(deleteTasks[1])) {
            throw new BadFormatException("delete", "delete");
        }
        int taskNo = Integer.parseInt(deleteTasks[1]);
        if (taskNo > len) {
            throw new BadTaskOperationException("delete", "delete");
        }
        return taskNo;
    }

    /**
     * Returns the integer of the task of be marked as done.
     *
     * @param word the name of the task.
     * @param len the total number of tasks.
     * @return an integer indicating task to be deleted.
     * @throws DukeException if input is bad.
     */
    public static int handleDone(String word, int len) throws DukeException {
        String[] doneTasks = word.split(" ");
        if (doneTasks.length != 2) {
            throw new BadTaskOperationException("done", "done");
        }
        if (!isValidNum(doneTasks[1])) {
            throw new BadFormatException("done", "done");
        }
        int taskNo = Integer.parseInt(doneTasks[1]);
        if (taskNo > len) {
            throw new BadTaskOperationException("done", "done");
        }
        return taskNo;
    }

    /**
     * Returns the LocalDate form of user's input.
     *
     * @param str user input from command line.
     * @return A LocalDate.
     * @throws BadFormatException if input is bad.
     */
    public static LocalDate parseLD(String str) throws BadFormatException{
        try {
            String[] splitted = str.split(" ");
            return LocalDate.parse(splitted[splitted.length - 1].stripLeading());
        } catch (DateTimeParseException e) {
            throw new BadFormatException("date time error", "Date", "Date: <YYYY-MM-DD>");
        }
    }

    /**
     * Returns a string representing user's
     * search input.
     *
     * @param str user input from command line.
     * @return A String.
     * @throws EmptyDescException if input is bad.
     */
    public static String parseFind(String str) throws EmptyDescException {
        String[] split = str.split(" ");
        if (split.length < 2) {
            throw new EmptyDescException("empty description");
        }
        return split[1];
    }

    /**
     * Returns is number is valid.
     *
     * @param num user input from command line.
     * @return A boolean.
     */
    public static boolean isValidNum(String num) {
        char[] charas = num.toCharArray();
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(charas[i])) {
                return false;
            }
        }
        return true;
    }
}
