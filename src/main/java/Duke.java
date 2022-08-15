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
                Task t = new Task(response);
                toDoList.addTask(t);
            }
            response = sc.nextLine();
        }
        System.out.println("Awww see you soon!");
        sc.close();
    }

}


