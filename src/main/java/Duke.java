package main.java;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Totsuka Tomofumi
 * @version Level-6
 */
public class Duke {
    /**
     * Duke initialises with his personal logo and a welcome message before prompting for standard input
     * from the command line. 3 types of tasks can be remembered or deleted by Duke and a list can be retrieved
     * if the user types "list". Tasks can be marked or unmarked done. If the user types "bye",
     * Duke terminates with a goodbye message.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        //assume no more than 100 tasks
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");

        while (scanner.hasNextLine()) {
            //strip() to allow for any (unintentional) whitespaces before or after
            //also, whitespaces after the last valid char in a description/time input will be truncated.
            String response = scanner.nextLine().strip();
            //bye command
            if (response.equals("bye")) {
                break;
            //list command
            } else if (response.equals("list")) {
                int order = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task : tasks) {
                    System.out.println(order++ + "." + task.toString());
                }
            //mark command
            } else if (response.equals("mark") || response.startsWith("mark ")) {
                try {
                    if (response.length() < 6) {
                        throw new MissingTaskNumberException();
                    }
                    //will not allow if format "mark<space>int" is not followed
                    int query = Integer.parseInt(response.substring(5)) - 1;
                    if (query > tasks.size() - 1 || query < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    tasks.get(query).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(query).toString());
                } catch (NumberFormatException e) {
                    System.out.println(" ☹ OOPS!!! The task number you have inputted is invalid.");
                } catch (MissingTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! You did not input a task number.");
                } catch (InvalidTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! The task with the task number you have inputted does not exist" +
                            " or is invalid.");
                }
            //unmark command
            } else if (response.equals("unmark") || response.startsWith("unmark ")) {
                try {
                    if (response.length() < 8) {
                        throw new MissingTaskNumberException();
                    }
                    //will not allow if format "mark<space>int" is not followed
                    int query = Integer.parseInt(response.substring(7)) - 1;
                    if (query > tasks.size() - 1 || query < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    tasks.get(query).unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(query).toString());
                } catch (NumberFormatException e) {
                    System.out.println(" ☹ OOPS!!! The task number you have inputted is invalid.");
                } catch (MissingTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! You did not input a task number.");
                } catch (InvalidTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! The task with the task number you have inputted does not exist" +
                            " or is invalid.");
                }
            //delete command
            } else if (response.equals("delete") || response.startsWith("delete ")) {
                try {
                    if (response.length() < 8) {
                        throw new MissingTaskNumberException();
                    }
                    //will not allow if format "mark<space>int" is not followed
                    int query = Integer.parseInt(response.substring(7)) - 1;
                    if (query > tasks.size() - 1 || query < 0) {
                        throw new InvalidTaskNumberException();
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(query).toString());
                    tasks.remove(query);
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                } catch (NumberFormatException e) {
                    System.out.println(" ☹ OOPS!!! The task number you have inputted is invalid.");
                } catch (MissingTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! You did not input a task number.");
                } catch (InvalidTaskNumberException e) {
                    System.out.println(" ☹ OOPS!!! The task with the task number you have inputted does not exist" +
                            " or is invalid.");
                }
            //add task commands
            } else {
                //successfully added a task?
                boolean success = false;
                //todo command
                if (response.equals("todo") || response.startsWith("todo ")) {
                    try {
                        //because of strip() called on response earlier,
                        //todo command with whitespaces followed are not valid descriptions.
                        if (response.length() < 6) {
                            throw new MissingDescriptionException();
                        }
                        String description = response.substring(5);
                        tasks.add(new ToDo(description));
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
                        //to ensure whitespaces alone as a description or time is not allowed
                        if (description.strip().length() == 0) {
                            throw new MissingDescriptionException();
                        }
                        if (time.length() == 0) {
                            throw new MissingTimeException();
                        }
                        tasks.add(new Deadline(description, time));
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
                        //to ensure whitespaces alone as a description or time is not allowed
                        if (description.strip().length() == 0) {
                            throw new MissingDescriptionException();
                        }
                        if (time.length() == 0) {
                            throw new MissingTimeException();
                        }
                        tasks.add(new Event(description, time));
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
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                } else {
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means. :-(");
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
