import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    public static void print(ArrayList<String> stringArraylist) {
        int counter = 0;
        int numbering = 1;
        int len = stringArraylist.size();
        while (counter < len) {
            System.out.println(numbering + ". " + stringArraylist.get(counter));
            counter++; numbering++;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        ArrayList<String> arrayList = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String str1 = sc.nextLine();
                if (str1.equals("bye")) {
                    System.out.println("Bye, Hope to see you again soon!");
                    break;
                } else if (str1.equals("list")) {
                    print(arrayList);
                } else {
                    arrayList.add(str1);
                    System.out.println("added: " + str1);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            } catch (IndexOutOfBoundsException e1) {
                System.out.println("List cannot be empty");
            }
        }


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
