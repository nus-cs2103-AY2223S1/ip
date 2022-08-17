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
        String[] lst = new String[100];
        int i = 0;



        while(true) {
           String str = help.nextLine();
           if (str.equals("bye")) {
               System.out.println("Bye see you again buddy !");
               break;
           }
           else if (str.equals("list")) {
               for(String item : lst) {
                   if(item != null)
                       System.out.println(item);

               }

            }
           else {
               lst[i] = i + 1 + "." + str;
               i++;
               System.out.println("added: " + str);
           }

       }


    }
}
