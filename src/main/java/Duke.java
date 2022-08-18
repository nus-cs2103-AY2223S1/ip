import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {



        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        getInput();


//        System.out.println(String.join(" ", args));

        }

    public static void getInput() {
        String inData;
        Scanner scan = new Scanner( System.in );
        inData = scan.nextLine();

        switch (inData) {
            case("bye"):
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            default:
                System.out.println("____________________________________________________________");
                System.out.println(inData);
                System.out.println("____________________________________________________________");
                getInput();
        }




    }

}
