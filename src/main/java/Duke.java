public class Duke {

    private UI ui;

   public String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Duke() {
        this.ui = new UI();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        System.out.println(logo + "\n" + ui.greet());
        boolean stillRunning = true;
        while (stillRunning) {
            String input = ui.getInput();
            if (input.equals("bye")) {
            System.out.println(ui.bye());
            stillRunning = false;
            } else if (input.equals("list")) {
                System.out.println(ui.list());
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                System.out.println(ui.editTask(input));
            } else {
                System.out.println(ui.addTask(input));
            }
        }
    }
}
