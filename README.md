# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Final Project

<a href="https://play.google.com/apps/testing/com.showme.android.finalproject">##Meet Up, Speak Up (MuSu)</a>

###Overview:
Meet Up, Speak Up is a chat app offering instant translation to and from 13 languages. It features public chatrooms as well as the ability to create your own private chatrooms. The app uses Firebase's UI to send and receive data from a Firebase database. The translations come from the Microsoft Translate API, using their library as well as Volley's library to handle translation requests asynchronously.

###Summary
My app is your standard run of the mill chat app with the exception of the instant translation feature.  When you first open the app, you need to log in with your Google account.  This becomes your username for MuSu.  In the main activity, you can see chats you've been invited to, join a public chatroom, or create your own private chatroom.  In each room you can select a language "I want to speak" in as well as the language "I speak".  You are also able to invite people into the chat room that you are in as well as accept invitations into chat rooms.

##How to use:
- To translate the message you are sending, first make sure you have selected a language "I want to speak".  If you have not selected one, it will default to English.  Once you have that selected, type in your text and then long click the send button.  The app will translate your text and you can then send it.

- To translate the messages you are reading, first make sure you have a language "I speak".  If you have not selected one, it will default to English.  Once you have selected, simply click on the chat bubble and it will translate the text so that you can read it.

- To invite, simply click "Invite" in the menu bar on the top right.  You will be able to type in a username to invite or simply click on a username in the "Recent Users" list.

- To accept an invite, you simply need to click on it and it will bring you into that room.

###How it works:
The app relies on Firebase at the core which store and sends all the chats.  The app translates using Microsoft's Bing Translator API and library.  Their library makes it very simple to send a String and translate to language and returns the translated string.

###Stretch Goals:
While I'm happy with the app, there are a number of features I would like to add in eventually.

1) Notifications - Because there are no notifications, you cannot tell if someone has invited you into a chat room unless you're actively looking at the invite board.  Similarily, you will not know if someone has sent you a message unless you're actively in the chat room.  Unfortunately I could not figure out a proper way to do this.  While I had tested the feature, it would always notify you each time the recycler view updated as opposed to when you were getting new data.

2) Contacts list - It took me some time to figure out how to create and gather a users' list that I hadn't had the time to make a contact list.  However, I'm certain I could do it easily later on.

3) Direct messaging - Currently you need to invite someone into a private chat in order to message each other one on one.  This is also one of the reasons why I can't use notifications.  At some point I would like a user to be able to send a message directly to another user.

4) Tell if a user is online/logged off.  I'm not certain I'm able to do this with Firebase, but I would like to research how to do so eventually.

###After thoughts:
As a final project, while I'm not completely happy with the results as it could have been better, I am very satisfied of what I've been able to achieve.  From not being able to read a single line of code to creating a chat app that can connect multiple people through their phones is amazing.  I big thank you to my instructors Drew & James, my TA Kristen, and General Assembly and their staff.

###Trello Board:
<a href="https://trello.com/b/uRETpF6L/project4">Trello Board</a>

###Screenshots:
![](https://github.com/chris-shum/chris-shum.github.io/blob/master/img/portfolio/musu.jpg)
