import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("What are your commands sir: ");
        String[] arr = new String[100];
        boolean pred = true;

        while(pred){
            if (input.hasNext()){
                String str = input.nextLine();

                if (str.contains("list")) {
                    System.out.println("list");
                } else if (str.contains("blah")) {
                    System.out.println("blah");
                } else if (str.contains("bye")) {
                    System.out.println("Bye. Hope to see you again");
                    pred = false;
                }else {
                    System.out.println("I dont understand lol");
                }
            }

        }

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
