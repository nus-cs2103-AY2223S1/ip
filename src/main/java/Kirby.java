import java.util.Scanner;
public class Kirby {
    public static void main(String[] args) {
        System.out.println("Kirby: Hai I'm Kirby (੭｡╹▿╹｡)੭ your friendly chat assistant!! \n" +
                "How are you today?");
        while (true){
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            if (inputString.equals("bye")) {
                System.out.println("I loved talking to you :> \n" +
                        "Hope to see you again!");
                break;
            } else {
                System.out.println(inputString);
            }
        }
    }
}