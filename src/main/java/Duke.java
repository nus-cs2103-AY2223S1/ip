import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.greeting();

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")){
            ui.echo(cmd);
            cmd = sc.nextLine();
        }
        ui.exit();
    }
}
