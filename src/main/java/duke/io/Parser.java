package duke.io;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import duke.DukeException;
import duke.types.Deadline;
import duke.types.Event;
import duke.types.Task;
import duke.types.Todo;
/**
 * Parses given string to operate on.
 *
 * @author Aaron Tan
 */
public class Parser {

    private TaskList tasks;

    /**
     * Processes String s to be operated on.
     *
     * @param s String to be processed.
     * @param tasks Task list to be operated on.
     */
    public String process(String s, TaskList tasks) {
        this.tasks = tasks;
        String[] words = s.split(" ");
        String command = words[0];
        switch (command) {
        case "list":
            return outputList();
        case "done":
            return markItemDone(s);
        case "unmark":
            return markItemUndone(s);
        case "todo":
            return insertTodo(s);
        case "deadline":
            return insertDeadline(s);
        case "event":
            return insertEvent(s);
        case "delete":
            return deleteTask(s);
        case "find":
            return find(s);
        case "bye":
            return bye();
        case "remind":
            return reminder();
        default:
            return "sorry, I don't understand you";
        }
    }

    /**
     * Prints out tasks in list in format specified in each tasks' toString function.
     */
    protected String outputList() {
        if (tasks.size() == 0) {
            return "you got no tasks";
        } else {
            assert tasks.size() > 0 : "TaskList size should be positive";
            StringBuilder sb = new StringBuilder();
            sb.append("heres your tasks\n");
            for (int i = 0; i < tasks.size(); i++) {
                String temp = String.format("\t%d.%s\n", i + 1, tasks.get(i));
                sb.append(temp);
            }
            return sb.toString();
        }
    }

    /**
     * Converts a string to a Todo and inserts into task list.
     *
     * @param input String to be converted to a Todo.
     */
    protected String insertTodo(String input) {
        try {
            String description = input.substring(5);
            return insertTask(new Todo(description, false));
        } catch (StringIndexOutOfBoundsException e) {
            return "description cannot be empty";
        }
    }

    /**
     * Converts a string to a Deadline and inserts into task list.
     *
     * @param input String to be converted to a Deadline.
     */
    protected String insertDeadline(String input) {
        try {
            String[] items = input.substring(9).split(" /by ");
            return insertTask(new Deadline(items[0], false, items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            return "description cannot be empty";
        } catch (DukeException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("please enter a valid date format.");
            sb.append("date: dd/mm/YYYY");

            return sb.toString();
        }
    }

    /**
     * Converts a string to an Event and inserts into task list.
     *
     * @param input String to be converted to an Event.
     */
    protected String insertEvent(String input) {
        try {
            String[] items = input.substring(6).split(" /at ");
            return insertTask(new Event(items[0], false, items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            return "description cannot be empty";
        } catch (DukeException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("please enter a valid date format.");
            sb.append("date and time: dd/mm/YYYY hh:mm");

            return sb.toString();
        }
    }

    /**
     * Inserts task into given task list.
     *
     * @param task Task to be inserted into the list.
     */
    protected String insertTask(Task task) {
        tasks.add(task);
        StringBuilder sb = new StringBuilder();
        sb.append("added: \n");
        sb.append("\t" + task + "\n");
        sb.append(String.format("you have %d task(s) in the list\n", tasks.size()));

        return sb.toString();
    }

    /**
     * Parses String in the form of mark x, marking index x in the list as done.
     *
     * @param input String that contains information about item to be marked done.
     */
    protected String markItemDone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException("too many parameters!");
            }
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markDone();
            StringBuilder sb = new StringBuilder();
            sb.append("cool, this task is marked as done\n");
            sb.append("\t" + tasks.get(index - 1));

            return sb.toString();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            return "format: mark <number>";
        } catch (IndexOutOfBoundsException e) {
            return "enter a valid index";
        }
    }

    /**
     * Parses String in the form of mark x, marking index x in the list as not done.
     *
     * @param input String that contains information about item to be marked not done.
     */
    protected String markItemUndone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException("too many parameters!");
            }
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markUndone();
            StringBuilder sb = new StringBuilder();
            sb.append("ok, this task is marked as not done yet\n");
            sb.append("\t" + tasks.get(index - 1));

            return sb.toString();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            return "format: mark <number>";
        } catch (IndexOutOfBoundsException e) {
            return "enter a valid index";
        }
    }

    /**
     * Parses String in the form of mark x, deleting task at index x in the list.
     *
     * @param input String that contains information about item to be deleted.
     */
    protected String deleteTask(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException("too many parameters!");
            }
            int index = Integer.parseInt(words[1]);
            tasks.remove(index - 1);
            return "ok, i removed this task";
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            return "format: delete <number>";
        } catch (IndexOutOfBoundsException e) {
            return "enter a valid index";
        }
    }

    /**
     * Finds all tasks that contain String s and returns them.
     *
     * @param s String to be matched
     * @return All tasks that match String s
     */
    protected String find(String s) {
        try {
            String toFind = s.substring(5);
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String description = task.getDescription();
                if (description.contains(toFind)) {
                    matchingTasks.add(task);
                }
            }

            if (matchingTasks.isEmpty()) {
                return "\tyou have no matching tasks!";
            } else {
                StringBuilder sb = new StringBuilder();
                int count = 1;
                sb.append("\there are your matching tasks:\n");
                for (Task task : matchingTasks) {
                    sb.append(String.format("\t%d. %s\n", count, task));
                    count++;
                }

                return sb.toString();
            }
        } catch (IndexOutOfBoundsException e) {
            return "invalid format! try find item";
        }
    }

    /**
     * Exits the program.
     *
     * @return bye to signal exit of program
     */
    protected String bye() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1000L);
        return "bye!";
    }

    /**
     * Gives user reminders of tasks with due dates.
     *
     * @return Returns String filled with reminders
     */
    protected String reminder() {
        ArrayList<Task> reminders = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String type = task.getClass().getName();
            if (type.equals("duke.types.Deadline") || type.equals("duke.types.Event")) {
                reminders.add(task);
            }
            System.out.println(task.getClass());
        }

        if (reminders.isEmpty()) {
            return "\tyou have no reminders!";
        } else {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            sb.append("\there are your reminders:\n");
            for (Task task : reminders) {
                sb.append(String.format("\t%d. %s\n", count, task));
                count++;
            }
            return sb.toString();
        }
    }
}
