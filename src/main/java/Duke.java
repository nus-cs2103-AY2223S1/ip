import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class Duke {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("DukeLogger");
        try {
            processUserRequest();
        } catch (Exception e) {
            logger.log(SEVERE, e.getMessage());
        }

    }

    /* ------------------------HELPER FUNCTIONS------------------------ */
    public static void processUserRequest() throws Exception {

        String[] toDoList = new String[100];

        System.out.println(Constants.GREETING);
        Scanner reader = new Scanner(System.in);

        boolean end = false;
        while (!end) {
            String userRequest = reader.nextLine();
            if (userRequest.equals(Constants.BYE)) {
                end = true;
                System.out.println(Constants.END_PROGRAM);
            } else if (userRequest.equals(Constants.LIST)) {
                printItemsInList(toDoList);
                System.out.print("Missing something?\n");
            } else {
                toDoList = putItemInList(toDoList, userRequest);

                System.out.println("Added: " + userRequest);
                System.out.print("What else would you like in the list?\n");
            }
        }
        reader.close();
    }

    public static void printItemsInList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                break;
            }
            System.out.println(i + ": " + list[i]);
        }
    }

    public static String[] putItemInList(String[] list, String item) throws Exception {
        for (int i = 0; i < list.length; i++) {
            if (item.equals("")) {
                throw new InterruptedException("Nothing is not allowed!");
            } else if (list[i] != null) {
                continue;
            } else if (list[i] == null && i != 0 && list[i - 1].equals(item)) {
                break;
            } else {
                list[i] = item;
            }
        }
        return list;
    }

}
