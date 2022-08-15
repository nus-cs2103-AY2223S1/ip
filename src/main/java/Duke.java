import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static class Task {
        private final String name;
        private boolean done = false;

        public Task(String name) {
            this.name = name;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        public String toString() {
            return String.format("[%s] %s", this.done ? "x" : " ", this.name);
        }
    }

    private final static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        customPrint("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (!(userInput = sc.nextLine()).equals("bye")) {
            handleCommand(userInput);
        }
        customPrint("Bye. Hope to see you again soon!");
    }

    private static void customPrint(String s) {
        System.out.println("-------------------");
        System.out.println(s);
        System.out.println("-------------------");
    }

    private static void handleCommand(String s) {
        String[] args = s.split(" ");
        if (args.length == 0) {
            customPrint("Please enter something!");
            return;
        }
        switch (args[0]) {
            case "list":
                StringBuilder stringBuilder = new StringBuilder();
                int n = list.size();
                for (int i = 0; i < n; i++) {
                    stringBuilder.append(String.format("%d. %s", i + 1, list.get(i)));
                    if (i != n - 1) {
                        stringBuilder.append("\n");
                    }
                }
                customPrint(stringBuilder.toString());
                break;
            case "mark":
                if (args.length < 2) {
                    customPrint("Invalid list index!\nUsage: `mark 2`");
                    return;
                }
                try{
                    int number = Integer.parseInt(args[1]);
                    Task item = list.get(number - 1);
                    item.setDone(true);
                    customPrint("Nice! I've marked this task as done:\n  " + item);
                }
                catch (NumberFormatException ex) {
                    customPrint("Invalid list index!\nUsage: `mark 2`");
                }
                break;
            case "unmark":
                if (args.length < 2) {
                    customPrint("Invalid list index!\nUsage: `unmark 2`");
                    return;
                }
                try{
                    int number = Integer.parseInt(args[1]);
                    Task item = list.get(number - 1);
                    item.setDone(false);
                    customPrint("OK, I've marked this task as not done yet:\n  " + item);
                }
                catch (NumberFormatException ex) {
                    customPrint("Invalid list index!\nUsage: `unmark 2`");
                }
                break;
            default:
                addToList(s);
                customPrint("added: " + s);
        }
    }

    private static void addToList(String s) {
        list.add(new Task(s));
    }
}
