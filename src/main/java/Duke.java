import java.util.Scanner;

public class Duke {

    private static final String INDENTATION = "     ";
    private static final String EXTRA_INDENTATION = "  ";
    private static final String[] WORK_TYPE = new String[] {" ", "T", "D", "E"};
    private static String reply(String message) {
        return "    ____________________________________________________________\n" +
                INDENTATION + message + "\n" +
                "    ____________________________________________________________";
    }

    private static String craftList(String[] array, boolean[] bArray, int[] workTypeArray) {
        int length = array.length;
        String result = "";
        for (int x = 0; x < length; x++) {
            if (array[x] == null) {
                break;
            } else {
                if (x == 0) {
                    result += (x + 1) + "." + workTypeBox(workTypeArray[x]) + checkBox(bArray[x]) + " " + array[x];
                } else {
                    result += "\n" + INDENTATION + (x + 1) + "." + workTypeBox(workTypeArray[x]) + checkBox(bArray[x]) + " " + array[x];
                }
            }
        }
        return result;
    }

    private static String craftTDEMessage(int work_type, boolean bool, String todo, int work_number) {
        return "Got it. I've added this task:\n" +
                INDENTATION + EXTRA_INDENTATION + workTypeBox(work_type) + checkBox(bool) +  " " + todo + "\n" +
                INDENTATION + "Now you have " + work_number + " tasks in the list.";
    }

    private static String checkBox(boolean bool) {
        if (bool) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    private static String workTypeBox(int number) {
        return "[" + WORK_TYPE[number] + "]";
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println(reply("Hello! I'm Duke\n" +
                "     What can I do for you?"));
        boolean conversation = true;
        String[] work = new String[100];
        boolean[] workCompleted = new boolean[100];
        int[] workType = new int[100];
        int workCounter = 0;
        while (conversation) {
            String response = scan.nextLine();
            String[] splitResponse = response.split(" ");
            String keyword = splitResponse[0];
            if (splitResponse.length == 2 && (keyword.equals("mark") || keyword.equals("unmark"))) {
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
            } else if (splitResponse.length > 1 && keyword.equals("todo")) {
                String todo = response.substring(5);
                work[workCounter] = todo;
                workType[workCounter] = 1;
                workCounter++;
                System.out.println(reply(craftTDEMessage(1, false, todo, workCounter)));
            } else if (splitResponse.length > 1 && keyword.equals("deadline")) {
                String[] newSplit = response.split(" /by ");
                String todo = newSplit[0].substring(9);
                String by = newSplit[1];
                String newTodo = todo + " (by: " + by + ")";
                work[workCounter] = newTodo;
                workType[workCounter] = 2;
                workCounter++;
                System.out.println(reply(craftTDEMessage(2, false, newTodo, workCounter)));
            } else if (splitResponse.length > 1 && keyword.equals("event")) {
                String[] newSplit = response.split(" /at ");
                String todo = newSplit[0].substring(6);
                String at = newSplit[1];
                String newTodo = todo + " (at: " + at + ")";
                work[workCounter] = newTodo;
                workType[workCounter] = 3;
                workCounter++;
                System.out.println(reply(craftTDEMessage(3, false, newTodo, workCounter)));
            } else if (response.equals("bye")) {
                System.out.println(reply("Bye. Hope to see you again soon!"));
                conversation = false;
            } else if (response.equals("list")) {
                System.out.println(reply(craftList(work, workCompleted, workType)));
            } else {
                work[workCounter] = response;
                workCounter++;
                System.out.println(reply("added: " + response));
            }
        }
    }
}
