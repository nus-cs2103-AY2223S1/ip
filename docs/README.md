# User Guide

<b>Pluto</b> is a <b>personal assistant to manage all your tasks</b> and keep them in one place. No need to worry about keeping track of all your upcoming deadlines and events anymore!

* <a href="#start" name="top">Quick start</a>
* <a href="#features">Features</a>
    * <a href="#todo">Adding a ToDo Task : `todo`</a>
    * <a href="#deadline">Adding a Deadline Task : `deadline`</a>
    * <a href="#event">Adding an Event Task : `event`</a>
    * <a href="#list">Listing all tasks : `list`</a>
    * <a href="#mark">Marking a task as done : `mark`</a>
    * <a href="#unmark">Marking a task as not done : `unmark`</a>
    * <a href="#delete">Deleting a task : `delete`</a>
    * <a href="#show">Finding day schedule  : `show`</a>
    * <a href="#find">Finding tasks by keyword(s) : `find`</a>
    * <a href="#reschedule">Rescheduling a task : `reschedule`</a>
    * <a href="#help">Displaying help message : `help`</a>
    * <a href="#bye">Exiting the program : `bye`</a>
* <a href="#summary">Command summary</a>


<p align="center">
  <img src="Ui.png" height="650px">
</p>

---

# <a name="start" style = "color: inherit;"></a>Quick start

