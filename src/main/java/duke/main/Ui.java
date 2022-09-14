package duke.main;

/**
 * Represents the UI that is used for the interaction with the user
 */
public class Ui {

    /**
     * Returns a string greeting to the user
     *
     * @return string greeting
     */
    public static String greet() {
        return "Hello! I'm BotChat123. \nI am glad that you are using me"
                + "Please read my user guide if you are wondering to use me."
                + " \nWhat can I do for you? :)";
    }

    /**
     * Returns a string bye method
     *
     * @return string bye
     */
    public static String bye() {
        return "Bye. Please chat with me again!";
    }

    /**
     * Returns a list of tasks in the tasklist
     *
     * @param taskList
     * @return string tasks
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
