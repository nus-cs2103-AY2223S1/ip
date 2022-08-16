import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Turtle";
        System.out.println("Hello I am " + logo +"!");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        String input;
        ArrayList<String> items = new ArrayList<String>();

        while(run) {
            System.out.println("\n--------------------");
            input = sc.nextLine();

            if(input.equals("bye")) {
                System.out.println("Goodbye!");
                run = false;
            } else if(input.equals("list")) {
                for ( int i = 0; i < items.size(); i++) {
                    System.out.println(i+1 + ". " + items.get(i));
                }
            } else {
                items.add(input);
                System.out.print("added: " + input);
            }
        }
    }
}
