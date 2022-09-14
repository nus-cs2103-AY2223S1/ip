package duke.command;

import duke.task.Task;

public class UnmarkCommand extends Command {

    /**
     * A constructor that encapsulates the information of marking a task as not done.
     *
     * @param fullCommand input of the task we want to unmark.
     */
    public UnmarkCommand(String fullCommand) {
        super((tasks, ui, storage) -> {
            try {

                String itemString = fullCommand.split(" ")[1];
                int itemNumber = Integer.parseInt(itemString) - 1;
                Task task = tasks.get(itemNumber);

                if (!task.isDone()) {
                    ui.showError("This task is already marked as not done yet!");
                } else {
                    task.unmark();
                    ui.showMarkedAsNotDoneMessage(task);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please input a valid item to mark!");
            } catch (NumberFormatException e) {
                System.out.println("Please input a number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task not found!");
            }

        });
    }
}
