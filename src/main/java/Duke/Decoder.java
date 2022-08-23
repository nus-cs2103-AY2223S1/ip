package Duke;

import Duke.DukeExceptions.BadFormatException;
import Duke.DukeExceptions.BadTaskOperationException;
import Duke.DukeExceptions.DukeException;
import Duke.DukeExceptions.EmptyDescException;
import Duke.DukeTasks.Deadline;
import Duke.DukeTasks.Event;
import Duke.DukeTasks.Task;
import Duke.DukeTasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Decoder {

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

    public static LocalDate parseLD(String str) throws BadFormatException{
        try {
            String[] splitted = str.split(" ");
            return LocalDate.parse(splitted[splitted.length - 1].stripLeading());
        } catch (DateTimeParseException e) {
            throw new BadFormatException("date time error", "Date", "Date: <YYYY-MM-DD>");
        }
    }

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
