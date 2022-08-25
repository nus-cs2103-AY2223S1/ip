package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String findWord;

    public FindCommand(String findWord) {
        this.findWord = findWord;
    }

    @Override
    public void run(TaskList taskList) throws DukeException {
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
                Ui.formatMessage("Could not find anything with the word '" + findWord + "' inside of the list");
            } else {
                for (Integer index : indexList) {
                    message += index + ": " + taskList.getTask(index).toString() + "\n";
                }
                Ui.formatMessage(message);
            }
        } else {
            throw new DukeException("I'm only able to find one word in the list for now..");
        }
    }
}
