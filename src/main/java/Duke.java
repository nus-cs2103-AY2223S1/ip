import java.util.Scanner;
public class Duke {

    private static Task[] tasks = new Task[100];
    private static int pointer = 0;
    public static void main(String[] args) {
        String logo = "\n" +
        "   ██▓    ▄▄▄       ███▄ ▄███▓▓█████▄  ▄▄▄      \n" +
        "  ▓██▒   ▒████▄    ▓██▒▀█▀ ██▒▒██▀ ██▌▒████▄    \n" +
        "  ▒██░   ▒██  ▀█▄  ▓██    ▓██░░██   █▌▒██  ▀█▄  \n" +
        "  ▒██░   ░██▄▄▄▄██ ▒██    ▒██ ░▓█▄   ▌░██▄▄▄▄██ \n" +
        "  ░██████▒▓█   ▓██▒▒██▒   ░██▒░▒████▓  ▓█   ▓██▒\n" +
        "  ░ ▒░▓  ░▒▒   ▓▒█░░ ▒░   ░  ░ ▒▒▓  ▒  ▒▒   ▓▒█░\n" +
        "  ░ ░ ▒  ░ ▒   ▒▒ ░░  ░      ░ ░ ▒  ▒   ▒   ▒▒ ░\n" +
        "    ░ ░    ░   ▒   ░      ░    ░ ░  ░   ░   ▒   \n" +
        "      ░  ░     ░  ░       ░      ░          ░  ░\n" +
        "                               ░                \n";

        System.out.print(logo);
        System.out.println("Hi, I am LaMDA.\nHow may I assist you today?\n");
        greeting();

    }
    public static void greeting() {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        System.out.println("\t____________________________________________");
        switch (s) {
            case "bye":
                System.out.println("\t It's a great time talking with you.\n\t See you next time!");
                System.out.println("\t____________________________________________");
                break;
            case "list":
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < pointer; i++) {
                    System.out.println(tasks[i].toString());
                }
                System.out.println("\t____________________________________________");
                greeting();
                break;
            case "mark": {
                int num = scn.nextInt();
                tasks[num - 1].markAsDone();
                System.out.println("\t I've marked this task as done:");
                System.out.println(tasks[num - 1].toString());
                System.out.println("\t____________________________________________");
                greeting();
                break;
            }
            case "unmark": {
                int num = scn.nextInt();
                tasks[num - 1].markAsNotDone();
                System.out.println("\t I've marked this task as not done yet:");
                System.out.println(tasks[num - 1].toString());
                System.out.println("\t____________________________________________");
                greeting();
                break;
            }
            default:
                s += scn.nextLine();
                tasks[pointer] = new Task(s, pointer + 1);
                pointer++;
                System.out.println("\t added: " + s);
                System.out.println("\t____________________________________________");
                greeting();
                break;
        }
    }

}
