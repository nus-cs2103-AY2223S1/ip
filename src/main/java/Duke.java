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
                if (arr[0].equals("mark")) {
                    Integer index = Integer.valueOf(arr[1]);
                    //should handle non-int
                    toDoList.mark(index);
                } else if (arr[0].equals("unmark")) {
                    Integer index = Integer.valueOf(arr[1]);
                    toDoList.unMark(index);
                } else {
                    Task t = new Task(response);
                    toDoList.addTask(t);
                }
            }
            response = sc.nextLine();
        }
        System.out.println("Awww see you soon!");
        sc.close();
    }

}


