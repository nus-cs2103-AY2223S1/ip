import java.util.Scanner;

public class Duke {

    private static String greetings = "Hello! I'm Ekud \n" + "What can I do for you?";
    private static String banner = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private static void print(String msg) {
        System.out.println(banner);
        System.out.println(msg);
        System.out.println(banner);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        print(greetings);

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            print(input);
            input = sc.nextLine();
        }
        print("Cya!");
    }
}
