import java.util.Scanner;

public class Hazell {
    public static void main(String[] args) {
        String logo = "  _    _               _ _ \n"
                + " | |  | |             | | |\n"
                + " | |__| | __ _ _______| | |\n"
                + " |  __  |/ _` |_  / _ \\ | |\n"
                + " | |  | | (_| |/ /  __/ | |\n"
                + " |_|  |_|\\__,_/___\\___|_|_|\n";
        System.out.println(logo);
        System.out.println("Hello, I am Hazell!\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userinput = scanner.nextLine().strip();
            if (userinput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            } else {
                System.out.println(userinput);
            }
        }
    }
}
