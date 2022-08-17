import java.util.Scanner;

public class Duke {
    private static final String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String separator = "––––––––––––––––––––––––––––––––––––––––\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.print("Tell me what you need\n");
        String userInput = sc.nextLine();
        while (!(userInput.equals("Bye") || userInput.equals("bye"))) {
            System.out.print(separator + userInput + "\n" + separator);
            userInput = sc.nextLine();
        }
        System.out.print("Goodbye!");
    }
}
