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
        TaskList taskList = new TaskList();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Ok, see you next time!");
                break;
            }

            String[] splitInput = input.split(" ", 2);
            Command command = Command.fromName(splitInput[0]);
            if (command == null) {
                System.out.printf("Sorry, I don't understand what %s means. :/%n", splitInput[0]);
                continue;
            }
            String argument = null;
            boolean hasArgument = splitInput.length > 1;
            if (hasArgument) {
                argument = splitInput[1];
            }
            switch (command) {
                case LIST: {
                    if (taskList.isEmpty()) {
                        System.out.println("You have no tasks at the moment!");
                    } else {
                        int i = 1;
                        for (Task task : taskList) {
                            System.out.printf("%d. %s%n", i, task);
                            i++;
                        }
                    }
                    break;
                }
                case MARK: {
                    if (hasArgument) {
                        int taskNum = Integer.parseInt(argument);
                        Task task = taskList.getTask(taskNum - 1);
                        task.markAsDone();
                        System.out.printf("Well done! I've marked task %d as done:%n", taskNum);
                        System.out.println(task);
                    } else {
                        System.out.println("Sorry, you need to tell me which task to mark.");
                    }
                    break;
                }
                case UNMARK: {
                    if (hasArgument) {
                        int taskNum = Integer.parseInt(argument);
                        Task task = taskList.getTask(taskNum - 1);
                        task.markAsUndone();
                        System.out.printf("Sure, I've marked task %d as not done:%n", taskNum);
                        System.out.println(task);
                    } else {
                        System.out.println("Sorry, you need to tell me which task to unmark.");
                    }
                    break;
                }
                case TODO: {
                    if (hasArgument) {
                        Todo todo = new Todo(argument);
                        taskList.addTask(todo);
                        System.out.println("Got it, I've added this to-do:");
                        System.out.println(todo);
                    } else {
                        System.out.println("Sorry, I will need a description for the to-do.");
                    }
                    break;
                }
                case DEADLINE: {
                    if (hasArgument) {
                        try {
                            Deadline deadline = new Deadline(argument);
                            taskList.addTask(deadline);
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
                case EVENT: {
                    if (hasArgument) {
                        try {
                            Event event = new Event(argument);
                            taskList.addTask(event);
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
                case DELETE: {
                    if (hasArgument) {
                        int taskId = Integer.parseInt(argument) - 1;
                        Task task = taskList.getTask(taskId);
                        System.out.println("Ok, I've deleted this task:");
                        System.out.println(task);
                        taskList.deleteTask(taskId);
                        if (taskList.isEmpty()) {
                            System.out.println("You have no more tasks left in your list!");
                        } else {
                            System.out.printf("You have %d task(s) left in your list.%n", taskList.size());
                        }
                    } else {
                        System.out.println("Sorry, you need to tell me which task to delete.");
                    }
                    break;
                }
            }
        }
    }
}
