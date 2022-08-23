package duke;

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
    protected void process(String s, TaskList tasks) {
        this.tasks = tasks;
        String[] words = s.split(" ");
        String command = words[0];
        switch (command) {
        case "list":
            outputList();
            break;
        case "done":
            markItemDone(s);
            break;
        case "unmark":
            markItemUndone(s);
            break;
        case "todo":
            insertTodo(s);
            break;
        case "deadline":
            insertDeadline(s);
            break;
        case "event":
            insertEvent(s);
            break;
        case "delete":
            deleteTask(s);
            break;
        default:
            System.out.println("sorry, I don't understand you");
            break;
        }
    }

    /**
     * Prints out tasks in list in format specified in each tasks' toString function.
     */
    protected void outputList() {
        if (tasks.size() == 0) {
            System.out.println("you got no tasks");
        } else {
            System.out.println("heres your tasks");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.format("\t%d.%s\n", i + 1, tasks.get(i));
            }
        }
    }

    /**
     * Converts a string to a Todo and inserts into task list.
     *
     * @param input String to be converted to a Todo.
     */
    protected void insertTodo(String input) {
        try {
            String description = input.substring(5);
            insertTask(new Todo(description, false));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        }
    }

    /**
     * Converts a string to a Deadline and inserts into task list.
     *
     * @param input String to be converted to a Deadline.
     */
    protected void insertDeadline(String input) {
        try {
            String[] items = input.substring(9).split(" /by ");
            insertTask(new Deadline(items[0], false, items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        } catch (DukeException e) {
            System.out.println("please enter a valid date format.");
            System.out.println("date: dd/mm/YYYY");
        }
    }

    /**
     * Converts a string to an Event and inserts into task list.
     *
     * @param input String to be converted to an Event.
     */
    protected void insertEvent(String input) {
        try {
            String[] items = input.substring(6).split(" /at ");
            insertTask(new Event(items[0], false, items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        } catch (DukeException e) {
            System.out.println("please enter a valid date format.");
            System.out.println("date and time: dd/mm/YYYY hh:mm");
        }
    }

    /**
     * Inserts task into given task list.
     *
     * @param task Task to be inserted into the list.
     */
    protected void insertTask(Task task) {
        tasks.add(task);
        System.out.println("added: ");
        System.out.println("\t" + task);
        System.out.format("you have %d task(s) in the list\n", tasks.size());
    }

    /**
     * Parses String in the form of mark x, marking index x in the list as done.
     *
     * @param input String that contains information about item to be marked done.
     */
    protected void markItemDone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException("too many parameters!");
            }
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markDone();
            System.out.println("cool, this task is marked as done");
            System.out.println("\t" + tasks.get(index - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }

    /**
     * Parses String in the form of mark x, marking index x in the list as not done.
     *
     * @param input String that contains information about item to be marked not done.
     */
    protected void markItemUndone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException("too many parameters!");
            }
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markUndone();
            System.out.println("ok, this task is marked as not done yet");
            System.out.println("\t" + tasks.get(index - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }

    /**
     * Parses String in the form of mark x, deleting task at index x in the list.
     *
     * @param input String that contains information about item to be deleted.
     */
    protected void deleteTask(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException("too many parameters!");
            }
            int index = Integer.parseInt(words[1]);
            tasks.remove(index - 1);
            System.out.println("ok, i removed this task");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }
}
