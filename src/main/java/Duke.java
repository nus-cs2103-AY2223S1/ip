import java.util.*;

public class Duke {
    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Task[] list = new Task[100];
        int i = 0;
        boolean loop = true;
        System.out.println("Hello! Duke here, how can I help you?");
        Scanner sc = new Scanner(System.in);

        while (loop) {
            if (sc.hasNext("bye")) {
                loop = false;
                break;
            }

            if (sc.hasNext("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; list[j] != null; j++) {
                    System.out.println(j+1 + ". " + "[" + list[j].getStatus() + "] " + list[j].getDescription());
                }
                sc.nextLine();
                continue;
            }

            if (sc.hasNext("mark")) {
                sc.next();
                if (sc.hasNextInt()) {
                    int input = sc.nextInt();
                    try {
                        list[input - 1].markAsDone();
                        System.out.println("Good job! Task " + input + " has been completed:");
                        System.out.println("[" + list[input - 1].getStatus() + "] " + list[input - 1].getDescription());
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("You've given me an invalid task to mark!");
                    }
                }
                sc.nextLine();
                continue;
            }

            if (sc.hasNext("unmark")) {
                sc.next();
                if (sc.hasNextInt()) {
                    int input = sc.nextInt();
                    try {
                        list[input - 1].markAsUndone();
                        System.out.println("Got it! Task " + input + " has not yet been completed:");
                        System.out.println("[" + list[input - 1].getStatus() + "] " + list[input - 1].getDescription());
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("You've given me an invalid task to mark!");
                    }
                }
                sc.nextLine();
                continue;
            }

            String str = sc.nextLine();
            Task task = new Task(str);
            list[i] = task;
            i++;
            System.out.println("added: " + task.getDescription());
        }

        System.out.println("Thanks for having me!\nHave a nice day ahead!");
    }
}
