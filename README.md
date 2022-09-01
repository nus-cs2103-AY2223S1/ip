# CaCa 🤖

### _A personal assistant chatbot that helps users manage and track tasks._

> "It is our attitude at the beginning of a difficult task which, more than anything else, will affect its successful outcome." – William James ([source](https://www.brainyquote.com/quotes/william_james_157168))

<hr>

Start your task easily and boost your productivity with CaCa 🤖, a chatbot which frees your mind 🤯 of having to remember and keep track of overwhelming things you need to do! ✅

<br>

CaCa is
- text-based
- easy to learn
- ~~FAST~~ _SUPER_ FAST to use

All you need to do is,
1. download it from [here](https://github.com/carriezhengjr/ip/releases/tag/A-Jar).
2. open a command window in that same folder.
3. run the command `java -jar {filename}.jar`. 
    - e.g., `java -jar caca.jar` (i.e., run the command in the same folder as the jar file)
4. let it manage your tasks for you! 😉

And it is **FREE**!

<br>

Main features:
- [x] Managing tasks (add, delete, mark, unmark, list, find)
- [x] Managing deadlines
- [x] Managing events

Look out for more features in future! 😎

<hr>

If you Java programmer, you can use it to practice Java too. Here's the `main` method:
```java
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
```

<hr>

# User Guide

### Here are the detailed description of CaCa features.

Functions with respective commands are listed below as Function (description): `command`. e.g...
- Greet user (triggered as soon as the chatbot is run)
- Exit program (end chatbot): `bye`
- Add tasks:
    - ToDos (tasks without any date & time): `todo taskDescription`
        - e.g.`todo borrow book`
    - Deadlines (deadlines by a date & time): `deadline taskDescription /by dateTime`
        - e.g. `deadline return book /by 01/09/2022 1200`
    - Events (events at a date & time): `event taskDescription /at dd/MM/yyyy HHmm`
        - e.g. `event project meeting /at 01/09/2022 1600`
- List task (displays a list of all tasks stored): `list`
- Mark task (marks task as done with a "X"): `mark taskIndex` 
    - e.g. `mark 2`
- Unmark task (marks task as not done and removes "X"): `unmark taskIndex`
    - e.g. `unmark 2`
- Delete task (deletes task from list): `delete taskIndex`
    - e.g. `delete 3`
- Find task (finds all matching tasks from list): `find task`
    - e.g. `find book`
