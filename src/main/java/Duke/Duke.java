package Duke;

import java.util.Scanner;


public class Duke {
    Scanner sc;

    public TaskList tasklist;
    public UserInterface userInterface;

    public Duke() {
        this.userInterface = new UserInterface();
        this.tasklist = new TaskList();
        this.sc = new Scanner(System.in);

    }

    public void run() {
        System.out.println(userInterface.greeting());
        boolean isExit = false;
        while (!isExit) {
            String echo = sc.nextLine();
            EventHandler eventHandler = new EventHandler(tasklist, userInterface);
            if (echo.equals("bye")) {
                System.out.println(userInterface.bye());
                isExit = true;
            } else if (echo.equals("list")) {
                System.out.println(userInterface.showList());
            } else if (echo.startsWith("mark")) {
                System.out.println(eventHandler.handleMark(echo));
            } else if (echo.startsWith("unmark")) {
                System.out.println(eventHandler.handleUnmark(echo));
            } else {
                System.out.println(eventHandler.addTask(new Task(echo)));
            }
        }
//        System.out.println("Hello! I'm Duke.Duke \n what can I do for you?");
//        boolean isExit = false;
//        while ((!isExit)) {
//            String echo = sc.nextLine();
//            if (echo.equals("bye")) {
//                System.out.println("Bye, see you again!");
//                isExit = true;
//            } else if (echo.equals("list")) {
//                for (int i = 0; i < list.size(); i++) {
//                    int j = i+1;
//                    System.out.println(j + ". " + list.get(i));
//                }
//            } else {
//                System.out.println("added: " + echo);
//                list.add(echo);
//            }
//        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
