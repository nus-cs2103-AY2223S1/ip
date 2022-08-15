import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" +
                      "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" +
                      "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        Responder responder = new Responder();
        while (true) {
            System.out.print("\n> ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(responder.respond(input));
                break;
            } else {
                System.out.println(responder.respond(input));
            }
        }
    }
}
