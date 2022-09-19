#User Guide

##Duke

> “Your mind is for having ideas, not holding them.” – David Allen

###Overview
Duke frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ ***SUPER FAST to use***

All you need to do is,

1. download it from [here](https://github.com/shawnkai/ip).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you :+1:
And it is FREE!

###Features:

 - [x] Managing tasks
 - [x] Managing deadlines
 - [x] Reminders ~~(coming soon)~~

If you are a Java programmer, you can use it to practice Java too. Here's the `main` method:

```java
    public static void main(String[] args) throws IOException {
        Storage storage = new Storage();
        TaskList tasks = storage.loadFile();
        Parser parser = new Parser(tasks, storage);
        UI ui = new UI();
        ui.welcomeUser();
        String input = ui.readInput();  // Read user input
        while (!input.equals("bye")) {
            parser.parse(input);
            input = ui.readInput(); // Read next user input
        }
        ui.sayBye();
    }
```

###Usage

####Commands

- `todo DESCRIPTION`: Add todo task
- `deadline DESCRIPTION /by DEADLINE': Add deadline task
- `event DESCRIPTION /at TIME`: Add event task
- `list`: List all tasks
- `mark INDEX`: Mark task as done
- `unmark INDEX`: Unmark task
- `bye`: Exit program
- `find KEYWORD`: Filter task by keyword
- `delete INDEX`: Delete task
- `reminder`: Gives a reminder for the upcoming deadlines and events for the coming week