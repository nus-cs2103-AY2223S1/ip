import java.util.*;
public class Duke {
    private String[] toDoList = new String[100];
    private int index = 0;

    private void addTask(String task) {
        toDoList[index++] = task;
        System.out.println("You have added: " + task);
    }

    private void showTasks() {
        for (int i = 1; i <= index; i++) {
            System.out.println(i + ". " + toDoList[i - 1]);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String startMsg = "Hello!!! My name is Wanya! \nWWaku WWaku! \nHow can I help you? \n";
        String closeMsg = "I am so sad that you have to go :( bye bye :(";
        Scanner sc = new Scanner(System.in);
        System.out.println(startMsg);

        while (sc.hasNext()) {
            String commandInput = sc.nextLine();
            if (commandInput.equals("bye")) {
                System.out.println(closeMsg);
                break;
            } else if (commandInput.equals("list")) {
                duke.showTasks();
            } else {
                duke.addTask(commandInput);
            }
        }
    }
}

