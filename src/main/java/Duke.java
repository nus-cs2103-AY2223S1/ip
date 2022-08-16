import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("what you want");

        Scanner sc = new Scanner(System.in);
        String[] db = new String[100];
        int i = 0;
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Sayonara, Adios!");
                break;
            }
            if (str.equals("list")) {
                for (int j = 0; j < i; j++) {
                    int k = j + 1;
                    System.out.println(k + ". " + db[j]);
                }
            } else {
                db[i] = str;
                System.out.println("added: " + str);
                i++;
            }
        }
    }
}
