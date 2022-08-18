import java.util.Scanner;

public class Duke {

    private static final String INDENTATION = "     ";
    private static final String EXTRA_INDENTATION = "  ";

    private static String reply(String message) {
        return "    ____________________________________________________________\n" +
                INDENTATION + message + "\n" +
                "    ____________________________________________________________";
    }

    private static String craftList(String[] array, boolean[] bArray) {
        int length = array.length;
        String result = "";
        for (int x = 0; x < length; x++) {
            if (array[x] == null) {
                break;
            } else {
                if (x == 0) {
                    result += (x + 1) + "." + checkBox(bArray[x]) + " " + array[x];
                } else {
                    result += "\n" + INDENTATION + (x + 1) + "." + checkBox(bArray[x]) + " " + array[x];
                }
            }
        }
        return result;
    }

    private static String checkBox(boolean bool) {
        if (bool) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println(reply("Hello! I'm Duke\n" +
                "     What can I do for you?"));
        boolean conversation = true;
        String[] work = new String[100];
        boolean[] workCompleted = new boolean[100];
        int workCounter = 0;
        while (conversation) {
            String response = scan.nextLine();
            String[] splitResponse = response.split(" ");
            if (splitResponse.length == 2 && (splitResponse[0].equals("mark") || splitResponse[0].equals("unmark"))) {
                int number = Integer.parseInt(splitResponse[1]) - 1;
                if (splitResponse[0].equals("mark")) {
                    workCompleted[number] = true;
                    System.out.println(reply("Nice! I've marked this task as done:\n" +
                            INDENTATION + EXTRA_INDENTATION + checkBox(true) + " " + work[number]));
                } else {
                    workCompleted[number] = false;
                    System.out.println(reply("OK, I've marked this task as not done yet:\n" +
                            INDENTATION + EXTRA_INDENTATION + checkBox(false) + " " + work[number]));
                }
            } else if (response.equals("bye")) {
                System.out.println(reply("Bye. Hope to see you again soon!"));
                conversation = false;
            } else if (response.equals("list")) {
                System.out.println(reply(craftList(work, workCompleted)));
            } else {
                work[workCounter] = response;
                workCounter++;
                System.out.println(reply("added: " + response));
            }
        }
    }
}
