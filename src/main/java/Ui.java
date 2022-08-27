import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("Hai I'm Kirby (੭｡╹▿╹｡)੭ your friendly chat assistant!! \n" + "What amazing plans do you have today?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
