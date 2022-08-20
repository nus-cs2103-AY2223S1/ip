import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static boolean inUse = true;

    private static void startIntro() {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        startChat();
    }

    private static void startChat() {
        Scanner sc = new Scanner(System.in);
        while (inUse) {
            String input = sc.nextLine();
            try {
                String[] str = input.split(" ", 2);
                String firstWord = str[0];
                if (str.length == 1) {
                    switch (firstWord) {
                    case "bye": {
                        endChat();
                        break;
                    }
                    case "list": {
                        displayList();
                        break;
                    }
                    case "mark":
                    case "deadline":
                    case "unmark":
                    case "todo":
                    case "event":
                    case "delete":
                        throw new MissingDescriptionException(firstWord);
                    default:
                        throw new InvalidInputException();
                    }
                } else {
                    switch (firstWord) {
                    case "mark": {
                        markTaskDone(str[1]);
                        break;
                    }
                    case "unmark": {
                        markTaskUndone(str[1]);
                        break;
                    }
                    case "todo": {
                        addTask(str[1], TaskType.TODO);
                        break;
                    }
                    case "deadline": {
                        addTask(str[1], TaskType.DEADLINE);
                        break;
                    }
                    case "event": {
                        addTask(str[1], TaskType.EVENT);
                        break;
                    }
                    case "delete": {
                        deleteTask(str[1]);
                        break;
                    }
                    default:
                        throw new InvalidInputException();
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    private static void endChat() {
        inUse = false;
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + "." + listOfTasks.get(i));
        }
    }

    private static String displayListSize() {
        return "\nNow you have " + listOfTasks.size() + " tasks in the list.";
    }

    private static void addTask(String input, TaskType type) {
        Task task = null;
        try {
            switch (type) {
            case TODO: {
                task = new ToDo(input, TaskType.TODO);
                break;
            }
            case DEADLINE: {
                String[] deadlineComponents = input.split("/by ", 2);
                task = new Deadline(deadlineComponents[0], deadlineComponents[1], TaskType.DEADLINE);
                break;
            }
            case EVENT: {
                String[] eventComponents = input.split("/at ", 2);
                task = new Event(eventComponents[0], eventComponents[1], TaskType.EVENT);
                break;
            }
            default:
                throw new InvalidInputException();
            }
            listOfTasks.add(task);
            System.out.println("Got it. I've added this task:\n  " + task
                    + displayListSize());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void markTaskDone(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            Task selectedTask = listOfTasks.get(index);
            selectedTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:" + "\n  " + selectedTask);
        } catch (NumberFormatException nfe) {
            throw new InvalidIndexException("please input a valid integer");
        } catch (IndexOutOfBoundsException er) {
            throw new InvalidIndexException("no tasks exist at this index");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void markTaskUndone(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            Task selectedTask = listOfTasks.get(index);
            selectedTask.markUndone();
            System.out.println("OK, I've marked this task as not done yet:" + "\n  " + selectedTask);
        } catch (NumberFormatException nfe) {
            throw new InvalidIndexException("please input a valid integer");
        } catch (IndexOutOfBoundsException er) {
            throw new InvalidIndexException("no tasks exist at this index");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteTask(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            Task selectedTask = listOfTasks.remove(index);
            System.out.println("Noted. I've removed this task:" + "\n  " + selectedTask
                    + displayListSize());
        } catch (NumberFormatException nfe) {
            throw new InvalidIndexException("please input a valid integer");
        } catch (IndexOutOfBoundsException er) {
            throw new InvalidIndexException("no tasks exist at this index");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        startIntro();
    }
}
