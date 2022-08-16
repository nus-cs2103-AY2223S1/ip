import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String request;
        String print;
        ArrayList<String> items = new ArrayList<String>();

        Scanner sc= new Scanner(System.in);

        String line = "________________________________________________________________\n";
        System.out.println(line + "Hello! I'm Shanice:)");
        System.out.println("What can I do for you?\n" + line);

        //level 2:
        request = sc.nextLine();
        while (!request.toLowerCase(Locale.ROOT).equals("bye")) {
            if (request.toLowerCase(Locale.ROOT).equals("list")) {
                if (items.size() != 0) {
                    System.out.println(line);
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println(i+1 + ". " + items.get(i));
                    }
                    System.out.println(line);
                }
            }
            else {
                items.add(request);
                System.out.println(line + "added: " + request + "\n" + line);

            }
            request = sc.nextLine();
        }

        print = "Bye. Hope to see you again soon!";
        System.out.println(line + print + "\n" + line);


    }
}
