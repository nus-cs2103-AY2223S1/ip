package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command{
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        ArrayList<Task> tempTaskArray = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).isContainsKeyword(keyword)) {
                tempTaskArray.add(taskList.getTask(i));
            }
        }
        if (tempTaskArray.size() == 0) {
            String message = "\t" + "No search results are available for this keyword!";
            ui.displayCommandMessage(message, null, null);
        } else {
            String findMessage = "\t" + "Here are the list of matching tasks!";
            ui.displayCommandMessage(findMessage,null,null);
            for (int j = 0; j < tempTaskArray.size(); j++) {
                String itemDisplayed = String.format("\t\t\t%d. %s", j + 1, tempTaskArray.get(j));
                ui.displayCommandMessage(itemDisplayed, null, null);
            }
        }
        ui.printBorder();
    }
}
