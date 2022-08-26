import java.util.Scanner;

public class Ui {
    private final Scanner SC = new Scanner(System.in);
    private boolean isActive = true;
    private final String HEADER = "WANYA: ";
    private final String DIVIDER = "\n";

    public void greet() {
        String startMsg = "Hello!!! My name is Wanya! \nWWaku WWaku! \nHow can I help you?";
        System.out.println(startMsg + DIVIDER);
    }

    public void exit() {
        String closeMsg = "Yayyy Wanya gets to slack and watch shows now. Bye bye! :)";
        System.out.println(closeMsg + DIVIDER);
        isActive = false;
        SC.close();
    }

    public boolean isActive() {
        return isActive;
    }

    public String getUserCommand() {
        String commandInput = SC.nextLine();
        return commandInput;
    }

    public boolean hasNextLine() {
        return SC.hasNextLine();
    }

    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage() + DIVIDER);
    }

    public void showDateTimeFormat(String taskType) {
        String preposition = taskType.equals("E") ? "/at" : "/by";
        System.out.println("Please enter a valid date behind " + preposition + " with the format " +
                        "\"yyyy-mm-dd HH:mm\" where time is optional.\n " +
                         "If time is provided, leave it in 24 hours format." + DIVIDER);
    }

    public void showLoading() {
        System.out.println("....Loa....Loading....Please wait...." + DIVIDER);
    }
}
