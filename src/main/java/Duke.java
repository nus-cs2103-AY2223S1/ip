import java.util.*;

public class Duke {
    public static void main(String[] args) {

        //Initialize Scanner and ToDoList
        Scanner sc = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        //Welcome message
        String logo = "*\\(^o^)/*";
        System.out.println(logo);
        System.out.println("Bonjour~~ I'm Byu! How can I help you?");

        String response = sc.nextLine();

        while (!response.equals("bye")) {
            if (response.equals("list")) {
                toDoList.print();
            } else {
                String[] arr = response.split(" ");
                String firstWord = arr[0];
                int len = firstWord.length();
                String secondPart = response.substring(len + 1);
                switch (firstWord) {
                    case "mark": {
                        Integer index = Integer.valueOf(secondPart);
                        //should handle non-int
                        toDoList.mark(index);
                        break;
                    }
                    case "unmark": {
                        Integer index = Integer.valueOf(secondPart);
                        toDoList.unMark(index);
                        break;
                    }
                    case "deadline": {
                        String[] substrings = secondPart.split(" /by ");
                        Deadlines d = new Deadlines(substrings[0], substrings[1]);
                        toDoList.addTask(d);
                        break;
                    }
                    case "event": {
                        String[] substrings = secondPart.split(" /at ");
                        Events e = new Events(substrings[0], substrings[1]);
                        toDoList.addTask(e);
                        break;
                    }
                    case "todo": {
                        ToDos t = new ToDos(secondPart);
                        toDoList.addTask(t);
                        break;
                    }
                    default: {
                        Task t = new Task(response);
                        toDoList.addTask(t);
                        break;
                    }
                }
            }
            response = sc.nextLine();
        }
        System.out.println("Awww see you soon!");
        sc.close();

    }

}


