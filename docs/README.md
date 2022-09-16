# User Guide
Yilia is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the 
benefits of a Graphical User Interface (GUI). Yilia provides flexibility by allowing the tracking of various types of 
tasks including todos, events and deadlines.
- [Features](#features)
- [Usage](#usage)
- [FAQ](#faq)

## Features 

### Task management
Tasks including todos, events and deadlines can be easily added or deleted by indices. 😍
### Task tracking
The status of each task can be marked or unmarked to indicate complete or incomplete. 😉
### Task viewing
You can view all the tasks or some of them according to the keywords they contain. 😌
### Auto saving
THe list can be auto saved into local drive when the program exits. 😃
## Usage

### ```todo``` - creates a new todo task.
Format of use: 
```todo TASK_DESCRIPTION```\
Example: <pre>todo swim</pre>
Expected outcome:
<pre>ヾ（′▽｀*）ゝGot it!
I've added this task
[T][ ] swim
Now you have 2 tasks in the list.</pre>
### ```event``` - creates a new event task.
Format of use:
```event TASK_DESCRIPTION / at YYYY-mm-dd```\
Example: <pre>event read a book / at 2019-12-01</pre>
Expected outcome:
<pre>ヾ（′▽｀*）ゝGot it!
I've added this task:
[E][ ] read a book (at: Dec 1 2019)
Now you have 3 tasks in the list.</pre>
### ```deadline``` - creates a new deadline task.
Format of use:
```deadline TASK_DESCRIPTION / by YYYY-mm-dd```\
Example: <pre>deadline return a book / by 2020-01-20</pre>
Expected outcome:
<pre>ヾ（′▽｀*）ゝGot it!
I've added this task:
[D][ ] return a book (by: Jan 20 2020)
Now you have 4 tasks in the list.</pre>
### ```delete``` - deletes a task from the list.
Format of use:
```delete INDEX```\
Example: <pre>deadline 3 4</pre>
Expected outcome:
<pre>Noted. |･ω･｀) I've removed this task:
[D][ ] return a book (by: Jan 20 2020)
Now you have 3 tasks in the list.

Noted. |･ω･｀) I've removed this task:
[E][ ] read a book (at: Dec 1 2019)
Now you have 2 tasks in the list.
</pre>
### ```mark``` - marks a task status as done.
Format of use:
```mark INDEX```\
Example: <pre>mark 1 2</pre>
Expected outcome:
<pre>Nice! (o≖◡≖)
I've marked this task as done:
[T][X] go to sleep
Nice! (o≖◡≖)
I've marked this task as done:
[T][X] swim</pre>

### ```unmark``` - unmarks a task status as not done.
Format of use:
```unmark INDEX```\
Example: <pre>unmark 1 2</pre>
Expected outcome:
<pre>Nice! (o≖◡≖)
I've marked this task as done:
[T][ ] go to sleep
Nice! (o≖◡≖)
I've marked this task as done:
[T][ ] swim</pre>
### ```list``` - lists all tasks.
Format of use:
```list```\
Example: <pre>list</pre>
Expected outcome:
<pre>ヾ(◍°∇°◍)ﾉﾞHere are your tasks:
[T][ ] go to sleep
[T][ ] swim
[E][X] read a book (at: Dec 1 2019)
[D][ ] return a book (by: Jan 20 2020)</pre>
### ```find``` - filters all tasks including keywords.
Format of use:
```find KEYWORD```\
Example: <pre>find book</pre>
Expected outcome:
<pre>ヾ(◍°∇°◍)ﾉﾞHere are your tasks:
[E][X] read a book (at: Dec 1 2019)
[D][ ] return a book (by: Jan 20 2020)</pre>
### ```bye``` -  saves and exits
Format of use:
```bye```\
Example: <pre>bye</pre>
Expected outcome:
<pre>Bye. Hope to see you again soon! (｡･ω･｡)ﾉ♡</pre>

## FAQ

Q: What if I didn't see expected outcome?\
A: There may be some errors in your inputs. You can easily figure them out by the error messages.\
   By the way, Yilia has provided an ~~elegant~~ way of handling errors and exceptions.
