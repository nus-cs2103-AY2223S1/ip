import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> inputArray = new ArrayList<>();
    public static int inputCount = 1;
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
            case("list"):
                System.out.println("____________________________________________________________");
                for(String s : inputArray) {
                    System.out.println(s);
                }
                System.out.println("____________________________________________________________");
                getInput();
                break;
            default:
                System.out.println("____________________________________________________________");
                inputArray.add(inputCount + ". " + inData);
                inputCount++;
                System.out.println("added: " + inData);
                System.out.println("____________________________________________________________");
                getInput();
        }




    }

}
