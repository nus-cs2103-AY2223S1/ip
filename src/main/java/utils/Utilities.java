package utils;

public class Utilities {

    public void printMsg(String message) {
        printStraightLine();
        System.out.println("\t" + message);
        printStraightLine();
    }

    public void printStraightLine() {
        System.out.println("\t____________________________________________________________");
    }
}
