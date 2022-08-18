package main.java;
import java.util.Scanner;

/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Totsuka Tomofumi
 * @version Level-4, Level-5
 */
public class Duke {
    /**
     * Duke initialises with his personal logo and a welcome message before prompting for standard input
     * from the command line. 3 types of taasks are remembered by Duke and a list can be retrieved
     * if the user types "list". Tasks can be marked or unmarked done. If the user types "bye",
     * Duke terminates with a goodbye message.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        //assume no more than 100 tasks
        Task[] tasks = new Task[100];
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");

        while (true) {
            String response = scanner.nextLine().strip();
            //bye command
            if (response.equals("bye")) {
                break;
            //list command
            } else if (response.equals("list")) {
                int order = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task : tasks) {
                    if (task == null) {
                        break;
                    }
                    System.out.println(order++ + "." + task.toString());
                }
            //mark command
            } else if (response.equals("mark") || response.startsWith("mark ")) {
                try {
                    if (response.length() < 6) {
                        throw new MissingTaskNumberException();
                    }
                    int query = Integer.parseInt(response.substring(5)) - 1;
                    if (tasks[query] == null) {
                        throw new InvalidTaskNumberException();
                    }
                    tasks[query].mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[query].toString());
                } catch (NumberFormatException e) {
                    System.out.println(" ☹ OOPS!!! The task number you have inputted is invalid.");
                } catch (MissingTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! You did not input a task number.");
                } catch (InvalidTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! The task with the task number you have inputted does not exist.");
                }
            //unmark command
            } else if (response.equals("unmark") || response.startsWith("unmark ")) {
                try {
                    if (response.length() < 8) {
                        throw new MissingTaskNumberException();
                    }
                    int query = Integer.parseInt(response.substring(7)) - 1;
                    if (tasks[query] == null) {
                        throw new InvalidTaskNumberException();
                    }
                    tasks[query].unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[query].toString());
                } catch (NumberFormatException e) {
                    System.out.println(" ☹ OOPS!!! The task number you have inputted is invalid.");
                } catch (MissingTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! You did not input a task number.");
                } catch (InvalidTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! The task with the task number you have inputted does not exist.");
                }
            //add task commands
            } else {
                //successfully added a task?
                boolean success = false;
                //todo command
                if (response.equals("todo") || response.startsWith("todo ")) {
                    try {
                        if (response.length() < 6) {
                            throw new MissingDescriptionException();
                        }
                        String description = response.substring(5);
                        tasks[index] = new ToDo(description);
                        success = true;
                    } catch (MissingDescriptionException e) {
                        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    }
                //deadline command
                } else if (response.equals("deadline") || response.startsWith("deadline ")) {
                    try {
                        if (response.length() < 10) {
                            throw new MissingTokenException();
                        }
                        String subresponse = response.substring(9);
                        if (subresponse.equals("/by") || subresponse.startsWith("/by ")) {
                            throw new MissingDescriptionException();
                        }
                        if (subresponse.endsWith(" /by")) {
                            throw new MissingTimeException();
                        }
                        int tokenpos = subresponse.indexOf(" /by ");
                        if (tokenpos < 0) {
                            throw new MissingTokenException();
                        }
                        String description = subresponse.substring(0, tokenpos);
                        String time = subresponse.substring(tokenpos + 5);
                        if (description.strip().length() == 0) {
                            throw new MissingDescriptionException();
                        }
                        if (time.length() == 0) {
                            throw new MissingTimeException();
                        }
                        tasks[index] = new Deadline(description, time);
                        success = true;
                    } catch (MissingTokenException e) {
                        System.out.println(" ☹ OOPS!!! You are mising the \"/by\" token.");
                        continue;
                    } catch (MissingDescriptionException e) {
                        System.out.println(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                        continue;
                    } catch (MissingTimeException e) {
                        System.out.println(" ☹ OOPS!!! You have not indicated the time for your deadline.");
                        continue;
                    }
                //event command
                } else if (response.equals("event") || response.startsWith("event ")) {
                    try {
                        if (response.length() < 7) {
                            throw new MissingTokenException();
                        }
                        String subresponse = response.substring(6);
                        if (subresponse.equals("/at") || subresponse.startsWith("/at ")) {
                            throw new MissingDescriptionException();
                        }
                        if (subresponse.endsWith(" /at")) {
                            throw new MissingTimeException();
                        }
                        int tokenpos = subresponse.indexOf(" /at ");
                        if (tokenpos < 0) {
                            throw new MissingTokenException();
                        }
                        String description = subresponse.substring(0, tokenpos);
                        String time = subresponse.substring(tokenpos + 5);
                        if (description.strip().length() == 0) {
                            throw new MissingDescriptionException();
                        }
                        if (time.length() == 0) {
                            throw new MissingTimeException();
                        }
                        tasks[index] = new Event(description, time);
                        success = true;
                    } catch (MissingTokenException e) {
                        System.out.println(" ☹ OOPS!!! You are mising the \"/at\" token.");
                        continue;
                    } catch (MissingDescriptionException e) {
                        System.out.println(" ☹ OOPS!!! The description of a event cannot be empty.");
                        continue;
                    } catch (MissingTimeException e) {
                        System.out.println(" ☹ OOPS!!! You have not indicated the time for your event.");
                        continue;
                    }
                }
                if (success) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[index++].toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", index));
                } else {
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
