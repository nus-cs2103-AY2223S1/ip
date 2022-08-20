public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke. How may I assist you?");
    }
    
    public void showPrompt() {
        System.out.print("\n>>> ");
    }
    
    public void showLoadingError() {
        System.out.println("FAILED! Could not find storage file containing your tasks");
        System.out.println("Add a task to generate one!!!");
    }
    
    public void showLoadingSuccess() {
        System.out.println("Successfully loaded all tasks! :)");
    }
    
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    
    public void showGoodbye() {
        System.out.println("Goodbye! Hope to see you soon!");
    }
    
}
