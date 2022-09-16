package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.tag.Tag;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * ListCommand lists all tasks in TaskList
 */
public class TagCommand extends Command {

    private final int index;
    private final String tagDescription;

    /**
     * Creates a TagCommand to tag a task
     *
     * @param description String representation of task number to be marked
     * @throws DukeException if user did not type in a correct task number
     */
    public TagCommand(String description) throws DukeException {
        try {
            assert description.split(" ")[0].equals("tag") : "Keyword should be tag for TagCommand";
            String index = description.split(" ")[1];
            this.index = Integer.parseInt(index);

            String[] descriptionLst = description.split("/t ", 2);

            if (descriptionLst.length < 2 || descriptionLst[1].equals("")) {
                throw new DukeException("Please input the tag description");
            }

            this.tagDescription = description.split("/t ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Fill in index of task to delete");
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid tasks");
        }
    }

    /**
     * Tags command and prints out message to users depending on whether the
     * command was successful
     *
     * @return @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("No such tasks found");
        } else {
            Task task = tasks.get(index - 1);
            Tag tag = new Tag(this.tagDescription);
            task.addTag(tag);
            storage.save(tasks);
            return getMessage(task);
        }
    }

    public String getMessage(Task task) {
        String str = "Tag added successfully ";
        str += task;
        return str;
    }
}
