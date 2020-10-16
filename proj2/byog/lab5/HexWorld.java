package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);


    public static void nothingWorld(TETile[][] world) {
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y <HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private static void fillOneLine(TETile[][] world, int width, int xaxis, int yaxis, TETile tile) {
        for(int x = 0; x < width; x++) {
            world[xaxis + x][yaxis] = tile;
        }
    }

    private static int width(int nCall, int s) {
        return 2 * s + (s - 2) - 2 * nCall;
    }

    private static void recursiveFillLines(int nCall, int s, TETile[][] world,
                                           int xMiddleLeft, int yMiddleLeft, TETile tile) {
        if (nCall == s) {
            return;
        }

        int width = width(nCall, s);
        int xaxis = xMiddleLeft + nCall;
        int yaxisUp = yMiddleLeft + nCall;
        int yaxisDown = yMiddleLeft - nCall - 1;
        fillOneLine(world, width, xaxis, yaxisUp, tile);
        fillOneLine(world, width, xaxis, yaxisDown, tile);

        recursiveFillLines(nCall + 1, s, world, xMiddleLeft, yMiddleLeft, tile);
    }

    /**
     * Adds a hexagon of side length s to a specified position in the world.
     *
     * @param s                Side length of the hexagon.
     * @param world            World to which the hexagon will be added
     * @param xMiddleLeft X-coordinate of upper left of the hexagon's "middle"
     * @param yMiddleLeft Y-coordinate of upper left of the hexagon's "middle"
     * @param tile             TETile used to constitute the hexagon
     */
    public static void addHexagon(int s, TETile[][] world,
                                  int xMiddleLeft, int yMiddleLeft, TETile tile) {
        recursiveFillLines(0, s, world, xMiddleLeft, yMiddleLeft, tile);
    }


    public static void randomAddHexagon(int s, TETile[][] world,
                                        int xMiddleLeft, int yMiddleLeft) {
        int tileNum = RANDOM.nextInt(5);
        TETile tile;
        switch (tileNum) {
            case 0:
                tile = Tileset.WALL;
                break;
            case 1:
                tile = Tileset.FLOWER;
                break;
            case 2:
                tile = Tileset.TREE;
                break;
            case 3:
                tile = Tileset.MOUNTAIN;
                break;
            case 4:
                tile = Tileset.PLAYER;
                break;
            default:
                tile = Tileset.NOTHING;
                break;
        }
        addHexagon(s, world, xMiddleLeft, yMiddleLeft, tile);
    }


    private static void expand(int nExpand,
                               TETile[][] world,
                               int xMiddleUpperLeft,
                               int yMiddleUpperLeft) {
        randomAddHexagon(3, world, xMiddleUpperLeft, yMiddleUpperLeft + 6);
        randomAddHexagon(3, world, xMiddleUpperLeft + 5, yMiddleUpperLeft + 3);
        randomAddHexagon(3, world, xMiddleUpperLeft + 5, yMiddleUpperLeft - 3);
        randomAddHexagon(3, world, xMiddleUpperLeft, yMiddleUpperLeft - 6);
        randomAddHexagon(3, world, xMiddleUpperLeft - 5, yMiddleUpperLeft - 3);
        randomAddHexagon(3, world, xMiddleUpperLeft - 5, yMiddleUpperLeft + 3);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft, yMiddleUpperLeft + 6);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft + 5, yMiddleUpperLeft + 3);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft + 5, yMiddleUpperLeft - 3);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft, yMiddleUpperLeft - 6);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft - 5, yMiddleUpperLeft - 3);
        recursiveExpand(nExpand - 1, world, xMiddleUpperLeft - 5, yMiddleUpperLeft + 3);
    }

    private static void recursiveExpand(int nExpand,
                                        TETile[][] world,
                                        int xMiddleUpperLeft,
                                        int yMiddleUpperLeft) {
        if (nExpand == 0) {
            return;
        }
        randomAddHexagon(3, world, xMiddleUpperLeft, yMiddleUpperLeft);
        expand(nExpand, world, xMiddleUpperLeft, yMiddleUpperLeft);
    }

    private static void tessellate(TETile[][] world) {
        recursiveExpand(2, world, 22, 25);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        nothingWorld(world);
      //  addHexagon(4, world, 16, 20, Tileset.FLOOR);
      //  randomAddHexagon(2, world, 15, 15);
      //  randomAddHexagon(4, world, 20, 20);
       // randomAddHexagon(6, world, 30, 30);
        tessellate(world);
        ter.renderFrame(world);
    }

}
