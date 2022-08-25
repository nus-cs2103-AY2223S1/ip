import java.util.Scanner;

class Ui {
    protected Scanner sc = new Scanner(System.in);

    public void greet() {
        String greet = "Hello! I'm Lan\n"
                + "What can I do for you?";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(greet + "\n" + logo);
    }

    public String requirement() {
        return sc.nextLine();
    }

    public boolean hasNextReq() {
        return sc.hasNextLine();
    }
}