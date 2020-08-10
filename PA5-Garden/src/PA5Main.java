
/*
 * Author : Hung Le Ba - CSC210
 * fileName: PA5Main.java
 * Purpose: This program stimulates a garden. Users can perform many actions
 *  on their garden such as: plant, grow, pick(flower), cut(tree),
 *  harvest(vegetable), and print the garden out as 2d array. Besides,
 *  the users can choose details on their action like which plot to perform
 *  action, how many time that performance should be repeated, which seed or
 *  seedtype users want to remove from the garden.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class PA5Main {
    /*
     * The main method first takes a file name which contains rows, columns,
     * and commands line include one of the following:plant, print, grow,
     * harvest(for vegetables), pick(for flowers), cut(for trees)
     * and call the other methods to complete the task.
     * 
     * @param: fileName
     */
    public static void main(String args[]) {
        // Reading input file into variable "commands"
        Scanner commands = null;
        try {
            commands = new Scanner(new File(args[0]));
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Create infinite loops to read command lines (except rows&columns)
        int rows = Integer.parseInt(commands.nextLine().split(": ")[1]);
        int cols = Integer.parseInt(commands.nextLine().split(": ")[1]);
        if (rows > 16 || cols > 16) {
            System.out.println("Too many plot columns.");
        } else {
            processFile(commands, rows, cols);
        }
        commands.close();
    }

    /*
     * This is a sub method of main one. It reads the user's commands and
     * then call the appropirate methods to perform it.
     * 
     * @params: commands (Scanner), number of rows, number of columns
     */
    public static void processFile(Scanner commands, int rows, int cols) {
        commands.nextLine();
        Garden myGarden = new Garden(rows, cols);
        while (commands.hasNextLine()) {
            String line = commands.nextLine();
            switch (line.substring(0, 3).toLowerCase()) {
            case "pri":
                System.out.println("> PRINT");
                System.out.println(myGarden);
                break;
            case "pla":
                plantMain(line, myGarden);
                break;
            case "gro":
                growMain(line, myGarden, rows, cols);
                break;
            case "har":
                harvestMain(line, myGarden, rows, cols);
                break;
            case "pic":
                pickMain(line, myGarden, rows, cols);
                break;
            case "cut":
                cutMain(line, myGarden, rows, cols);
                break;
            }
        }
    }

    /*
     * This method cuts all trees in the garden.
     */
    private static void cutMain_all(Garden myGarden, int rows, int cols) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Plant myPlant = myGarden.getPlant(x, y);
                if (myPlant.getTree() != null) {
                    myPlant.cut();
                }
            }
        }
    }

    /*
     * This method cuts all trees at user's input coordinate. If the coordinate
     * is invalid, it prints out "Can't cut there."
     */
    private static void cutMain_coor(String coordinate, Garden myGarden,
            String line) {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "
                + line.split(" ")[1]);
        Plant myPlant = myGarden.getPlant(row, col);
        if (myPlant.getTree() == null) {
            System.out.println("Can't cut there.");
        } else {
            myPlant.cut();
        }
    }

    /*
     * This methods cut all trees based on user's input seed. For example, if
     * user's input is "coconut" then all the coconut will be removed
     * from the garden.
     */
    private static void cutMain_seed(String seed, int rows, int cols,
            Garden myGarden, String line) {
        String firstLet = seed.substring(0, 1);
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "
                + line.split(" ")[1]);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Plant myPlant = myGarden.getPlant(x, y);
                Tree tempTree = myPlant.getTree();
                if (tempTree != null && tempTree.firstStr.equals(firstLet)) {
                    tempTree.cut();
                }
            }
        }
    }

    /*
     * This method analyzes the command line's meaning then call the sub-method
     * to perform actions include: cut all trees, cut specific type of tree,
     * and cut trees at a coordinate.
     */
    public static void cutMain(String line, Garden myGarden, int rows,
            int cols) {
        // cut all
        if (line.split(" ").length == 1) {
            System.out.println("> " + line.toUpperCase());
            cutMain_all(myGarden, rows, cols);
        } else {
            String coordinate = line.split(" ")[1];
            if (coordinate.substring(0, 1).equals("(")) {
                // cut at specific coordinate
                cutMain_coor(coordinate, myGarden, line);
            } else {
                // cut specific seed only
                String seed = coordinate;
                cutMain_seed(seed, rows, cols, myGarden, line);
            }
        }

    }
    
    /*
     * All the pickMains' methods include pickMain_all, pickMain_coor,
     * pickMain_seed, and pickMain are similar to curMain methods.
     * The difference is pickMain methods are for flowers.
     */

    private static void pickMain_all(Garden myGarden, int rows, int cols) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Plant myPlant = myGarden.getPlant(x, y);
                if (myPlant.getFlower() != null) {
                    myPlant.pick();
                }
            }
        }
    }

    private static void pickMain_coor(String coordinate, Garden myGarden,
            String line) {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "
                + line.split(" ")[1]);
        Plant myPlant = myGarden.getPlant(row, col);
        if (myPlant.getFlower() == null) {
            System.out.println("Can't pick there.");
        } else {
            myPlant.pick();
        }
    }

    private static void pickMain_seed(String seed, int rows, int cols,
            Garden myGarden, String line) {
        String firstLet = seed.substring(0, 1);
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "
                + line.split(" ")[1]);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Plant myPlant = myGarden.getPlant(x, y);
                Flower tempFlower = myPlant.getFlower();
                if (tempFlower != null
                        && tempFlower.firstStr.equals(firstLet)) {
                    tempFlower.pick();
                }
            }
        }
    }

    public static void pickMain(String line, Garden myGarden, int rows,
            int cols) {
        // pick all
        if (line.split(" ").length == 1) {
            System.out.println("> " + line.toUpperCase());
            pickMain_all(myGarden, rows, cols);
        } else {
            String coordinate = line.split(" ")[1];
            // pick by coordinate
            if (coordinate.substring(0, 1).equals("(")) {
                pickMain_coor(coordinate, myGarden, line);
            } else {
                // pick by seed
                String seed = coordinate;
                pickMain_seed(seed, rows, cols, myGarden, line);
            }
        }

    }

    /*
     * 
     * All the harvestMains' methods include pickMain_all, pickMain_coor,
     * pickMain_seed, and pickMain are similar to curMain methods.
     * The difference is harvestMain methods are for vegetables.
     */
    private static void harvestMain_all(Garden myGarden, int rows, int cols) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Plant myPlant = myGarden.getPlant(x, y);
                if (myPlant.getVegetable() != null) {
                    myPlant.harvest();
                }
            }
        }
    }

    private static void harvestMain_coor(String coordinate, Garden myGarden,
            String line) {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "
                + line.split(" ")[1]);
        Plant myPlant = myGarden.getPlant(row, col);
        if (myPlant.getVegetable() == null) {
            System.out.println("Can't harvest there.");
        } else {
            myPlant.harvest();
        }
    }

    private static void harvestMain_seed(String seed, int rows, int cols,
            Garden myGarden, String line) {
        String firstLet = seed.substring(0, 1);
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "
                + line.split(" ")[1]);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Plant myPlant = myGarden.getPlant(x, y);
                Vegetable tempVeg = myPlant.getVegetable();
                if (tempVeg != null && tempVeg.firstStr.equals(firstLet)) {
                    tempVeg.harvest();
                }
            }
        }
    }

    public static void harvestMain(String line, Garden myGarden, int rows,
            int cols) {
        // harvest all
        if (line.split(" ").length == 1) {
            System.out.println("> " + line.toUpperCase());
            harvestMain_all(myGarden, rows, cols);
        } else {
            String coordinate = line.split(" ")[1];
            if (coordinate.substring(0, 1).equals("(")) {
                // harvest by coordinate
                harvestMain_coor(coordinate, myGarden, line);
            } else {
                // harvest by seed
                String seed = coordinate;
                harvestMain_seed(seed, rows, cols, myGarden, line);
            }
        }
    }

    public static void growMain_time_coor(String coordinate, Garden myGarden,
            int time, int rows, int cols, String line) {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        System.out.println("> " + line.toUpperCase());
        if (row < rows && row >= 0 && col < cols && col >= 0) {
            Plant myPlant = myGarden.getPlant(row, col);
            for (int i = 0; i < time; i++) {
                myPlant.grow();
            }
        } else {
            System.out.println("Can't grow there.");
        }

    }

    public static void growMain_time_seed(String coordinate, int rows, int cols,
            Garden myGarden, int time, String line) {
        String seed = coordinate;
        String firstLet = seed.substring(0, 1).toLowerCase();
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "
                + line.split(" ")[1] + " " + line.split(" ")[2]);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Plant myPlant = myGarden.getPlant(x, y);
                String seedType = myPlant.getSeedType(seed);
                switch (seedType) {
                case "Flowers":
                    Flower tempFlower = myPlant.getFlower();
                    if (tempFlower != null
                            && tempFlower.firstStr.equals(firstLet)) {
                        for (int i = 0; i < time; i++) {
                            tempFlower.grow();
                        }
                    }
                case "Trees":
                    Tree tempTree = myPlant.getTree();
                    if (tempTree != null
                            && tempTree.firstStr.equals(firstLet)) {
                        for (int i = 0; i < time; i++) {
                            tempTree.grow();
                        }
                    }
                case "Vegetables":
                    Vegetable tempVeg = myPlant.getVegetable();
                    if (tempVeg != null && tempVeg.firstStr.equals(firstLet)) {
                        for (int i = 0; i < time; i++) {
                            tempVeg.grow();
                        }
                    }
                }
            }
        }
    }

    public static void growMain_time_seedType(String coordinate, int rows,
            int cols, Garden myGarden, int time, String line) {
        String seedType = coordinate;
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "
                + line.split(" ")[1] + " " + line.split(" ")[2]);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Plant myPlant = myGarden.getPlant(x, y);
                if (seedType.equals("flower")) {
                    Flower tempFlower = myPlant.getFlower();
                    if (tempFlower != null) {
                        for (int i = 0; i < time; i++) {
                            tempFlower.grow();
                        }
                    }
                } else if (seedType.equals("tree")) {
                    Tree tempTree = myPlant.getTree();
                    if (tempTree != null) {
                        for (int i = 0; i < time; i++) {
                            tempTree.grow();
                        }
                    }
                } else if (seedType.equals("vegetable")) {
                    Vegetable tempVeg = myPlant.getVegetable();
                    if (tempVeg != null) {
                        for (int i = 0; i < time; i++) {
                            tempVeg.grow();
                        }
                    }
                }
            }
        }
    }

    public static void growMain_all(Garden myGarden, int rows, int cols,
            int time) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Plant myPlant = myGarden.getPlant(x, y);
                for (int i = 0; i < time; i++) {
                    myPlant.grow();
                }
            }
        }
    }

    public static void growMain(String line, Garden myGarden, 
            int rows, int cols) {
        int time = Integer.parseInt(line.split(" ")[1]);
        if (line.split(" ").length == 2) {
            // Grow all
            System.out.println("> " + line.toUpperCase());
            growMain_all(myGarden, rows, cols, time);
        } else {
            String coordinate = line.split(" ")[2];
            // Grow [time] [coordinate]
            if (coordinate.substring(0, 1).equals("(")) {
                growMain_time_coor(coordinate, myGarden, time, rows, cols,
                        line);

            } else if (coordinate.equals("flower") || coordinate.equals("tree")
                    || coordinate.contentEquals("vegetable")) {
                // Grow [time] [seedType]
                growMain_time_seedType(coordinate, rows, cols,
                        myGarden, time, line);
            } else {
                // Grow [time] [seed]
                growMain_time_seed(coordinate, rows, cols, myGarden, time,
                        line);
            }
        }

    }

    /*
     * This method plants a new plant based on their seed at specific
     * coordinate that user typed in.
     */
    public static void plantMain(String line, Garden myGarden) {
        String coordinate = line.split(" ")[1];
        String seed = line.split(" ")[2];
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        Plant myPlant = myGarden.getPlant(row, col);
        myPlant.plant(seed);
    }
    
    /*
     * Helper-method to get the number of rows
     */
    public static int getRow(String coor) {
        coor = coor.split(",")[0];
        if (coor.length() == 2) {
            return Integer.parseInt(coor.substring(1, 2));
        } else {
            return Integer.parseInt(coor.substring(1, 3));
        }
    }

    /*
     * 
     * Helper-method to get the number of rows
     */
    public static int getCol(String coor) {
        coor = coor.split(",")[1];
        if (coor.length() == 2) {
            return Integer.parseInt(coor.substring(0, 1));
        } else {
            return Integer.parseInt(coor.substring(0, 2));
        }
    }
}
