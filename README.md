## CS360Portfolio
**Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?**
The goals and requirements of this inventory app wre as follows:
* Allow a user to log in to the application
* Allow a user to see a dashboard with a list of all items in the inventory and be able to add, delete, and update items in this inventory
* Allow a user to opt in for SMS notifications when an item is low in stock
* This app was designed as an inventory app, so the user was able to log in, see all of the items in their inventory as well as how many items were in stock of each item currently. The user could add new items, update existing items, and delete items. The user could optionally opt-in so that they could get text messages when an item was running low in stock.

**What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?**
* Some features that were needed were a log-in screen that connected to a database to check the user's login information versus what was stored in the database. This screen also had to support creating new users and allowing a new user to create a new log-in and password.
* Another feature is the dashboard UI that was a list of items, and each item in the list was clickable. This list was simplified and didn't show all information about an item, it showed the overview of the item's name as well as the number in stock, and once clicked on revealed all information about the item.
* Another feature in the app is the add/edit item screen, this was a form that the user could fill out to add a new item, or if they clicked on an item to edit it would populate all of the information of the item they wished to edit.
* This design was simple, and functional. It allowed the users to successfully navigate between screens and was simple to use, as well as very straightforward.

**How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?**
* I coded this app differently than I normally would, and I'm not sure I really enjoyed doing it the way I did for this project. We had to design the UI screens for our Project 2, and then for project 3 design the database and the backend logic for all of the buttons and interactivity and movement between UI screens.
* When coding this project, I broke things up into functionality as well as features. First, I worked on the database code. I worked on making CRUD functionaltiy to get, read, update, and delete data from the SQLite database for the login table and the inventory table. 
* Next, I worked on the login screen and making sure that the forms and buttons were all functional. I then tested each button to make sure that a user could login, create a new user name, and that these items were being saved and retrieved correctly from the database.
* After this, I worked on the dashboard screen and making sure that it could display a list of card items.
* Last, I worked on the add/edit item screens to make sure that a user could add items and edit existing items. I then tested all of the functionality for this to make sure that it was connecting to the database correctly and behaving as expected.

**How did you test to ensure your code was functional? Why is this process important and what did it reveal?**
* I tested incrementally as I developed, as well as tested once everything was coded in depth to make sure that everything worked together.
* Each time I wrote code for a button or functionality, I would test to make sure that it was functioning. A lot of the time I would put in some dummy if/else statements that would display a toast just to make sure that the data was being captured and the button press was firing the correct method before continuing. I also would figure out all possible scenarios for each button and made sure that each case was covered in my testing.
* Through testing, I was able to iron out any issues with the methods, make sure that the app was functioning end-to-end, and make sure that I was able to use each button, text box, and feature that I coded.

**Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?**
* I had a difficult time with getting the dashboard -> list item parent to child functionality to work. That was where I struggled the most. I found the zybook section as well as online tutorials of how to make fragments, however I was not successfully able to implement this, so I had to improvise by saving the id of the item that was selected and opening a new activity when a user clicked to edit an item. It may not have been the best way to to that, or the correct way, however the app was able to function and allow users to update an item.

**In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?**
* I work as a full-stack engineer, however my experience is highly tilted towards back end development. 
* I felt that I was able to really shine and show my skills and experience when developing code for the database connection and the back-end functionality for each of the UI components. This part for me came very naturally even though I had never worked with SQLite nor Android Studio before. 
