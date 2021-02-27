/*
 * This class represent all kinds of vegetables. The uniqueness is that
 *  flowers can only be harvested. Also, vegetable plant on top.
 */
public class Vegetable extends Plant {
    private int grow_stack;

    public Vegetable(String seed) {
        firstStr = seed.substring(0, 1).toLowerCase();
        grow_stack = 0;
        grow_x = 0;
        grow_y = 2;
    }

    public void plant() {
        super.setGrid(grow_x, grow_y, firstStr);
    }

    public void harvest() {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (super.grid[x][y].equals(firstStr)) {
                    super.grid[x][y] = ".";
                }
            }
        }
    }

    private void firstGrow() {
        super.grid[1][2] = firstStr;
    }

    public void grow() {
        grow_stack++;
        if (grow_stack == 1) {
            firstGrow();
        } else {
            for (int x = 1; x < 4; x++) {
                for (int y = 1; y < 4; y++) {
                    if (super.grid[x][y].equals(firstStr)
                            && super.grid[x + 1][y].equals(".")) {
                        super.grid[x + 1][y] = "c";
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
