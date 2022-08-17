import java.util.Scanner;

public class Echo {
    private static final String greet =
            "OMG HII! I am \n" +
            "███████╗██╗░░░░░░█████╗░██████╗░███████╗███╗░░██╗\n" +
            "██╔════╝██║░░░░░██╔══██╗██╔══██╗██╔════╝████╗░██║\n" +
            "█████╗░░██║░░░░░██║░░██║██████╔╝█████╗░░██╔██╗██║\n" +
            "██╔══╝░░██║░░░░░██║░░██║██╔══██╗██╔══╝░░██║╚████║\n" +
            "██║░░░░░███████╗╚█████╔╝██║░░██║███████╗██║░╚███║\n" +
            "╚═╝░░░░░╚══════╝░╚════╝░╚═╝░░╚═╝╚══════╝╚═╝░░╚══╝\n" +
                    "What can I do for you?\n";

    public Echo() {
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command + "\n");
            sc = new Scanner(System.in);
            command = sc.nextLine();
        }
        System.out.println("Hiks. I'm sad, but see you again!!");
    }

}
