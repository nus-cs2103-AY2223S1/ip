import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private List<Task> taskList = new ArrayList<>();
    private int count = 0;

    public void addToDo(String taskName) {
        count++;
        Task newTask = new ToDo(taskName);
        taskList.add(newTask);
        this.printTask(newTask);
    }

    public void addDeadline(String commandInput) {
        count++;
        String[] inputs = commandInput.split("/");
        String taskName = inputs[0];
        String dueDate = inputs[1];
        Task newTask = new Deadline(taskName, dueDate);
        taskList.add(newTask);
        this.printTask(newTask);
    }

    public void addEvent(String commandInput) {
        count++;
        String[] inputs = commandInput.split("/");
        String taskName = inputs[0];
        String date = inputs[1];
        Task newTask = new Event(taskName, date);
        taskList.add(newTask);
        this.printTask(newTask);
    }

    public void printTask(Task task) {
        System.out.println("You have added: \n" + task);
        System.out.println("Now you have " + count + " tasks in your list.");
    }

    public void showTasks() {
        for (int i = 1; i <= count; i++) {
            Task task = taskList.get(i - 1);
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
            String[] inputs = commandInput.split(" ", 2);
            String command = inputs[0];

            if (commandInput.equals("bye")) {
                System.out.println(closeMsg);
                break;
            } else if (commandInput.equals("list")) {
                duke.showTasks();
            } else if (command.equals("mark")) {
                int indexToMark = Integer.parseInt(inputs[1]);
                duke.taskList.get(indexToMark - 1).completedTask();
            } else if (command.equals("unmark")) {
                int indexToMark = Integer.parseInt(inputs[1]);
                duke.taskList.get(indexToMark - 1).uncompletedTask();
            } else if (command.equals("todo")) {
                duke.addToDo(inputs[1]);
            } else if (command.equals("deadline")) {
                duke.addDeadline(inputs[1]);
            } else if (command.equals("event")) {
                duke.addEvent(inputs[1]);
            }
        }
    }
}

