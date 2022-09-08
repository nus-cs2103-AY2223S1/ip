package qoobee;

/**
 * Represents a parser that executes commands based on user's input.
 */
public class Parser {

    protected Ui ui;
    protected TaskList tasks;

    /**
     * Creates a parser given a ui and taskList.
     * @param ui The interface that user interacts with.
     * @param tasklist The list of tasks of the user.
     */
    public Parser(Ui ui, TaskList tasklist) {
        this.ui = ui;
        this.tasks = tasklist;
    }

    /**
     * Executes a specific action given a user's input.
     * @param input The user's input.
     */
    public String parse(String input) {
        assert input.length() > 0: "Input cannot be empty";
        try {
            if (input.equals("bye")) {
                this.ui.exit();
                return "Bye. Don't miss me too much!";
            } else if (input.equals("list")) {
                return tasks.printTasks();
            } else {
                String[] command = input.split(" ", 2);
                assert command[1].length() > 0: "Description cannot be empty";
                if (input.startsWith("mark")) {
                    try {
                        Task task = tasks.getTask(Integer.parseInt(command[1]) - 1);
                        return tasks.mark(task);
                    } catch (IndexOutOfBoundsException | NumberFormatException | QoobeeException e) {
                        throw new QoobeeException("Please enter a right number!");
                    }
                } else if (input.startsWith("unmark")) {
                    try {
                        Task task = tasks.getTask(Integer.parseInt(command[1]) - 1);
                        return tasks.unmark(task);
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        throw new QoobeeException("Please enter a right number!");
                    }
                } else if (input.startsWith("todo")) {
                    if (command[1].isBlank()) {
                        throw new QoobeeException("The description of a todo cannot be empty :^(");
                    }
                    Task todo = new ToDo(command[1]);
                    return tasks.addTask(todo);
                } else if (input.startsWith("deadline")) {
                    if (command[1].isBlank()) {
                        throw new QoobeeException("The description of a deadline cannot be empty :^(");
                    }
                    if (!command[1].contains("/by")) {
                        throw new QoobeeException("Please use /by to specify a deadline :]");
                    }
                    String[] curr = command[1].split("/by ", 2);
                    Task deadline = new Deadline(curr[0], curr[1]);
                    return tasks.addTask(deadline);
                } else if (input.startsWith("event")) {
                    if (command[1].isBlank()) {
                        throw new QoobeeException("The description of a event cannot be empty :^(");
                    }
                    if (!command[1].contains("/at")) {
                        throw new QoobeeException("Please use /at to specify a deadline :]");
                    }
                    String[] curr = command[1].split("/at", 2);
                    Task event = new Event(curr[0], curr[1]);
                    return tasks.addTask(event);
                } else if (input.startsWith("delete")) {
                    try {
                        int index = Integer.parseInt(command[1]) - 1;
                        return tasks.removeTask(index);
                    } catch (NumberFormatException e) {
                        throw new QoobeeException("Please enter a right number!");
                    }
                } else if (input.startsWith("find")) {
                    return tasks.findTask(command[1]);
                } else {
                    return "I'm sorry, but I don't know what that means :^(";
                }
            }
        } catch (QoobeeException e) {
            System.out.println(e);
        }
        return "I'm sorry, but I don't know what that means :^(";
    }



}
