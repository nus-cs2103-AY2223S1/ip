import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String mort = "                               .---.        .-----------\n"
                + "                              /     \\  __  /    ------\n"
                + "                             / /     \\(  )/    -----\n"
                + "                            //////   ' \\/ `   ---\n"
                + "                           //// / // :    : ---\n"
                + "                          // /   /  /`    '--\n"
                + "                         //          //..\\\\\n"
                + ">>==================================UU[**]UU==================================<<\n"
                + "                                    '//||\\\\`\n"
                + "                                      ''``";
        String border = ">>====================================[**]====================================<<";
        System.out.println(mort);
        System.out.println("  Oh, it's you again...\n  Mort, begrudgingly at your service.");
        Scanner sc = new Scanner(System.in);
        System.out.println("  Hmph, what do you want now?\n" + border);

        while (true) {
            System.out.println();
            String command = sc.nextLine();
            System.out.println();
            if (command.compareTo("bye") == 0) {
                System.out.println(border + "\n"
                        + "  Good riddance, I say. With all due disrespect, leave me alone next time.\n"
                        + border);
                break;
            }
            System.out.println(border + "\n  " + command + "\n" + border);
        }

    }
}
