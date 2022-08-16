import exceptions.*;
import utils.TaskOperation;
import static utils.UserOperation.*;

import java.util.Scanner;

public class Duke {

    public static final String GREETING = "Hello!! I'm Duke, and I can help you " +
            "do up a simple to-do list!\nThe following commands are valid:\n" + "--------------\n"
            + "todo (creates a new todo task)\n" +
            "event (creates a new event)\ndeadline (creates a new task with a deadline)\n" +
            "mark (marks the completion of your tasks)\nlist (lists your tasks)\nbye (ends the program)";
    public static final String END_PROGRAM = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        try {
            Task[] toDoList = new Task[100];

            System.out.println(GREETING);

            boolean end = false;
            while (!end) {
                String userRequest = reader.nextLine();
                if (userRequest.equals(BYE.getOperation())) {
                    end = true;
                    System.out.println(END_PROGRAM);
                } else if (userRequest.equals(LIST.getOperation())) {
                    printTasksInList(toDoList);
                    System.out.print("If you would like to mark a task as complete, type 'mark', " +
                            "otherwise, you can continue using other commands.\n" +
                            "Please type here: ");
                } else if (userRequest.equals(MARK.getOperation())) {
                    markTask(toDoList);
                } else {
                    Task newTask = taskBuilder(userRequest);
                    putTaskInList(toDoList, newTask);
                }
            }
            reader.close();
        } catch (EmptyBodyException | InvalidInputException e) {
            System.out.println(e.getMessage());
        }

    }

    /* ------------------------HELPER FUNCTIONS------------------------ */

    private static Task taskBuilder(String operation) throws EmptyBodyException, InvalidInputException {
        if (operation.equals(TaskOperation.TODO.getOperation())) {
            String description = taskDescription();
            return new ToDoTask(description);
        } else if (operation.equals(TaskOperation.DEADLINE.getOperation())) {
            String description = taskDescription();
            System.out.println("When does this need to get completed by?");
            Scanner reader = new Scanner(System.in);
            String deadline = reader.nextLine();
            return new DeadlineTask(description, deadline);
        } else if (operation.equals(TaskOperation.EVENT.getOperation())) {
            String description = taskDescription();
            System.out.println("When is this event happening?");
            Scanner reader = new Scanner(System.in);
            String eventDate = reader.nextLine();
            return new EventTask(description, eventDate);
        } else if (operation.equals("")) {
            throw new EmptyBodyException();
        } else {
            throw new InvalidInputException();
        }
    }

    private static String taskDescription() {
        System.out.println("Give a brief overview of your task:");
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    private static void printTasksInList(Task[] list) {
        System.out.print("Here are the tasks in your list:\n");
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                break;
            }
            System.out.println(i + 1 + ": " + list[i]);
        }
    }

    private static void putTaskInList(Task[] list, Task task) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                // do nothing
            } else {
                list[i] = task;
                System.out.println("I have added the task! Type 'list' to show all tasks.");
                break;
            }
        }

        System.out.print("What else would you like to do?\n" +
                "Please type here: ");
    }

    private static void markTask(Task[] list) {
        System.out.print("Which task would you like to mark as complete?\n");
        Scanner intReader = new Scanner(System.in);
        printTasksInList(list);
        System.out.print("Task number: ");
        int chosenTask = intReader.nextInt();
        markDone(list[chosenTask - 1]);
        printTasksInList(list);
        System.out.print("You have marked task " + chosenTask + " as complete! Congrats!\n" +
                "If you want to mark another task as complete, please type 'mark' again.\n" +
                "Else, you can input a new task!\n" +
                "Please type here: ");
    }

    private static void markDone(Task task) {
        task.isDone = true;
    }

}
