import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ChatBot chatBot = new ChatBot("duke");

        chatBot.greet();

        boolean quit = false;
        String input;
        String[] command;
        String time; // for the deadline or time of the event


        while(!quit) {

            input = sc.next();

            switch (input) {

                case "bye":
                    quit = true;
                    chatBot.bye();
                    break;
                case "list":
                    chatBot.printTasks();
                    break;
                case "mark":
                    chatBot.markDone(sc.nextInt() - 1);
                    sc.nextLine();
                    break;
                case "unmark":
                    chatBot.markUndone(sc.nextInt() - 1);
                    sc.nextLine();
                    break;
                case "deadline":
                    command = sc.nextLine().split("/by ");
                    chatBot.addTask(new Deadline(command[0], command[1]));
                    break;
                case "event":
                    command = sc.nextLine().split("/at ");
                    chatBot.addTask(new Events(command[0], command[1]));
                    break;
                case "todo":
                    chatBot.addTask(new ToDo(sc.nextLine()));
                    break;
                case "delete":
                    chatBot.delete(sc.nextInt() - 1);
                    sc.nextLine();
                default:
                    break;
            }
        }
    }
}
