import java.util.Scanner;

public class Sky {
    public static void main(String[] args) {
        System.out.println("Hello from Sky! Your heavenly personal assistant chatbot to help you track your things.");

        String[] arr = new String[100];
        // index stores the pointer index of arr array
        int index = 0;
        // Label the while loop
        outerLoop:
        while (true) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            System.out.println("  ____________________________________________________________");
            switch(s) {
                case "bye":
                    System.out.println("  Bye. May all your endeavours fly high!");
                    System.out.println("  ____________________________________________________________");
                    break outerLoop;
                case "list":
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == null) {
                            break;
                        }
                        System.out.println("  " + i + ". " + arr[i]);
                    }
                    break;
                default:
                    arr[index++] = s;
                    System.out.println("  added: " + s);
                    break;
            }
            System.out.println("  ____________________________________________________________");
        }
    }
}