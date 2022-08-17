public class DobbyChat {
//        HELLO("\t" + "Hello! I'm Bobby\n" +
//               "\t" + "What can I do for you?"),
//        BYE("\t" + "Byebyeeee. Hope to see you soon!"),
//        LINE("\t------------------------------------");

    private static final String hello = "Hello! I'm Bobby\n" +
                           "\t" + "What can I do for you?";
    private static final String bye = "Byebyeeee. Hope to see you soon!";
    private static final String line = "\t------------------------------------";

    private static void printChat(String c) {
        System.out.println(line);
        System.out.println("\t" + c);
        System.out.println(line);
    }
    public static void echo(String s) {
        printChat(s);
    }
    public static void sayHello() {
        printChat(hello);
    }
    public static void sayBye() {
        printChat(bye);
    }
    public static void added(String s) {
        printChat("added: " + s);
    }
    public static void marked(String s) {
        String marked = "Nice! I've marked this task as done\n";
        String toPrint = marked + s;
        printChat(toPrint);
    }
    public static void unmarked(String s) {
        String unmarked = "OK, I've marked this task as not done yet\n";
        String toPrint = unmarked + s;
        printChat(toPrint);
    }
    public static void noDeadlineDate() {
        printChat("Please add the deadline after the task name using /by");
    }
    public static void noEventDate() {
        printChat("Please add the event date after the task name using /at");
    }
    public static void added(Task task, DobbyList list) {
        String accept = "Got it. I've added this task:\n\t";
        String taskString = task.toString() + "\n\t";
        String length = "Now you have " + list.getLength()+ " tasks in the list.\n";
        printChat(accept + taskString + length);
    }
}