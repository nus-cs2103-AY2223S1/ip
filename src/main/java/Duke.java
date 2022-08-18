public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        IOhelper.print("Hello from\n" + logo);

        while(true) {
            String input = IOhelper.read();
            if(input.equals("bye")) {
                IOhelper.print("Bye. Hope to see you again soon!");
                break;
            }
            IOhelper.print(input);
        }

    }
}
