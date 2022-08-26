package DukeProgram.Commands.Task.Annotations;

public class UnmarkTaskCommand extends AnnotateTaskCommand {

    public UnmarkTaskCommand(String[] fullCommandParameters) {
        super(fullCommandParameters, false);
    }
}
