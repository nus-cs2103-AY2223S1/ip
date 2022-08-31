package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command 'event'.
 */
public class FindCommand extends Command {
    private String findWord;

    /**
     * Constructor for a new Find command.
     *
     * @param findWord The word to find.
     */
    public FindCommand(String findWord) {
        this.findWord = findWord;
    }

    /**
     * Runs the command 'find'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @param builder
     * @throws DukeException Duke Exception for finding more than one word.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {
        String[] segments = findWord.split(" ");
        String message = "Here are the matching tasks in your list:\n";

        if (segments.length == 1) {
            ArrayList<Integer> indexList = new ArrayList<>();

            for (int i = 0; i < taskList.getSize(); i++) {
                String taskDescription = taskList.getTask(i).getDescription();
                if (taskDescription.contains(findWord)) {
                    indexList.add(i);
                }
            }
            if (indexList.size() < 1) {
                builder.append("Could not find anything with the word '" + findWord + "' inside of the list");
            } else {
                for (Integer index : indexList) {
                    message += (index + 1) + ": " + taskList.getTask(index).toString() + "\n";
                }
                builder.append(message);
            }
        } else {
            builder.append("I'm only able to find one word in the list for now..");
            throw new DukeException("I'm only able to find one word in the list for now..");
        }
    }
}
