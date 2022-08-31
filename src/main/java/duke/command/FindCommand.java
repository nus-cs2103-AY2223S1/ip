package duke.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * Handles the "find" command.
 * @author Jason
 */
public class FindCommand extends Command {
    private String keyWord;
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Finds the tasks with descriptions containing the keyWord.
     * @param taskList TaskList to update tasks data.
     * @param storage Storage to save updates to TaskList.
     * @throws DukeException Find command with improper syntax.
     */
    @Override
    public void run(TaskList taskList, Storage storage) throws DukeException, IOException {
        if (keyWord.split(" ").length == 1) {
            List<Integer> taskIndexes = new ArrayList<>(100);
            StringBuilder message = new StringBuilder();

            for (int i = 0; i < taskList.size(); i++) {
                String taskDescription = taskList.get(i).getDescription();
                if (taskDescription.contains(keyWord)) {
                    taskIndexes.add(i);
                }
            }

            if (taskIndexes.size() == 0) {
                message = new StringBuilder("Sorry! I am unable to find any task with this keyword: "
                        + keyWord + "\n");
            } else {
                for (Integer i: taskIndexes) {
                    message.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
                }
            }

            Ui.printMessage(message.toString());

        } else {
            throw new DukeException("☹ OOPS!!! Please only input ONE keyword.");
        }
    }
}
