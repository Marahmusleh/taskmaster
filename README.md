## Lab29

1- Add Task Form
Modify your Add Task form to save the data entered in as a Task in your local database.

2- Homepage
Refactor your homepage’s RecyclerView to display all Task entities in your database.

3- Detail Page
Ensure that the description and status of a tapped taskOrg are also displayed on the detail page, in addition to the title.

 ![image description](screenshots/home2.png)

![image description](screenshots/form.png)

### Example of Task detail Page of a tapped taskOrg :

 ![image description](screenshots/taskdetail29.png)

## Lab 31

Add Espresso to your application, and use it to test basic functionality of the main components of your application. For example:

* assert that important UI elements are displayed on the page
* tap on a taskOrg, and assert that the resulting activity displays the name of that taskOrg
* edit the user’s username, and assert that it says the correct thing on the homepage

 ![image description](screenshots/Tests.png)


## Lab 32
* Using the amplify add api command, create a Task resource that replicates our existing Task schema. Update all references to the Task data to instead use AWS Amplify to access your data in DynamoDB instead of in Room.

* Add Task Form
Modify your Add Task form to save the data entered in as a Task to DynamoDB.

* Homepage
Refactor your homepage’s RecyclerView to display all Task entities in DynamoDB.

 ![image description](screenshots/home5.png)

 #####  Data is posted and displayed based on DynamoDB status:

  ![image description](screenshots/dynamoDB.png)

### Lab 33

* Create a second entity for a team, which has a name and a list of tasks. Update your tasks to be owned by a team.

* Manually create three teams by running a mutation exactly three times in your code. (You do NOT need to allow the user to create new teams.)

* Add Task Form
* Modify your Add Task form to include either a Spinner or Radio Buttons for which team that task belongs to.

* Settings Page
* In addition to a username, allow the user to choose their team on the Settings page. Use that Team to display only that team’s tasks on the homepage.

  ![image description](screenshots/Screenshot_4.png)

  ![image description](screenshots/Screenshot_5.png)

  ![image description](screenshots/Screenshot_6.png)

  ![image description](screenshots/Screenshot_7.png)

  ![image description](screenshots/Screenshot_8.png)

### Lab 36
* User Login
* Add Cognito to your Amplify setup. 
  Add in user login and sign up flows to your application, 
  using Cognito’s pre-built UI as appropriate.
  Display the logged in user’s username somewhere relevant in your app.

* User Logout
* Allow users to log out of your application.

![image description](screenshots/signUp.png)

![image description](screenshots/signin2.png)

![image description](screenshots/verification.png)

![image description](screenshots/main.png)

### Lab 37 

* Uploads
On the “Add a Task” activity,
allow users to optionally select a file to attach to that task. 
If a user attaches a file to a task, that file should be uploaded to S3, and associated with that task.

* Displaying Files
On the Task detail activity, if there is a file that is an image associated with a particular Task, that image should be displayed within that activity. 


![image description](screenshots/addTask37.png)

![image description](screenshots/uploadImg.png)

![image description](screenshots/settings37.png)

![image description](screenshots/detailPage.png)

![image description](screenshots/Screenshot_11.png)

### Lab 38
When a new task is created within a team, alert all users who are a part of that team about that new task.

![image description](screenshots/notification.png)

### Lab 39

Add Analytics to your amplify project. Create and send an Event whenever you launch intents that start new activities.

![image description](screenshots/events.png)

### Lab 41

Add an intent filter to your application such that a user can hit the “share” button on an image in another application, choose TaskMaster as the app to share that image with, and be taken directly to the Add a Task activity with that image pre-selected.


![image description](screenshots/imageShared.png)

![image description](screenshots/sharedImage2.png)

## Lab 42

* When the user adds a task, their location should be retrieved and included as part of the saved Task.

* On the Task Detail activity, the location of a Task should be displayed if it exists.


![image description](screenshots/location.png)

![image description](screenshots/location2.png)

