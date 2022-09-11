# User Guide

Duke is a dedicated task manager chat-bot app that helps you **record and manage your tasks.** It is designed to free user mind and **ease them in remembering all the different tasks and crucial dates in their life.** It uses **Command Line Interface (CLI)** to operate and **integrates with Graphical User Interfaces (GUI)** to enhance user experiences.

## Quick start

1. Download the latest release of **duke.jar** from [here](https://github.com/eesung00/ip/releases).
2. Move the duke.jar folder to your desire home folder.
3. Double click to run it.
4. Have fun!

To know all `command` to use this app, user can simply send anything to the bot. Duke will respond the list of `command`. They are simple and easy to use!

## Features

Add Tasks: add task according to format. See [below](#feature-add).<br/>
Delete Task: `delete`<br/>
Mark Task as "done": `mark`<br/>
Mark Task as "undone": `unmark`<br/>
Find Task: `find`<br/>
Show all Tasks: `list`<br/>

Extension: Duke is able to detect anomalies of task to be added. Confirmation will be needed from user if two tasks happen in very close timing or task to be added is already added before. `(Y/N)`

### Feature-add

Adds any three type of tasks into the list shown in sample below:

- todo tasks: `todo dummyTask`
- event tasks: `event dummyEvent /at 2022-09-10 2214`
- deadline tasks: `deadline dummyDeadline /by 2022-09-10 2215`

<details><summary><mark>Tips!</mark></summary>
Please follow the format shown above! Every first word in the input line is a command. (Case-sensitive)
The second section of the input line is the tasks detail.
The third section after /at and /by is the date and time of the task. Please follow the format.(YYYY-MM-DD HHmm)
</details>

### Feature-`delete`

* Use command `delete [number of task in the list]` to delete the task u wish to eliminate.

<details><summary><mark>Tips!</mark></summary>
Use list command to show the current tasks list you have if you are not sure what is your tasks' number.
</details>

### Feature-`mark/unmark`

* Use command `mark [number of task in the list]` to mark the task u wish to mark as "done".
* Use command `unmark [number of task in the list]` to unmark the task u wish to mark as "undone".

### Feature-`find`

* Use command `find [search keyword]` to find the task according to the search keyword. (Case-sensitive)

### Feature-`list`

* Use command `list` to show all the tasks and their current status.


## Usage

<sub>This section covers some sample usage of duke app.</sub>

### `event` - adding an event task

<details><summary>Event task is added with a correct format and duke response user the command success status</summary>

* Example of usage:</br>
  `event go for final exam /at 2022-11-04 1000`

* Expected outcome:
    ```
    ~~~~~-----DUKE-----~~~~~
    New task is registered as you wish, you can come back to check if you wish!:
    [E][ ] go for final exam (at: Nov 04 2022 10:00)
    Now you have 1 tasks in your list.
    ```
* **Description:** There is currently 1 task in user's list and the adding command performed successfully.
</details>

### `delete` - deleting a task

<details><summary>Event task is deleted and there are currently 0 task in the list</summary>

* Example of usage:</br>
  `delete 1`

* Expected outcome:
    ```
    ~~~~~-----DUKE-----~~~~~
    Ching Ching Poof~~ This task is removed:
    [E][ ] go for final exam (at: Nov 04 2022 10:00)
    Now you have 0 tasks on your list.
    ```
* **Description:** There is currently 0 task in user's list and the deleting command performed successfully.
</details>

### `find` - finding a task with search keyword.

<details><summary>Find a task which contain `exam` word in it's detail</summary>

* Example of usage:</br>
`find exam`

* Expected outcome:
    ``` 
    ~~~~~-----DUKE-----~~~~~
    Here you go! your matching tasks in your list
    [T][ ] exam preparation
    [E][ ] exam at Utown (at: Nov 12 2022 19:00)
    ```

* **Description:** There is currently 2 tasks in user's list with "exam" detail.
</details>

### `list` - listing all the tasks in the tasks list

<details><summary>Show all tasks added in the tasks list</summary>

* Example of usage:</br>
`list`

* Expected outcome:
    ```
    ~~~~~-----DUKE-----~~~~~
    Weeeee, your current list is as follow:
    1. [E][ ] dummyEvent (at: Sep 11 2011 11:30)
    2. [D][ ] dummyDeadline (by: Jan 28 2011 09:00)
    3. [T][ ] dummyTodo
    4. [T][ ] exam preparation
    5. [E][ ] exam at Utown (at: Nov 12 2022 19:00)
    ```
* **Description:** There are currently 6 tasks in user's list and all of them are undone.
</details>

### `mark/unmark` - mark tasks as "done" or "undone"

<details><summary>Mark and unmark a task</summary>

* Example of usage:</br>
`mark 1`</br>
`unmark 1`

* Expected outcome:
    ```
    ~~~~~-----DUKE-----~~~~~
    Nice! this task is marked as done. Good Job!
    [E][X] dummyEvent (at: Sep 11 2011 11:30)
    ```
    ```
    ~~~~~-----DUKE-----~~~~~
    This task is marked as not done. Keep it up!
    [E][ ] dummyEvent (at: Sep 11 2011 11:30)
    ```
* **Description:** The event task is mark as "done" and unmark as "undone" respectively
</details>

### Anomalies detection

<details><summary>Tasks date and time is close</summary>

* Example of input: </br>
`event attend party /at 2011-09-11 1300`

* Expected outcome:
    ```
    ~~~~~-----DUKE-----~~~~~
    Hey, these two tasks timing are quite close, are you sure to proceed? (Y/N)
    New Task: [E][ ] attend party (at: Sep 11 2011 13:00)
    Existing Task: [E][ ] dummyEvent (at: Sep 11 2011 11:30)
    ```
* **Description:** This happens because there is another event task in the task list occur at close timing. Please respond `Y` to proceed adding task or `N` to cancel the previous add task command.
</details>

### Response for random input

<details><summary>Random Input</summary>

* Example of random input:</br>
`hi`

* Expected outcome:
    ```
    ~~~~~-----DUKE-----~~~~~
    Based on my understanding, your command didn't follow the format
    todo              : todo [task description]
    deadline          : deadline [task description] /by [YYYY-MM-DD HHmm]
    event             : event [task description] /at [YYYY-MM-DD HHmm]
    single command    : | bye | list |
    mark/unmark/delete: [command] [number of task in list you wish to modify]
    find              : find [search keyword]
    ```
* **Description:** Duke will not understand any random input. It will show the available input if it does not recognise the input.
</details>

<details><summary>Incorrect date format</summary>

* Example of error input 1:</br>
`deadline some deadline /by 09-01-2022 0900`

* Expected outcome:
    ```
    ~~~~~-----DUKE-----~~~~~
    Date and time format should be [YYYY-MM-DD HHmm]!
    (eg. 2022-08-21 1300)
    ```
* **Description:** Duke will show the correct date format that user should use while interacting with the app.
</details>