* Ensure that you have Java 11 or above installed in your computer.
* Download the latest `pluto.jar` from [here](https://github.com/pratham31012002/ip/releases/download/A-Release/pluto.jar).
* Copy the file to the directory you want to use as the home folder for your assistant.
* Make sure the directory has read and write permissions.
* Double-click the file to start the application. A GUI similar to the one above should appear in a few seconds.
* If the previous step doesn't work, open the terminal, go to the application directory and try running `java -jar pluto.jar`.
* Type in the command and press Enter or click on the Send button to execute it. Enter `help` to know about all commands.

<p align="right">
<a href="#top">Back To Top</a>
</p>

---

# <a name="features" style = "color: inherit;"></a>Features

<ul>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;Add a ToDo task </li>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;Add a Deadline task </li>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;Add an Event task </li>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;List all tasks </li>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;Mark a task as Done </li>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;Unmark a task </li>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;Delete a task </li>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;Find a task by keyword(s) </li>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;Find a task by date </li>
<li><input type="checkbox" checked="checked" disabled="disabled"> &nbsp;Reschedule a task </li>
</ul>

<p align="right">
<a href="#top">Back To Top</a>
</p>

---

# Usage

## <a name="todo" style = "color: inherit;"></a>Adding a ToDo Task : `todo`

Adds a ToDo task with the provided description.

**Format:** `todo <task name>`

**Example of usage:** 

```
todo join meeting
> Got it. I've added this task:
> 	[T][ ] join meeting
> Now you have 1 task in the list.
```
<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="deadline" style = "color: inherit;"></a>Adding a Deadline Task : `deadline`

Adds a Deadline task with the provided description and deadline date and time.

**Format:** `deadline <task name> /by <date & time in 'dd-MM-yyyy HHmm' format>`

**Example of usage:**

```
deadline submit essay /by 01-10-2023 2359
> Got it. I've added this task:
> 	[D][ ] submit essay (by: Oct 01 2023 23:59)
> Now you have 2 tasks in the list.
```

<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="event" style = "color: inherit;"></a>Adding an Event Task : `event`

Adds an Event task with the provided description and event date and time.

**Format:** `event <task name> /at <date & time in 'dd-MM-yyyy HHmm' format>`

**Example of usage:**

```
event dinner /at 30-09-2022 2000
> Got it. I've added this task:
> 	[E][ ] dinner (at: Sep 30 2022 20:00)
> Now you have 3 tasks in the list.
```

<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="list" style = "color: inherit;"></a>Listing all tasks : `list`

Displays all tasks with their completion status.

**Format:** `list`

**Example of usage:**

```
list
> Here are the tasks in your list:
>  	1. [T][ ] join meeting
> 	2. [D][ ] submit essay (by: Oct 01 2023 23:59)
>	3. [E][ ] dinner (at: Sep 30 2022 20:00)
```
<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="mark" style = "color: inherit;"></a>Marking a task as done : `mark`

Marks the specified task as done.

**Format:** `mark <task number>`

**Example of usage:**

```
mark 1
> Nice! I've marked this task as done:
> 	[T][X] join meeting
```

<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="unmark" style = "color: inherit;"></a>Marking a task as not done : `unmark`

Marks the specified task as not done.

**Format:** `unmark <task number>`

**Example of usage:**

```
unmark 1
> OK, I've marked this task as not done yet:
> 	[T][ ] join meeting
```

<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="delete" style = "color: inherit;"></a>Deleting a task : `delete`

Deletes the specified task from the list of tasks.

**Format:** `delete <task number>`

**Example of usage:**

```
delete 2
> Noted. I've removed this task:
> 	[D][ ] submit essay (by: Oct 01 2023 23:59)
Now you have 2 tasks in the list.
```

<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="show" style = "color: inherit;"></a>Finding day schedule : `show`

Finds all tasks scheduled for the given date.

**Format:** `show <date in 'dd-MM-yyyy' format>`

**Example of usage:**

```
show 30-09-2022
> Here are the tasks on this date:
>	1. [E][ ] dinner (at: Sep 30 2022 20:00)
```
<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="find" style = "color: inherit;"></a>Finding tasks by keyword(s) : `find`

Finds all tasks that contain the given keyword(s). 
* The search is case-insensitive.
* Multiple keywords can be provided and the keywords in the task description need not be consecutive.

**Format:** `find <keyword(s)>`

**Example of usage:**

```
find MEETING
> Here are the matching tasks in your list:
> 	1. [T][ ] join meeting

todo read Harry Potter novel
> Got it. I've added this task:
>	[T][ ] read Harry Potter novel
> Now you have 3 tasks in the list.

find read novel
> Here are the matching tasks in your list:
>	1. [T][ ] read Harry Potter novel
```

<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="reschedule" style = "color: inherit;"></a>Rescheduling a task : `reschedule`

Reschedules an event or deadline task.

**Format:** `reschedule <task number> <date & time in 'dd-MM-yyyy HHmm' format>`

**Example of usage:**

```
reschedule 2 29-09-2022 2030
> Noted. I've rescheduled this task:
>	[E][ ] dinner (at: Sep 29 2022 20:30)
```

<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="help" style = "color: inherit;"></a>Displaying help message : `help`

Displays a help message with the list of commands and their usage format.

**Format:** `help`

**Example of usage:**

```
help
> List of Commands:
>
> Add a todo task  
> 		todo <task>
> Add an event     
>		event <task> /at <date>
> Add a deadline   
>		deadline <task> /by <date>
> Delete a task    
>		delete <index>
> Find keyword(s)  
>		find <keyword(s)>
> List all tasks   
>		list
> Mark as done     
>		mark <index>
> Unmark task      
>		unmark <index>
> Reschedule a task
>		reschedule <index> <date>
> Show day schedule
>		show <dd-MM-yyyy>
> Exit application 
>		bye
>
> NOTE: All dates should be in 'dd-MM-yyyy HHmm' format.
```
<p align="right">
<a href="#top">Back To Top</a>
</p>

---

## <a name="bye" style = "color: inherit;"></a>Exiting the program : `bye`

Exits the program.

**Format:** `bye`

**Example of usage:**

```
bye
> See You Later!
```
<p align="right">
<a href="#top">Back To Top</a>
</p>

---

# <a name="summary" style = "color: inherit;"></a>Command summary

<div align="center">

| Action     | Format, Examples                                                                                                       |
|------------|------------------------------------------------------------------------------------------------------------------------|
| Todo       | `todo <task name>` <br/> e.g., `todo read book`                                                                        |
| Deadline   | `deadline <task name> /by <date & time in 'dd-MM-yyyy HHmm' format>`<br/> e.g., `deadline project /by 16-09-2022 2359` |
| Event      | `event <task name> /at <date & time in 'dd-MM-yyyy HHmm' format>`<br/> e.g., `event seminar /at 20-09-2022 1600`       |
| List       | `list`                                                                                                                 |
| Mark       | `mark <task number>` <br/>e.g., `mark 2`                                                                               |
| Unmark     | `unmark <task number>` <br/>e.g., `unmark 3`                                                                           |
| Delete     | `delete <task number>` <br/>e.g., `delete 1`                                                                           |
| Show       | `show <date in 'dd-MM-yyyy' format>` <br/>e.g., `show 25-09-2022`                                                      |
| Find       | `find <keyword(s)>` <br/>e.g., `find meeting`                                                                          |
| Reschedule | `reschedule <task number> <date & time in 'dd-MM-yyyy HHmm' format>` <br/>e.g., `reschedule 3 25-09-2022 1900`         |
| Help       | `help`                                                                                                                 |
| Bye        | `bye`                                                                                                                  |

</div>

<p align="right">
<a href="#top">Back To Top</a>
</p>