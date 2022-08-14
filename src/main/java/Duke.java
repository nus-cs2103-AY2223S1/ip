import java.util.Scanner;

public class Duke {
    public static String printOut(String str) {
        String line = "____________________________________________________________\n";
        return line + str + "\n" + line;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println(printOut("Hello! I'm Duke.\n" +
                "What can I do for you?"));
        String next = input.nextLine();

        while (!next.equals("bye")) {
            System.out.println(printOut(next));
            next = input.nextLine();
        }

        System.out.println(printOut("See you later. Bye!"));
    }
}
