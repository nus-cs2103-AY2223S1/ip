import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        final int MAXSIZE = 100;
        Task[] storedTasks = new Task[MAXSIZE];
        int index = 0;
        while (true) {
            String str = sc.nextLine();
            try {
                if ("bye".equals(str)) {
                    System.out.println("Bye! Hope to see you again soon");
                    break;
                } else if ("list".equals(str)) {
                    int p = 0;
                    while (p < MAXSIZE && storedTasks[p] != null) {
                        System.out.printf("%d. %s\n", p + 1, storedTasks[p]);
                        p++;
                    }
                } else {
                    String[] command = str.split(" ", 2);
                    if(command[0].equals("mark")) {
                        try {
                            int i = Integer.parseInt(command[1]) - 1;
                            assert i >= 0 && i < index;
                            storedTasks[i].markAsDone();
                            System.out.println("Marked task " + (i + 1) + " as done!");
                            System.out.printf("%d. %s\n", i + 1, storedTasks[i]);
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Invalid mark command syntax.");
                        } catch (AssertionError e) {
                            throw new DukeException("Exception: Invalid task number.");
                        }
                    } else if(command[0].equals("unmark")) {
                        try {
                            int i = Integer.parseInt(command[1]) - 1;
                            assert i >= 0 && i < index;
                            storedTasks[i].markAsNotDone();
                            System.out.println("Marked task " + (i + 1) + " as not done!");
                            System.out.printf("%d. %s\n", i + 1, storedTasks[i]);
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Invalid mark command syntax.");
                        } catch (AssertionError e) {
                            throw new DukeException("Exception: Invalid task number.");
                        }
                    } else if(command[0].equals("todo")) {
                        try {
                            Task task = new Todo(command[1]);
                            storedTasks[index++] = task;
                            System.out.printf("Got it! I stored this task:\n" + task + "\nNow you have %d tasks in the list.\n", index);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Empty task entry.");
                        }
                    } else if(command[0].equals("deadline")) {
                        try {
                            String[] taskAndDateTime = command[1].split("/by ", 2);
                            assert taskAndDateTime.length == 2;
                            Task task = new Deadline(taskAndDateTime[0], taskAndDateTime[1]);
                            storedTasks[index++] = task;
                            System.out.printf("Got it! I stored this task:\n" + task + "\nNow you have %d tasks in the list.\n", index);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Empty task entry.");
                        } catch (AssertionError e) {
                            throw new DukeException("Exception: No date-time.");
                        }
                    } else if(command[0].equals("event")) {
                        try {
                            String[] taskAndDateTime = command[1].split("/at ");
                            assert taskAndDateTime.length == 2;
                            Task task = new Event(taskAndDateTime[0], taskAndDateTime[1]);
                            storedTasks[index++] = task;
                            System.out.printf("Got it! I stored this task:\n" + task + "\nNow you have %d tasks in the list.\n", index);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("Exception: Empty task entry.");
                        } catch (AssertionError e) {
                            throw new DukeException("Exception: No date-time.");
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

