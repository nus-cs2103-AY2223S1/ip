import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String command = sc.nextLine();
        switch(command){
            case "list":
                System.out.println("list");
                break;
            case "blah":
                System.out.println("blah");
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
        }
        sc.close();
    }
}
