import java.util.Scanner;

public class Duke {
    private static String horizontalBorder = "_________________________________\n";

    public static String welcomeMessage(){
        return horizontalBorder + "Hello! I'm Duke \nWhat can I do for you? \n" + horizontalBorder;
    }

    public static String level1(){
        System.out.println(welcomeMessage());
        while(true) {
            Scanner scan = new Scanner(System.in);
            String s = scan.next();
            if (s.equals("bye")) {
                return horizontalBorder + "Bye. Hope to see you again soon! \n" + horizontalBorder;
            }
            System.out.println(horizontalBorder + s + "\n" + horizontalBorder);
        }
    }


    public static void main(String[] args) {
        System.out.println(level1());
    }
}
