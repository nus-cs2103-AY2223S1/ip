import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Echo();
    }

    private static void Echo() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.printf("Type something: ");
            System.out.println("\t" + "-".repeat(25) + "\n\t\t" + sc.nextLine());
            System.out.println("\t" + "-".repeat(25));
        }
    }
}
