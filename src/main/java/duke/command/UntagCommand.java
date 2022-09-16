package duke.command;

import duke.task.Task;

public class UntagCommand extends Command {

    /**
     * A constructor that encapsulates the information of removing a tag from a particular task.
     *
     * @param fullCommand input of the task and tag we want to remove.
     */
    public UntagCommand(String fullCommand) {
        super((tasks, ui, storage) -> {
            try {

                String itemString = fullCommand.split(" ")[1];
                String tagToRemove = fullCommand.split(" ")[2];
                int itemNumber = Integer.parseInt(itemString) - 1;
                Task taskWithTagToRemove = tasks.get(itemNumber);

                if (!taskWithTagToRemove.containsTag(tagToRemove)) {
                    ui.showError("This task does not contain that tag!");
                } else {
                    taskWithTagToRemove.removeTag(tagToRemove);

                    ui.showRemovedTagMessage(taskWithTagToRemove);
                }


            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please input a valid item to remove a tag!");
            } catch (NumberFormatException e) {
                System.out.println("Please input a number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task not found!");
            }
        });
    }
}
