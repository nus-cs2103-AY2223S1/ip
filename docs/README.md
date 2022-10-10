# TaskDive User Guide

## Introduction
### What is TaskDive?
__TaskDive__ is a personalized chatbot designed to arrange tasks for users. It is a __Command-Line Software__ that reads in 
user input command and prints out real-time response messages. Three types of tasks are supported by TaskDive, 
namely todo, deadline and event. Rich task-related features are provided, 
a user can use TaskDive to freely add and delete unlimited number of tasks, list tasks, mark tasks as done/undone, 
and tag tasks. TaskDive can be started and stopped by user at any time, 
all changes to tasks made by user will be automatically stored.

### Tasks in TaskDive
There are 3 types of tasks supported by TaskDive, as shown in the table below.

|  Task Type  | Task Description | Task status |  Tags    |    Start date     |   End date    |
|:-----------:|:----------------:|:-----------:|:--------:|:-----------------:|:-------------:|
|   1. ToDo   | &#10003;         | &#10003;    | &#10003; |     &#10007;      |    &#10007;   |
| 2. Deadline | &#10003;         | &#10003;    | &#10003; |     &#10007;      |    &#10003;   |
|  3. Event   | &#10003;         | &#10003;    | &#10003; |     &#10003;      |    &#10003;   |

The attributes of a task include:
- __Task description__: a simple one-line description of the task.
- __Task status__: done or undone.
- __Tags__: a list of tags attached to the task. A tag is a single word that only contains alphabets,
  repeated tags are not allowed in the tag list.
- __Start/End Date__: the date that the tasks starts/ends.

### User command
In this user guide, the user command for each feature is of the format `keyword {argument} (optional argument)`, 
all command keywords are case-sensitive. __For simplicity, all example commands listed in the feature section are assumed 
to be executed sequentially.__ Please see the Features section for more detailed commands.

Please take note that if the command entered by user is invalid (E.g.: missing arguments, invalid arguments, 
typo in keyword, etc.), __the command will NOT be executed!__
TaskDive will print out a message to inform user what's wrong with the command, and wait for the next user command.


## Features
1. View user guide information
2. Add task
   - add Todo task
   - add Deadline Task
   - add Event task
3. Delete task
4. Mark task
5. Unmark task
6. Add tag to task 
7. Delete tag in task 
8. List tasks
   - list all tasks
   - list tasks by date
   - list tasks by keyword
   - list tasks by tag
9. Exit chatbot
10. Store tasks

### `help` -- View User guide information
__Command format__: `help`

This command prints out important user guide information related to task types, command formatting 
and other cautions about user commands. 

__Example of usage:__`help`

__Expected outcome:__
```
There are 3 types of task implemented:
  1. todo : tasks without any date/time attached to it
  2. deadline : tasks that need to be done before a specific date/time
  3. event : tasks that start at a specific time and ends at a specific time

Below is all the command you can use with the command format specified:
  1.  Add todo : todo {task description}
  2.  Add deadline : deadline {task description} /by {end date}
  3.  Add event : event {task description} /at {start date} to {end date}
  4.  Delete task : delete {task index}
  5.  Mark task as done : mark {task index}
  6.  Mark task as undone : unmark {task index}
  7.  Tag task : tag {task index} /with {tag}
  8.  Untag task : untag {task index} /with {tag}
  9.  List all tasks : list
  10. List unfinished tasks by date : list {date}
  11. List tasks by description keyword : list {keyword}
  12. List tasks by tag : list #{tag}
  13. Exit TaskDive chatbot : bye

Other Remarks:
  1. Acceptable date formats include dd/MM/yyyy, yyyy/MM/dd, yyyy-MM-dd, dd-MM-yyyy, dd MM yyyy, yyyy MM dd.
  2. A tag / keyword must be a case-sensitive word containing only alphabets.
  3. A task can contain at most 3 tags, adding repeated tags to a task is not allowed.
  4. Task list will be auto-saved after bye command and auto-loaded when TaskDive chatbot starts up.
```

### Add Task
This feature allows user to add a ToDo/ Deadline/ Event task to TaskDive. 
The command format of adding different types of tasks are different, as shown in the next 3 subsections.


### `todo` - Add ToDo task
__Command format:__ `todo {description}`

