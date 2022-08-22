package jduke.commands;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String FORMAT = "unmark <integer>";

    private final String unmarkParams;

    public UnmarkCommand(String unmarkParams) {
        this.unmarkParams = unmarkParams;
    }
    @Override
    public String execute() {
        return tasklist.unmarkTask(this.unmarkParams);
    }
}
