package utils;

import tasks.ToDoList;
import exceptions.BobException;

public class Parser {

    public void parseCommand(String command, ToDoList list) throws BobException {
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
        } else {
            list.addTask(command);
        }
    }
}
