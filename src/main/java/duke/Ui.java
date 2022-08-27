package duke;

import java.util.Scanner;

public class Ui {
  public void greet(){
    System.out.print("Hello I'm duke.Duke\nWhat can I do for you?\n");
  }
  public void quit(){
    System.out.println("Bye. Hope to see you again soon!");
  }

  public void start(Scanner sc, Storage storage, TaskList taskList){
    while (true) {
      String command = sc.nextLine();
      if(command.equals("bye")) {
        break;
      }
      Parser.parseCommand(command,taskList,this);
      storage.save(taskList.getList());
    }
  }
  public void listOutTasks(TaskList tasks){
    tasks.printList();
  }

  public void markTask(Task taskToMark) {
    taskToMark.mark();
    System.out.println("Nice! I've marked this task as done:\n  " + taskToMark);
  }

  public void unmarkTask(Task taskToMark) {
    taskToMark.unmark();
    System.out.println("OK, I've marked this task as not done yet:\n  " + taskToMark);
  }

  public void addTask(TaskList taskList,Task taskToAdd) {
    taskList.addTask(taskToAdd);
    System.out.println("Got it. I've added this task:\n  " + taskToAdd + "\nNow you have "
            + taskList.getSize() + " tasks in the list.");
  }

  public void deleteTask(TaskList taskList, Task taskToDelete) {
    taskList.deleteTask(taskToDelete);
    System.out.println("Noted. I've removed this task:\n  " + taskToDelete + "\nNow you have "
                  + taskList.getSize() + " tasks in the list");
  }
}
