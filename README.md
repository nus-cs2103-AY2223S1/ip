# User Guide

Bobby is your friendly crewmate that will help you
to keep track of your tasks!
With Bobby, you will never forget another task again!

![This is a screenshot of the UI](https://github.com/ruihan00/ip/blob/master/docs/Ui.png?raw=true)
<br/>

##Table of contents
1. [Quick start](#quick-start-using-intellij)
2. [Features](#features)
3. [Adding a todo](#todo-description---creates-a-new-todo)
4. [Adding a deadline](#deadline-description-by-deadline---creates-a-deadline)
5. [Adding a event](#event-description-at-start-time---end-time---creates-a-event)
6. [Viewing all tasks](#list---view-tasks)
7. [Deleting a task](#delete-task-index---delete-task)
8. [Marking a task](#mark-task-index---mark-task-as-completed)
9. [Unmarking a task](#unmark-task-index---mark-task-as-uncompleted)
10. [Finding a task](#find-keywords---search-for-task)
11. [Switching data files](#switch-file-name---change-data-source)
12. [Exiting the application](#bye---exit)


## Quick Start: Using IntelliJ
Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/bobby.Bobby.java` file, right-click it, and choose `Run bobby.Bobby.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello fellow crewmate
   What can i do for u today?
   ```

## Features

### Managing Task

Bobby assist you in keeping track of 3 types of tasks. Todos, Events and Deadlines
You are able to add and delete task as well as toggle the completion status of the task
### Find Tasks

Bobby will help you find tasks based on your query


### Swtich data files

Bobby allows you to switch data files easily so you can organise your tasks even better!

### Auto Save

Bobby will automatically save your task into your local data file

## Usage

### `todo [description]` - Creates a new Todo

Adds a new Todo to your task list

Example of usage:

`todo buy groceries`

Expected outcome:

Description of the outcome.

```
Oh god you have another task
[T][] buy groceries
you have 1 tasks now...
```

### `deadline [description] /by [deadline]` - Creates a Deadline

Adds a new Deadline to your task list

Example of usage:

`deadline submit assignment 1 /by 2022-10-06 15:00`

Expected outcome:

Description of the outcome.

```
Oh god you have another task
[D][] submit assignment 1 (by: 06 Oct 2022, 3.00pm) 
you have 2 tasks now...
```

### `event [description] /at [start time] - [end time]` - Creates a Event

Adds a new Event to your task list


### Same-day Events
Example of usage :

`event Samantha's birthday /at 2022-11-10 19:00 - 21:00`

Expected outcome:

Description of the outcome.

```
Oh god you have another task
[E][] Samantha's birthday (at 10 Nov 2022, 7.00pm to 9.00pm) 
you have 3 tasks now...
```

###Multi-day Events
Example of usage:

`event Australia trip /at 2022-12-11 08:00 - 2022-12-19 23:59`

Expected outcome:

Description of the outcome.

```
Oh god you have another task
[E][] Australia trip (at 11 Dec 2022, 7.00pm to 19 Dec 2022 11.59pm) 
you have 4 tasks now...
```
### `list` - View tasks

List all the task in your task list

Example of usage:

`list`

Expected outcome:

```
Wow i am so useful
1. [T][] buy groceries
2. [D][] submit assignment 1 (by: 06 Oct 2022, 3.00pm) 
3. [E][] Samantha's birthday (at 10 Nov 2022, 7.00pm to 9.00pm) 
4. [E][] Australia trip (at 11 Dec 2022, 7.00pm to 19 Dec 2022 11.59pm) 
```
### `delete [task index]` - delete task

Delete a specified task

Example of usage:

`delete 4`

Expected outcome:

```
Thats right tasks are just a concept
[E][] Australia trip (at 11 Dec 2022, 7.00pm to 19 Dec 2022 11.59pm) 
You have 3 tasks now...
```

### `mark [task index]` - Mark task as completed

Mark a specified task as completed

Example of usage:

`mark 1`

Expected outcome:

```
Wow so effishun
[T][X] buy groceries
```

### `unmark [task index]` - Mark task as uncompleted

Mark a specified task as uncompleted

Example of usage:

`unmark 1`

Expected outcome:

```
SUS you didnt finish the task
[T][] buy groceries
```

### `find [keywords]` - Search for task

Searches for task that includes the keywords

Example of usage:

`find assignment`

Expected outcome:

```
Scanning all file
[D][] submit assignment 1 (by: 06 Oct 2022, 3.00pm) 
Wow i am the best
```

### `switch [file name]` - change data source

Switch the data file being used by the task manager

Example of usage:

`switch school.txt`

Expected outcome:

```
File switched successfully to school.txt
```

### `bye` - exit

Exits bobby

Example of usage:

`bye`

Expected outcome:

```
Bobby was ejected
```
