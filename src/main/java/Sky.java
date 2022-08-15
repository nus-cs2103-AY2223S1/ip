import java.util.Scanner;

public class Sky {
    public static void main(String[] args) {
        System.out.println("Hello from Sky! Your heavenly personal assistant chatbot to help you track your things.");

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
                default:
                    System.out.println("  " + s);
                    break;
            }
            System.out.println("  ____________________________________________________________");
        }
    }
}