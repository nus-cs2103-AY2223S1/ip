import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        List<Task> storedTasks = new ArrayList<>();
        while (true) {
            String str = sc.nextLine();
            try {
                if ("bye".equals(str)) {
                    System.out.println("Bye! Hope to see you again soon");
                    break;
                } else if ("list".equals(str)) {
                    for (int i = 0; i < storedTasks.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, storedTasks.get(i));
                    }
                    System.out.println("That's all!");
                } else {
                    String[] command = str.split(" ", 2);
                    if (command[0].equals("mark")) {
                        try {
                            int i = Integer.parseInt(command[1]) - 1;
                            if (i < 0 || i >= storedTasks.size()) {
                                throw new DukeException("Exception: Invalid task number.");
                            };
                            Task task = storedTasks.get(i);
                            task.markAsDone();
                            System.out.println("Marked task " + (i + 1) + " as done!");
                            System.out.printf("%d. %s\n", i + 1, task);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Exception: Invalid command syntax.");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Invalid task number.");
                        }
                    } else if(command[0].equals("unmark")) {
                        try {
                            int i = Integer.parseInt(command[1]) - 1;
                            if (i < 0 || i >= storedTasks.size()) {
                                throw new DukeException("Exception: Invalid task number.");
                            };
                            Task task = storedTasks.get(i);
                            task.markAsNotDone();
                            System.out.println("Marked task " + (i + 1) + " as not done!");
                            System.out.printf("%d. %s\n", i + 1, task);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Exception: Invalid command syntax.");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Invalid task number.");
                        }
                    } else if(command[0].equals("delete")) {
                        try {
                            int i = Integer.parseInt(command[1]) - 1;
                            if (i < 0 || i >= storedTasks.size()) {
                                throw new DukeException("Exception: Invalid task number.");
                            };
                            Task task = storedTasks.get(i);
                            System.out.println("The following task is deleted:");
                            System.out.printf("%d. %s\n", i + 1, task);
                            storedTasks.remove(i);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Exception: Invalid command syntax.");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Invalid task number.");
                        }
                    } else if(command[0].equals("todo")) {
                        try {
                            if (command[1].length() == 0) {
                                throw new DukeException("Exception: Empty task entry.");
                            }
                            Task task = new Todo(command[1]);
                            storedTasks.add(task);
                            System.out.printf("Got it! I stored this task:\n" + task +
                                    "\nNow you have %d tasks in the list.\n", storedTasks.size());
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Empty task entry.");
                        }
                    } else if(command[0].equals("deadline")) {
                        try {
                            if (command[1].length() == 0) {
                                throw new DukeException("Exception: Empty task entry.");
                            }
                            String[] taskAndDateTime = command[1].split("/by ", 2);
                            if (taskAndDateTime.length != 2) {
                                throw new DukeException("Exception: No date-time.");
                            }
                            Task task = new Deadline(taskAndDateTime[0], taskAndDateTime[1]);
                            storedTasks.add(task);
                            System.out.printf("Got it! I stored this task:\n" + task +
                                    "\nNow you have %d tasks in the list.\n", storedTasks.size());
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Empty task entry.");
                        }
                    } else if(command[0].equals("event")) {
                        try {
                            if (command[1].length() == 0) {
                                throw new DukeException("Exception: Empty task entry.");
                            }
                            String[] taskAndDateTime = command[1].split("/at ");
                            if (taskAndDateTime.length != 2) {
                                throw new DukeException("Exception: No date-time.");
                            }
                            System.out.println(taskAndDateTime.length);
                            Task task = new Event(taskAndDateTime[0], taskAndDateTime[1]);
                            storedTasks.add(task);
                            System.out.printf("Got it! I stored this task:\n" + task +
                                    "\nNow you have %d tasks in the list.\n", storedTasks.size());
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Empty task entry.");
                        }
                    } else {
                        throw new DukeException("Exception: Unknown command.");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}

