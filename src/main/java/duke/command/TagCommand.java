package duke.command;

import duke.task.Task;

public class TagCommand extends Command {

    /**
     * A constructor that encapsulates the information of tagging a task.
     *
     * @param fullCommand input of the task we want to tag and the name of the tag.
     */
    public TagCommand(String fullCommand) {
        super((tasks, ui, storage) -> {
            try {

                String itemString = fullCommand.split(" ")[1];
                String tagString = fullCommand.split(" ")[2];
                int itemNumber = Integer.parseInt(itemString) - 1;
                Task taskToTag = tasks.get(itemNumber);

                if (taskToTag.containsTag(tagString)) {
                    ui.showError("This task already has that tag!");
                } else {
                    taskToTag.addTag(tagString);

                    ui.showAddedTagMessage(taskToTag);
                }


            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please input a valid item to tag!");
            } catch (NumberFormatException e) {
                System.out.println("Please input a number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task not found!");
            }
        });
    }
}
