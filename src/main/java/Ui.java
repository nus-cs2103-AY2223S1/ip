import java.util.Scanner;

public class Ui {
    protected void intro() {
        System.out.println("Good Day!");
    }

    protected String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    protected void showLoadingError() {
        System.out.println("No records detected, creating new file...");
    }

    protected void lineBreak() {
        System.out.println("^_^ -------------------------------------------- ^_^");
    }
    protected void outro(){
        System.out.println("Bye. Hope to see you again");
    }

    protected void updateTask(Task updatedTask, String status) {
        System.out.printf("Marked task as %s.\n%s\n", status, updatedTask);
    }
    protected void display(String output){
        System.out.println(output);
    }

    protected void addTaskConfirmation(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("\t%s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    protected void deleteTaskConfirmation(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.printf("\t%s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }
}
