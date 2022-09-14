package duke.command;

import duke.task.Task;

public class MarkCommand extends Command {
    /**
     * A constructor that encapsulates the information of marking a task as done.
     *
     * @param fullCommand input of the task we want to mark.
     */
    public MarkCommand(String fullCommand) {
        super((tasks, ui, storage) -> {
            try {

                String itemString = fullCommand.split(" ")[1];
                int itemNumber = Integer.parseInt(itemString) - 1;
                Task task = tasks.get(itemNumber);

                if (task.isDone()) {
                    ui.showError("This task is already marked as done!");
                } else {
                    task.mark();
                    ui.showMarkedAsDoneMessage(task);
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
