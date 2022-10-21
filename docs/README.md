# User Guide

Duke is a dedicated task manager chatBot app that helps you **record and manage your tasks.** It is designed to free user mind and **ease them in remembering all the different tasks and crucial dates in their life.** It uses **Command Line Interface (CLI)** to operate and **integrates with Graphical User Interfaces (GUI)** to enhance user experiences.<br>
<br><img src="Ui.png" height = "400">
## Quick start

1. Download the latest release of **duke.jar** from [here](https://github.com/eesung00/ip/releases).
2. Move the duke.jar folder to your desire home folder.
3. Double click to run it.
4. **Sample data** are available for new user.
5. Have fun!

To know all `command` to use this app, user can simply send anything to the bot. Duke will respond the list of `command`. They are simple and easy to use!

## Features

Add Tasks: add task according to format. See [below](#feature-add).<br/>
Delete Task: `delete`<br/>
Mark Task as "done": `mark`<br/>
Mark Task as "undone": `unmark`<br/>
Find Task: `find`<br/>
Show all Tasks: `list`<br/>
Stop using app: `bye` <br/>

Extension: Duke is able to detect anomalies of task to be added. Confirmation will be needed from user if two tasks happen in very close timing or task to be added is already added before. `(Y/N)`

### Feature-add

Adds any three type of tasks into the list shown in sample below:

- todo tasks: `todo visit Singapore`
- event tasks: `event go birthday party /at 2022-09-10 2214`
- deadline tasks: `deadline final year project /by 2022-09-10 2215`

<details><summary><mark>Tips!</mark></summary>
Please follow the format shown above! Every first word in the input line is a command. (Case-sensitive) <br>
The second section of the input line is the tasks detail.<br>
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

### Feature-`bye`
* Use command `bye` to stop using the program. User text field and send button will be disabled.

## Usage

<sub>This section covers some sample usage of duke app.</sub>

### `event` - adding an event task

<details><summary>Event task is added with a correct format and duke response user the command success status</summary>

<ul>
  <li> Example of usage:<br>
    <code>event go for final exam /at 2022-11-04 1000</code>
  </li>

  <li>Expected outcome:
  <pre><code>  ~~~~~~~-----DUKE-----~~~~~~~
  New task is registered as you wish, you can come back to check if you wish!:
    [E][ ] go for final exam (at: Nov 04 2022 10:00)
  Now you have 1 tasks in your list.</code></pre>
  </li>
  <li><b>Description:</b> There is currently 1 task in user's list and the adding command performed successfully.</li>
</ul>

</details>

### `todo` - adding a todo task

<details><summary>ToDo task is added with a correct format and duke response user the command success status</summary>

<ul>
  <li> Example of usage:<br>
    <code>todo workout tomorrow morning.</code>
  </li>

  <li>Expected outcome:
  <pre><code>  ~~~~~-----DUKE-----~~~~~
  New task is registered as you wish, you can come back to check if you wish!:
    [T][ ] workout tomorrow morning.
  Now you have 1 tasks on your list.</code></pre>
  </li>
  <li><b>Description:</b> There is currently 1 task in user's list and the adding command performed successfully.</li>
</ul>

</details>

### `deadline` - adding a deadline task

<details><summary>Deadline task is added with a correct format and duke response user the command success status</summary>

<ul>
  <li> Example of usage:<br>
    <code>deadline ip project /by 2022-09-15 2359</code>
  </li>

  <li>Expected outcome:
  <pre><code>  ~~~~~-----DUKE-----~~~~~
  New task is registered as you wish, you can come back to check if you wish!:
    [D][ ] ip project (by: Sep 15 2022 23:59)
  Now you have 2 tasks on your list.</code></pre>
  </li>
  <li><b>Description:</b> There are currently 2 tasks in user's list and the adding command performed successfully.</li>
</ul>

</details>

### `delete` - deleting a task

<details><summary>Event task is deleted and there are currently 0 task in the list</summary>
<ul>
  <li> Example of usage:<br>
    <code>delete 1</code>
  </li>

  <li>Expected outcome:
  <pre><code>  ~~~~~-----DUKE-----~~~~~
  Ching Ching Poof~~ This task is removed:
  [E][ ] go for final exam (at: Nov 04 2022 10:00)
  Now you have 0 tasks on your list.</code></pre>
  </li>
  
  <li><b>Description:</b> There is currently 0 task in user's list and the deleting command performed successfully.</li>
</ul>
</details>

### `find` - finding a task with search keyword.

<details><summary>Find a task which contain <code>exam</code> word in it's detail</summary>
<ul>
  <li>Example of usage:<br>
  <code>find exam</code>
  </li>

  <li>Expected outcome:
  <pre><code>  ~~~~~-----DUKE-----~~~~~
  Here you go! your matching tasks in your list
  [T][ ] exam preparation
  [E][ ] exam at Utown (at: Nov 12 2022 19:00) </code></pre>
  </li>

  <li><b>Description:</b> There are currently 2 tasks in user's list with "exam" detail.</li>
</ul>
</details>

### `list` - listing all the tasks in the tasks list

<details><summary>Show all tasks added in the tasks list</summary>
<ul>
  <li> Example of usage:<br>
  <code>list</code>
  </li>

  <li>Expected outcome:
  <pre><code>  ~~~~~-----DUKE-----~~~~~
  Weeeee, your current list is as follow:
  1. [E][ ] dummyEvent (at: Sep 11 2011 11:30)
  2. [D][ ] dummyDeadline (by: Jan 28 2011 09:00)
  3. [T][ ] dummyTodo
  4. [T][ ] exam preparation
  5. [E][ ] exam at Utown (at: Nov 12 2022 19:00)</code></pre>
  </li>

  <li><b>Description:</b> There are currently 6 tasks in user's list and all of them are undone.</li>
</ul>
</details>

### `mark/unmark` - mark tasks as "done" or "undone"

<details><summary>Mark and unmark a task</summary>
<ul>
  <li> Example of usage:<br>
    <code>mark 1</code><br>
    <code>unmark 1</code>
  </li>

  <li> Expected outcome:
  <pre><code>  ~~~~~-----DUKE-----~~~~~
  Nice! this task is marked as done. Good Job!
  [E][X] dummyEvent (at: Sep 11 2011 11:30)</code></pre>
  
  <pre><code>  ~~~~~-----DUKE-----~~~~~
  This task is marked as not done. Keep it up!
  [E][ ] dummyEvent (at: Sep 11 2011 11:30)</code></pre>
  </li>

  <li> <b>Description:</b> The event task is mark as "done" and unmark as "undone" respectively. "X" in the second brackets indicate the task is marked.</li>
</ul>
</details>

### Anomalies detection

<details><summary>Tasks date and time is close</summary>
<ul>
  <li> Example of input: <br>
  <code>event attend party /at 2011-09-11 1300</code>
  </li>

  <li>Expected outcome:
  <pre><code>  ~~~~~-----DUKE-----~~~~~
  Hey, these two tasks timing are quite close, are you sure to proceed? (Y/N)
  New Task: [E][ ] attend party (at: Sep 11 2011 13:00)
  Existing Task: [E][ ] dummyEvent (at: Sep 11 2011 11:30)</code></pre>
  </li>   
  
  <li> <b>Description:</b> This happens because there is another event task in the task list occur at close timing. Please respond `Y` to proceed adding task or `N` to cancel the previous add task command.</li>
</ul>
</details>

### Other Responses

<details><summary>Random Input</summary>
<ul>
  <li> Example of random input:<br>
  <code>hi</code>
  </li>

  <li> Expected outcome:
  <pre><code>  ~~~~~-----DUKE-----~~~~~
  Based on my understanding, your command didn't follow the format
  todo              : todo [task description]
  deadline          : deadline [task description] /by [YYYY-MM-DD HHmm]
  event             : event [task description] /at [YYYY-MM-DD HHmm]
  single command    : | bye | list |
  mark/unmark/delete: [command] [number of task in list you wish to modify]
  find              : find [search keyword]</code></pre>
  </li>

  <li> <b>Description:</b> Duke will not understand any random input. It will show the available input if it does not recognise the input.</li>
</ul>
</details>

<details><summary>Incorrect date format</summary>
<ul>
  <li> Example of incorrect date format:<br>
  <code>deadline some deadline /by 09-01-2022 0900</code>
  </li>

  <li>Expected outcome:
  <pre><code> ~~~~~-----DUKE-----~~~~~
  Date and time format should be [YYYY-MM-DD HHmm]!
  (eg. 2022-08-21 1300)</code></pre>
  </li>

  <li><b>Description:</b> Duke will show the correct date format that user should use while interacting with the app.</li>
</ul>
</details>