This command adds a new task of type ToDo to TaskDive. To ensure this command is valid:
- The description argument CANNOT be empty.

__Example of usage:__ 
1. `todo go shopping`
2. `todo  `

__Expected outcome:__ (Assume there are no tasks in TaskDive before this command)
1. ```
   Got it. I've added this task:
     [T][ ] go shopping {}
   
   Now you have 1 tasks in the list.
   ```
2. ```
   ☹ OOPS!!! The description of a todo task cannot be empty.
   ```

### `deadline` - Add Deadline task
__Command format:__ `deadline {description} /by {date}`

This command adds a new task of type Deadline to TaskDive. To ensure this command is valid:
- The description argument CANNOT be empty.
- The date must follow one of the following format: `dd/MM/yyyy`, `yyyy/MM/dd`, `yyyy-MM-dd`, 
`dd-MM-yyyy`, `dd MM yyyy`, `yyyy MM dd`, for example, `2022-09-14`.
- The year/ month/ day value in the date must be valid, for example, `2022/13/12` is an invalid date. 

__Example of usage:__ 
1. `deadline short quiz /by 2022-09-15`
2. `deadline short quiz /by 2022 Sep 15`
3. `deadline short quiz /by 2022-09-34`

__Expected outcome:__
1. ```
   Got it. I've added this task:
     [D][ ] short quiz (by 2022-09-15) {}

   Now you have 2 tasks in the list.
   ```
2. ```
   ☹ OOPS!!! This is not a proper date format, year, month or day value is invalid.
   ```
3. ```
   ☹ OOPS!!! This is not a proper date format, year, month or day value is invalid.
   ```

### `event` - Add Event task
__Command format:__ `event {description} /at {start date} to {end date}`

This command adds a new task of type Event to TaskDive. To ensure this command is valid:
- The description argument CANNOT be empty.
- The date must follow one of the following format: `dd/MM/yyyy`, `yyyy/MM/dd`, `yyyy-MM-dd`,
  `dd-MM-yyyy`, `dd MM yyyy`, `yyyy MM dd`, for example, `2022-09-14`.
- The year/ month/ day value in the date must be valid, for example, `2022/13/12` is an invalid date.
- The start date CANNOT be after end date.

__Example of usage:__
1. `event long ideathon /at 2022-09-15 to 2022-10-01`
2. `event long ideathon /at 2022-09-15 to 2022-09-14`

__Expected outcome:__
1. ```
   Got it. I've added this task:
     [E][ ] long ideathon (at 2022-09-15 - 2022-10-01) {}
   
   Now you have 3 tasks in the list.
   ```
2. ```
   ☹ OOPS!!! The start date of a Event task cannot be after end date.
   ```

### `delete` - Delete task
__Command format:__ `delete {task index}`

This command deletes a task at index entered by user; list command can be used to see all tasks with indexes (explained later).
To ensure this command is valid:
- The index CANNOT be empty. It can ONLY be positive integers from 1 to the last task index.

__Example of usage:__
1. `delete 2` 
2. `delete  `
3. `delete 4`

__Expected outcome:__
1. ```
   Noted. I've removed this task:
     [D][ ] short quiz (by 2022-09-15)
   
   Now you have 2 tasks in the list.
   ```
2. ```
   ☹ OOPS!!! The task index of a delete command cannot be empty.
   ```
3. ```
   ☹ OOPS!!! The task index exceeds task list size limit.
   ```

### `mark` - Mark task
__Command format:__ `mark {task index}`

This command marks the status of the task at index as done regardless of the previous status of the task (done/ undone).
List command can be used to see all tasks with indexes (explained later). The status of the task is represented by:
- `[x]`: task already done
- `[ ]`: task not done yet

To ensure this command is valid:
- The index CANNOT be empty. It can ONLY be positive integers from 1 to the last task index.

__Example of usage:__
1. `mark 2`
2. `mark  5`

__Expected outcome:__ 
1. ```
   Nice! I've marked this task as done:
     [E][X] long ideathon (at 2022-09-15 - 2022-10-01) {}
   ```
2. ```
   ☹ OOPS!!! The task index exceeds task list size limit.
   ```

### `unmark` - Mark task
__Command format:__ `unmark {task index}`

