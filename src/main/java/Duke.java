import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Naruto and one day I will be Hokage! \nWhat can i do for you?");
        Scanner scanner = new Scanner(System.in);
        String lineBreak = "━".repeat(20);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(lineBreak);
            if (input.equals("bye")) {
                System.out.println("    Looks like this is the end for now, till we meet again! Ja Ne!");
                System.out.println(lineBreak);
                break;
            }
            System.out.println("    " + input + " Dattebayo");
            System.out.println(lineBreak);
        }

    }
}
