import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Luke\nWhat can I do for you?");
        String command = "";
        String quit = "bye";

        while (!command.equals(quit)){
            command = scanner.nextLine();
            if (!command.equals(quit)){
                System.out.println(command);
            } else {
                System.out.println("Bye! Thanks for using Luke!");
            }
        }
        
        scanner.close();
        
    }
}
