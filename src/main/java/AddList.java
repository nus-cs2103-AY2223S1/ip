import java.util.Scanner;
import java.util.ArrayList;

public class AddList {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<String> toDo = new ArrayList<String>();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            if (input.equals("list")) {
                toDo.forEach((n) -> {
                    String result = String.format("%d: %s", toDo.indexOf(n) + 1, n);
                    System.out.println(result);
                });
            } else {
                toDo.add(input);
                System.out.println("added: " + input);
            }

            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

}
