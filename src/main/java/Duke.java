import java.util.Scanner;
public class Duke {
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
        if (!s.equals("bye")) {
            System.out.println("\t " + s);
            System.out.println("\t____________________________________________");
            greeting();
        }
        else {
            System.out.println("\t It's a great time talking with you.\n\t See you next time!");
            System.out.println("\t____________________________________________");
        }
    }

}
