package DukeProgram.Commands.Annotations;

import DukeProgram.Commands.Annotations.AnnotateTaskCommand;

public class MarkTaskCommand extends AnnotateTaskCommand {

    public MarkTaskCommand(String[] fullCommandParameters) {
        super(fullCommandParameters, true);
    }
}
