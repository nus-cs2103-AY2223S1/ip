import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int counter = 0;
    final static String logo =
            "   __ __    ____       ___               __    \n" +
            "  / // /__ / / /__    / _ \\___ ____  ___/ /__ _\n" +
            " / _  / -_) / / _ \\  / ___/ _ `/ _ \\/ _  / _ `/\n" +
            "/_//_/\\__/_/_/\\___/ /_/   \\_,_/_//_/\\_,_/\\_,_/ \n" +
            "                                               \n";

//    private static void printCommand(String str) {
//        System.out.println("added: " + str);
//        System.out.println("#########################");
//    }
//
    private static void storeTask(Task t) {
        if (counter >= 100) return;
        taskList[counter] = t;
        counter++;
    }

    private static void markTask(int i) {
        if (i >= 100) return;
        if (taskList[i] == null) return;
        taskList[i].doTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList[i]);
    }

    private static void unmarkTask(int i) {
        if (i >= 100) return;
        if (taskList[i] == null) return;
        taskList[i].undoTask();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList[i]);
    }

    private static void displayList() {
        for (int i = 0; i < 100; i++) {
            if (taskList[i] == null) break;
            System.out.println(i + 1 + ". " + taskList[i]);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        System.out.println("How can I help you today? :)");
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                displayList();
                command = sc.nextLine();
                continue;
            } else if (command.contains("unmark")) {
                String s = command.substring(7);
                int i = Integer.parseInt(s);
                unmarkTask(i - 1);
                command = sc.nextLine();
                continue;
            } else if (command.contains("mark")) {
                String s = command.substring(5);
                int i = Integer.parseInt(s);
                markTask(i - 1);
                command = sc.nextLine();
                continue;
            }
            Task t = new Task(command);
            t.printTask();
            storeTask(t);
            command = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
