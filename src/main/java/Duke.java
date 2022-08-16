import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {

        ArrayList<Task> myList = new ArrayList<>();
        boolean bye = false;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("hello this is uncle raymond");

        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;
                UserInterface.sayBye();
            } else if (input.equals("list")) {
                UserInterface.printList(myList);
            } else if (input.length() >= 4 && input.substring(0,4).equals("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                EventHandler.markTask(taskIndex, myList);
            } else if (input.length() >= 6 && input.substring(0,6).equals("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                EventHandler.unmarkTask(taskIndex, myList);
            }
            else {
                EventHandler.addItem(input, myList);
            }
        }
    }
}
