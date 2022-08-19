import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class UI {

    private Scanner sc;
    private List<Task> taskList;

    private static String initGreeting = "Hello! I'm Duke \nWhat can I do for you?";

    public UI() {
        this.sc = new Scanner(System.in);
        this.taskList = new ArrayList<Task>();
    }

    public String greet() {
        return initGreeting;
    }

    /**
     * Returns the current task list if it is not empty, prints an alternative message if otherwise.
     *
     * @return String which content depends on whether the current task list is empty
     */
    public String list() {

        if (this.taskList.isEmpty()) {
            return "No tasks as of now :)";
        } else {
            String temp = "";
            for (int i = 0; i < taskList.size(); i++) {
                int curr = i + 1;
                temp = temp + curr + ": " + taskList.get(i).toString() + "\n";
            }
            return "Here are the tasks in your list:\n"
                   + temp;
        }
    }

    /**
     * Returns a farewell message before Duke closes.
     *
     * @return String containing the farewell message
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Adds the given task to the current task list.
     *
     * @param str String input specifying the type of task and its description.
     * @return String telling the user what task was added and the number of current tasks.
     * @throws DukeInvalidDescriptionException if the description of the given is empty.
     */
    public String addTask(String str) throws DukeInvalidDescriptionException {
        if (str.startsWith("todo")) {
            if (str.length() == 4 || str.length() == 5) {
                throw new DukeInvalidDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            ToDo todo = new ToDo(str.substring(5));
            taskList.add(todo);
            return "Got it. I've added this task:\n" + todo.toString() + "\n"
                    + String.format("There are %d task(s) in the list", taskList.size());
        } else if (str.startsWith("event")) {
            String[] arrOfStrings = str.split("/at", 2);
                if (arrOfStrings[0].length() == 5 || arrOfStrings[0].length() == 6 || !str.contains("/at")) {
                    throw new DukeInvalidDescriptionException("☹ OOPS!!! The description you provided is invalid! " +
                            "Did you add both the description and time/date of this Event?");
                }
                Event event = new Event(arrOfStrings[0], arrOfStrings[1]);
                taskList.add(event);
                return "Got it. I've added this task:\n" + event.toString() + "\n"
                        + String.format("There are %d task(s) in the list", taskList.size());
        } else {
            String[] arrOfStrings = str.split("/by", 2);
                if (arrOfStrings[0].length() == 8 || arrOfStrings[0].length() == 9 || !str.contains("/by")) {
                    throw new DukeInvalidDescriptionException("☹ OOPS!!! The description you provided is invalid! "
                            + "Did you add both the description and due time/date of this deadline?");
                }
                Deadline deadline = new Deadline(arrOfStrings[0], arrOfStrings[1]);
                taskList.add(deadline);
                return "Got it. I've added this task:\n" + deadline.toString() + "\n"
                        + String.format("There are %d task(s) in the list", taskList.size());
        }
    }

    /**
     * Marks the task with the given ID in the current task list as done.
     *
     * @param id the ID of the target task in the current task list.
     * @return String confirming the marking of the specified task.
     * @throws DukeMarkException if the task has already been marked as done.
     */
    public String markTask(int id) throws DukeMarkException {
        Task curr = taskList.get(id - 1);
        if (curr.getIsDone()) {
            throw new DukeMarkException("☹ OOPS!!! can't mark as this task has already been done...");
        }
        curr.mark();
        return "Nice! I've marked this task as done: \n"
                + curr.toString();
    }

    /**
     * Un-marks the task with the given ID in the current task list as done.
     *
     * @param id the ID of the target task in the current task list.
     * @return String confirming the un-marking of the specified task.
     * @throws DukeMarkException if the task has not been marked as done (already un-marked).
     */
    public String unmarkTask(int id) throws DukeMarkException {
        Task curr = taskList.get(id - 1);
        if (!curr.getIsDone()) {
            throw new DukeMarkException("☹ OOPS!!! can't unmark as this task has not been done yet...");
        }
        curr.unmark();
        return "OK, I've marked this task as not done yet: \n"
                + curr.toString();
    }

    /**
     * Marks or un-marks the specified task depending on the command given by the user.
     *
     * @param str the command/input given by the user
     * @return String confirming the marking or un-marking of the specified task.
     * @throws DukeMarkException if the task has not been marked as done (already un-marked).
     */
    public String editTask(String str) throws DukeMarkException {
        if (str.startsWith("mark")) {
            int taskId = Integer.parseInt(str.substring(5));
            return markTask(taskId);
        } else if (str.startsWith("unmark")) {
            int taskId = Integer.parseInt(str.substring(7));
            return unmarkTask(taskId);
        }
        return "";
    }

    /**
     * Deletes the task, specified in the user input, from the current task list.
     *
     * @param str the command/input given by the user
     * @return String confirming the deletion of the specified task.
     * @throws DukeInvalidDeletionException if the current task list is already empty, or if the specified task does
     * not exist.
     */
    public String deleteTask(String str) throws DukeInvalidDeletionException {
        int targetId = Integer.parseInt(str.substring(7));
        if (taskList.isEmpty()) {
            throw new DukeInvalidDeletionException("OOPS!!! Can't delete tasks from an empty task list :P");
        } else if (targetId > taskList.size() || targetId <= 0) {
            throw new DukeInvalidDeletionException("Can't delete as there is no such task! D:" );
        }
        String removed = taskList.get(targetId - 1).toString();
        taskList.remove(targetId - 1);
        return "Noted. I've removed this task:\n" + removed + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    public String getDukeErrorMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Returns the user's input using the Scanner sc.
     *
     * @return String sentence of user input.
     */
    public String getInput() {
        return sc.nextLine();
    }
}
