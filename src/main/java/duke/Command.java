package duke;


/**
 * This class encapsulates the information needed to execute
 * a command: the command's keyword and required arguments.
 * 
 * @author Siau Wee
 */
public class Command {
    
    /** The Keyword enum associated with this command. */
    private Keyword keyword;

    /** Arguments to be supplied along with the command Keyword. */
    private String[] commandArgs;

    /**
     * Constructor to initialise a Command with a given Keyword and arguments.
     * 
     * @param keyword
     * @param commandArgs
     */
    public Command(Keyword keyword, String[] commandArgs) {
        this.keyword = keyword;
        this.commandArgs = commandArgs;
    }

    /**
     * Returns the Keyword associated with this Command.
     * 
     * @return Keyword enum of this Command
     */
    public Keyword getKeyword() {
        return this.keyword;
    }

    /**
     * Returns the array of arguments associated with this Command.
     * 
     * @return arguments of this Command
     */
    public String[] getCommandArgs() {
        return this.commandArgs;
    }
}
