/*
 * This class represent all kinds of flowers. The uniqueness is that
 *  flowers can only be picked. Also, flower plant in the middle of the grid.
 */
public class Flower extends Plant {
    private int grow_stack;
    public Flower(String seed) {
        firstStr = seed.substring(0, 1).toLowerCase();
        grow_stack = 0;
        grow_x = 2;
        grow_y = 2;
    }

    public void plant() {
        super.setGrid(grow_x, grow_y, firstStr);
    }

    public void pick() {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (super.grid[x][y].equals(firstStr)) {
                    super.grid[x][y] = ".";
                }
            }
        }
    }

    private void fullGrow() {
        super.grid[0][0] = "c";
        super.grid[0][4] = "c";
        super.grid[4][0] = "c";
        super.grid[4][4] = "c";
    }

    public void grow() {
        grow_stack++;
        if (grow_stack >= 4 && super.grid[2][2].equals(firstStr)) {
            fullGrow();
        } else {
            for (int x = 1; x < 4; x++) {
                for (int y = 1; y < 4; y++) {
                    if (super.grid[x][y].equals(firstStr)) {
                        super.grid[x - 1][y] = "c";
                        super.grid[x + 1][y] = "c";
                        super.grid[x][y - 1] = "c";
                        super.grid[x][y + 1] = "c";
                    }
                }
            }
        }
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (super.grid[x][y].equals("c")) {
                    super.grid[x][y] = firstStr;
                }
            }
        }
    }
}
