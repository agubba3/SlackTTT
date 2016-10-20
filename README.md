# SlackTTT
Tic Tac Toe Plugin for Slack written with Java Spring running on AWS

Commands:

/ttt @username - This initiates a new game within the current channel. After this command, a new game is initiated and any of the players (either @username or the user who started the game) can make the first move. Please type for example:
"/ttt @agubba" where agubba is the usename registered within the channel. You can play against yourself. If your username is "John", just type in "/ttt @John". A weakness of the app is it does not recognize players who do not exist or usernames not present within the app. So please start a game with a valid username. After that, any of you can make a move with the command below, and play the game.

/ttt status - This shows the game board and status of the game in the current channel. If no game exists, it asks if you would like to start one.
Simply type "/ttt status" and if you are in the channel, only you via a slash message will receive a status of the current game,

/ttt move - Once the game is in motion, a player who is part of the game in a given channel can make a move.
Examples of this command using the below commands are:
"/ttt Upper-Left"
"/ttt Middle-Center"
"/ttt Lower-Right"

The moves are as follows: (Only these can be used as moves) 

Upper-Left, Upper-Center, Upper-Right, Middle-Left, Middle-Center, Middle-Right, Lower-Left, Lower-Center, Lower-Right 

To play, you MUST first create a game or else app will let you know. Then ANY player who is in the game (either the user who requested the game or the user the requested user requested to play against) can make the first move with the above "/ttt move" command. Then messages will be sent to the channel anytime someone plays and a user can also request a status. Once the game is over via a stalemate or a player wins, the app will let the channel and reopen the channel to add a new game. That would complete the life cycle of a game.
