import java.io.*;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

import main.java.*;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int index = 0;


    public static void main(String[] args) throws DukeException, IOException {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        read();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] breakitdown = input.split(" ");
            String command = breakitdown[0];
            Task newTask;
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    write();
                    break;
                } else if (command.equals("list")) {
                    if (taskList.size() == 0) {
                        System.out.println("There are currently no tasks in the list.");
                    } else {
                        printList(taskList);
                    }
                } else if (command.equals("unmark")) {
                    int idx = Integer.parseInt(breakitdown[1]);
                    Task undone = taskList.get(idx - 1);
                    undone.markAsUndone();
                } else if (command.equals("mark")) {
                    int idx = Integer.parseInt(breakitdown[1]);
                    Task done = taskList.get(idx - 1);
                    done.markAsDone();
                } else if (command.equals("todo")) {
                    if (breakitdown.length == 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String taskName = input.substring(5);
                    newTask = new ToDos(taskName);
                    taskList.add(newTask);
                    printOnAdd(newTask);
                } else if (command.equals("deadline")) {
                    if (breakitdown.length == 1) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String desc = input.substring(9);
                    if (!desc.contains("/by")) {
                        throw new DukeException("OOPS!!! /by keyword not found!");
                    }
                    String[] taskNameBy = desc.split("/by ");
                    if (taskNameBy.length == 1) {
                        throw new DukeException("OOPS!!! Date of deadline cannot be empty.");
                    }
                    String taskName = taskNameBy[0];
                    String by = taskNameBy[1];
                    try {
                        newTask = new Deadlines(taskName, by);
                        taskList.add(newTask);
                        printOnAdd(newTask);
                    } catch (DateTimeParseException e) {
                        System.out.println("OOPS!!! Please enter date in YYYY-MM-DD format");
                    }
                } else if (command.equals("event")) {
                    if (breakitdown.length == 1) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }
                    String desc = input.substring(6);
                    if (!desc.contains("/at")) {
                        throw new DukeException("OOPS!!! /at keyword not found!");
                    }
                    String[] taskNameLocation = desc.split("/at ");
                    if (taskNameLocation.length == 1) {
                        throw new DukeException("OOPS!!! Location of event cannot be empty.");
                    }
                    String taskName = taskNameLocation[0];
                    String location = taskNameLocation[1];
                    newTask = new Events(taskName, location);
                    taskList.add(newTask);
                    printOnAdd(newTask);
                } else if (command.equals("delete")) {
                    int idx = Integer.parseInt(breakitdown[1]);
                    printOnDelete(taskList.remove(idx - 1));
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void read() throws IOException {
        File directory = new File("data");
        File file = new File("data/duke.txt");
        if (directory.exists()) {
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String taskStr = sc.nextLine();
                    char type = taskStr.charAt(1);
                    char done = taskStr.charAt(4);
                    String task = taskStr.substring(7);
                    if (type == 'T') {
                        ToDos todo = new ToDos(task);
                        taskList.add(todo);
                    } else if (type == 'D') {
                        String[] taskNameBy = task.split("/by ");
                        String taskName = taskNameBy[0];
                        String by = taskNameBy[1];
                        Deadlines deadline = new Deadlines(taskName, by);
                        if (done == 'X') {
                            deadline.markAsDone();
                        }
                        taskList.add(deadline);
                    } else if (type == 'E') {
                        String[] taskNameBy = task.split("/at ");
                        String taskName = taskNameBy[0];
                        String at = taskNameBy[1];
                        Events event = new Events(taskName, at);
                        if (done == 'X') {
                            event.markAsDone();
                        }
                        taskList.add(event);
                    }

                }
            } else {
                file.createNewFile();
            }
        } else {
            directory.mkdir();
            file.createNewFile();
        }

    }

    public static void write() {
        try {
           FileWriter fw = new FileWriter("data/duke.txt");
           for (Task t : taskList) {
               fw.write(t.toString() + "\n");
           }
           fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    public static void printOnAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? " " : "s ") + "in the list");
    }

    public static void printOnDelete(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? " " : "s ") + "in the list");
    }
}
