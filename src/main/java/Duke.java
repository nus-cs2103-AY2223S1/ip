import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome to UNC");

        String input;
        Scanner scanner = new Scanner(System.in);
        while(!Objects.equals(input = scanner.nextLine(), "bye")) {
            System.out.println(input);
        }

        System.out.println("Bye");
    }


}
