package duke;
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
            String keyword = command.split(" ", 2)[1];
            return taskList.findTasksByKeyword(keyword, ui);
        } else {
            return taskList.addToList(command, ui);
        }
    }
}
