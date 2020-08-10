/*
 * Keeps track of all plant objects by a 2d arrays. The users can get
 *  or reset a specific plant's object.
 * Note: Each plant objects contains a 5x5 grid which users 
 *  can perform actions on their plant's object there.
 *  
 * E.g: Garden[2][3] (2d-array of plants):
 *      Plant Plant Plant
 *      Plant Plant Plant
 */
public class Garden {
    private Plant[][] myGarden;

    // Constructor
    public Garden(int rows, int cols) {
        myGarden = new Plant[rows][cols];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                myGarden[x][y] = new Plant();
            }
        }
    }

    public Plant getPlant(int rows, int cols) {
        return myGarden[rows][cols];
    }

    public void resetPlant(int rows, int cols) {
        myGarden[rows][cols] = new Plant();
    }

    public String combineStrHelper(String a, String b) {
        String combineStr = "";
        for (int i = 0; i < 5; i++) {
            if (!a.substring(i, i + 1).equals(".")) {
                combineStr += a.substring(i, i + 1);
            } else if (!b.substring(i, i + 1).equals(".")) {
                combineStr += b.substring(i, i + 1);
            } else {
                combineStr += ".";
            }
        }
        return combineStr;
    }

    public String toString() {
        String returnStr = new String();
        for (Plant[] plantRow : myGarden) {
            for (int i = 0; i < 5; i++) {
                for (Plant plant : plantRow) {
                    String combineStr = ".....";
                    if (plant.getFlower() != null) {
                        combineStr = combineStrHelper(
                                combineStr,
                                plant.getFlower().getRowElement(i));
                    }
                    if (plant.getTree() != null) {
                        combineStr = combineStrHelper(combineStr,
                                plant.getTree().getRowElement(i));
                    }
                    if (plant.getVegetable() != null) {
                        combineStr = combineStrHelper(combineStr,
                                plant.getVegetable().getRowElement(i));
                    }
                    returnStr += combineStr;
                }
                returnStr += "\n";
            }
            // returnStr += "\n";
        }
        return returnStr;
    }

}
