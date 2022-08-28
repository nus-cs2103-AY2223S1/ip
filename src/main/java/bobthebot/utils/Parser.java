package bobthebot.utils;

import bobthebot.exceptions.BobException;
import bobthebot.tasks.ToDoList;

/**
 * Parser class which primarily handles the logic of how the handle the input.
 * */
public class Parser {
    /**
     * Function which takes in the user's command and handles the logic of the output.
     * @param command A String containing the user's input.
     * @param list The Todo List which the command will act on.
     *
     * */
    public void parseCommand(String command, ToDoList list) {
        if (command.toLowerCase().equals("list")) {
            Ui.listMessage(list);
        } else if (command.startsWith("mark")) {
            try {
                if (list.getLength() == 0) {
                    String errorString = "\tPlease add items to your list before wanting to mark them as done!";
                    Ui.printErrorMessage(errorString);
                } else {
                    int index = Integer.parseInt(command.replace("mark ", ""));
                    list.markItemDone(index);
                }
            } catch (NumberFormatException e) {
                list.addTask(command);
            }
        } else if (command.startsWith("unmark")) {
            try {
                if (list.getLength() == 0) {
                    String errorString = "\tPlease add items to your list before wanting to mark them as undone!";
                    Ui.printErrorMessage(errorString);
                } else {
                    int index = Integer.parseInt(command.replace("unmark ", ""));
                    list.markItemUndone(index);
                }
            } catch (NumberFormatException e) {
                list.addTask(command);
            }
        } else if (command.startsWith("delete")) {
            try {
                if (list.getLength() == 0) {
                    String errorString = "\tPlease add items to your list before deleting them!";
                    Ui.printErrorMessage(errorString);
                } else {
                    int index = Integer.parseInt(command.replace("delete ", ""));
                    list.deleteTask(index - 1);
                }
            } catch (NumberFormatException e) {
                String result = e.toString() + "\n";
                result += "\tPlease enter the index of the item you would like to delete!\n";
                result += "\tEg. delete 2 (where 2 is the index of the item you would like to delete)";
                Ui.printErrorMessage(result);
            }
        } else if (command.startsWith("find")) {
            command = command.replace("find", "").trim();
            String result = "\tHere are the matching items on your list: \n";
            int index = 1;
            for (int i = 0; i < list.getLength(); i++) {
                if (list.getTask(i).toString().contains(command)) {
                    result += "\t" + index + ". " + list.getTask(i).toString() + "\n";
                    index++;
                }
            }
            Ui.formatMessage(result);
        } else {
            list.addTask(command);
        }
    }
}
