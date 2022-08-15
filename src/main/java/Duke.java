import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Greets User
        System.out.println("Hey there! I'm Tutter! \nHow can I help?");

        // Receive Input
        Scanner sc = new Scanner(System.in);
        String str = "";
        while (true) {
            str = sc.next();
            if (str.equals("bye")) {
                break;
            }

            // Echo
            System.out.println("\t" + str);
        }

        // Exits Program
        System.out.println("\tGoodbye!");
    }
}
