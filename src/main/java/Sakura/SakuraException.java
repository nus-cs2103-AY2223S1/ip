package Sakura;

public class SakuraException {
    protected static void invalidMark() {
        System.out.println("\tMark command written incorrectly. Please check again.\n");
    }

    protected static void genericTask() {
        System.out.println("\tPlease specify either a Deadline, To Do, or Event!\n");
    }

    protected static void noSuchTask() {
        System.out.println("\tNo such task in list of task.\n");
    }

    protected static void invalidDeadline() {
        System.out.println("\tDeadline format is invalid\n");
    }

    protected static void invalidEvent() {
        System.out.println("\tEvent format is invalid\n");
    }
    protected static void invalidCommand() {
        System.out.println("\tCommand written incorrectly. Please check again.\n");
    }
}