This command marks the status of the task at index as undone regardless of the previous status of the task (done/ undone).
list command can be used to see all tasks with indexes (explained later).

To ensure this command is valid:
- The index CANNOT be empty. It can ONLY be positive integers from 1 to the last task index.

__Example of usage:__
1. `unmark 2`
2. `unmark  5`

__Expected outcome:__ (Assume only the commands in previous sections are executed)
1. ```
   OK, I've marked this task as not done yet:
     [E][ ] long ideathon (at 2022-09-15 - 2022-10-01) {}
   ```
2. ```
   ☹ OOPS!!! The task index exceeds task list size limit.
   ```
   
### `tag` - Add tag to task
__Command format:__ `tag {task index} /with {tag}`

This command adds a tag to the task at index. If the index is invalid/ tag is invalid/ 
the tag already exists in the task/ the number of tags in task exceeds 3, the command will not be executed.

To ensure this command is valid:
- The index CANNOT be empty. It can ONLY be positive integers from 1 to the last task index.
- The tag is a single word __only containing alphabets__; it should be new (i.e. different from all tags existing in the task).

__Example of usage:__
1. `tag 2 /with important`
2. `tag 2 /with important` (executed after command 1)

These 2 tag commands are executed after command 2 and before command 3:
```
tag 2 /with fun
tag 2 /with team
```
The task no. 2 now has 3 tags: {important, fun, team}.
3. `tag 2 /with long`
4. `tag 1 with 2day`

__Expected outcome:__
1. ```
   OK, I've added this tag important for the task:
     [E][ ] long ideathon (at 2022-09-15 - 2022-10-01) {important}
   
   Now the task have 1 tags: {important}
   ```
2. ```
   ☹ OOPS!!! The tag important is already in the task tag list, same tag cannot be added again.
   ```
3. ```
   ☹ OOPS!!! The tag cannot be added because a task cannot have more than 3 tags.
   ```
4. ```
   ☹ OOPS!!! The tag can only contain alphabets and it cannot be empty.
   ```

### `untag` - Delete tag in task
__Command format:__ `untag {task index} /with {tag}`

This command deletes a tag to the task at index. If the index is invalid/ tag is invalid/
the tag doesn't exist in the task, the command will not be executed.

To ensure this command is valid:
- The index CANNOT be empty. It can ONLY be positive integers from 1 to the last task index.
- The tag is a single word __only containing alphabets__; it should already exist in the task so that it can be deleted.

__Example of usage:__
1. `untag 2 /with team`
2. `untag 2 /w fun`
3. `untag 1 /with fun`

__Expected outcome:__
1. ```
   OK, I've deleted this tag team for the task:
     [E][ ] long ideathon (at 2022-09-15 - 2022-10-01) {important,fun}
   
   Now the task have 2 tags: {important,fun}
   ```
2. ```
   ☹ OOPS!!! The command is not properly formatted. Please follow the format: tag {task index} /with {tag}.
   ```
3. ```
   ☹ OOPS!!! The tag fun is not in the task tag list, so it cannot be deleted.
   ```

### List tasks
__Command format:__ `list (date) (keyword) (#tag)`

This command lists tasks by the argument entered. The task index, status, description, date and tags are shown for each task in the list.
Please take note that there should be __<= 1 optional argument__ presented in the command, 
the details of the optional arguments are explained in the next 4 subsections.

### `list` - List all tasks
__Command format:__ `list`

This command lists all tasks in TaskDive.

__Example of usage:__

To make the list longer, 3 other tasks are added after all previous commands.
```
event finish CS2103 project /at 2022-09-12 to 2022-09-16
deadline DSA3102 pop quiz /by 2022-09-14
todo team project startup
```
```
list
```

__Expected outcome:__ 
```
Here are the tasks in your list:

1.[T][ ] go shopping {}
2.[E][ ] long ideathon (at 2022-09-15 - 2022-10-01) {important,fun}
3.[E][ ] finish CS2103 project (at 2022-09-12 - 2022-09-16) {}
4.[D][ ] DSA3102 pop quiz (by 2022-09-14) {}
5.[T][ ] team project startup {}

```

### `list` - List tasks by date
__Command format:__ `list {date}`

