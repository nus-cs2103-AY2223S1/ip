package DukeProgram.Commands.Annotations;

import DukeProgram.Commands.Annotations.AnnotateTaskCommand;

public class UnmarkTaskCommand extends AnnotateTaskCommand {

    public UnmarkTaskCommand(String[] fullCommandParameters) {
        super(fullCommandParameters, false);
    }
}
