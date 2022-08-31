package duke;
public class Parser {
    protected void commandParser(String command, TaskList taskList, UI ui) throws Exception {
        if (command.split(" ")[0].toLowerCase().equals("mark")) {
            int number = Integer.parseInt(command.split(" ")[1]);
            taskList.markAsDone(number, ui);
        } else if (command.split(" ")[0].toLowerCase().equals("unmark")) {
            int number = Integer.parseInt(command.split(" ")[1]);
            taskList.unmarkTask(number, ui);
        } else if (command.split(" ")[0].toLowerCase().equals("delete")) {
            int number = Integer.parseInt(command.split(" ")[1]);
            taskList.deleteFromList(number, ui);
        } else if (command.split(" ")[0].toLowerCase().equals("find")) {
            String keyword = command.split(" ", 2)[1];
            taskList.findTasksByKeyword(keyword, ui);
        } else {
            taskList.addToList(command, ui);
        }
    }
}
