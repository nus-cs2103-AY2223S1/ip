package duke;

/**
 * This class is responsible for giving commands
 *
 * @author Kang Zong Xian
 */
public class Command {

    // Attributes of a duke.Command
    protected CommandName givenCommand;

    /**
     * Constructor for the Command class
     * @param givenCommand the given command
     */
    public Command(CommandName givenCommand) {
        this.givenCommand = givenCommand;
    }

    /**
     * Greet the user depending on the given command
     */
    public void printCommand() {
        switch (givenCommand) {
        case GREETINGS:
            System.out.println("Hello! I'm duke.Duke");
            System.out.println("What can I do for you?");
            break;
        case DEPARTURE:
            System.out.println("Bye. Hope to see you again soon ^^!");
            break;
        default:
            System.out.println("Invalid Sentence!");
        }
    }
}
