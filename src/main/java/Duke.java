import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // initialise variables
        String inputArray[] = new String[100];
        int counter = 0;

        // print out starting message
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        // new scanner object to take in input
        Scanner sc = new Scanner(System.in);

        while (true) {
            // take in input every loop
            String input = sc.nextLine();

            if (!input.equals("bye")) {
                // for when input is anything but bye
                if (!input.equals("list")) {
                    // for when input is anything but list
                    counter++;

                    // message when new element is added to list
                    System.out.println("added: " + input);

                    // add the element to the array
                    String currElement = counter + ". " + input;
                    inputArray[counter] = currElement;
                } else {
                    // list out the current list
                    for (int i = 0; i < inputArray.length; i++) {
                        if (inputArray[i] != null) {
                            System.out.println(inputArray[i]);
                        }
                    }
                }
            } else {
                // end program when input is bye
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
