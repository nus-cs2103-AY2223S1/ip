import java.util.Scanner;

public class Duke {

    private void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I am a Store And Response Bot, otherwise known as Starboard\n" +
                "What can I do for you today?");

        while (true) {
            String next = sc.nextLine();

            if (next.equals("bye")) {
                System.out.println("Bye bye! Hope to see you soon!");
                break;
            }

            System.out.println(next);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
