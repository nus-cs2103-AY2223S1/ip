import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Control dc = new Control();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        boolean hasnext = true;

        while (hasnext) {
            String message = sc.nextLine();
            dc.eval(message);

            }
        }
    }
