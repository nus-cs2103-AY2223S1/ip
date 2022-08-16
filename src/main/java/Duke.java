import java.util.*;

public class Duke {

    private static Task[] addedTask = new Task[100];
    private static int addedTaskCount = 0;

    private static void addTask(String task) {
        Duke.addedTask[Duke.addedTaskCount] = new Task(task);
        Duke.addedTaskCount++;
    }

    private static void listTask() {
        for (int i = 0; i < Duke.addedTask.length; ++i) {
            if (Duke.addedTask[i] == null) {
                return;
            }
            System.out.println((i + 1) + "." + Duke.addedTask[i]);
        }
    }

    private static void markTask(int index) {
        if (Duke.addedTask[index - 1] == null) {
            System.out.println("Task does not exist!");
            return;
        }
        Duke.addedTask[index - 1].mark();
    }

    private static void unmarkTask(int index) {
        if (Duke.addedTask[index - 1] == null) {
            System.out.println("Task does not exist!");
            return;
        }
        Duke.addedTask[index - 1].unmark();
    }

    private static void greet() {
        System.out.println("Hi, my name is Duke\nand it's power hour!");
        System.out.println("***********************");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Come again soon!");
                System.exit(0);
            } else if (command.equals("list")) {
                Duke.listTask();
            } else if (command.startsWith("mark ")) {
                String[] splitCommand = command.split(" ");
                Duke.markTask(Integer.parseInt(splitCommand[1]));
            } else if (command.startsWith("unmark ")) {
                String[] splitCommand = command.split(" ");
                Duke.unmarkTask(Integer.parseInt(splitCommand[1]));
            } else if (command.equals("")) {
                continue;
            } else {
                Duke.addTask(command);
                System.out.println("added: " + command);
            }
        }
    }

    public static void main(String[] args) {
        Duke.greet();
    }
}
