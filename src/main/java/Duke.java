import exceptions.*;

import static utils.UserRequest.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Duke {

    public static final String GREETING = "Hello!! I'm Duke, and I can help you " +
            "do up a simple to-do list!\nThe following commands are valid:\n" + "--------------\n"
            + "todo (creates a new todo task)\n" +
            "event (creates a new event)\ndeadline (creates a new task with a deadline)\n" +
            "mark (marks the completion of your tasks)\ndelete (removes a task)\n" +
            "list (lists your tasks)\nbye (ends the program)";
    public static final String END_PROGRAM = "Bye. Hope to see you again soon!";
    public static int taskCount = 0;
    public static final Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {


        try {
            ArrayList<Task> toDoList = new ArrayList<>();
            String path="./test.txt";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                toDoList = loadTasks();
                printTasksInList(toDoList);
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            System.out.println(GREETING);

            boolean end = false;
            while (!end) {
                String userRequest = reader.nextLine().toLowerCase(Locale.ROOT);
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
            bw.write(saveList(toDoList));
            bw.close();
        } catch (EmptyBodyException | InvalidInputException | IOException e) {
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
            String deadline = reader.nextLine();
            return new DeadlineTask(description, deadline);
        } else if (operation.equals(EVENT.getOperation())) {
            String description = taskDescription();
            System.out.println("When is this event happening?");
            String eventDate = reader.nextLine();
            return new EventTask(description, eventDate);
        } else {
            throw new InvalidInputException();
        }
    }

    private static String taskDescription() throws EmptyBodyException {
        System.out.println("Give a brief overview of your task:");
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
        taskCount = i;
    }

    private static void removeTaskFromList(ArrayList<Task> list) throws InvalidInputException {
        System.out.print("Which task would you like to remove?\n");
        printTasksInList(list);
        System.out.print("Task number: ");
        int chosenTaskIndex = reader.nextInt();
        reader.nextLine();
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
        printTasksInList(list);
        System.out.print("Task number: ");
        int chosenTask = reader.nextInt();
        reader.nextLine();
        if (chosenTask > taskCount || chosenTask < 1) {
            throw new InvalidInputException("Are you sure this number corresponds to a task?");
        }
        list.get(chosenTask - 1).markDone();
        printTasksInList(list);
        System.out.print("You have marked task " + chosenTask + " as complete! Congrats!\n" +
                "If you want to mark another task as complete, please type 'mark' again.\n" +
                "Else, you can input a new task!\n" +
                "Please type here: ");
    }

    // Make todolist into a class - has tasks, can add, delete....
    private static String saveList(ArrayList<Task> list) {
        StringBuilder start = new StringBuilder();
        for (Task task : list) {
            String taskDes = task.toString();
            if (task.getStatusIcon().equals("X")) {
                start.append(taskDes.charAt(1))
                        .append(" | ")
                        .append("Done")
                        .append(" | ")
                        .append(task.description)
                        .append(" | ");
                if (task instanceof EventTask) {
                    start.append(((EventTask) task).eventDate);
                } else if (task instanceof DeadlineTask) {
                    start.append(((DeadlineTask) task).dateline);
                }

                start.append(System.lineSeparator());
            } else {
                start.append(taskDes.charAt(1))
                        .append(" | ")
                        .append("Undone")
                        .append(" | ")
                        .append(task.description)
                        .append(" | ");
                if (task instanceof EventTask) {
                    start.append(((EventTask) task).eventDate);
                } else if (task instanceof DeadlineTask) {
                    start.append(((DeadlineTask) task).dateline);
                }

                start.append(System.lineSeparator());
            }
        }

        return start.toString();
    }

    private static ArrayList<Task> loadTasks() throws IOException {
        List<String> content;
        ArrayList<Task> toDoList = new ArrayList<>();
        try {
            content = Files.readAllLines(Paths.get("./test.txt"));
            String[] inputArray;
            String taskType;
            String taskDescription;
            for (String line : content) {
                inputArray = Arrays.stream(line.split("\\|")).map(String::trim).toArray(String[]::new);
                taskType = inputArray[0];
                taskDescription = inputArray[2];
                Task task = null;
                switch (taskType) {
                    case "T":
                        task = new ToDoTask(taskDescription);
                        break;
                    case "D":
                        task = new DeadlineTask(taskDescription, inputArray[3]);
                        break;
                    case "E":
                        task = new EventTask(taskDescription, inputArray[3]);
                        break;
                }

                if (task != null && inputArray[1].equals("Done")) {
                    task.markDone();
                }
                toDoList.add(task);
            }
            return toDoList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return toDoList;
    }

}
