/*
 * This class represent all kinds of tree. The uniqueness is that
 *  flowers can only be cut. Also, tree plant under the bottom.
 */
public class Tree extends Plant {
    private int grow_stack;

    public Tree(String seed) {
        firstStr = seed.substring(0, 1).toLowerCase();
        grow_stack = 0;
        grow_x = 4;
        grow_y = 2;
    }

    public void plant() {
        super.setGrid(grow_x, grow_y, firstStr);
    }

    public void cut() {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (super.grid[x][y].equals(firstStr)) {
                    super.grid[x][y] = ".";
                }
            }
        }
    }

    private void firstGrow() {
        super.grid[3][2] = firstStr;
    }

    public void grow() {
        grow_stack++;
        if (grow_stack == 1) {
            firstGrow();
        } else {
            for (int x = 3; x > 0; x--) {
                for (int y = 3; y > 0; y--) {
                    if (super.grid[x][y].equals(firstStr)
                            && super.grid[x - 1][y].equals(".")) {
                        super.grid[x - 1][y] = "&";
                    }
                }
            }
        }

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (super.grid[x][y].equals("&")) {
                    super.grid[x][y] = firstStr;
                }
            }
        }
    }
}
