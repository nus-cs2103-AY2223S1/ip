import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Drake {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("media/logo.txt"));
        StringBuilder logo = new StringBuilder();
        while (sc.hasNextLine())
            logo.append(sc.nextLine()).append("\n");
        System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        System.out.println("You used to call me on my cellphone\n" + logo);
        System.out.println("Drake's (me) the kind of guy to help you out uwu");
        System.out.println("Go ahead, make that hotline bling");
        System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");

        sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!Objects.equals(command, "bye")) {
            System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
            System.out.println(command);
            System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
            command = sc.nextLine();
        }
        System.out.println("I'm down for you always. See you (✿˶˘ ³˘)~♡");
    }

}
