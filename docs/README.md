# CaCa 🤖 User Guide

CaCa is a desktop application for managing tasks. It is optimised for use via a Command Line Interface (CLI) with a lovely Graphical User Interface (GUI).

Start your task easily and boost your productivity with CaCa 🤖, a personal assistant chatbot which frees your mind 🤯 of having to remember and keep track of overwhelming things you need to do! ✅

- [Quick Start](#quick-start)
- [Features & Usage](#features--usage)
  - [Showing help page](#showing-help-page-help)
  - [Adding ToDo](#adding-todo-todo-todo_description)
  - [Adding Deadline](#adding-deadline-deadline-deadline_description-by-ddmmyyyy-hhmm)
  - [Adding Event](#adding-event-event-event_description-at-ddmmyyyy-hhmm)
  - [Listing all tasks](#listing-all-tasks-list)
  - [Marking task](#marking-task-mark-task_index)
  - [Unmarking task](#unmarking-task-unmark-task_index)
  - [Deleting task](#deleting-task-delete-task_index)
  - [Finding task using keyword](#finding-task-using-keyword-find-keyword)
  - [Exiting application](#exiting-application-bye)
- [FAQ](#faq)

<hr>

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `CaCa.jar` from [here](https://github.com/carriezhengjr/ip/releases).
3. Move `CaCa.jar` to the folder you want to use as the home folder for your CaCa chatbot.
4. Double-click `CaCa.jar` to start the app.
5. You have successfully opened CaCa 🥳! Try typing some commands in the text box, press Enter on your keyboard or click "Send" to execute it.  
More details on the available commands can be found under [Features & Usage](#features--usage).

<img width="450" alt="Ui" src="https://carriezhengjr.github.io/ip/Ui.png">

<hr>

## Features & Usage

Let's explore the powers of CaCa! 😎

| Features                                                                        | Command                                                        |
|---------------------------------------------------------------------------------|----------------------------------------------------------------|
| Greet user                                                                      | (triggered as soon as the chatbot is run)                      |
| [Show help page](#showing-help-page-help)                                       | `help` <br/>(or via "Quick Start" or "Detailed Guide" buttons) |
 | [Add ToDo](#adding-todo-todo-todo_description)                                  | `todo TODO_DESCRIPTION`                                        |
 | [Add Deadline](#adding-deadline-deadline-deadline_description-by-ddmmyyyy-hhmm) | `deadline DEADLINE_DESCRIPTION /by dd/MM/yyyy HHmm`            |
| [Add Event](#adding-event-event-event_description-at-ddmmyyyy-hhmm)             | `event EVENT_DESCRIPTION /at dd/MM/yyyy HHmm`                  |
| [List all tasks](#listing-all-tasks-list)                                       | `list`                                                         |
| [Mark task](#marking-task-mark-task_index)                                      | `mark TASK_INDEX`                                              |
| [Unmark task](#unmarking-task-unmark-task_index)                                | `unmark TASK_INDEX`                                            |
| [Delete task](#deleting-task-delete-task_index)                                 | `delete TASK_INDEX`                                            |
| [Find task using keyword](#finding-task-using-keyword-find-keyword)             | `find KEYWORD`                                                 |
| [Exit application](#exiting-application-bye)                                    | `bye`                                                          |

<br>

### Showing help page: `help`

Access the help page containing all the commands with usage.  

_Note: You can also access the help page via __Quick Start__ or __Detailed Guide__ buttons found on the top bar._  
(1) __Quick Start__: displays all the available command words.  
(2) __Detailed Guide__: displays all the commands with usage (same as `help`).

- Example of usage: `help`

- Expected outcome: The full command guide will be displayed.

### Adding ToDo: `todo TODO_DESCRIPTION`

Add a todo without any date & time.

- Example of usage: `todo revision`

- Expected outcome: The todo will be added to your task list and saved locally.

```
Got it. I've added this task (^_^)
[T][ ] revision
Now you have 1 tasks in the list.
```

### Adding Deadline: `deadline DEADLINE_DESCRIPTION /by dd/MM/yyyy HHmm`

Add a deadline to be completed by a date & time.

- Example of usage: `deadline iP /by 16/09/2022 2359`

- Expected outcome: The deadline will be added to your task list and saved locally.

```
Got it. I've added this task (^_^)
[D][ ] iP (by: Sep 16 2022 23:59)
Now you have 2 tasks in the list.
```

### Adding Event: `event EVENT_DESCRIPTION /at dd/MM/yyyy HHmm`

Add an event that occurs at a date & time.

- Example of usage: `event talk /at 30/09/2022 1100`

- Expected outcome: The event will be added to your task list and saved locally.

```
Got it. I've added this task (^_^)
[E][ ] talk (at: Sep 30 2022 11:00)
Now you have 3 tasks in the list.
```

### Listing all tasks: `list`

Display a list of all your tasks.

- Example of usage: `list`

- Expected outcome: The list of tasks stored locally will be displayed.

```
Your task list (^_^)
1.[T][ ] revision
2.[D][ ] iP (by: Sep 16 2022 23:59)
3.[E][ ] talk (at: Sep 30 2022 11:00)
```

### Marking task: `mark TASK_INDEX`

Marks the task with specified task index as done, using a "X".

- Example of usage: `mark 1`

- Expected outcome: The task will be marked with "[X]" and updated locally.

```
Nice! (> O <)
I've marked this task as done:
[T][X] revision
```

### Unmarking task: `unmark TASK_INDEX`

Unmarks the task with specified task index to update task status as not done, and removes "X".

- Example of usage: `unmark 1`

- Expected outcome: The task will be unmarked with "[ ]" and updated locally.

```
OK (O_O)
I've marked this task as not done yet:
[T][ ] revision
```

### Deleting task: `delete TASK_INDEX`

Deletes the task with specified task index from your task list.

- Example of usage: `delete 3`

- Expected outcome: The task will be deleted from your task list and updated locally.

```
Noted (^_^)
I've removed this task:
[E][ ] talk (at: Sep 30 2022 11:00)
Now you have 2 tasks in the list.
```

### Finding task using keyword: `find KEYWORD`

Finds all matching tasks containing the keyword from your task list.

- Example of usage: `find week 5`

- Expected outcome: All matching tasks containing the keyword will be displayed.  
Note: The output from this example is obtained by first adding `todo week 5 iP` and `deadline week 5 quiz /by 09/09/2022 1600`.

```
Matching tasks in your list (^_^)
1.[T][ ] week 5 iP
2.[D][ ] week 5 quiz (by: Sep 09 2022 16:00)
```

### Exiting application: `bye`

Ends CaCa chatbot and exits the application. 

- Example of usage: `bye`

- Expected outcome: A goodbye message is shown. The program window will close automatically after 1.5 seconds. 

```
Bye (T_T)
Hope to see you again soon!
```

<hr>

## FAQ

__Q:__ Is my task list saved?  
__A:__ Yes. All of your data will be saved and updated automatically when you use CaCa.

__Q:__ How do I access all my saved data?  
__A:__ You can go to the subfolder named `data` located in the same folder as `CaCa.jar`. Then, you can open the file named `caca.txt` located under `data`.

__Q:__ Can I edit the saved file directly and load those data when I run CaCa?  
__A:__ It is possible but not recommended to make edits directly in `caca.txt`, as doing so might corrupt the data if not done correctly.

__Q:__ What to do if my saved data is corrupted and could not be read?  
__A:__ Unfortunately, your data cannot be restored. You can delete the `caca.txt` file or simply delete the whole `data` folder. A __fresh__ `caca.txt` will be created when you run the program again.

__Q:__ Where can I report an issue?  
__A:__ You can post issues [here](https://github.com/carriezhengjr/ip/issues). Simply click "New issue" and fill in the form.
If you know Markdown, you can also use [GitHub flavored Markdown (GFMD)](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)
to style your comments and preview it under the "Preview" tab.