import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello I'm Duke" + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = "";


        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
            }
        }
        sc.close();


    }
}