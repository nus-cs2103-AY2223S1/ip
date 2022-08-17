import java.util.Scanner;

public class Duke {
    public static void printIndented(Object obj) {
        System.out.println("    " + obj);
    }

    public static void printHorizontalLine() {
        printIndented("_".repeat(50));
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        String name = "Duke";
        int pointer = 0;
        printHorizontalLine();
        printIndented("Hello! I'm " + name);
        printIndented("What can I do for you?");
        printHorizontalLine();
        while (true) {
            try {
                String input = sc.nextLine();
                printHorizontalLine();
                if (input.equals("bye")) {
                    printIndented("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    printIndented("Here are the tasks in your list:");
                    for (int i = 1; i <= pointer; i++) {
                        printIndented(i + "." + tasks[i - 1]);
                    }
                } else if (input.startsWith("mark")) {
                    int taskID = Integer.parseInt(input.split(" ")[1]);
                    printIndented("Nice! I've marked this task as done:");
                    tasks[taskID - 1].markAsDone();
                    printIndented(tasks[taskID - 1]);
                } else if (input.startsWith("unmark")) {
                    int taskID = Integer.parseInt(input.split(" ")[1]);
                    printIndented("OK, I've marked this task as not done yet:");
                    tasks[taskID - 1].unmarkAsNotDone();
                    printIndented(tasks[taskID - 1]);
                } else {
                    String taskName;
                    String by;
                    String at;
                    if (input.startsWith("todo")) {
                        taskName = input.replace("todo", "").strip();
                        if (taskName.isEmpty()) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        tasks[pointer++] = new ToDos(taskName);
                    } else if (input.startsWith("deadline")) {
                        String[] temp = input.replace("deadline", "").strip().split("/by");
                        taskName = temp[0].strip();
                        by = temp[1].strip();
                        tasks[pointer++] = new Deadlines(taskName, by);
                    } else if (input.startsWith("event")) {
                        String[] temp = input.replace("event", "").strip().split("/at");
                        taskName = temp[0].strip();
                        at = temp[1].strip();
                        tasks[pointer++] = new Events(taskName, at);
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    printIndented("Got it. I've added this task:");
                    printIndented("  " + tasks[pointer - 1]);
                    printIndented("Now you have " + pointer + " tasks in the list.");
                }
            } catch (DukeException d) {
                printIndented(d.getMessage());
            }
            printHorizontalLine();
        }
    }
}
