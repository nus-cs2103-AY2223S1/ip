import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;


public class Duke {
    public static void main(String[] args) throws DukeException, IllegalArgumentException {

        List<Task> list = new ArrayList<>(); // to store list of inputs

        // attempt to open file within same folder as src code, create file if file doesn't exist
        try {
            File file = new File("./tasks.txt");
            if (!file.exists()) {
                System.out.println("File doesn't exist, creating file");
                file.createNewFile();
                System.out.println("File created");
            } else {
                System.out.println("File exists");
                // read from file and add to task list
            }
        } catch (IOException e) {
            System.out.println("Error while opening file");
        }

        // at this point file will exist, init taskList
        listInit(list);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMsg = "Hello! I'm\n" + logo + "\nWhat can I do for you?\n";
        System.out.println(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (true) {

            try {
                input = sc.nextLine();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    taskPrinter(list);
                } else if (input.startsWith("mark")) {
                    String in = input.replaceAll("mark", "").trim();
                    if (in.isEmpty()) {
                        throw new TaskStatusException("Please provide task number");
                    } else {
                        int index = Integer.parseInt(in) - 1;
                        if (index >= list.size() || index < 0) {
                            throw new TaskStatusException("Please provide correct task number");
                        } else {
                            if (list.get(index).getDone()) {
                                throw new TaskStatusException("Task already marked done!");
                            }
                            String oldDescription = list.get(index).toString();
                            list.get(index).Done();
                            Task newTask = list.get(index);
                            fileUpdater(oldDescription, newTask, false);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(list.get(index).toString());
                        }
                    }
                } else if (input.startsWith("unmark")) {
                    String in = input.replaceAll("unmark", "").trim();
                    if (in.isEmpty()) {
                        throw new TaskStatusException("Please provide task number");
                    } else {
                        int index = Integer.parseInt(in) - 1;
                        if (index >= list.size() || index < 0){
                            throw new TaskStatusException("Please provide correct task number");
                        } else {
                            if (!list.get(index).getDone()) {
                                throw new TaskStatusException("Task is currently marked undone");
                            }
                            String oldDescription = list.get(index).toString();
                            list.get(index).unDone();
                            Task newTask = list.get(index);
                            fileUpdater(oldDescription, newTask, false);
                            System.out.println("Ok, I've marked this task as not done yet:");
                            System.out.println(list.get(index).toString());
                        }
                    }
                } else if (input.startsWith("delete")) {
                    String in = input.replaceAll("delete", "").trim();
                    if (in.isEmpty()) {
                        throw new TaskStatusException("Please provide task number");
                    } else {
                        int index = Integer.parseInt(in) - 1;
                        if (index >= list.size() || index < 0){
                            throw new TaskStatusException("Please provide correct task number");
                        } else {
                            String oldDescription = list.get(index).toString();
                            Task removed = list.remove(index);
                            fileUpdater(oldDescription, removed, true);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(removed.toString());
                            System.out.println("Now you have " + list.size() + " task(s) in the list");
                        }
                    }
                } else {
                    Task task;
                    if (input.startsWith("todo")) {
                        String s = input;
                        if (s.replaceAll("todo","").trim().isEmpty()) {
                            throw new IncompleteTaskNameException("Please provide task name");
                        } else {
                            task = new ToDo(input.replaceAll("todo", "").trim());
                        }
                    } else if (input.startsWith("event")) {
                        String[] arr = input.split("/");
                        if (arr.length != 2) {
                            throw new IllegalArgumentException("Please write your task in the proper format");
                        } else {
                            String name = arr[0].replaceAll("event","").trim();
                            String date = arr[1];
                            if (name.isEmpty()) {
                                throw new IncompleteTaskNameException("Please provide task name");
                            } else if (date.isEmpty()) {
                                throw new MissingDateException("Please provide a time for your task");
                            } else if (!date.startsWith("at")) {
                                throw new IllegalArgumentException("Event time must be specified with at");
                                } else {
                                task = new Event(arr[0].replaceAll("event", "").trim(), arr[1].replaceAll("at", "")
                                        .trim());
                            }
                        }
                    } else if (input.startsWith("deadline")) {
                        String[] arr = input.split("/");
                        if (arr.length != 2) {
                            throw new IllegalArgumentException("Please write your task in the proper format");
                        } else {
                            String name = arr[0].replaceAll("deadline", "").trim();
                            String date = arr[1];
                            if (name.isEmpty()) {
                                throw new IncompleteTaskNameException("Please provide task name");
                            } else if (date.isEmpty()) {
                                throw new MissingDateException("Please provide a time for your task");
                            } else if (!date.startsWith("by")) {
                                throw new IllegalArgumentException("Deadline time must be specified with by");
                            } else {
                                task = new Deadline(arr[0].replaceAll("deadline", "").trim(), arr[1].replaceAll("by", "")
                                        .trim());
                            }
                        }
                    }
                    else {
                        throw new IllegalArgumentException("Please provide a proper format");
                    }
                    list.add(task);
                    writeToFile(task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + list.size() + " task(s) in the list.");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            } catch (IllegalArgumentException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Helper function to print the task description
     * @param list list of tasks
     */
    static void taskPrinter(List<Task> list) {
        String out = "";
        int num = 1;
        for (Task x : list) {
            out += num + ". " + x.toString() + "\n";
            num++;
        }
        System.out.println(out);
    }

    /**
     * Helper function to write task descriptions to the file
     * @param task task which we want information for
     */
    static void writeToFile(Task task) {
        try {
            FileWriter writer = new FileWriter("./tasks.txt", true);
            writer.write(task.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occured while writing to file");
        }
    }

    /**
     * Helper function to update file
     * @param oldDescription old task description to be updated
     * @param newTask new task description
     */
    static void fileUpdater(String oldDescription, Task newTask, boolean delete) {
        try {
            List<String> list = Files.readAllLines(Path.of("./tasks.txt"));
            for (int i = 0; i < list.size() - 1; i++) { // loop through all lines and find old line to update
                if (list.get(i).toString().equals(oldDescription)) {
                    if (!delete) {
                        list.set(i, newTask.toString());
                    } else {
                        list.remove(i);
                    }
                    break;
                }
            }

            // after task list is updated, rewrite to the file
            FileWriter writer = new FileWriter("./tasks.txt", false);
            for (String x: list) {
                writer.write(x + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error occured while updating the file");

        }
    }

    static void listInit(List<Task> list) {
        try {
            List<String> tasks = Files.readAllLines(Path.of("./tasks.txt"));
            for (String x : tasks) {
                String taskType = x.substring(1,2);
                Boolean done = x.substring(4,5).equals("X");
                String[] arr = x.substring(6).split("\\(");
                String taskName = arr[0].trim();
                // String date = arr[1].replace(")","").trim();
                Task toAdd;
                if (taskType.equals("T")) {
                    toAdd = new ToDo(taskName);
                } else if (taskType.equals("D")) {
                    toAdd = new Deadline(taskName, arr[1].replace(")", "").trim());
                } else {
                    toAdd = new Event(taskName, arr[1].replace(")","").trim());
                }

                if (toAdd == null) {
                    throw new DukeException("File could not be read properly to retrieve task list.");
                } else {
                    if (done) {
                        toAdd.Done();
                    }
                    list.add(toAdd);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occured while reading the file for existing tasks");
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

}
