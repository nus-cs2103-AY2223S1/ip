package blink.command;

import blink.BlinkException;
import blink.Storage;
import blink.TaskList;
import blink.Ui;

/**
 * Command to add tag to a task.
 */
public class TagCommand extends Command {

    private int index;
    private String tag;

    /**
     * Constructor for TagCommand
     *
     * @param input String containing task number to tag and tag content
     * @throws BlinkException thrown if missing parameters for tag
     * @throws NumberFormatException thrown if input is not a number
     */
    public TagCommand(String input) {
        String[] info = input.split(" ", 2);
        if (info.length < 2) {
            throw new BlinkException("Missing parameters for tag command");
        }
        int index = Integer.parseInt(info[0].strip());
        this.index = index;
        this.tag = info[1].strip();
    }

    /**
     * Tags the task at the specified index with the tag content.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     * @return String containing information of the Task after it is tagged
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.tagTask(this.index, this.tag);
        storage.save(tasks);
        return ui.showTag(this.index, tasks);
    }
}
