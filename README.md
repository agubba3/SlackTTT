# SlackTTT
Tic Tac Toe Plugin for Slack written with Java Spring running on AWS

Commands:
/ttt @username - This initiates a new game within the current channel. After this command, a new game is initiated and any of the players (either @username or the user who started the game) can make the first move.

/ttt @status - This shows the game board and status of the game in the current channel. If no game exists, it asks if you would like to start one.

/ttt move - Once the game is in motion, a player who is part of the game in a given channel can make a move.
The moves are as follows: (Only these can be used as moves) \r
-Upper-Left \r
-Upper-Center \r
-Upper-Right \r
-Middle-Left \r
-Middle-Center \r
-Middle-Right \r
-Lower-Left \r
-Lower-Center \r
-Lower-Right \r
