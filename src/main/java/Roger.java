import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Roger {
    private List<String> textList = new ArrayList<>();

    private static void sayGoodbye() {
        System.out.println("Bye bye niece and nephew.");
    }

    private static void echo(String input) {
        System.out.println(input);
    }

    private static void sayHello() {
        String logo = "██████╗░░█████╗░░██████╗░███████╗██████╗░ \n"
                    + "██╔══██╗██╔══██╗██╔════╝░██╔════╝██╔══██╗ \n"
                    + "██████╔╝██║░░██║██║░░██╗░█████╗░░██████╔╝ \n"
                    + "██╔══██╗██║░░██║██║░░╚██╗██╔══╝░░██╔══██╗ \n"
                    + "██║░░██║╚█████╔╝╚██████╔╝███████╗██║░░██║ \n"
                    + "╚═╝░░╚═╝░╚════╝░░╚═════╝░╚══════╝╚═╝░░╚═╝ ";
        System.out.println("Hello its \n" + logo);
        System.out.println("What you wan? What you wan?");
    }

    private void list() {
        int i = 1;
        for (String text: textList) {
            System.out.println(String.valueOf(i) + ". " + text);
            ++i;
        }
    }

    private void add(String text) {
        textList.add(text);
        System.out.println("Added: " + text);
    }

    public static void main(String[] args) {
        Roger roger = new Roger();
        roger.sayHello();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                roger.sayGoodbye();
                break;
            } else if (input.equals("list")) {
                roger.list();
            } else {
                roger.add(input);
            }

        }

    }
}
