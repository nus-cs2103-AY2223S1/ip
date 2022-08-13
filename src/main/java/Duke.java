import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private final static String INTRODUCTION_MSG = "Hello I'm Duke\nWhat can I do for you?";
    private final static String MARK_TASK_DONE_MSG = "Nice! I've marked this task as done:";
    private final static String MARK_TASK_UNDONE_MSG = "OK, I've marked this task as not done yet:";
    private final static String ADDED_TASK_MSG = "Got it. I've added this task:";
    private final static String DISPLAY_LIST_MSG = "Here are the tasks in your list:";
    private final static String CLOSING_MSG = "Bye. Hope to see you again soon!";
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static void startIntro() {
        System.out.println(INTRODUCTION_MSG);
        startChat();
    }

    private static void startChat() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                displayList();
            } else if (input.startsWith("mark")) {
                markTaskDone(input);
            }  else if (input.startsWith("unmark")) {
                markTaskUndone(input);
            } else if (input.startsWith("todo")) {
                addNewToDo(input);
            } else if (input.startsWith("deadline")) {
                addNewDeadline(input);
            } else if (input.startsWith("event")) {
                addNewEvent(input);
            }
        }
        endChat();
        sc.close();
    }

    private static void endChat() {
        System.out.println(CLOSING_MSG);
    }

    private static void displayList() {
        System.out.println(DISPLAY_LIST_MSG);
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + "." + listOfTasks.get(i));
        }
    }

    private static void addNewToDo(String input) {
        String[] taskDescription = input.split("todo ", 2);
        addToList(new ToDo(taskDescription[1]));
    }

    private static void addNewDeadline(String input) {
        String[] deadlineDescription = input.split("deadline ", 2);
        String[] deadlineComponents = deadlineDescription[1].split("/by ", 2);
        addToList(new Deadline(deadlineComponents[0], deadlineComponents[1]));
    }

    private static void addNewEvent(String input) {
        String[] eventDescription = input.split("event ", 2);
        String[] eventComponents = eventDescription[1].split("/at ", 2);
        addToList(new Event(eventComponents[0], eventComponents[1]));
    }

    private static void addToList(Task task) {
        listOfTasks.add(task);
        System.out.println(ADDED_TASK_MSG + "\n  " + task + "\n"
                + "Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    private static void markTaskDone(String str) {
        if (str.length() <= 5) {
            System.out.println("Error: Incomplete command");
        } else {
            try {
                String[] indexString = str.split("mark ", 2);
                int index = Integer.parseInt(indexString[1]) - 1;
                Task selectedTask = listOfTasks.get(index);
                if (selectedTask != null) {
                    selectedTask.markAsDone();
                    System.out.println(MARK_TASK_DONE_MSG+ "\n  " + selectedTask);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error: Valid Integer required");
            }
        }
    }

    private static void markTaskUndone(String str) {
        if (str.length() <= 7) {
            System.out.println("Error: Incomplete command");
        } else {
            try {
                String[] indexString = str.split("unmark ", 2);
                int index = Integer.parseInt(indexString[1]) - 1;
                Task selectedTask = listOfTasks.get(index);
                if (selectedTask != null) {
                    selectedTask.markUndone();
                    System.out.println(MARK_TASK_UNDONE_MSG + "\n  " + selectedTask);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error: Valid Integer required");
            }
        }
    }

    public static void main(String[] args) {
        startIntro();
    }
}
