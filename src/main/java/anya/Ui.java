package anya;

import java.util.Scanner;

import anya.task.Task;
import anya.task.TaskList;

public class Ui {

    private Scanner sc;

    // Constructor
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    // Instance methods
    private void breakLine() {
        System.out.println("---------------------------------------------------------------------");
    }

    public String readLine() {
        return this.sc.nextLine();
    }

    public void closeScanner() {
        this.sc.close();
    }

    public void greetMessage() {
        System.out.println("Hello! Anya is happy to meet you.\nHow can Anya help?");
        breakLine();
    }

    public void exitMessage() {
        System.out.println("Anya is sad to see you leave. Please be back soon.");
        breakLine();
    }

    public void addTaskMessage(Task task, int taskListLength) {
        System.out.println("Anya added: " + task);
        System.out.println("Anya sees that you have " + taskListLength + " task(s) in your list.");
        breakLine();
    }

    public void deleteTaskMessage(Task task) {
        System.out.println("Anya has removed this task : \n" + task.toString());
        breakLine();
    }

    public void getListMessage(TaskList taskList) {
        System.out.println("Anya is getting you your list...");
        for (int i = 0; i < taskList.getLength(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + taskList.getTaskFromIndex(num).toString());
        }
        breakLine();
    }

    public void markTaskMessage(Task task) {
        System.out.println("Anya has marked this task as done: \n  " + task.toString());
        breakLine();
    }

    public void unmarkTaskMessage(Task task) {
        System.out.println("Anya has marked this task as uncompleted: \n  " + task.toString());
        breakLine();
    }

    public void savingFileMessage() {
        System.out.println("Anya is saving your data...");
    }

    public void saveFileSuccessMessage() {
        System.out.println("Anya has successfully saved your data!");
        breakLine();
    }

    public void loadingFileMessage() {
        System.out.println("Anya is loading your saved file...");
    }

    public void loadFileSuccessMessage() {
        System.out.println("Anya has finished loading your saved file!");
        breakLine();
    }

    public void errorMessage(String errorMessage) {
        System.out.println("Anya spotted an error: " + errorMessage);
        breakLine();
    }

    public void deadlineFormatExample() {
        System.out.println("Format: <name> /by <dd/MM/yyyy> <HHmm>.");
        System.out.println("Example: return book /by 01/01/2022 2030");
        breakLine();
    }

    // TODO: Add Error messages
}
