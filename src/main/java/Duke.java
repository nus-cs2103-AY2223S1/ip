import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greetUser();
        askUser();
        sayBye();
    }

    public static void greetUser() {
        System.out.println("\t______________________________________________________");
        System.out.println("\tHey there! I'm Arias!");
        System.out.println("\tHow may I help you? :)");
        System.out.println("\t______________________________________________________\n");
    }

    public static void sayBye() {
        System.out.println("\t______________________________________________________");
        System.out.println("\tNice seeing you! Bye!");
        System.out.println("\t______________________________________________________\n");
    }

    public static void askUser() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye") && !userInput.equals("Bye")) {
            System.out.println("\t______________________________________________________");
            System.out.println("\t" + userInput);
            System.out.println("\t______________________________________________________\n");
            userInput = sc.nextLine();
        }
    }
}
