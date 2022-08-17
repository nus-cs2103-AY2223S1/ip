import java.util.Scanner;
public class Duke {

    private static String[] memory = new String[100];
    private static int memoryPointer = 0;
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
        String s = scn.nextLine();
        System.out.println("\t____________________________________________");
        if (s.equals("bye")) {
            System.out.println("\t It's a great time talking with you.\n\t See you next time!");
            System.out.println("\t____________________________________________");
        }
        else if (s.equals("list")) {
            for (int i = 0; i < memoryPointer; i++) {
                System.out.println("\t 1. " + memory[i]);
            }
            System.out.println("\t____________________________________________");
            greeting();
        }
        else {
            memory[memoryPointer] = s;
            memoryPointer++;
            System.out.println("\t added: " + s);
            System.out.println("\t____________________________________________");
            greeting();
        }
    }

}
