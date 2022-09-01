package duke.main;

/**
 * Represents the UI that is used for the interaction with the user
 */
public class Ui {

    /**
     * greet method that Sends a greeting to the user
     *
     */
    public static String greet() {
        return "Hello! I'm BotChat123 \nWhat can I do for you?"
                + "\n You can use list to see everything in the list."
                + "\n\ntodo <todo> to add a todo task in the list"
                + "\n\nevent <event> at <time> to add a event in the list"
                + "\n\ndeadline <deadline> by <time> to add a deadline in the list"
                + "\n\nwhere time is in YYYY-MM-DD format."
                + "\n\nmark <num> to mark a task as done"
                + "\n\nunmark <num> to mark a task as not done"
                + "\n\nfind <taskname> to find a task"
                + "\n\nbye to quit the bot!";
    }

    /**
     * bye method that is sent on termination of conversation with the user
     *
     */
    public static String bye() {
        return "Bye. Please chat with me again!";
    }

    /**
     * list method that lists out the tasks in task list
     *
     * @param taskList
     */
    public static String list(TaskList taskList) {
        String output = "";
        for (int i = 0; i < taskList.length(); i++) {
            System.out.println(i + 1 + ": " + taskList.getTask(i));
            output += i + 1 + ": " + taskList.getTask(i) + "\n";
        }
        return output;
    }
}
