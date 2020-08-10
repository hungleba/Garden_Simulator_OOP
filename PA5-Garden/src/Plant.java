/*
 * This class represents all kind of plants in it. A plant's object
 *  might include flower, tree, and vegetable classes. This create
 *  high security and allow users to perform actions on different
 *  kinds of plants.
 * *Note: a plant's object contains 5x5 grid as the following
 *      .....
 *      .....
 *      .....
 *      .....
 *      .....
 */
public class Plant {
    private Flower flower;
    private Tree tree;
    private Vegetable veg;
    protected String firstStr;
    protected String[][] grid;
    protected int grow_x;
    protected int grow_y;
    protected String seed;

    // Enumerations are designed to be accessible
    public enum Flowers {
        Iris, Lily, Rose, Daisy, Tulip, Sunflower
    }

    public enum Trees {
        Oak, Willow, Banana, Coconut, Pine
    }

    public enum Vegetables {
        Garlic, Zucchini, Tomato, Yam, Lettuce
    }

    // Constructor
    public Plant() {
        grid = new String[5][5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                setGrid(x, y, ".");
            }
        }
    }
    
    public Flower getFlower() {
        return flower;
    }

    public Tree getTree() {
        return tree;
    }

    public Vegetable getVegetable() {
        return veg;
    }

    /*
     * Get the seedtype of a seed and return it as a string.
     */
    public String getSeedType(String seed) {
        seed = seed.toLowerCase();
        // Flowers
        for (Flowers flower : Flowers.values()) {
            if (seed.equalsIgnoreCase(flower.name())) {
                return "Flowers";
            }
        }
        // Trees
        for (Trees tree : Trees.values()) {
            if (seed.equalsIgnoreCase(tree.name())) {
                return "Trees";
            }
        }
        // Vegetables
        for (Vegetables veg : Vegetables.values()) {
            if (seed.equalsIgnoreCase(veg.name())) {
                return "Vegetables";
            }
        }
        return null;
    }

    public String[][] getGrid() {
        return grid;
    }

    public void plant(String seed) {
        String seedType = getSeedType(seed);
        switch (seedType) {
        case "Flowers":
            flower = new Flower(seed);
            flower.plant();
            break;
        case "Trees":
            tree = new Tree(seed);
            tree.plant();
            break;
        case "Vegetables":
            veg = new Vegetable(seed);
            veg.plant();
            break;
        }
    }

    public void grow() {
        if (flower != null) {
            flower.grow();
        }
        if (tree != null) {
            tree.grow();
        }
        if (veg != null) {
            veg.grow();
        }
    }

    public void grow(String seed) {
        String seedType = getSeedType(seed);
        switch (seedType) {
        case "Flowers":
            flower.grow();
            break;
        case "Tree":
            tree.grow();
            break;
        case "Vegetables":
            veg.grow();
            break;
        }
    }

    public void cut() {
        tree.cut();
    }

    public void pick() {
        flower.pick();
    }

    public void harvest() {
        veg.harvest();
    }

    public void setGrid(int x, int y, String firstLetter) {
        this.grid[x][y] = firstLetter;
    }

    public String toString() {
        String returnStr = new String();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                returnStr += grid[x][y];
            }
            returnStr += "\n";
        }
        return returnStr;
    }

    /*
     * return every elements within a specific row of a plant's object.
     */
    public String getRowElement(int row) {
        String returnStr = new String();
        for (String element : grid[row]) {
            returnStr += element;
        }
        return returnStr;
    }
}
