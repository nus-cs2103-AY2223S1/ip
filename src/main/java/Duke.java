import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Sayonara, Adios!");
                break;
            }
            System.out.println(str);
        }
    }
}
