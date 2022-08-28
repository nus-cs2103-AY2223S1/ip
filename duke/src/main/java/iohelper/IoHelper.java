package iohelper;

import java.util.Scanner;

public class IoHelper {
    private final Scanner scanner;
    private String text;

    public IoHelper() {
        scanner = new Scanner(System.in);
    }

    private void scan() {
        text = scanner.nextLine();
    }

    public String getText() {
        scan();
        return text;
    }

    public void closeScanner() {
        scanner.close();
    }

    public void print(Object Message) {
        System.out.println(Message);
    }

    public void printError(Exception e) {
        System.err.println(e);
    }

}
