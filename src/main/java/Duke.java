import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();

        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while(!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            
            if(userInput.equals("bye")) {
                break;
            }
            echo(userInput);
        }

        farewell();

    }

    public static void greeting() {
        String greet = "Hello! I'm Duke \n"
                + "What can I do for you? \n";
        System.out.println(greet);
    }

    public static void echo(String UserInput) {
        System.out.println(UserInput);
    }

    public static void farewell() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
