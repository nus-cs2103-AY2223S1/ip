package duke.command.response;

import duke.data.tasks.Task;

public class UpdateTaskResponse extends CommandResponse {

    /**
     * Constructor for UpdateTaskResponse class.
     *
     * @param updatedTask a newly updated task.
     * @param updateType  type of update.
     */
    public UpdateTaskResponse(Task updatedTask, UpdateType updateType) {
        super(String.format(updateType.updateResponseTemplate, updatedTask), true, false);
    }

    public enum UpdateType {
        MARK(String.join("\n", "Nice! I've mark this task as done:", "\t%s")),
        UNMARK(String.join("\n", "OK, I've marked this task as not done yet:", "\t%s")),
        TAG(String.join("\n", "OK, I've tag this task", "\t%s"));

        public final String updateResponseTemplate;

        /**
         * Constructor for UpdateType enum.
         *
         * @param updateResponseTemplate response string template for an update event.
         */
        UpdateType(String updateResponseTemplate) {
            this.updateResponseTemplate = updateResponseTemplate;
        }
    }
}
