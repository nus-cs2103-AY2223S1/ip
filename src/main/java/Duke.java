import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        ArrayList<String> ls = new ArrayList<>();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if(line.equals("list")) {
                for (int i=0; i<ls.size(); i++) {
                    System.out.println(i+1 + "." +" " + ls.get(i));
                }
            }

            else {
                System.out.println("added: " + line);
                ls.add(line);
            }


        }


    }


}
