import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static class Task {
        private boolean finished = false;
        private final String task;

        public Task(String task) {
            this.task = task;
        }

        public void markAsFinished() {
            this.finished = true;
        }

        public void markAsUnfinished() {
            this.finished = false;
        }

        public String status() {
            return this.finished ? "X" : " ";
        }

        public String getTask() {
            return this.task;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int length = taskList.size();
                for (int i = 0; i < length; i++) {
                    Task currentTask = taskList.get(i);
                    int taskNumber = i + 1;
                    String status = currentTask.status();
                    System.out.println(taskNumber + ".[" + status + "] " + currentTask.task);
                }
            } else {
                String[] splitString = input.split(" ", 2);

                if (splitString.length == 2) {
                    if (splitString[0].equals("mark") || splitString[0].equals("unmark")) {
                        int taskNumber;

                        try {
                            taskNumber = Integer.parseInt(splitString[1]);
                            if (taskNumber > taskList.size() || taskNumber <= 0) {
                                System.out.println("Error: task number invalid");
                            } else {
                                Task selectedTask = taskList.get(taskNumber - 1);
                                if (splitString[0].equals("mark")) {
                                    System.out.println("Nice! I've marked this task as done:");
                                    selectedTask.markAsFinished();
                                } else {
                                    System.out.println("OK, I've marked this task as not done yet:");
                                    selectedTask.markAsUnfinished();
                                }
                                System.out.println("[" + selectedTask.status() + "] " + selectedTask.getTask());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: incorrect format");
                        }
                    } else {
                        System.out.println("added: " + input);
                        Task newTask = new Task(input);
                        taskList.add(newTask);
                    }
                } else {
                    System.out.println("added: " + input);
                    Task newTask = new Task(input);
                    taskList.add(newTask);
                }
            }

            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
