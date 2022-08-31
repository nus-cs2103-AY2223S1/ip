package duke;

/**
 * A class that encapsulates the Ui object
 * which deals with interactions with the user
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class Ui {
    private final String GREETING_MESSAGE = "Hello I am LUNA!\n How can I be of help?";
    private final String GOODBYE_MESSAGE = "LUNA bids farewell\nMay the Moon shine bright "
             + "and illuminate your night.";
    private final String UNMARK_MESSAGE =  "LUNA thought you were already done with this?\n";
    private final String MARK_MESSAGE = "LUNA waited many moons for you to finish this one\n";
    private final String DELETE_MESSAGE = "LUNA has deleted that task... Erased forever, lost "
            + "in the depth of Space...\n";
    private final String TODO_MESSAGE = "LUNA will write this one down on her finest crater...:\n";
    private final String EVENT_MESSAGE = "What event could possibly be more important than the Moon Festival?\n";
    private final String DEADLINE_MESSAGE = "Deadlines... LUNA isn't too good with those...\n";
    private final String FIND_MESSAGE = "These are the Tasks you have been finding:";
    /**
     * Prints a greeting message
     */
    public String greetingMessage() {
        return this.GREETING_MESSAGE + "\n";
    }

    /**
     * Prints a farewell message
     */
    public String goodbyeMessage() {

        return GOODBYE_MESSAGE;
    }

    /**
     * Prints a message indicating a Task has been unmarked
     */
    public String unmarkMessage(String taskName) {

        return UNMARK_MESSAGE + taskName;
    }

    /**
     * Prints a message indicating a Task has been marked
     */
    public String markMessage(String taskName) {

        return MARK_MESSAGE + taskName;
    }

    /**
     * Prints a message indicating a Task has been deleted
     */
    public String deleteMessage(String taskName, int len) {

        return DELETE_MESSAGE + taskName;
    }

    /**
     * Prints a message indicating a ToDos Object has been added to the TaskList
     */
    public String todoMessage(ToDos todo, int len) {
        return TODO_MESSAGE + todo + "\n" + lengthMessage(len);

    }

    /**
     * Prints a message indicating a Events Object has been added to the TaskList
     */
    public String eventMessage(Events event, int len) {
        return "\n" + EVENT_MESSAGE + event + "\n" + lengthMessage(len);
    }

    /**
     * Prints a message indicating a Deadlines Object has been added to the TaskList
     */
    public String deadlineMessage(Deadlines deadline, int len) {
        return DEADLINE_MESSAGE + deadline +  "\n" + lengthMessage(len);
    }

    public String findMessage() {

        return FIND_MESSAGE + "\n";
    }

    public String lengthMessage(int len) {
        return "You now have " + len + " tasks added\n" + "How many moons before you complete them?";
    }

}
