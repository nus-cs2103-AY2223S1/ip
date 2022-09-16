package duke.ui;

/**
 * Class that deals with all the interactions with the user for GUI
 */
public class GUIUi {

    private static final String GREETINGS   = "Hello! I am Duke.\n" + "Nice to Meet you, how can I help you ?";
    private static final String HINT        = "You can type \"help\" to see all my services and get more information:)";
    private static final String SAVED       = "Your data is saved automatically.";
    private static final String ENDING      = "Thank you for your using. Hope you have a good time:)";
    private static final String HELP        = "Commands:\n" +
                    "list\t\t\t\t\t\t\t\t\t--list all tasks in your list\n" +
                    "todo <content>\t\t\t\t\t\t--add a \"todo\" task\n" +
                    "deadline <content> /<date> [time]\t--add a \"deadline\" task  \n" +
                    "event <content> /<date> <time>\t--add a \"event\" task  \n" +
                    "done <i>\t\t\t\t\t\t\t--mark the ith task as done\n" +
                    "delete <i>\t\t\t\t\t\t\t--delete the ith task\n" +
                    "find <content>\t\t\t\t\t\t--find task(s) with content\n" +
                    "sort\t\t\t\t\t\t\t\t\t--sort all the tasks\n" +
                    "sort Deadline\t\t\t\t\t\t--sort all the deadlines\n" +
                    "bye\t\t\t\t\t\t\t\t\t--quit and save all data\n" +
                    "\n" + "\n" +
                    "Others: \n" +
                    "<content>\t\tsentence(e.g. task description, keywords)\n" +
                    "<i>\t\t\t\tindex starting from 1\n" +
                    "<date>\t\t\tformat: YYYY-MM-DD>\n" +
                    "<time>\t\t\tformat: HH:MM\n" +
                    "[time]\t\t\tformat: HH:MM (Optional)\n" +
                    "\n" +
                    "For deadline and event command: \"/\" is needed to separate content and time.";

    /**
     * Shows help message to user when they encounter potential difficulties.
     * @return the help message with user guide in detail.
     */
    public String showHelp() {
        return HELP;
    }

    /**
     * Shows welcome message to user at the beginning when users come to use this program.
     * @return the welcome message.
     */
    public String showWelcome() {
        return GREETINGS;
    }

    /**
     * Shows hint message to offer potential help to users.
     * @return the hint message.
     */
    public String showHint() {
        return HINT;
    }

    /**
     * Shows ending message when users exit this program.
     * @return the goodbye message.
     */
    public String showEnding() {
        return ENDING;
    }

    /**
     * Shows all data saved message.
     * @return the message that all data is saved.
     */
    public String showSaved() {
        return SAVED;
    }

}
