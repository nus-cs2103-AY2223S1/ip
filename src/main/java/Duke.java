import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String banner = "★★★★★★★★★★★★\n"
                + " TED ʕ•́ᴥ•̀ʔっ \n"
                + "★★★★★★★★★★★★\n";
        System.out.println(banner);
        System.out.println("Hello! I'm Ted and I'm here to help you keep track of your tasks ʕ•́ᴥ•̀ʔっ\n"
                + "How can I assist you today?");

        Scanner sc = new Scanner(System.in).useDelimiter("\\n");;

        while (sc.hasNext()) {
            String command = sc.next();

            if (command.equals("bye")) {
                System.out.println("Goodbye! Have a pleasant day ʕ•́ᴥ•̀ʔっ");
                sc.close();
                return;
            }

            System.out.println("~ ʕ•́ᴥ•̀ʔっ ~\n" + command + "\n" + "~\n");
        }
    }
}
