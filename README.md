MagneticBall2
=============

Description
-----------
Logic game written in kotlin.
Orbox like game.
Game purpose: Enter into a portal, setting the direction of a ball.
The ball flies while doesn't meet an obstacle.

Installation and running
------------------------
$ gradlew run

Game configuration
------------------
Game config is stored in:
"\MagneticBall2App\src\main\resources\appProperties.cfg"

You can comment/uncomment (#) lines to see different game behaviors.
E.g. "moveBehavior" property set to "Pacman" means that going right out of border player will appear from left,
while for moveBehavior set to "Simple" it's a losing.

Level files are stored in:
"\MagneticBall2App\src\main\resources\levels\"
'0' means there isn't any block,
'1' means player initial position,
other numbers means different block types.
