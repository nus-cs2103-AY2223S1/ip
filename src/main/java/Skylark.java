import java.util.ArrayList;
import java.util.Scanner;

public class Skylark {

    private static final String line = "________________________________________________";

    private static final String byeCommand = "bye";
    private static final String listCommand = "list";
    private static final String doneCommand = "mark";
    private static final String undoneCommand = "unmark";

    private static final String deadlineCommand = "deadline";
    private static final String toDoCommand = "todo";
    private static final String eventCommand = "event";

    private static final String deleteCommand = "delete";

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

    private static void printText(ArrayList<Task> list, Task currentTask, boolean isDelete) {
        System.out.println(Skylark.line);
        if (isDelete) {
            System.out.println("Noted. I've removed this task:");
        } else {
            System.out.println("Got it. I've added this task:");
        }
        System.out.println(currentTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(Skylark.line);
    }

    private static boolean doesIndexExist(ArrayList<Task> list, int index) {
        return index >= 0 && index < list.size();
    }

    private static boolean response(Scanner scan, ArrayList<Task> taskList) throws SkylarkException {
        String command = scan.nextLine();
        if (command.length() > 0) {
            if (command.equals(Skylark.byeCommand)) {
                Skylark.printText("Bye. Hope to see you again soon!");
                return true;
            } else if (command.equals(Skylark.listCommand)) {
                Skylark.printText(taskList);
            } else if (command.length() >= 4 && command.startsWith(Skylark.doneCommand)) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                if (Skylark.doesIndexExist(taskList, index)) {
                    Task currentTask = taskList.get(index);
                    currentTask.markAsDone();
                    Skylark.printText(currentTask, true);
                }
            } else if (command.length() >= 6 && command.startsWith(Skylark.undoneCommand)) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                if (Skylark.doesIndexExist(taskList, index)) {
                    Task currentTask = taskList.get(index);
                    currentTask.markAsUndone();
                    Skylark.printText(currentTask, false);
                }
            } else if (command.length() >= 4 && command.startsWith(Skylark.toDoCommand)) {
                if (command.equals(Skylark.toDoCommand)) {
                    throw new ToDoException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                ToDo toDoTask = new ToDo(command.substring(5));
                taskList.add(toDoTask);
                Skylark.printText(taskList, toDoTask, false);
            } else if (command.length() >= 8 && command.startsWith(Skylark.deadlineCommand)) {
                int slashIndex = command.lastIndexOf("/");
                Deadline deadlineTask = new Deadline(command.substring(9, slashIndex - 1),
                        command.substring(slashIndex + 4));
                taskList.add(deadlineTask);
                Skylark.printText(taskList, deadlineTask, false);
            } else if (command.length() >= 5 && command.startsWith(Skylark.eventCommand)) {
                int slashIndex = command.lastIndexOf("/");
                Event eventTask = new Event(command.substring(6, slashIndex - 1),
                        command.substring(slashIndex + 4));
                taskList.add(eventTask);
                Skylark.printText(taskList, eventTask, false);
            } else if (command.length() >= 6 && command.startsWith(Skylark.deleteCommand)) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                if (Skylark.doesIndexExist(taskList, index)) {
                    Task currentTask = taskList.get(index);
                    taskList.remove(index);
                    Skylark.printText(taskList, currentTask, true);
                }
            } else {
                throw new SkylarkException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        Skylark.printText("Hello, I am Skylark, how can I help you today?");

        while (true) {
            try {
                boolean result = Skylark.response(scan, taskList);
                if (result) {
                    break;
                }
            } catch (SkylarkException ex) {
                System.out.println(ex);
            }
        }

    }
}
