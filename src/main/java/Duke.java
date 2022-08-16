import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);
    private static String[] tasks = new String[100];

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
            String result = "";
            for (int i = 0; i < pointer; i++) {
                result += " " + (i + 1) + ". " + tasks[i] + "\n";
            }
            speak(result);
        }
    }

    private static void addTask(String task) {
        tasks[pointer] = task;
        pointer++;
        speak(" added: " + task + "\n");
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
            } else {
                addTask(cmd);
            }
        }
    }
}
