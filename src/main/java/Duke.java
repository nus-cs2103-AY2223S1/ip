import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    protected static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String startLine = "---->".repeat(10);
        String endLine = "<----".repeat(10);
        String greeting = "Hello! Imma Duke!\n What can I do for you?";
        String farewell = "Bye. Duke misses you.";

        System.out.println(startLine);
        System.out.println(greeting);
        System.out.println(endLine);

        Scanner dukeSc = new Scanner(System.in);

        while (true) {
            String input = dukeSc.nextLine();
            System.out.println(startLine);

            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(farewell);
                    return;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                }  else if (input.startsWith("mark ") || input.startsWith("unmark ") || input.startsWith("delete ")) {
                    try {
                        if (input.startsWith("mark ")) {
                            // Parse substring starting from index 5
                            int taskNum = Integer.parseInt(input.substring(5)) - 1;
                            Task t = list.get(taskNum);
                            t.markAsDone();
                            System.out.println("Nice! I've marked this tasks as done:");
                            System.out.println(t);
                        } else if (input.startsWith("unmark ")) {
                            // Parse substring starting from index 7
                            int taskNum = Integer.parseInt(input.substring(7)) - 1;
                            Task t = list.get(taskNum);
                            t.unmarkAsDone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(t);
                        } else if (input.startsWith("delete ")) {
                            // Parse substring starting from index 7
                            int taskNum = Integer.parseInt(input.substring(7)) - 1;
                            Task t = list.remove(taskNum);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(t);
                            System.out.printf("Now you have %d task(s) in the list.%n", list.size());
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        int listSize = list.size();
                        System.out.printf("Please enter a valid number from 1 to %d.%n", listSize);
                    }
                } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                    try {
                        Task newTask = null;
                        if (input.startsWith("todo ")) {
                            String todoInput = input.substring(5).trim();
                            if (todoInput.length() == 0) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            newTask = new Todo(input.substring(5));
                        } else if (input.startsWith("deadline ")) {
                            String[] deadlineInputs = input.substring(9).split(" /by ", 2);
                            if (deadlineInputs.length < 2 || deadlineInputs[1].trim().equals("")) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            }
                            newTask = new Deadline(deadlineInputs[0], deadlineInputs[1]);
                        } else if (input.startsWith("event ")) {
                            String[] eventInputs = input.substring(6).split(" /at ", 2);
                            if (eventInputs.length < 2 || eventInputs[1].trim().equals("")) {
                                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                            }
                            newTask = new Event(eventInputs[0], eventInputs[1]);
                        }
                        list.add(newTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask);
                        System.out.printf("Now you have %d task(s) in the list.%n", list.size());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dukeException) {
                System.out.println(dukeException.getMessage());
            } finally {
                System.out.println(endLine);
            }
        }
    }
}
