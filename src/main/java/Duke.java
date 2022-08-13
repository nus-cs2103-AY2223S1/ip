import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class Duke {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("DukeLogger");
        try {
            processUserRequest();
        } catch (Exception e) {
            logger.log(SEVERE, "An error has occurred.");
        }

    }

    /* ------------------------HELPER FUNCTIONS------------------------ */
    public static void processUserRequest() {

        System.out.println(Constants.GREETING);
        Scanner reader = new Scanner(System.in);

        boolean end = false;
        while (!end) {
            String userRequest = reader.next();
            if (userRequest.equals(Constants.BYE)) {
                end = true;
                System.out.println(Constants.END_PROGRAM);
            }
            else {
                System.out.println(userRequest);
                System.out.print("Enter something else you'd like to have!");
            }
        }
        reader.close();
    }

}
