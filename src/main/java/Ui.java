import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    public Ui() {
    }

    public void showLoadingError() {

    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("_____");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
