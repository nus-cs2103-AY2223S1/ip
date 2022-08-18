public class Command {

    // Attributes of a Command
    protected CommandName givenCommand;

    /**
     * Constructor for the Command class
     * @param givenCommand the given command
     */
    public Command(CommandName givenCommand) {
        this.givenCommand = givenCommand;
    }

    public void printCommand() {
        switch (givenCommand) {
            case GREETINGS:
                System.out.println("Hello! I'm Duke");
                System.out.println("What can I do for you?");
                break;
            case DEPARTURE:
                System.out.println("Bye. Hope to see you again soon ^^!");
        }
    }
}
