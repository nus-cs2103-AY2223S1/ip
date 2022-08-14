import java.util.ArrayList;
import java.util.Scanner;

public class Skylark {

    private static final String line = "________________________________________________";

    private static void printText(String text) {
        System.out.println(Skylark.line);
        System.out.println(text);
        System.out.println(Skylark.line);
    }

    private static void printText(ArrayList<Task> text) {
        System.out.println(Skylark.line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < text.size(); i++) {
            Task currentTask = text.get(i);
            System.out.println((i + 1) + ". " + currentTask.toString());
        }
        System.out.println(Skylark.line);
    }

    private static void printText(Task currentTask, boolean markAsDone) {
        System.out.println(Skylark.line);
        if (markAsDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(currentTask.toString());
        System.out.println(Skylark.line);
    }

    private static void printText(ArrayList<Task> list, Task currentTask) {
        System.out.println(Skylark.line);
        System.out.println("Got it. I've added this task:");
        System.out.println(currentTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(Skylark.line);
    }
    private static boolean doesIndexExist(ArrayList<Task> list, int index) {
        return index >= 0 && index < list.size();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        String byeCommand = "bye";
        String listCommand = "list";
        String doneCommand = "mark";
        String undoneCommand = "unmark";

        String deadlineCommand = "deadline";
        String toDoCommand = "todo";
        String eventCommand = "event";

        String command;

        Skylark.printText("Hello, I am Skylark, how can I help you today?");

        while (true) {
            command = scan.nextLine();
            if (command.length() > 0) {
                if (command.equals(byeCommand)) {
                    Skylark.printText("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals(listCommand)) {
                    Skylark.printText(taskList);
                } else if (command.length() > 4 && command.substring(0, 4).equals(doneCommand)) {
                    int index = Integer.parseInt(command.substring(5)) - 1;
                    if (Skylark.doesIndexExist(taskList, index)) {
                        Task currentTask = taskList.get(index);
                        currentTask.markAsDone();
                        Skylark.printText(currentTask, true);
                    }
                } else if (command.length() > 6 && command.substring(0, 6).equals(undoneCommand)) {
                    int index = Integer.parseInt(command.substring(7)) - 1;
                    if (Skylark.doesIndexExist(taskList, index)) {
                        Task currentTask = taskList.get(index);
                        currentTask.markAsUndone();
                        Skylark.printText(currentTask, false);
                    }
                } else if (command.length() > 4 && command.substring(0, 4).equals(toDoCommand)) {
                    ToDo toDoTask = new ToDo(command.substring(5));
                    taskList.add(toDoTask);
                    Skylark.printText(taskList, toDoTask);
                } else if (command.length() > 8 && command.substring(0, 8).equals(deadlineCommand)) {
                    int slashIndex = command.lastIndexOf("/");
                    Deadline deadlineTask = new Deadline(command.substring(9, slashIndex - 1),
                            command.substring(slashIndex + 4));
                    taskList.add(deadlineTask);
                    Skylark.printText(taskList, deadlineTask);
                } else if (command.length() > 5 && command.substring(0, 5).equals(eventCommand)) {
                    int slashIndex = command.lastIndexOf("/");
                    Event eventTask = new Event(command.substring(6, slashIndex - 1),
                            command.substring(slashIndex + 4));
                    taskList.add(eventTask);
                    Skylark.printText(taskList, eventTask);
                } else {
                    taskList.add(new Task(command));
                    Skylark.printText("added: " + command);
                }
            }
        }
    }
}
