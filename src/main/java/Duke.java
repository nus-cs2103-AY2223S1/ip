import java.util.*;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("_________________________");
        System.out.println("    Hi, my name is Doke");
        System.out.println("    What can I do for you?");
        System.out.println("    Enter a String!!");
        System.out.println("_________________________");

        String str= sc.nextLine();

        while (!str.equals("bye")) {
            System.out.println("_________________________");
            System.out.println("    " + str);
            System.out.println("_________________________");
            System.out.println();

            str = sc.nextLine();
        }

        System.out.println("_________________________");
        System.out.println("    Bye bye!");
        System.out.println("_________________________");

    }
}