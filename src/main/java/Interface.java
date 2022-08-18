
public class Interface {
    /**
     *  Greet
     */
    public static void greet() {
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }
    /**
     *  Goodbye
     */
    public static void bye() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    public static void echo(String str) {
        printLine();
        System.out.println("     " + str);
        printLine();
    }

    private static void printLine() {
        System.out.println("    --------------------------------------------------------------------------------------");
    }
}
