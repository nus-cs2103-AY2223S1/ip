import java.util.Scanner;
public class Duke {

    public static void main(String[] args) throws DukeException {
        Response dukeResponse = new Response();
        dukeResponse.startUp();
        Scanner scanner = new Scanner(System.in);
        String userResponse = "0";
        while (true) {
            userResponse = scanner.nextLine();
            dukeResponse.handleUserInput(userResponse);
        }
    }
}
