import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        DukeFile dukeFile = new DukeFile("./data/", "duke.txt");
        TaskList taskList = dukeFile.readFile();

        Bot bot = new Bot(taskList);
        Scanner sc = new Scanner(System.in);

        System.out.println(bot.introduce());
        while (true) {
            String input = sc.nextLine();
            System.out.println(bot.answer(input));
            if (input.equals("bye")) {
                break;
            }
        }
        dukeFile.writeFile(taskList);
    }
}

