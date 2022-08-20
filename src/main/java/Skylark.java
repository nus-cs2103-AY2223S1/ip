import java.util.ArrayList;
import java.util.Scanner;

public class Skylark {

    private static final String LINE = "________________________________________________";

    private enum CommandList {
        COMMAND_BYE, COMMAND_LIST, COMMAND_DONE,
        COMMAND_UNDONE, COMMAND_DEADLINE,
        COMMAND_TODO, COMMAND_EVENT, COMMAND_DELETE;

        @Override
        public String toString() {
            switch (this) {
            case COMMAND_BYE:
                return "bye";
            case COMMAND_LIST:
                return "list";
            case COMMAND_DONE:
                return "mark";
            case COMMAND_UNDONE:
                return "unmark";
            case COMMAND_DEADLINE:
                return "deadline";
            case COMMAND_TODO:
                return "todo";
            case COMMAND_EVENT:
                return "event";
            case COMMAND_DELETE:
                return "delete";
            default:
                throw new IllegalArgumentException();
            }
        }
    }

    private static void printText(String text) {
        System.out.println(Skylark.LINE);
        System.out.println(text);
        System.out.println(Skylark.LINE);
    }

    private static void printText(ArrayList<Task> text) {
        System.out.println(Skylark.LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < text.size(); i++) {
            Task currentTask = text.get(i);
            System.out.println((i + 1) + ". " + currentTask.toString());
        }
        System.out.println(Skylark.LINE);
    }

    private static void printText(Task currentTask, boolean markAsDone) {
        System.out.println(Skylark.LINE);
        if (markAsDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(currentTask.toString());
        System.out.println(Skylark.LINE);
    }

    private static void printText(ArrayList<Task> list, Task currentTask, boolean isDelete) {
        System.out.println(Skylark.LINE);
        if (isDelete) {
            System.out.println("Noted. I've removed this task:");
        } else {
            System.out.println("Got it. I've added this task:");
        }
        System.out.println(currentTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(Skylark.LINE);
    }

    private static boolean doesIndexExist(ArrayList<Task> list, int index) {
        return index >= 0 && index < list.size();
    }

    private static boolean response(Scanner scan, ArrayList<Task> taskList) throws SkylarkException {
        String command = scan.nextLine();
        if (!command.isEmpty()) {
            if (command.equals(CommandList.COMMAND_BYE.toString())) {
                Skylark.printText("Bye. Hope to see you again soon!");
                return true;
            } else if (command.equals(CommandList.COMMAND_LIST.toString())) {
                Skylark.printText(taskList);
            } else if (command.length() >= 4 && command.startsWith(CommandList.COMMAND_DONE.toString())) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                if (Skylark.doesIndexExist(taskList, index)) {
                    Task currentTask = taskList.get(index);
                    currentTask.markAsDone();
                    Skylark.printText(currentTask, true);
                } else {
                    throw new SkylarkException("Sorry, index does not exist!");
                }
            } else if (command.length() >= 6 && command.startsWith(CommandList.COMMAND_UNDONE.toString())) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                if (Skylark.doesIndexExist(taskList, index)) {
                    Task currentTask = taskList.get(index);
                    currentTask.markAsUndone();
                    Skylark.printText(currentTask, false);
                } else {
                    throw new SkylarkException("Sorry, index does not exist!");
                }
            } else if (command.length() >= 4 && command.startsWith(CommandList.COMMAND_TODO.toString())) {
                if (command.equals(CommandList.COMMAND_TODO.toString())) {
                    throw new ToDoException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                ToDo toDoTask = new ToDo(command.substring(5));
                taskList.add(toDoTask);
                Skylark.printText(taskList, toDoTask, false);
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
                Skylark.printText(taskList, deadlineTask, false);
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
                Skylark.printText(taskList, eventTask, false);
            } else if (command.length() >= 6 && command.startsWith(CommandList.COMMAND_DELETE.toString())) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                if (Skylark.doesIndexExist(taskList, index)) {
                    Task currentTask = taskList.get(index);
                    taskList.remove(index);
                    Skylark.printText(taskList, currentTask, true);
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

        Skylark.printText("Hello, I am Skylark, how can I help you today?");

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
