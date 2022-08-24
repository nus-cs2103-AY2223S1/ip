package command;

import data.tasks.Task;

public class UpdateTaskResponse extends CommandResponse {

    public UpdateTaskResponse(Task updatedTask, UpdateType updateType) {
        super(String.format(updateType.updateResponseTemplate, updatedTask), true, false);
    }

    enum UpdateType {
        MARK(String.join("\n", "Nice! I've mark this task as done:", "\t%s")),
        UNMARK(String.join("\n", "OK, I've marked this task as not done yet:", "\t%s"));

        public final String updateResponseTemplate;

        UpdateType(String updateResponseTemplate) {
            this.updateResponseTemplate = updateResponseTemplate;
        }
    }
}
