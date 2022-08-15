import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        customPrint("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (!(userInput = sc.nextLine()).equals("bye")) {
            customPrint(userInput);
        }
        customPrint("Bye. Hope to see you again soon!");
    }

    private static void customPrint(String s) {
        System.out.println("-------------------");
        System.out.println(s);
        System.out.println("-------------------");
    }
}
