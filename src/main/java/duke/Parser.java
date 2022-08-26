package duke;

/**
 * A class that handles user inputs for the Duke chat-bot
 */
public class Parser {

    /**
     * Constructor for the Parser class
     */
    public Parser() {
    }

    /**
     * Processes the user input and does a corresponding action
     * @param response The user input
     * @param tasks The TaskList object that contains the tasks currently stored in the Duke chat-bot
     */
    public void parse(String response, TaskList tasks) {
        if (response.equals("list")) {
            System.out.println("     The following are your saved tasks: ");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                Task t = tasks.getTasks().get(i);
                System.out.println("       "
                        + (i + 1)
                        + ". "
                        + t.toString());
            }
        } else if (response.length() > 4 && response.substring(0, 4).equals("mark")) {
            int taskNumber = Integer.parseInt(response.substring(5, 6)) - 1;
            Task t = tasks.getTasks().get(taskNumber);
            t.markAsDone();
        } else if (response.length() > 6 && response.substring(0, 6).equals("unmark")) {
            int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
            Task t = tasks.getTasks().get(taskNumber);
            t.markAsUnDone();
        } else if (response.length() > 3 && response.substring(0, 4).equals("todo")) {
            if (response.length() <= 5) {
                System.out.println("     Please add a task after 'todo'!");
            } else {
                Task newTodo = new Todo(response.substring(5));
                tasks.addTask(newTodo);
            }
        } else if (response.length() > 4 && response.substring(0, 5).equals("event")) {
            if (response.length() <= 6) {
                System.out.println("     Please add a task after 'event'!");
            } else {
                int separatorPosition = response.indexOf("/");
                Task newEvent = new Event(
                        response.substring(6, separatorPosition - 1),
                        response.substring(separatorPosition + 4));
                tasks.addTask(newEvent);
            }
        } else if (response.length() > 7 && response.substring(0, 8).equals("deadline")) {
            if (response.length() <= 9) {
                System.out.println("     Please add a task after 'deadline'!");
            } else {
                int separatorPosition = response.indexOf("/");
                Task newDeadline = new Deadline(
                        response.substring(9, separatorPosition - 1),
                        response.substring(separatorPosition + 4));
                tasks.addTask(newDeadline);
            }
        } else if (response.length() > 5 && response.substring(0, 6).equals("delete")) {
            int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
            tasks.deleteTask(taskNumber);
        } else if (response.equals("bye")) {
            ;
        } else {
            System.out.println("     Please specify one of the 3 commands before your task to add a task:\n"
                    + "       todo\n"
                    + "       event\n"
                    + "       deadline\n");
        }
    }
}
