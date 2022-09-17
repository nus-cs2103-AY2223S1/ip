package duke.commands;

import duke.Response;
import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class TagTaskCommand extends Command {
    private final int index;
    private final String tag;

    /**
     * Constructor for MarkCommand.
     * @param index the index specifying the task to be deleted.
     */
    public TagTaskCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public Response execute(TaskList taskList, Storage storage) throws NoSuchTaskException {
        Task task = taskList.get(index);
        String message = String.format("OK, I've added the tag '#%s' to this task:\n\t%s",
                tag,
                task);

        task.addTag(tag);
        storage.save(taskList);
        return new Response(message);
    }
}
