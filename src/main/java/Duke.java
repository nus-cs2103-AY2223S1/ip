import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello there! I'm Duke!");
        System.out.println("How can I help you?");

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTasks = 0;

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Ok, see you next time!");
                break;
            }

            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            String argument = null;
            boolean hasArgument = splitInput.length > 1;
            if (hasArgument) {
                argument = splitInput[1];
            }
            switch (command) {
                case "list": {
                    for (int i = 0; i < numTasks; i++) {
                        System.out.printf("%d. %s%n", i + 1, tasks[i]);
                    }
                    break;
                }
                case "mark": {
                    if (hasArgument) {
                        int taskNum = Integer.parseInt(argument);
                        Task task = tasks[taskNum - 1];
                        task.markAsDone();
                        System.out.printf("Well done! I've marked task %d as done:%n", taskNum);
                        System.out.println(task);
                    } else {
                        System.out.println("Sorry, you need to tell me which task to mark.");
                    }
                    break;
                }
                case "unmark": {
                    if (hasArgument) {
                        int taskNum = Integer.parseInt(argument);
                        Task task = tasks[taskNum - 1];
                        task.markAsUndone();
                        System.out.printf("Sure, I've marked task %d as not done:%n", taskNum);
                        System.out.println(task);
                    } else {
                        System.out.println("Sorry, you need to tell me which task to unmark.");
                    }
                    break;
                }
                case "todo": {
                    if (hasArgument) {
                        Todo todo = new Todo(argument);
                        tasks[numTasks++] = todo;
                        System.out.println("Got it, I've added this to-do:");
                        System.out.println(todo);
                    } else {
                        System.out.println("Sorry, I will need a description for the to-do.");
                    }
                    break;
                }
                case "deadline": {
                    if (hasArgument) {
                        try {
                            Deadline deadline = new Deadline(argument);
                            tasks[numTasks++] = deadline;
                            System.out.println("Got it, I've added this deadline:");
                            System.out.println(deadline);
                        } catch (InvalidTaskFormatException e) {
                            System.out.printf("Sorry, I could not create the deadline '%s'.%n", argument);
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Sorry, I will need a description for the deadline.");
                    }
                    break;
                }
                case "event": {
                    if (hasArgument) {
                        try {
                            Event event = new Event(argument);
                            tasks[numTasks++] = event;
                            System.out.println("Got it, I've added this event:");
                            System.out.println(event);
                        } catch (InvalidTaskFormatException e) {
                            System.out.printf("Sorry, I could not create the event '%s'.%n", argument);
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Sorry, I will need a description for the event.");
                    }
                    break;
                }
                default: {
                    System.out.printf("Sorry, I don't understand what %s means. :/%n", command);
                    break;
                }
            }
        }
    }
}
