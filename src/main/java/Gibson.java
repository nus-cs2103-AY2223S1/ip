import java.util.Scanner;
import java.util.ArrayList;

public class Gibson {
    public static void main(String[] args) {
        final String line = "____________________________________________________________";
        final String logo = " _____ ______ ____   _____  ____  _   _\n" +
        "/ _____|_   _|  _ \\ / ____|/ __ \\| \\ | |\n" +
        "| |  __  | | | |_) | (___ | |  | |  \\| |\n" +
        "| | |_ | | | |  _ < \\___ \\| |  | | . ` |\n" +
        "| |__| |_| |_| |_) |____) | |__| | |\\  |\n" +
        "\\______|_____|____/|_____/ \\____/|_| \\_|\n";
        ArrayList<String> list = new ArrayList<String>();

        // Prints introduction
        System.out.println(line);
        System.out.println(logo);
        System.out.println("Hello! I'm Gibson");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                int count = 1;
                System.out.println(line);
                for (String s : list) {
                    System.out.println(count + ". " + s);
                    count++;
                }
                System.out.println(line);
            } else {
                list.add(input);
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
    }
}
