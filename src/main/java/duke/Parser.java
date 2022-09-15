package duke;

import java.util.Arrays;

public class Parser {
    protected String commandParser(String command, TaskList taskList, UI ui) throws Exception {
        if (command.toLowerCase().equals("list")) {
            return ui.printList(taskList);
        } else if (command.split(" ")[0].toLowerCase().equals("mark")) {
            int number = Integer.parseInt(command.split(" ")[1]);
            return taskList.markAsDone(number, ui);
        } else if (command.split(" ")[0].toLowerCase().equals("unmark")) {
            int number = Integer.parseInt(command.split(" ")[1]);
            return taskList.unmarkTask(number, ui);
        } else if (command.split(" ")[0].toLowerCase().equals("delete")) {
            int number = Integer.parseInt(command.split(" ")[1]);
            return taskList.deleteFromList(number, ui);
        } else if (command.split(" ")[0].toLowerCase().equals("find")) {
            String[] keywords = command.split(" ", 6);
            keywords = Arrays.copyOfRange(keywords, 1, keywords.length);
            return taskList.findTasksByKeyword(ui, keywords);
        } else {
            return taskList.addToList(command, ui);
        }
    }
}
