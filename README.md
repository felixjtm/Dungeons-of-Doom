############################
##    Dungeons of Doom    ##
############################

You are an adventurer that has managed to get themselves drapped in a dungeon!
Find the gold you need and escape before you are caught, and meet your doom...

Installation instructions:
1. Compile all the .java files
2. Run the GameLogic class file
3. Enjoy the game!

How to play:
Before you begin the game you will be prompted to pick a map from the current directory,
if you would like to use the default map then enter nothing, otherwise enter a file name.

Once you have begun the game you have a set of commands at your disposal.
Each command takes up a turn.

HELLO - Displays the amount of gold you need to leave the dungeon

GOLD - Displays how much gold you currently have

MOVE <direction> - This can be n, s, e, w, corresponding the the compass directions

PICKUP - Picks up gold if you are on a tile with gold

LOOK - displays a 5x5 grid of the tiles around you

QUIT - Quits the game, used to exit the dungeon when you think you are finish


Careful! As you are searching for treasure something appears to be following you...

A bot is patrolling the dungeon, if it catches sight of you then it navigates towards you, so watch out!


Maps:

If you would like to add your own custom map then this is the format you should use:

name <map name>
win <gold required to win>
####################
#..The actual map..#
####################

The map must be rectangular.

Save the file as a .txt and place it in the same directory as your .class files and you're good to go!






