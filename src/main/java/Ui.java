import java.util.Scanner;

public class Ui {
    private final static String UNDERLINE = "_________________________________";

    public static void intro() {
        System.out.println(UNDERLINE);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println(UNDERLINE);
    }

    public static void end() {
        String byeMessage = "Bye! See you again soon";
        System.out.println(UNDERLINE + "\n" + byeMessage  + "\n" + UNDERLINE);
    }

    public void showError(String error) {
        System.out.println(UNDERLINE + "\n" + error + "\n" + UNDERLINE);
    }

    public String getCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    public void printMessage(String message){
        System.out.println(UNDERLINE + "\n" + message + "\n" + UNDERLINE);
    }
}
