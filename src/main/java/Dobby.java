import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Dobby {
    private static Scanner scanner = new Scanner(System.in);
    private static DobbyList dobbyList;
    private static UserInput ui;

    public Dobby(String filePath) {
        dobbyList = new DobbyList();
        ui = new UserInput();
        try {
            DobbyIO.load(dobbyList, filePath);
        } catch(FileNotFoundException e) {

        }
    }
    private static void dobbyStart() throws IOException {
        DobbyChat.sayHello();
        boolean isBye = false;

        while(!isBye) {
            String task = ui.readCommand();
            Command cmd = Parser.parse(task);
            cmd.execute(dobbyList, ui);
            isBye = cmd.isBye();
        }
    }

    public static void main(String[] args) throws IOException {
        new Dobby("./src/main/dobbyList.txt").dobbyStart();
    }
}