# SlackTTT
Tic Tac Toe Plugin for Slack written with Java Spring running on AWS

Commands:
/ttt @username - This initiates a new game within the current channel. After this command, a new game is initiated and any of the players (either @username or the user who started the game) can make the first move. Please type for example:
"/ttt @agubba" where agubba is the usename registerd within the channel.

/ttt status - This shows the game board and status of the game in the current channel. If no game exists, it asks if you would like to start one.
Simply type "/ttt status" and if you are in the channel, only you via a slash message will receive a status of the current game,

/ttt move - Once the game is in motion, a player who is part of the game in a given channel can make a move.
Examples of this command using the below commands are:
"/ttt Upper-Left"
"/ttt Middle-Center"
"/ttt Lower-Right"

The moves are as follows: (Only these can be used as moves) 

-Upper-Left 
-Upper-Center 
-Upper-Right 
-Middle-Left 
-Middle-Center 
-Middle-Right 
-Lower-Left 
-Lower-Center 
-Lower-Right 
