import java.util.Scanner;

public class IOHelper {
    private final Scanner scanner;
    private String text;

    public IOHelper() {
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
}
