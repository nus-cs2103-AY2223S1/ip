package duke.command;

import duke.task.Task;

public class DeleteCommand extends Command {

    /**
     * A constructor that encapsulates the information of a delete command.
     *
     * @param fullCommand The task to be deleted.
     */
    public DeleteCommand(String fullCommand) {
        super((tasks, ui, storage) -> {
            try {

                String itemString = fullCommand.split(" ")[1];
                int itemNumber = Integer.parseInt(itemString) - 1;
                Task taskToRemove = tasks.get(itemNumber);

                tasks.remove(taskToRemove);

                ui.showRemovedTaskMessage(taskToRemove);
                ui.countTasks(tasks);

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
