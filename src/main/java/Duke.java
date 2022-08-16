/**
 * A Personal Assistant Chatbot that helps a person to keep track of various things.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = logo + "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greeting);
        Echo.echo();
    }
}
