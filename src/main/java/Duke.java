import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class Duke {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("DukeLogger");
        try {
            processUserRequest();
        } catch (Exception e) {
            logger.log(SEVERE, e.getMessage());
        }

    }

    /* ------------------------HELPER FUNCTIONS------------------------ */
    private static void processUserRequest() throws Exception {

        Task[] toDoList = new Task[100];

        System.out.println(Constants.GREETING);
        Scanner reader = new Scanner(System.in);

        boolean end = false;
        while (!end) {
            String userRequest = reader.nextLine();
            if (userRequest.equals(Constants.BYE)) {
                end = true;
                System.out.println(Constants.END_PROGRAM);
            } else if (userRequest.equals(Constants.LIST)) {
                printTasksInList(toDoList);
                System.out.print("If you would like to mark a task as complete, type 'mark'.\n" +
                        "Please type here: ");
            } else if (userRequest.equals(UserOperation.MARK.getOperation())) {
                markTask(toDoList);
            } else {
                Task task = new Task(userRequest);
                putTaskInList(toDoList, task);
            }
        }
        reader.close();
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

    private static Task[] putTaskInList(Task[] list, Task task) throws Exception {
        for (int i = 0; i < list.length; i++) {
            if (task.description.equals("")) {
                throw new InterruptedException("Nothing is not allowed!");
            } else if (list[i] != null) {
                continue;
            } else if (list[i] == null && i != 0 && list[i - 1].equals(task)) {
                break;
            } else {
                list[i] = task;
            }
        }

        System.out.println("Added: " + task.description);
        System.out.print("What else would you like to do?\n" +
                "Please type here: ");
        return list;
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
