public class Parser {

    public void parseCommand(String command, ToDoList list) throws BobException {
        if (command.toLowerCase().equals("list")) {
            Ui.listMessage(list);
        } else if (command.startsWith("mark")) {
            try {
                int index = Integer.parseInt(command.replace("mark ", ""));
                list.markItemDone(index);
            } catch (NumberFormatException e) {
                list.addTask(command);
            }
        } else if (command.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(command.replace("unmark ", ""));
                list.markItemUndone(index);
            } catch (NumberFormatException e) {
                list.addTask(command);
            }
        } else if (command.startsWith("delete")) {
            try {
                int index = Integer.parseInt(command.replace("delete ", ""));
                list.deleteTask(index - 1);
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
