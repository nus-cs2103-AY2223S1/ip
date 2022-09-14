package duke.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.TaskList;
import duke.utils.Storage;

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
     * @return String message of running the "find" command.
     * @throws DukeException Find command with improper syntax.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException, IOException {
        int correctKeyWordLength = 1;
        if (keyWord.split(" ").length == correctKeyWordLength) {
            List<Integer> taskIndexes = new ArrayList<>(100);
            StringBuilder message = new StringBuilder();

            for (int i = 0; i < taskList.size(); i++) {
                String taskDescription = taskList.get(i).getDescription();
                if (taskDescription.contains(keyWord)) {
                    taskIndexes.add(i);
                }
            }

            if (taskIndexes.size() == 0) {
                message = new StringBuilder("\uD83D\uDE14 Sorry! I am unable to find any task with this keyword: "
                        + keyWord + "\n");
            } else {
                for (Integer i: taskIndexes) {
                    message.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
                }
            }

            return message.toString();

        } else {
            throw new DukeException("\uD83D\uDE14 OOPS!!! Please only input ONE keyword.");
        }
    }
}
