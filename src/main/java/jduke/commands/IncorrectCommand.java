package jduke.commands;

public class IncorrectCommand extends Command {
    private final String feedback;

    public IncorrectCommand() {
        this.feedback = "";
    }

    public IncorrectCommand(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String execute() {
        return feedback.equals("") ? "|  cannot understand command\n" : feedback;
    }
}
