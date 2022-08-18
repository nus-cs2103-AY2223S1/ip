import java.util.*;

public class Duke {
    private Task[] toDoList = new Task[100];
    private int index = 0;

    public void addTask(String taskName) {
        Task task = new Task(taskName);
        toDoList[index++] = task;
        System.out.println("You have added: " + taskName);
    }

    public void showTasks() {
        for (int i = 1; i <= index; i++) {
            Task task = toDoList[i - 1];
            System.out.println(i + "." + task);
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
            String[] inputs = commandInput.split(" ");
            String command = inputs[0];

            if (commandInput.equals("bye")) {
                System.out.println(closeMsg);
                break;
            } else if (commandInput.equals("list")) {
                duke.showTasks();
            } else if (command.equals("mark")) {
                int indexToMark = Integer.parseInt(inputs[1]);
                duke.toDoList[indexToMark - 1].completedTask();
            } else if (command.equals("unmark")) {
                int indexToMark = Integer.parseInt(inputs[1]);
                duke.toDoList[indexToMark - 1].uncompletedTask();
            } else {
                duke.addTask(commandInput);
            }
        }
    }
}

