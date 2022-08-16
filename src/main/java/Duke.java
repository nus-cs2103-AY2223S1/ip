import java.util.Scanner;

public class Duke {

    private static void Greet() {
        String logo = " _______               \n"
                    + "|  _____|  _   _____   \n"
                    + "|  |____  | | |  __ |  \n"
                    + "|   ____| | | |  ___|  \n"
                    + "|  |____  | | | |      \n"
                    + "|_______| |_| |_|";
        System.out.println("Greetings from Elp\n" + logo);
        System.out.println("What can I help you with?");
    }

    private static void Echo() {
        Scanner sc = new Scanner(System.in);
        String i = sc.nextLine();

        while (!i.equals("bye")) {
            System.out.println(i + "\n");
            i = sc.nextLine();
        }
        {
            System.out.println("Have a nice day!");
        }
    }

    public static void main(String[] args) {
        Greet();
        Echo();
    }
}
