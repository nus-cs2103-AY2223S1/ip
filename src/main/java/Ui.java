import java.util.Scanner;

public class Ui {

    private boolean isOn;

    public Ui() {
        Scanner scanner = new Scanner(System.in);
        this.isOn = true;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void exit() {
        this.isOn = false;
    }

    public void showLoadingError() {
        System.out.println("OOPS! Something went wrong!! :(");
    }

    public void greet() {
        System.out.println("Hello I'm best bot Qoobee!\n" + "What can I do for you? ^.^");
    }


}
