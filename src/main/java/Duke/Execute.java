package Duke;


import java.io.IOException;

/**
 * Execute class performs the commands and returns the string representation
 * to be shown to the user.
 *
 */
public class Execute {

    /**
     * Constructor for Execute.
     */
    public Execute() {
    }

    /**
     * Builds list for every entry in the Task List.
     *
     * @param tasks TaskList to build from
     * @return string representation of list
     */
    public String executeList(TaskList tasks) {
        StringBuilder list = new StringBuilder();
        for (int i = 1; i <= tasks.getSize(); i++) {
            String index = String.format("%d.", i);
            String entry = index + tasks.getItem(i).toString() + "\n";
            list.append(entry);
        }
        return list.toString();
    }

    /**
     * Marks object in list.
     *
     * @param tasks TaskList to mark from
     * @param input String of user command
     * @param storage Storage to rewrite
     * @param ui Ui to process error messaged
     * @return string confirmation of mark
     */
    public String executeMark(TaskList tasks, String input, Storage storage, Ui ui) {
        String number = input.split(" ", 2)[1];
        try {
            int num = Integer.parseInt(number);
            String str = tasks.markTasks("mark", num);
            storage.rewriteFile(tasks.getTaskArray());
            return str;

        } catch (NumberFormatException e) {
            ui.showInvalidTaskMessage();
        } catch (
        IOException e) {
            ui.showErrorWritingMessage();
        }
        return "";
    }

    /**
     * Unmarks object in list.
     *
     * @param tasks TaskList to unmark from
     * @param input String of user command
     * @param storage Storage to rewrite
     * @param ui Ui to process error messaged
     * @return String representation of unmark
     */
    public String executeUnmark(TaskList tasks, String input, Storage storage, Ui ui) {
        try {
            String number = input.split(" ", 2)[1];
            int num = Integer.parseInt(number);
            String str = tasks.markTasks("unmark", num);
            storage.rewriteFile(tasks.getTaskArray());
            return str;

        } catch (NumberFormatException e) {
            ui.showInvalidTaskMessage();
        } catch (IOException e) {
            ui.showErrorWritingMessage();
        }
        return "";
    }

    /**
     * Deletes task from list.
     *
     * @param tasks TaskList to delete from
     * @param input String of user command
     * @param storage Storage to rewrite
     * @param ui Ui to process error messaged
     * @return String representation of delete
     */
    public String executeDelete(TaskList tasks, String input, Storage storage, Ui ui) {
        String number = input.split(" ", 2)[1];
        try {
            int num = Integer.parseInt(number);
            Task toDelete = tasks.getTaskArray().get(num - 1);
            tasks.delete(toDelete);
            String str = ui.showDeleteMessage(toDelete, tasks.getSize());
            storage.writeToFile(toDelete);
            return str;

        } catch (NumberFormatException e) {
            return ui.showInvalidTaskMessage();
        } catch (IndexOutOfBoundsException e) {
            return ui.showInvalidIndexMessage();
        } catch (IOException e) {
            return ui.showErrorWritingMessage();
        }
    }

    /**
     * Creates a new task and add into list.
     *
     * @param tasks TaskList to create task to
     * @param input String of user command
     * @param storage Storage to rewrite
     * @param ui Ui to process error messaged
     * @return String representation of adding new task
     */
    public String executeCreateTask(TaskList tasks, String input, Storage storage, Ui ui) {
        try {
            Task newTask = tasks.createTask(input, false);
            if (newTask != null) {
                storage.writeToFile(newTask);
                return newTask.addMessage(tasks.getSize());
            }

        } catch (DukeException.EmptyTaskException | DukeException.UnkownCommandException | DukeException.InvalidParameterException error) {
            return error.getMessage();
        } catch (IOException e) {
            return ui.showErrorWritingMessage();
        }
        return "";
    }

    /**
     *
     * @param tasks TaskList to find from
     * @param input String of user command
     * @param ui Ui to process error messaged
     * @return String of found items in a list.
     */
    public String executeFind(TaskList tasks, String input, Ui ui) {
        try {
            String word = input.split(" ", 2)[1];
            StringBuilder list = new StringBuilder();
            for (int i = 1; i <= tasks.getSize(); i++) {
                String index = String.format("%d.", i);
                String entry = index + tasks.getItem(i).toString();
                if (entry.contains(word)) {
                    list.append(entry);
                }
            }
            return list.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showInvalidFindFiledMessage();
        }
    }
}
