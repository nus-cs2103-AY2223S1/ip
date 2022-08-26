package duke;
import java.util.ArrayList;

public class Ui {
    public void greetUi() {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
    }

    public void bidFarewellUi() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void displayListUi(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + "." + list.get(i - 1).toString());
        }
    }

    public void deleteUi(Task task, ArrayList<Task> list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addToListUi(Task task, ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        if (list.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    public String getEmptyEventExceptionUi() {
        return "OOPS!!! The description of an event cannot be empty.";
    }

    public String getEmptyDeadlineExceptionUi() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }

    public String getEmptyTodoExceptionUi() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    public void markHelperUi(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void unmarkHelperUi(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }
//    public String readCommand() {
//        Scanner input = new Scanner(System.in);
//        String s = "";
//        while (input.hasNext()) {
//             s = input.nextLine();
//             Parser parser = new Parser(s);
//             if (parser.isSubStringForExitCommand()){
//                 ExitCommand exitCommand = new ExitCommand();
//                 exitCommand.execute(s,listOfTask,ui,storage);
//                 break;
//             }
//        }
//        return s;
//    }
}
