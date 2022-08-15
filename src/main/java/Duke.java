import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
       while (input.hasNextLine()) {
           String s = input.next();
           if(s.equals("bye")) {
               break;
           } else {
               System.out.println(s);
           }
       }
       System.out.println("Bye. Hope to see you again soon!");
    }
}
