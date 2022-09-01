package duke;

public class Parser {


    public void commandParser(String command, TaskList tasklist, Ui ui) {

            if (command.equals("list")) {
                tasklist.viewList(ui);
            } else if (command.matches("\\bmark\\s\\d+\\b")) {
                int num = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                tasklist.taskDone(num,ui);
            } else if (command.matches("\\bunmark\\s\\d+\\b")) {
                int num = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                tasklist.taskUndone(num,ui);
            } else if (command.matches("\\bdelete\\s\\d+\\b")) {
                int num = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                tasklist.deleteTask(num,ui);
            } else {
                tasklist.addToList(command,ui);
            }



    }
}
