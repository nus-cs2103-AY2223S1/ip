import java.util.Scanner;
import java.util.ArrayList;

class Yilia {
    private static ArrayList<String> abilities = new ArrayList();
    public static void greet() {
        String logo = "  \\‾\\     /‾/  |‾|  |‾|          |‾|       /‾‾‾\\      \n" +
                "   \\ \\   / /   | |  | |          | |      / /‾\\ \\     \n" +
                "    \\ \\_/ /    | |  | |          | |     / /___\\ \\    \n" +
                "     ‾| |‾     | |  | |          | |    / /_____\\ \\   \n" +
                "      | |      | |  | |_______   | |   / /       \\ \\  \n" +
                "      |_|      |_|  |_________|  |_|  /_/         \\_\\ \n";
        System.out.println("Hello! I'm Yilia\n" + logo + "What can I do for you?");
    }

    public static void echo(String text) {
        abilities.add(text);
        System.out.println("added: " + text);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showList() {
        for (int i = 0; i < abilities.size(); i++) {
            System.out.println(String.valueOf(i + 1)+ ". " + abilities.get(i));
        }
    }

    public static void main(String[] args) {
        greet();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.next();
            if (message.equals("bye")) {
                exit();
                return;
            } else if (message.equals("list")) {
                showList();
            } else {
                echo(message);
            }
        }
    }
}