import java.util.Scanner;

public class Luffy {
    public static void main(String[] args) {
        String logo = "██╗░░░░░██╗░░░██╗███████╗███████╗██╗░░░██╗\n"
                + "██║░░░░░██║░░░██║██╔════╝██╔════╝╚██╗░██╔╝\n"
                + "██║░░░░░██║░░░██║█████╗░░█████╗░░░╚████╔╝░\n"
                + "██║░░░░░██║░░░██║██╔══╝░░██╔══╝░░░░╚██╔╝░░\n"
                + "███████╗╚██████╔╝██║░░░░░██║░░░░░░░░██║░░░\n"
                + "╚══════╝░╚═════╝░╚═╝░░░░░╚═╝░░░░░░░░╚═╝░░░";
        System.out.println("------------------------------------------------------");
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------------------");

        //Actual Luffy Logic:
        Scanner in = new Scanner(System.in);

        while (true) {
            String s = in.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon :)");
                break;
            } else {
                System.out.println("------------------------------------------------------");
                System.out.println(s);
                System.out.println("------------------------------------------------------");
            }
        }
    }
}
