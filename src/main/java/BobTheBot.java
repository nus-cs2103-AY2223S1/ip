import java.util.Scanner;

public class BobTheBot {
    public static void main(String[] args) throws DukeException {
        ToDoList list = new ToDoList();
        System.out.println("Hello! I am Bob the Bot, your friendly task manager! \n" +
                           "What can I help you with?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.toLowerCase().equals("bye")) {
            if (command.toLowerCase().equals("list")) {
                System.out.println(list.toString());
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
                    list.deleteTask(index);
                } catch (NumberFormatException e) {
                    throw new DukeException(
                            "\n   --------------------------------------------------------------------------------\n" +
                                    "     Please enter the index of the item you would like to delete!\n" +
                                    "     Eg. delete 2 (where 2 is the index of the item you would like to delete)\n" +
                                    "   --------------------------------------------------------------------------------"
                    );
                }
            } else {
                list.addTask(command);
            }
            command = scanner.nextLine();
        }

        System.out.println(
                "   --------------------------------------------------------------------------------\n" +
                        "     Bye! Hope to see you again soon! \n" +
                        "   --------------------------------------------------------------------------------"
        );
    }
}
