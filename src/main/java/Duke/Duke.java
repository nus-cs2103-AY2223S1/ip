
package Duke;
import Duke.Exceptions.DukeException;
import Duke.Tasks.TaskList;

import Duke.UI.Ui;

import Duke.Storage.Storage;


import java.io.IOException;
import java.util.Scanner;


public class Duke {
    private TaskList tasks;
    private Storage storage;


    private Ui ui;

    public Duke() {

        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "OneDrive - National University of Singapore", "2022_fall_sem_NUS", "CS2103T Software Engineering", "Code_Independent Project", "src", "data","Duke.txt");

        ui = new Ui();
        storage = new Storage("Duke");

        try { tasks = storage.obtain(); } catch (DukeException e)
        {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
//        while (!isExit) {
//            /*
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine();
//                Command c = Parser.parse(fullCommand);
//
//
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//*/
//        }


    }
   public static void main(String[] args) throws DukeException {
        new Duke().run();






/*
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "OneDrive - National University of Singapore", "2022_fall_sem_NUS", "CS2103T Software Engineering", "Code_Independent Project", "data", "Duke.txt");
        boolean directotyExists = java.nio.file.Files.exists(path);
        System.out.println("path: " + path);
        System.out.println("path exists: " + directotyExists);



        System.out.println("Hello! I'm Duke\n" +  "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input;
        input = sc.nextLine();
        List<Task> taskList = new ArrayList<>();

        String[] str;
        String order;
        String content;

        boolean needUpdate = false;


        // read data
        try {
            File myfile = new File(String.valueOf(path));
            Scanner myReader = new Scanner(myfile);
            String line = myReader.nextLine();


        } catch (FileNotFoundException e)
        {
            System.out.println("Invalid Path!");
        }

        // Handle incoming data
        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < taskList.size(); i++) {
                    System.out.print(i+1 + ".");
                    System.out.println(taskList.get(i));
                    }

            }
            else {

                if(!needUpdate) {needUpdate = true;}

                str = input.split(" ", 2);
                order = str[0];
                Task task;
                if(order.equals("mark")) {
                    content = str[1];
                    int index = Integer.parseInt(content)-1;
                    task = taskList.get(index);
                    task.setIsDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);

                }
                else if(order.equals("unmark")) {
                    content = str[1];
                    int index = Integer.parseInt(content) - 1;
                    task = taskList.get(index);
                    task.setIsDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + task);

                }
                else if(order.equals("delete")){
                    content = str[1];
                    int index = Integer.parseInt(content) - 1;
                    task = taskList.get(index);
                    taskList.remove(index);
                    int num = taskList.size();

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + num + " tasks in the list.");



                }
                else if (order.equals("todo")) {
                    try {
                        content = str[1];

                        ToDo todo = new ToDo(content);
                        taskList.add(todo);
                        int num = taskList.size();
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + todo);
                        System.out.println("Now you have " + num + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e)
                    {
                        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                }
                else if (order.equals("deadline")) {

                    content = str[1];
                    String[] contents = content.split(" /by ");
                    LocalDate date = LocalDate.parse(contents[1]);
                    Deadline deadline = new Deadline(contents[0], date);
                    taskList.add(deadline);
                    int num = taskList.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + num + " tasks in the list.");

                }
                else if (order.equals("event")) {
                    content = str[1];
                    String[] contents = content.split(" /at ");

                    Event event = new Event(contents[0], contents[1]);
                    taskList.add(event);
                    int num = taskList.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + num + " tasks in the list.");

                }
                else {
                    needUpdate = false;
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            input = sc.nextLine();
        }

        // Update the data
        if(needUpdate == true){
            String contents = "";
            for(int i = 0; i < taskList.size(); i++){
                Task cur_task = taskList.get(i);
                contents = contents + cur_task.toString() + "\n";
            }
            try {
                Files.writeString(path, contents, StandardCharsets.UTF_8);
                System.out.println("the list is updated already!");
            } catch (IOException ex)
            {
                System.out.println("Invalid Path!");
            }

        }

        System.out.print("Bye. Hope to see you again soon!");

    }

*/

    }




}

