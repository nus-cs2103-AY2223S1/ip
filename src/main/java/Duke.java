import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);   // Create Scanner object
        String[] strLst = new String[100];

        String intro = "Hello! My name is GustavoBot, but you can call me Gus\n"
                + "How may I help you today?";

        System.out.println(intro);
        String act = scanObj.nextLine();            // read user input

        switch (act) {
            case "list":
                System.out.println("list");
                break;
            case "blah":
                System.out.println("blah");
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again!");
                break;
        }
    }
}
