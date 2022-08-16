import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String reply = "";
        String exit = "bye";
        System.out.println("hi im chompers what do u need!!!\n");

        while(true) {
            Scanner scanIn = new Scanner(System.in);
            reply = scanIn.nextLine();
            if(reply.equals(exit)) {
                System.out.println("bye see u");
                scanIn.close();
                break;
            } else {
                System.out.println(reply);
            }

        }
    }
}
