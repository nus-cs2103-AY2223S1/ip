import java.util.Scanner;

public class Roger {
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

    public static void main(String[] args) {
        Roger.sayHello();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                Roger.sayGoodbye();
                break;
            }

            Roger.echo(input);
        }

    }
}
