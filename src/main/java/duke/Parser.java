package duke;

import java.util.Scanner;

public class Parser {

    protected TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public void parseFunc() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while(!userInput.equals("bye")) {
            userInput = scanner.nextLine();

            if(userInput.equals("list")) {
                tasks.list();
                continue;
            }
            if(userInput.equals("bye")) {
                break;
            }
            if(userInput.split(" ", 2)[0].equals("mark")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                tasks.mark(inputTaskIndex);
                continue;
            }
            if(userInput.split(" ", 2)[0].equals("unmark")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                tasks.unmark(inputTaskIndex);
                continue;
            }
            if(userInput.split(" ", 2)[0].equals("delete")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                tasks.deleteTask(inputTaskIndex);
                continue;
            }

            try{
                tasks.userInputCheck(userInput);

                if(userInput.split(" ", 2)[0].equals("todo")) {
                    String taskInput = userInput.split(" ", 2)[1];
                    tasks.todo(taskInput);
                    continue;
                }
                if(userInput.split(" ", 2)[0].equals("deadline")) {
                    String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                    String by = userInput.split("/", 2)[1].split(" ", 2)[1];
                    tasks.deadline(taskInput, by);
                    continue;
                }
                if(userInput.split(" ", 2)[0].equals("event")) {
                    String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                    String duration = userInput.split("/", 2)[1].split(" ", 2)[1];
                    tasks.event(taskInput, duration);
                    continue;
                }

            }
            catch (DukeException err) {
                System.out.println(err.getMessage());
                continue;
            }
            tasks.addTask(userInput);
        }
    }
}
