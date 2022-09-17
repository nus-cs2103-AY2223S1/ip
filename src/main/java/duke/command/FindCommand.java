package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;

/**
 * Handles the command 'event'.
 */
public class FindCommand extends Command {
    private String findWord;
    private String[] segments;

    /**
     * Constructor for a new Find command.
     *
     * @param findWord The word to find.
     */
    public FindCommand(String findWord) {
        this.findWord = findWord;
        this.segments = findWord.split(" ");
    }

    public boolean isValidFind() {
        return segments.length == 1;
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
        String message = "Here are the matching tasks in your list:";

        if (isValidFind()) {
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
                assert indexList.size() > 0 : "There should be tasks found within the list";
                for (Integer index : indexList) {
                    message += "\n" + (index + 1) + ": " + taskList.getTask(index).toString();
                }
                builder.append(message);
            }
        } else {
            throw new DukeException("I'm only able to find one word in the list for now..");
        }
    }
}
