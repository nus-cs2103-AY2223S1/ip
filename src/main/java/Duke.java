import java.util.Scanner;

public class Duke {

    private static String INDENTATION = "     ";

    private static String reply(String message) {
        return "    ____________________________________________________________\n" +
                INDENTATION + message + "\n" +
                "    ____________________________________________________________";
    }

    private static String craftList(String[] array) {
        int length = array.length;
        String result = "";
        for (int x = 0; x < length; x++) {
            if (array[x] == null) {
                break;
            } else {
                if (x == 0) {
                    result += array[x];
                } else {
                    result += "\n" + INDENTATION + array[x];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println(reply("Hello! I'm Duke\n" +
                "     What can I do for you?"));
        boolean conversation = true;
        String[] work = new String[100];
        int workCounter = 0;
        while (conversation) {
            String response = scan.nextLine().toString();
            if (response.equals("bye")) {
                System.out.println(reply("Bye. Hope to see you again soon!"));
                conversation = false;
            } else if (response.equals("list")) {
                System.out.println(reply(craftList(work)));
            } else {
                work[workCounter] = (workCounter + 1) + ". " + response;
                workCounter++;
                System.out.println(reply("added: " + response));
            }
        }
    }
}
