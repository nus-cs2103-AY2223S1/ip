import exceptions.*;

import static utils.TaskOperation.*;
import static utils.UserOperation.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String GREETING = "Hello!! I'm Duke, and I can help you " +
            "do up a simple to-do list!\nThe following commands are valid:\n" + "--------------\n"
            + "todo (creates a new todo task)\n" +
            "event (creates a new event)\ndeadline (creates a new task with a deadline)\n" +
            "mark (marks the completion of your tasks)\ndelete (removes a task)\n" +
            "list (lists your tasks)\nbye (ends the program)";
    public static final String END_PROGRAM = "Bye. Hope to see you again soon!";
    public static int taskCount = 0;

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        try {
            ArrayList<Task> toDoList = new ArrayList<>();

            System.out.println(GREETING);

            boolean end = false;
            while (!end) {
                String userRequest = reader.nextLine();
                if (userRequest.equals(BYE.getOperation())) {
                    end = true;
                    taskCount = 0;
                    System.out.println(END_PROGRAM);
                } else if (userRequest.equals(LIST.getOperation())) {
                    printTasksInList(toDoList);
                    System.out.print("If you would like to mark a task as complete, type 'mark', " +
                            "otherwise, you can continue using other commands.\n" +
                            "Please type here: ");
                } else if (userRequest.equals(MARK.getOperation())) {
                    markTask(toDoList);
                } else if (userRequest.equals(DELETE.getOperation())) {
                    removeTaskFromList(toDoList);
                    taskCount--;
                } else {
                    Task newTask = taskBuilder(userRequest);
                    putTaskInList(toDoList, newTask);
                    taskCount++;
                }
            }
            reader.close();
        } catch (EmptyBodyException | InvalidInputException e) {
            System.out.println(e.getMessage());
        }

    }

    /* ------------------------HELPER FUNCTIONS------------------------ */

    private static Task taskBuilder(String operation) throws EmptyBodyException, InvalidInputException {
        if (operation.equals(TODO.getOperation())) {
            String description = taskDescription();
            return new ToDoTask(description);
        } else if (operation.equals(DEADLINE.getOperation())) {
            String description = taskDescription();
            System.out.println("When does this need to get completed by?");
            Scanner reader = new Scanner(System.in);
            String deadline = reader.nextLine();
            return new DeadlineTask(description, deadline);
        } else if (operation.equals(EVENT.getOperation())) {
            String description = taskDescription();
            System.out.println("When is this event happening?");
            Scanner reader = new Scanner(System.in);
            String eventDate = reader.nextLine();
            return new EventTask(description, eventDate);
        } else {
            throw new InvalidInputException();
        }
    }

    private static String taskDescription() throws EmptyBodyException {
        System.out.println("Give a brief overview of your task:");
        Scanner reader = new Scanner(System.in);
        String description = reader.nextLine();
        if (description.equals("")) {
            throw new EmptyBodyException();
        }
        return description;
    }

    private static void printTasksInList(ArrayList<Task> list) {
        int i = 1;
        System.out.print("Here are the tasks in your list:\n");
        for (Task task : list) {
            System.out.println(i + ": " + task);
            i++;
        }
    }

    private static void removeTaskFromList(ArrayList<Task> list) throws InvalidInputException {
        System.out.print("Which task would you like to remove?\n");
        Scanner intReader = new Scanner(System.in);
        printTasksInList(list);
        System.out.print("Task number: ");
        int chosenTaskIndex = intReader.nextInt();
        if (chosenTaskIndex > taskCount || chosenTaskIndex < 1) {
            throw new InvalidInputException("Are you sure this number corresponds to a task?");
        }
        Task chosenTask = list.get(chosenTaskIndex - 1);
        list.remove(chosenTask);
        System.out.println("I have removed the task: " + chosenTask + ". Type 'list' to show all tasks.");
        System.out.println("What else would you like to do?\n" +
                "Please type here: ");
    }

    private static void putTaskInList(ArrayList<Task> list, Task task) {

        list.add(task);
        System.out.println("I have added the task! Type 'list' to show all tasks.");
        System.out.println("What else would you like to do?\n" +
                "Please type here: ");
    }

    private static void markTask(ArrayList<Task> list) throws InvalidInputException {
        System.out.print("Which task would you like to mark as complete?\n");
        Scanner intReader = new Scanner(System.in);
        printTasksInList(list);
        System.out.print("Task number: ");
        int chosenTask = intReader.nextInt();
        if (chosenTask > taskCount || chosenTask < 1) {
            throw new InvalidInputException("Are you sure this number corresponds to a task?");
        }
        markDone(list.get(chosenTask - 1));
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
