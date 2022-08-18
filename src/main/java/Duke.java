import java.util.*;
import java.lang.Enum;

public class Duke {

    public static void main(String[] args) {

        //Initialize Scanner and ToDoList
        Scanner sc = new Scanner(System.in);
        ToDoList list = new ToDoList();

        //Welcome message
        String logo = "*\\(^o^)/*";
        System.out.println(logo);
        System.out.println("Bonjour~~ I'm Byu! How can I help you?");

        String response = sc.nextLine();

        while (!response.equals("bye")) {
            try {
                check(response);
                list.execute(response);
                response = sc.nextLine();
            } catch (DukeException de) {
                System.out.print(de.getMessage() + "\n");
                response = sc.nextLine();
            }
        }

        System.out.print("Awww see you soon!!");
        sc.close();
    }

        public static void check(String response) throws DukeException {
            if (response.equals("list")) {
            } else {
                String[] arr = response.split(" ");
                switch (arr[0]) {
                    case "mark":
                    case "unmark":
                    case "deadline":
                    case "todo":
                    case "event":
                    case "delete":
                        if (arr.length == 1) {
                            throw new EmptyDescription(arr[0]);
                        } else {
                            break;
                        }
                    default:
                        throw new InvalidInstruction();
                }
            }
        }

}


