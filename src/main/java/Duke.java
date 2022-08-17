import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TaskList lst = new TaskList();
        System.out.println(Constants.initMsg);
        while (true) {
            String input = scan.nextLine();
            String[] parse = input.split(" ", 2);
            if (parse[0].equals("bye")) {
                break;
            }
            try {
                switch (parse[0]) {
                    case "list":
                        lst.printList();
                        break;
                    case "mark":
                        checkInputError(parse);
                        lst.markTask(parseInt(parse[1]));
                        break;
                    case "unmark":
                        checkInputError(parse);
                        lst.unmarkTask(parseInt(parse[1]));
                        break;
                    case "delete":
                        checkInputError(parse);
                        lst.deleteTask(parseInt(parse[1]));
                        break;
                    case "todo":
                        checkInputError(parse);
                        lst.addTask(new ToDo(parse[1]));
                        break;
                    case "deadline":
                        checkInputError(parse);
                        String[] parse2 = parse[1].split("/by");
                        checkInputError(parse2);
                        lst.addTask(new Deadline(parse2[0], parse2[1]));
                        break;
                    case "event":
                        checkInputError(parse);
                        String[] parse3 = parse[1].split("/at");
                        checkInputError(parse3);
                        lst.addTask(new Event(parse3[0], parse3[1]));
                        break;
                    default:
                        throw new DukeException(Constants.invalid);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scan.close();
        System.out.println(Constants.byeMsg);
    }

    public static void checkInputError(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException(Constants.invalidInput);
        }
    }

}
