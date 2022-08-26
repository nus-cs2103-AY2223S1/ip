public class UI {

    public UI () {

    }

    public void showGotTask() {
        System.out.println("----------------------\nOooo looks like you have tasks already planned\n" +
                "----------------------\n");
    }

    public void showNoTask() {
        System.out.println("----------------------\nA fresh start for you :)\n----------------------\n");
    }

    public void welcomeMessage() {
        System.out.println("----------------------\n" +
                "Hello! I'm HelperBot\nWhat can I do for you?\n----------------------\n");
    }

    public void goodByeMessage() {
        System.out.println("Thank you and ATB :)");
    }

    public void showLoadingError() {
        System.out.println("You got an error");
    }

    public void showInaccurateInput() {
        System.out.println("----------------------\nI am sorry pls input again\n" +
                "----------------------\n");
    }

    public void showNoInputError() {
        System.out.println("----------------------\nSorry you can't do nothing :(\n" +
                "----------------------\n");
    }

    public void showNoEventError() {
        System.out.println("----------------------\nI am sorry pls input again\n" +
                "----------------------\n");
    }

    public void showNoDeadlineError() {
        System.out.println("----------------------\nNo deadline? Impossible !!!\n" +
                "----------------------\n");
    }

    public void showNoTodoError() {
        System.out.println("----------------------\nYou cannot do nothing :(\n" +
                "----------------------\n");
    }

    public void showList(String output) {
        System.out.println("----------------------\n" + output + "----------------------\n");
    }



}
