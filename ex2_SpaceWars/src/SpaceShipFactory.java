import oop.ex2.*;

public class SpaceShipFactory {
    /**
     * this method creates an array of new space ships in the types the user asked for, using a loop.
     *
     * @param args - the arguments that the user entered.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] shipArray = new SpaceShip[args.length];
        for (int i = 0; i < args.length; i++) {
            shipArray[i] = new SpaceShip(args[i]);
        }
        return shipArray;
    }
}

