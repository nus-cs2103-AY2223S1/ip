# User Guide
Duke is a **desktop application for keeping track of tasks, deadlines,
events and supports keeping track of loans given/taken, optimized for
use via a Command Line Interface (CLI)**,
while still having the benefits of a Graphical User Interface (GUI). If you
can type fast, Duke can help you get your goals achieved faster than
traditional GUI applications.

## Features
- [Showing all tasks: list](#list)
- [Creating a ToDo: todo](#todo)
- [Creating an Event: event](#event)
- [Creating a Deadline: deadline](#deadline)
- [Marking a Task: mark](#mark)
- [Unmarking a Task: unmark](#unmark)
- [Find a Task: find](#find)
- [Deletes a Task: delete](#delete)
- [Showing loanbook: loanbook list](#lb-list)
- [Creating Contact: loanbook add](#lb-add)
- [Deleting Contact: loanbook delete](#lb-delete)


## Usage

### <span style="color: orange;" id="list">Showing all tasks - `list`</span>
Lists all tasks that the user has

**Example of usage:**

`list`

**Expected outcome:**

![list expected outcome](https://user-images.githubusercontent.com/88147891/190044114-144f8214-41ed-48fd-922e-1c84cdfc05c1.png)

All results shown. In this case, user has 3 tasks (1 todo, 1 event, 1 deadline)

### <span style="color: orange;" id="todo">Creating a ToDo - `todo`</span>
Creates a Todo task 

**Format:**

`todo taskname`
- `taskname` can be of any length, and works for multiple words

**Example of usage:**

`todo bake cake`

**Expected outcome:**

![todo expected outcome](https://user-images.githubusercontent.com/88147891/190046301-32e1032f-74b0-4bc5-a530-59d61bb00adc.PNG)

### <span style="color: orange;" id="event">Creating an Event - `event`</span>
Creates an Event

**Format:**

`event description /at date time`
- `description` can be of any length, and works for multiple words
- `date` must be in the form 'YYYY-MM-DD'
- `time` must be in 24 hours format (e.g. 2359)

**Example of usage:**

`event 24km marathon /at 2022-12-12 1800`

**Expected outcome:**

![event expected outcome](https://user-images.githubusercontent.com/88147891/190046891-330b0840-69d3-4399-a906-019142a67dbc.PNG)

### <span style="color: orange;" id="deadline">Creating a Deadline  - `deadline`</span>
Creates a Deadline

**Format:**

`deadline description /by date time`
- `description` can be of any length, and works for multiple words
- `date` must be in the form 'YYYY-MM-DD'
- `time` must be in 24 hours format (e.g. 2359)

**Example of usage:**

`deadline CS2103T IP /by 2022-09-17 2359`

**Expected outcome:**

![deadline expected outcome](https://user-images.githubusercontent.com/88147891/190047388-7ad38912-5c9e-4e5a-aded-28e31cab7175.PNG)

### <span style="color: orange;" id="mark">Marking a Task - `mark`</span>
Marks a Task as done

**Format:**

`mark index`
- `index` must be an integer, and is the index of the task in list

**Example of usage:**

`mark 6`

**Expected output:**

![mark expected outcome](https://user-images.githubusercontent.com/88147891/190048132-8f345da7-b204-4f7b-8dec-3be0b4c9f061.PNG)

### <span style="color: orange;" id="unmark">Unmarking a Task - `unmark`</span>

Marks a task as not done yet

**Format:**

`unmark index`
- `index` must be an integer, and is the index of the task in list

**Example of usage:**

`unmark 6`

**Expected output:**

![unmark expected outcome](https://user-images.githubusercontent.com/88147891/190048584-a7ba4f88-8f24-4259-ae95-830c9a839a77.PNG)

### <span style="color: orange" id="find">Find a Task - `find`</span>
Finds all tasks that match the search input pattern

**Format:**

`find searchInput`
- `searchInput` is the keyword that is matched to the list of tasks
- `searchInput` can be of any length, and supports multiple words

**Example of usage:**

`find recess week`

**Expected output:**

Only if recess week exists as a task in your list!

![find expected outcome](https://user-images.githubusercontent.com/88147891/190049228-b9c0968b-7475-4901-b2a3-118e53bd58c2.png)

### <span style="color: orange" id="delete">Deletes a Task - `delete`</span>
Deletes a task from the list

**Format:**

`delete index`
- `index` must be an integer, and is the index of the task in list

**Example of usage:**

`delete 5`

**Expected outcome:**

![delete expected output](https://user-images.githubusercontent.com/88147891/190051607-aa6f3fe0-e4ea-4135-b48b-695226061330.png)

### <span style="color: orange" id="lb-list">Showing loanbook - `loanbook list`</span>
Shows all contacts in loanbook

**Example of usage:**

`loanbook list`

**Expected outcome:**

![loanbook list expected outcome](https://user-images.githubusercontent.com/88147891/190052533-a3316555-0bbd-44dd-9641-d861258eedcd.png)

### <span style="color: orange" id="lb-add">Creating Contact - `loanbook add`</span>

Adds a loan to your loanbook

**Format:**

`loanbook add NAME NUMBER AMOUNT isOwe`
- `NAME` is the name of contact (Must be one word)
- `NUMBER` is the phone number of contact (Only Singapore Number)
- `AMOUNT` is the amount of money in the loan
- `isOwe` is true if you owe the person money, false otherwise

**Example of usage:**

`loanbook add John 98547373 9.83 true`

**Expected outcome:**

![loanbook add expected outcome](https://user-images.githubusercontent.com/88147891/190054524-33c6f2f1-c912-4b69-a4e6-153be7b139eb.png)

### <span style="color: orange" id="lb-delete">Deleting Contact - `loanbook delete`</span>

Deletes a contact from loanbook 

**Format:**

`loanbook delete NAME`
- `NAME` is the name to delete (must match exactly)
- `NAME` is case sensitive

**Example of usage:**

`loanbook delete John`

**Expected outcome:**

![image](https://user-images.githubusercontent.com/88147891/190055494-5c4a0e22-9f8b-47b9-9ad3-fbcb9249cc86.png)