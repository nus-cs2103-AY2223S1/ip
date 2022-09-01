package duke;

import java.util.Scanner;

/**
 * Represents helper class to manage scanning in of user inputs
 * @author Reuben Chay 
 */
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