This command lists the  __unfinished__ tasks in TaskDive that satisfy the following conditions:
- The task is a Deadline task, with its end date later than /equal to the date entered by user. 
- The task is a Event task, with the date entered by user between start date and end date inclusively.

To ensure the command is valid, the date format must follow the rules in Add deadline task section.

__Example of usage:__ `list 2022-09-15`

__Expected outcome:__
```
Here are unfinished tasks on this date in your list:

1.[E][ ] long ideathon (at 2022-09-15 - 2022-10-01) {important,fun}
2.[E][ ] finish CS2103 project (at 2022-09-12 - 2022-09-16) {}

```

### `list` - List tasks by keyword
__Command format:__ `list {keyword}`

This command lists the tasks in TaskDive whose task description contains the keyword. To ensure the command is valid:
- The keyword CANNOT be empty, otherwise TaskDive recognize it as a `list` command that lists all tasks. 
- The keyword is a single word __only containing alphabets__.

__Example of usage:__ 
1. `list project`
2. `list test`

__Expected outcome:__
1. ```
   Here are the matching tasks in your list:
   
   1.[E][ ] finish CS2103 project (at 2022-09-12 - 2022-09-16) {}
   2.[T][ ] team project startup {}
   ```
2. ```
   Here are the matching tasks in your list:
   
   Oops! There's no matching tasks found :-(
   ```

### `list` - List tasks by tag
__Command format:__ `list #{tag}`

This command lists the tasks in TaskDive that has the tag entered by user. To ensure the command is valid:
- The tag CANNOT be empty, otherwise TaskDive recognize it as a `list` command that lists all tasks.
- The tag is a single word __only containing alphabets__.

__Example of usage:__

To make the list output longer, these commands are executed before command 1 and 2.
```
tag 3 /with important
tag 5 /with important
tag 4 /with short
```
1. `list #important`
2. `list #long`

__Expected outcome:__
1. ```
   Here are the matching tasks in your list:
   
   1.[E][ ] long ideathon (at 2022-09-15 - 2022-10-01) {important,fun}
   2.[E][ ] finish CS2103 project (at 2022-09-12 - 2022-09-16) {important}
   3.[T][ ] team project startup {important}

   ```
2. ```
   Here are the matching tasks in your list:
   
   Oops! There's no matching tasks found :-(
   ```
   
### `bye` - Exit TaskDive
__Command format:__ `bye`

This command allows user to exit TaskDive chatbot. Upon entering `bye` command, TaskDive prints out exiting message, 
and asks user to type `yes` to confirm exit, after user types `yes`, TaskDive stops itself. 

Please take note that if a user doesn't enter `yes` after he/she enters `bye` command, 
TaskDive will not recognize the command, and it will not stop itself.

__Example of usage:__`bye`

__Expected outcome:__
```
TaskDive Chatbot will stop, all previous tasks will be auto-saved :D

Bye, Hope to see you again soon!
Please type 'yes' to confirm exit or close the window.
```
- user types `yes`: TaskDive chatbot exits.
- user types other random command, e.g. `no`: TaskDive prints out `☹ OOPS!!! I'm sorry, but I don't know what that means :-(`
 
### Store tasks
All tasks added to TaskDive are stored as a list of tasks in TaskDive storage. TaskDive auto-saves the tasks 
immediately after a change is made (i.e.: user enters add/delete/mark/tag related commands), the list of tasks are stored in a 
text file in the user's local computer. 

The text file directory relatively to TaskDive is `data/Duke.txt`. To customize the text file path for TaskDive storage, a user can change the 
filepath in the following code in `src/main/java/Main.java` file.
```
private Duke duke = new Duke("data/Duke.txt");
```
## GUI
Please refer to `Ui.png` in `docs` folder for sample GUI of TaskDive. Some customizable GUI elements are listed below: 
- Change background picture: replace `src/main/resources/image/background.png` with a new background image.
- Change user profile photo: replace `src/main/resources/image/DUser.png` with a new user profile image.
- Change TaskDive profile picture: replace `src/main/resources/image/DDuke.png` with a new TaskDive profile image.

Please take note that the name of the new picture must remain the SAME as the previous picture.

## End of User Guide. Enjoy using TaskDive to arrange your tasks :-D