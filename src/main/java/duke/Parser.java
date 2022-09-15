package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * Class for parsing user inputs to be executed
 */
public class Parser {
    /**
     * Parses user input and returns true if program is to be exited
     * @param input user input
     * @param lst Tasklist of Duke program
     * @param ui user interface
     * @param storage storage for data
     * @return a boolean to decide if program is to be exited
     * @throws DukeException
     */
    public static String parse(String input, TaskList lst, Ui ui, Storage storage) throws DukeException {

        String[] words = input.split(" ", 2);
        String first = words[0];

        try {
            if (first.equals("bye")) {
                return ui.sayBye();

            } else if (first.equals("list")) {
                return parseList(lst);

            } else if (first.equals("mark")) {
                return parseMark(input, lst, storage);

            } else if (first.equals("unmark")) {
                return parseUnmark(input, lst, storage);

            } else if (first.equals("deadline") || first.equals("event") || first.equals("todo")) {
                if (words.length == 1) {
                    throw new DukeException("The description of a " + first + " cannot be empty.");
                }
                String s = words[1];
                if (first.equals("deadline")) {
                    return parseDeadline(s, lst, storage);
                } else if (first.equals("event")) {
                    return parseEvent(s, lst, storage);
                } else if (first.equals("todo")) {
                    return parseToDo(words[1], lst, storage);
                }
            } else if (first.equals("delete")) {
                return parseDelete(words, lst, storage);

            } else if (first.equals("find")) {
                return parseFind(words, lst);

            } else if (first.equals("all")) {
                return parseAll(words, lst);

            } else {
                throw new DukeException("I'm sorry, but I don't know what that means");
            }
        } catch (DukeException d) {
            return(d.getMessage());
        }
        return "";
    }

    /**
     * Converts date in String format to LocalDate object.
     *
     * @param date Date input by user.
     * @return LocalDate object converted from date input
     * @throws DukeException
     */
    public static LocalDate dateStringToLocalDate(String date) throws DukeException {
        LocalDate d;
        try {
            d = LocalDate.parse(date);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Please provide a date in the format yyyy-mm-dd.");
        }
        return d;
    }

    public static String parseList(TaskList lst) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < lst.size(); i++) {
            result += (i + 1) + "." + lst.get(i).formatTask() + "\n";
        }
        return result;
    }

    public static String parseMark(String input, TaskList lst, Storage storage) {
        char c = input.charAt(5);
        int index = Integer.parseInt(String.valueOf(c));
        assert index >= 0 && index < lst.size() + 1: "Task index should be from 1 to length of list";
        return (lst.markTask(index - 1, storage));
    }

    public static String parseUnmark (String input, TaskList lst, Storage storage) {
        char c = input.charAt(7);
        int index = Integer.parseInt(String.valueOf(c));
        assert index >= 0 && index < lst.size() + 1: "Task index should be from 1 to length of list";
        return(lst.unmarkTask(index - 1, storage));
    }

    public static String parseDeadline(String s, TaskList lst, Storage storage) throws DukeException {
        assert s.contains("/by") : "Please enter task in the format <desc> /by <date>";
        String[] arr = s.split("/by");
        String desc = arr[0];
        String date = arr[1].strip();
        LocalDate d = dateStringToLocalDate(date);
        Task t = new Deadline(desc, d);
        lst.addNewTask(t, storage);
        return ("Got it. I've added this duke.task: \n" + t.formatTask() + "\nNow you have "
                + lst.size() + " tasks in the list.");
    }

    public static String parseEvent(String s, TaskList lst, Storage storage) throws DukeException {
        assert s.contains("/at") : "Please enter task in the format <desc> /at <date>";
        String[] arr = s.split("/at");
        String desc = arr[0];
        String date = arr[1].strip();
        LocalDate d = dateStringToLocalDate(date);
        Task t = new Event(desc, d);
        lst.addNewTask(t, storage);
        return ("Got it. I've added this duke.task: \n" + t.formatTask() + "\nNow you have "
                + lst.size() + " tasks in the list.");

    }

    public static String parseToDo(String desc, TaskList lst, Storage storage) throws DukeException {
        Task t = new Deadline.ToDo(desc);
        lst.addNewTask(t, storage);
        return ("Got it. I've added this task: \n" + t.formatTask() + "\nNow you have "
                + lst.size() + " tasks in the list.");
    }

    public static String parseDelete(String[] words, TaskList lst, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new DukeException("Please specify task to delete");
        }
        int index = Integer.parseInt(words[1]) - 1;
        assert index >= 0 && index < lst.size() : "Task index should be from 1 to length of list";
        return (lst.deleteTask(index, storage));
    }

    public static String parseFind(String[] words, TaskList lst) throws DukeException {
        assert words.length > 1 : "Please enter a keyword!";
        String toFind = words[1];
        String toReply = "Here are the matching tasks in your list:\n";
        if (toFind.length() == 0) {
            throw new DukeException("Please enter a keyword!");
        }

        ArrayList<String> result = lst.findTasks(toFind);
        if (result.size() == 0) {
            throw new DukeException("No task found!");
        }

        for (int i = 0; i < result.size(); i++) {
            toReply += result.get(i) + "\n";
        }
        return toReply;
    }

    public static String parseAll(String[] words, TaskList lst) throws DukeException {
        String category = words[1].toUpperCase();
        String toReply = "Here are the " + category + "s in your list:\n";
        if (category.length() == 0) {
            throw new DukeException("Please enter a category!");
        }

        ArrayList<String> result = new ArrayList<>();
        switch (category) {
            case ("TODO"):
                result = lst.findTodo();
                break;

            case ("EVENT"):
                result = lst.findEvent();
                break;

            case ("DEADLINE"):
                result = lst.findDeadline();
                break;

            default:
                throw new DukeException("Please enter todo/ event / deadline");
        }

        for (int i = 0; i < result.size(); i++) {
            toReply += result.get(i) + "\n";
        }
        return toReply;
    }


}
