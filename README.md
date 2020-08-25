# Garden_Simulator_OOP
A Java garden simulator program interface which based heavily on Object-Oriented-Programming.

This program stimulates a garden. The gardeners can perform many actions
on their garden such as: plant, grow, pick(flower), cut(tree),
harvest(vegetable), and print the garden out as 2d array. Besides,
the users can choose details on their action like which plot to perform
action, how many time that performance should be repeated, which seed or
seedtype users want to remove from the garden.

This is a classic object-oriented-programming problem, therefore, the "Plant"
class has-a "Flower", "Tree", or "Vegetable" class. It also has the is-a 
relationship with "Garden" class to make sure the inheritance feature in
OOP can be fully utilized.

# Inheritance Diagram for garden simulator
![inheritance-diagram](https://user-images.githubusercontent.com/51266998/91128433-1365b100-e65d-11ea-90b2-e6d5efb4c357.JPG)

# Commands
- **PLANT**  
Example: PLANT (0,0) rose
If the PLANT command is read, it should be followed by plot coordinates and the type
of Plant to be planted. Use this type to plant the correct subclass of plant into the garden
at given plot coordinates. The plot coordinates are given as row and column. Both rows
and columns start at 0. Rows go down the screen, and columns go across the screen.
Each plot will itself contain a grid of 5x5 cells (represented as characters). There is a
restriction that the number of cells across should be less than or equal to 80, therefore
the most plot columns allowed is 80/5 or 16.
3
- **PRINT**  
Example: PRINT
If the PRINT command is read, then the entire garden should be printed to standard
out.
- **GROW**  
Example: GROW 1
If the GROW command is read, then each Plant should grow the specified number of
times as seen in the input command. A plant cannot grow out of its plot. No error will
happen, but growth should not occur outside the plot boundaries. Plots also cannot run
into each other.
- **GROW [num] (row,col)**  
Example: GROW 1 (2,3)
Grow whichever Plant is located in the garden at position (row,col) num times. If there
is nothing at this position or the position is outside the size of the garden, print, ”Can’t
grow there.” and continue.
- **GROW [num] [type]**  
Example: GROW 1 rose
Grow only Plants of the specified type num times.
- **GROW [num] [plant]**  
Example: GROW 1 flower
Grow only Plants of the specified class num times.
- **HARVEST**  
Example: HARVEST
Remove all Vegetables from the Garden.
- **HARVEST (row,col)**  
Example: HARVEST (2,3)
Harvest Vegetable at location (row,col). If not a Vegetable or outside of Garden, print,
”Can’t harvest there.” and continue.
- **HARVEST [type]**  
Example: HARVEST tomato
Harvests all Vegetables of the specified type. If there are no Vegetables with that type,
do nothing.
- **PICK**  
Example: PICK
Remove all Flowers from the Garden.
- **PICK (row,col)**  
Example: PICK (2,3)
Pick Flower at location (row,col). If not a Flower or outside of Garden, print, ”Can’t pick
there.” and continue.
4
- **PICK [type]**  
Example: PICK rose
Pick all Flowers of the specified type. If there are no Flowers with that type, do nothing.
- **CUT**  
Example: CUT
Remove all Trees from the Garden.
- **CUT (row,col)**  
Example: CUT (2,3)
Cut Tree at location (row,col). If not a Tree or outside of Garden, print, ”Can’t cut there.”
and continue.
- **CUT [type]**  
Example: CUT PINE
Cut all Trees of the specified type. If there are no Trees of that type, do nothing.
