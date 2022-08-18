import java.util.Scanner;
import java.util.ArrayList; 

public class Duke {

    private static class Task {
        private String content;
        private Boolean status;

        public Task(String content) {
            this.content = content;
            this.status = false;
        }

        public void markComplete() {
            this.status = true;
        }

        public void unmarkComplete() {
            this.status = false;
        }

        @Override
        public String toString() {
            if (status) {
                return String.format("[x] %s", content);
            } else {
                return String.format("[ ] %s", content);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.markDone();
    }

    public static void echo() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n \tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + input);
            System.out.println("\t____________________________________________________________");
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        scanner.close();
        return;
    }

    public static void addList() {
        ArrayList<String> list = new ArrayList<>();
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n \tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            if (input.toLowerCase().equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("\t%d: %s", i + 1, list.get(i)));
                }
                System.out.println("\t____________________________________________________________");
                continue;
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\tadded: " + input);
            System.out.println("\t____________________________________________________________");
            list.add(input);
        }
        
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        scanner.close();
        return;
    }

    public static void markDone() {
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n \tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            if (input.toLowerCase().equals("list")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("\t%d:%s", i + 1, list.get(i).toString()));
                }
                System.out.println("\t____________________________________________________________");
                continue;
            }
            if (input.toLowerCase().contains("unmark")) {
                int index = Integer.parseInt(input.replace("unmark", "").replaceAll(" ", "")) - 1;
                list.get(index).unmarkComplete();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t" + list.get(index).toString());
                System.out.println("\t____________________________________________________________");
                continue;
            }
            if (input.toLowerCase().contains("mark")) {
                int index = Integer.parseInt(input.replace("mark", "").replaceAll(" ", "")) - 1;
                list.get(index).markComplete();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + list.get(index).toString());
                System.out.println("\t____________________________________________________________");
                continue;
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\tadded: " + input);
            System.out.println("\t____________________________________________________________");
            list.add(new Task(input));
        }
        
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        scanner.close();
        return;
    }
}
