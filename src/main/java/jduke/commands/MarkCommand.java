package jduke.commands;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String FORMAT = "mark <integer>";

    private final String markParams;

    public MarkCommand(String markParams) {
        this.markParams = markParams;
    }
    @Override
    public String execute() {
        return tasklist.markTask(this.markParams);
    }
}
