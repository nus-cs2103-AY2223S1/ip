package jduke.commands;

public class IncorrectCommand extends Command {
    private final String feedback;

    public IncorrectCommand(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String execute() {
        return String.format("|  cannot understand command%n%s", feedback);
    }
}
