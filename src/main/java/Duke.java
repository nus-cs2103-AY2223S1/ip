import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws DukeException, IllegalArgumentException {

        List<Task> list = new ArrayList<>(); // to store list of inputs

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
                        if (index > list.size() || index < 0){
                            throw new TaskStatusException("Please provide correct task number");
                        } else {
                            list.get(index).Done();
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
                        if (index > list.size() || index < 0){
                            throw new TaskStatusException("Please provide correct task number");
                        } else {
                            list.get(index).unDone();
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
                        if (index > list.size() || index < 0){
                            throw new TaskStatusException("Please provide correct task number");
                        } else {
                            Task removed = list.remove(index);
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

    static void taskPrinter(List<Task> list) {
        String out = "";
        int num = 1;
        for (Task x : list) {
            out += num + ". " + x.toString() + "\n";
            num++;
        }
        System.out.println(out);
    }

}
