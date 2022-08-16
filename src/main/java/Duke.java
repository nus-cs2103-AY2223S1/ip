import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);
    private static Task[] tasks = new Task[100];

    /**
     * Points to the current index of tasks to insert the next task in
     */
    private static int pointer = 0;

    private static void speak(String message) {
        System.out.println("============================================================\n");
        System.out.println(message);
        System.out.println("============================================================");
    }

    private static void greet() {
        speak(" Greetings! My Name is Alfred ^_^\n How may I be of service today?\n");
    }

    private static void goodbye() {
        speak(" Farewell!\n");
    }

    private static void listTasks() {
        if (pointer == 0) {
            speak(" You have not added any tasks!\n");
        } else {
            String result = " Here are your current tasks:\n";
            for (int i = 0; i < pointer; i++) {
                result += " " + (i + 1) + "." + tasks[i].toString();
            }
            speak(result);
        }
    }

    private static void addTask(String task) {
        tasks[pointer] = new Task(task);
        pointer++;
        speak(" added: " + task + "\n");
    }

    private static void markTask(int taskNum) {
        if (taskNum < pointer + 1) {
            speak(tasks[taskNum - 1].mark());
        } else {

        }
    }

    private static void unmarkTask(int taskNum) {
        if (taskNum < pointer + 1) {
            speak(tasks[taskNum - 1].unmark());
        } else {

        }
    }

    public static void main(String[] args) {
        greet();

        String cmd = "";
        while (!cmd.equals("bye")) {
            cmd = scanner.nextLine().trim();
            if (cmd.equals("bye")) {
                goodbye();
            } else if (cmd.equals("list")) {
                listTasks();
            } else if (cmd.split(" ")[0].equals("mark")) {
                try {
                    int num = Integer.parseInt(cmd.split(" ")[1]);
                    markTask(num);
                } catch (NumberFormatException e) {

                }
            } else if (cmd.split(" ")[0].equals("unmark")) {
                try {
                    int num = Integer.parseInt(cmd.split(" ")[1]);
                    unmarkTask(num);
                } catch (NumberFormatException e) {

                }
            } else {
                addTask(cmd);
            }
        }
    }
}
