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
        String[] list = new String[100];
        int counter = 0;

        while (true) {
            String s = in.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon :)");
                break;
            } else if (s.equals("list")) {
                System.out.println("------------------------------------------------------");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
                System.out.println("------------------------------------------------------");
            } else {
                System.out.println("------------------------------------------------------");
                list[counter] = s;
                counter++;
                System.out.println("added: " + s);
                System.out.println("------------------------------------------------------");
            }
        }
    }
}
