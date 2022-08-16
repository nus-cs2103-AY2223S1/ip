import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String request;
        String print;
        Scanner sc= new Scanner(System.in);

        String line = "________________________________________________________________\n";
        System.out.println(line + "Hello! I'm Shanice:)");
        System.out.println("What can I do for you?\n" + line);

        //level 1:
        request = sc.next();
        while (!request.toLowerCase(Locale.ROOT).equals("bye")) {
            print = request;
            System.out.println(line + print + "\n" + line);
            request = sc.next();
        }

        print = "Bye. Hope to see you again soon!";
        System.out.println(line + print + "\n" + line);


    }
}
