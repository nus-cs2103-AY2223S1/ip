import java.util.Scanner;

public class Sally {
    public static void main(String[] args) {
        border();
        System.out.println("Hello! I'm Sally \uD83D\uDE04");
        System.out.println("What can I do for you?");
        border();
        messaging();
    }

    public static void messaging() {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        if (message.equals("bye")) {
            border();
            System.out.println("    Until next time!");
            border();
        } else {
            border();
            System.out.println("    " + message);
            border();
            messaging();
        }
    }

    public static void border () {
        System.out.println("-------------------------------------------------------------------------------------");
    }
}