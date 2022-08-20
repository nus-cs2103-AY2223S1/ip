import java.util.ArrayList;
import java.util.Scanner;

public class Skylark {

    private static String TEXT_HELLO = "Hello, I am Skylark, how can I help you today?";
    private static String TEXT_BYE = "Bye. Hope to see you again soon!";

    private static boolean doesIndexExist(ArrayList<Task> list, int index) {
        return index >= 0 && index < list.size();
    }

    private static boolean response(Scanner scan, ArrayList<Task> taskList) throws SkylarkException {
        String command = scan.nextLine();
        if (!command.isEmpty()) {
            if (command.equals(CommandList.COMMAND_BYE.toString())) {
                Printer.printText(Skylark.TEXT_BYE);
                return true;
            } else if (command.equals(CommandList.COMMAND_LIST.toString())) {
                Printer.printText("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task currentTask = taskList.get(i);
                    Printer.printText((i + 1) + ". " + currentTask.toString());
                }
            } else if (command.length() >= 4 && command.startsWith(CommandList.COMMAND_DONE.toString())) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                if (Skylark.doesIndexExist(taskList, index)) {
                    Task currentTask = taskList.get(index);
                    currentTask.markAsDone();
                    Printer.printText("Nice! I've marked this task as done:");
                    Printer.printText(currentTask.toString());
                } else {
                    throw new SkylarkException("Sorry, index does not exist!");
                }
            } else if (command.length() >= 6 && command.startsWith(CommandList.COMMAND_UNDONE.toString())) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                if (Skylark.doesIndexExist(taskList, index)) {
                    Task currentTask = taskList.get(index);
                    currentTask.markAsUndone();
                    Printer.printText("OK, I've marked this task as not done yet:");
                    Printer.printText(currentTask.toString());
                } else {
                    throw new SkylarkException("Sorry, index does not exist!");
                }
            } else if (command.length() >= 4 && command.startsWith(CommandList.COMMAND_TODO.toString())) {
                if (command.equals(CommandList.COMMAND_TODO.toString())) {
                    throw new ToDoException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                ToDo toDoTask = new ToDo(command.substring(5));
                taskList.add(toDoTask);
                Printer.printText("Got it. I've added this task:");
                Printer.printText(toDoTask.toString());
                Printer.printText("Now you have " + taskList.size() + " tasks in the list.");
            } else if (command.length() >= 8 && command.startsWith(CommandList.COMMAND_DEADLINE.toString())) {
                int slashIndex = command.lastIndexOf("/");
                String desc = command.substring(9, slashIndex - 1);
                String endDate = command.substring(slashIndex + 4);
                if (desc.isEmpty() || endDate.isEmpty()) {
                    throw new DeadlineException("☹ OOPS!!! " +
                            "The description or end date of a deadline cannot be empty.");
                }
                Deadline deadlineTask = new Deadline(desc, endDate);
                taskList.add(deadlineTask);
                Printer.printText("Got it. I've added this task:");
                Printer.printText(deadlineTask.toString());
                Printer.printText("Now you have " + taskList.size() + " tasks in the list.");
            } else if (command.length() >= 5 && command.startsWith(CommandList.COMMAND_EVENT.toString())) {
                int slashIndex = command.lastIndexOf("/");
                String desc = command.substring(6, slashIndex - 1);
                String timing = command.substring(slashIndex + 4);
                if (desc.isEmpty() || timing.isEmpty()) {
                    throw new EventException("☹ OOPS!!! " +
                            "The description or timing of an event cannot be empty.");
                }
                Event eventTask = new Event(desc, timing);
                taskList.add(eventTask);
                Printer.printText("Got it. I've added this task:");
                Printer.printText(eventTask.toString());
                Printer.printText("Now you have " + taskList.size() + " tasks in the list.");
            } else if (command.length() >= 6 && command.startsWith(CommandList.COMMAND_DELETE.toString())) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                if (Skylark.doesIndexExist(taskList, index)) {
                    Task currentTask = taskList.get(index);
                    taskList.remove(index);
                    Printer.printText("Noted. I've removed this task:");
                    Printer.printText(currentTask.toString());
                    Printer.printText("Now you have " + taskList.size() + " tasks in the list.");
                } else {
                    throw new SkylarkException("Sorry, index does not exist!");
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

        Printer.printText(Skylark.TEXT_HELLO);

        while (true) {
            try {
                boolean isEnd = Skylark.response(scan, taskList);
                if (isEnd) {
                    break;
                }
            } catch (SkylarkException exception) {
                System.out.println(exception);
            }
        }

    }
}
