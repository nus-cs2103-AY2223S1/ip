package Rabbit.util;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    public Ui(){
        this.sc = new Scanner(System.in);
    };

    public void showGreet() {
        System.out.println("-----------------------------------------------------------------------------\n"
                + "-----------------------------------------------------------------------------\n"
                + "Yo...nice to meet you. This is rabbit...Ughhhhh I hate this job.\n"
                + "You can input stuff that you want me to write on this grandma-aged notebook.\n"
                + "-----------------------------------------------------------------------------\n"
                + "1. Type the type of a task followed by its content and time to add it into the list.\n"
                + "   There are three types: todo, deadline and event.\n"
                + "   - To add todo, type 'todo the content' such as 'todo do homework'.\n"
                + "   - To add deadline, type 'deadline the content /year-month-day-time' "
                + "such as 'deadline do homework /2022-08-22-1800'.\n"
                + "   - To add event, type 'event the content /year-month-day-time' "
                + "such as 'deadline do homework /2022-08-22-1800'.\n"
                + "2. Type 'list' then I'll show all the existing lines to you.\n"
                + "3. Type 'mark + the index of an existing task' to marks it as done. Like 'mark 1'.\n"
                + "4. Type 'unmark + the index of an existing task' to unmark a task.\n"
                + "5. Type 'delete + the index of an existing task' to delete it.\n"
                + "-----------------------------------------------------------------------------\n"
                + "Actually why not just do me a favour? Type 'bye' in the console and free both of us.");
    }

    public void showBye() {
        System.out.println("Thanks a lot. I'm gonna have some carrot tea later. See you...");
    }

    public void showException(Exception e) {
        System.out.println(e);
    }

    public void showFind(String output) {
        System.out.println(output);
    }

    public void showAddToList(String content) {
        System.out.println("Okay...noted.\n" + content + "...Huh? Hope you can remember it.");
    }
    public void showList() {

    }

    public void showMark() {

    }

    public void showUnmark() {

    }

    public void showDelete() {

    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void endCommand() {
        this.sc.close();
    }
}
