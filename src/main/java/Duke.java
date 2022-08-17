import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello!\nWhat can I do for you ?");
        Scanner help = new Scanner(System.in);


        while(true) {
           String str = help.nextLine();
           if (str .equals("bye")) {
               System.out.println("Bye see you again buddy !");
               break;
           }
           System.out.println(str);

       }


    }
}
