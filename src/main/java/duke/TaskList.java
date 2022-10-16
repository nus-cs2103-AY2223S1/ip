package duke;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    /**
     * Constructs the TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Constructs the TaskList with previous inputs.
     *
     * @param dataList Previous input data.
     */
    public TaskList(ArrayList<String> dataList) {
        this.tasks = new ArrayList<>();
        for (String data : dataList) {
            tasks.add(Task.loadTask(data));
        }
        this.size = tasks.size();
    }

    /**
     * Saves the TaskList after user input bye.
     *
     * @return ArrayList of the input that has been saved.
     */
    public ArrayList<String> bye() {
        return saveTasks();
    }

    /**
     * Saves the TaskList.
     *
     * @return ArrayList of the input that has been saved.
     */
    public ArrayList<String> saveTasks() {
        ArrayList<String> list = new ArrayList<>();
        for (Task task : tasks) {
            list.add(task.saveTask());
        }
        return list;
    }

    /**
     * Marks a task.
     *
     * @param input Input of the user.
     * @return Message for user.
     */
    public String markTask(String input) {
        char n = input.charAt(5);
        int number = Character.getNumericValue(n) - 1;
        assert number >= 0 : "index should be >= 0";
        Task t = tasks.get(number);
        t.mark();
        String line1 = "Nice! I've marked this task as done:";
        String line2 = t.toString();
        String mark = line1 + "\n" + line2;
        return mark;
    }

    /**
     * Unmarks a task.
     *
     * @param input Input of the user.
     * @return Message for user.
     */
    public String unmarkTask(String input) {
        char n = input.charAt(7);
        int number = Character.getNumericValue(n) - 1;
        assert number >= 0 : "index should be >= 0";
        Task t = tasks.get(number);
        t.markAsNotDone();
        String line1 = "OK, I've marked this task as not done yet:";
        String line2 = t.toString();
        String unmark = line1 + "\n" + line2;
        return unmark;
    }

    /**
     * Lists the inputs of the user.
     *
     * @return Message for user.
     */
    public String list() {
        int count = 1;
        String list = "Here are the tasks in your list:" + "\n";
        for (Task task : tasks) {
            String newLine = count + ". " + task.toString() + "\n";
            list += newLine;
            count += 1;
        }
        if (count == 1) {
            String empty = "Your list is empty.";
            return empty;
        } else {
            return list;
        }
    }

    /**
     * Deletes a task.
     *
     * @param input Input of the user.
     * @return Message for user.
     */
    public String delete(String input) {
        char n = input.charAt(7);
        int number = Character.getNumericValue(n) - 1;
        assert number >= 0 : "index should be 0 or larger";
        String line1 = "I've removed this task:";
        String line2 = "  " + tasks.get(number).toString();
        tasks.remove(number);
        size -= 1;
        String line3 = "Now you have " + size + " tasks in the list";
        String message = line1 + "\n" + line2 + "\n" + line3;
        return message;
    }

    /**
     * Returns a message for deadline input.
     *
     * @param in Input of the user.
     * @return Message for user.
     * @throws DukeException An exception unique to duke.Duke.
     */
    public String deadline(String in) throws DukeException {
        String deadLine = in.replaceFirst("deadline", "");
        if (deadLine.trim().isEmpty()) {
            throw new DukeException("Yo! The description of a deadline cannot be empty!");
        } else {
            return addDeadline(deadLine);
        }
    }

    /**
     * Adds a deadline event to the list.
     *
     * @param deadLine Input of the user.
     * @return Message for user.
     */
    public String addDeadline(String deadLine) {
        String[] result = deadLine.split("/by ", 2);
        String desc = result[0];
        String by = result[1];
        Deadline d = new Deadline(desc, LocalDate.parse(by));
        tasks.add(d);
        size += 1;
        String line1 = "Got it. I've added this task:";
        String line2 = "  " + d.toString();
        String line3 = "Now you have " + size + " tasks in the list";
        String message = line1 + "\n" + line2 + "\n" + line3;
        return message;
    }

    /**
     * Returns a message for event input.
     *
     * @param input Input of the user.
     * @return Message for the user.
     * @throws DukeException An exception unique to duke.Duke.
     */
    public String event(String input) throws DukeException {
        String event = input.replaceFirst("event", "");
        if (event.trim().isEmpty()) {
            throw new DukeException("Yo! The description of an event cannot be empty.");
        } else {
            return addEvent(event);
        }
    }

    /**
     * Adds an event task to the list.
     *
     * @param event Input of the user.
     * @return Message for the user.
     */
    public String addEvent(String event) {
        String[] result = event.split("/at ", 2);
        String desc = result[0];
        String by = result[1];
        Event e = new Event(desc, LocalDate.parse(by));
        tasks.add(e);
        size += 1;
        String line1 = "I've added this task:";
        String line2 = "  " + e.toString();
        String line3 = "Now you have " + size + " tasks in the list";
        String message = line1 + "\n" + line2 + "\n" + line3;
        return message;
    }

    /**
     * Adds a to-do task to the list.
     *
     * @param input Input of the user.
     * @return message for user
     * @throws DukeException An exception unique to duke.Duke.
     */
    public String todo(String input) throws DukeException {
        String todo = input.replaceFirst("todo", "");
        if (todo.trim().isEmpty()) {
            throw new DukeException("Yo! The description of a deadline cannot be empty.");
        } else {
            Todo t = new Todo(todo);
            tasks.add(t);
            size += 1;
            String line1 = "Got it. I've added this task:";
            String line2 = "  " + t.toString();
            String line3 = "Now you have " + size + " tasks in the list";
            String message = line1 + "\n" + line2 + "\n" + line3;
            return message;
        }
    }

    /**
     * Finds tasks in list.
     *
     * @param input Input of the user.
     * @return Message for user.
     * @throws DukeException If there is an error in the input.
     */
    public String find(String input) throws DukeException {
        String toBeFound = input.replaceFirst("find", "");
        if (toBeFound.trim().isEmpty()) {
            throw new DukeException("Yo! The description of the task you want cannot be empty.");
        } else {
            assert toBeFound.length() > 0 : "The keywords should not be empty";
            int count = 1;
            String find = "Here is the task that you are looking for in your list:" + "\n";
            for (Task task : tasks) {
                if (task.getDescription().contains(toBeFound.trim())) {
                    String newLine = count + ". " + task.toString() + "\n";
                    count += 1;
                    find += newLine;
                }
            }
            return find;
        }
    }


    public boolean isValidTaskNumber(int input) {
        if (input > 0 && input < tasks.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tags a task in the list.
     *
     * @param input Input of the user.
     * @return Message for the user.
     * @throws DukeException If there is an error in the input.
     */
    public String tag(String input) throws DukeException {
        String tag = input.replaceFirst("tag", "");
        if (tag.trim().isEmpty()) {
            throw new DukeException("Yo! The description of your tag cannot be empty.");
        } else {
            return addTag(tag.trim());
        }
    }

    /**
     * Adds a tag to a task.
     * @param tag Input of user.
     * @return Message for user.
     */
    public String addTag(String tag) {
        char n = tag.charAt(0);
        int number = Character.getNumericValue(n) - 1;
        assert number >= 0 : "Index should be more or equal to 0";
        Task task = tasks.get(number);
        String[] result = tag.split("#", 2);
        String desc = result[1];
        task.tagWith(desc);
        String line1 = "Got it. I've tagged this task:";
        String line2 = "  " + task.toString();
        String line3 = "with #" + desc;
        String message = line1 + "\n" + line2 + "\n" + line3;
        return message;
    }

    /**
     * Lists the tags for a task.
     *
     * @param input Input of user.
     * @return Message for user.
     * @throws DukeException If there is an error in the input.
     */
    public String listTags(String input) throws DukeException {
        char n = input.charAt(8);
        int number = Character.getNumericValue(n) - 1;
        assert number >= 0 : "Index should be more or equal to 0";
        Task task = tasks.get(number);
        return task.listTheTags();
    }
}
