public class Ui {
    private String horizontalLine = "____________________________________________________________\n";

    public void welcomeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void reply(String s) {
        System.out.print(horizontalLine + s +'\n' + horizontalLine);
    }

    public void drawLine() {
        System.out.print(horizontalLine);
    }

    public void showLoadingError() {
        reply("File not found. Creating new file...");
    }

}
