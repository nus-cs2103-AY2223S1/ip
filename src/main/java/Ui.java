import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    String scan() {
        return this.sc.nextLine();
    }

    public void close() {
        sc.close();
    }
}
