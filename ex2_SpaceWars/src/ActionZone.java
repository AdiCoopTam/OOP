import oop.ex2.*;
/**
 * This class manages the types of the space ships and take care of the different types typical behavior.
 *
 * @author adi_hava
 */

public class ActionZone {
    /**
     * the ship that in action
     */
    SpaceShip ship;

    /**
     * the current game that runs
     */
    SpaceWars game;

    /**
     * the closest ship to the ship we manage
     */
    SpaceShip clShip;

    /**
     * the angle to the closest ship
     */
    double clAngle;

    /**
     * the distance to the closest ship
     */
    double clDistance;

    /**
     * the angle that to the current round. the default is 0
     */
    int myAngle;

    /**
     * the parameter that will set the acceleration of the ship. true if it will, false otherwise. the
     * default is false
     */
    boolean acc;

    /**
     * the constructer of the action zone
     *
     * @param game the current game we play with
     * @param ship the current ship we manage
     *
     *all the other parameters are an information about the ship.
     */
    public ActionZone(SpaceShip ship,
                      SpaceWars game) {
        this.ship = ship;
        this.game = game;
        this.clShip = game.getClosestShipTo(ship);
        this.clAngle = ship.myPhysics.angleTo(clShip.myPhysics);
        this.clDistance = ship.myPhysics.distanceFrom(clShip.myPhysics);
        this.myAngle = 0;
        this.acc = false;


    }

    /**
     * this function chooses the right action function by the space ship type given
     */

    protected void chooseFuncion() {
        switch (this.ship.shipType) {
            case "h":
                actLikeHuman();
                break;
            case "r":
                actLikeRunner();
                break;
            case "b":
                actLikeBasher();
                break;
            case "a":
                actLikeAggressive();
                break;
            case "d":
                actLikeDrunken();
            case "s":
                actLikeSpecial();
        }
    }

    /**
     * this function manages the behavior of the human player. it picks an action by the bottoms which are
     * pressed
     */
    private void actLikeHuman() {
        if (this.game.getGUI().isLeftPressed()) {
            this.myAngle++;
        }
        if (this.game.getGUI().isRightPressed()) {
            this.myAngle--;
        }
        if (this.game.getGUI().isUpPressed()) {
            this.ship.myPhysics.move(true, this.myAngle);
        }
        this.ship.myPhysics.move(false, this.myAngle);
        if (this.game.getGUI().isTeleportPressed()) {
            this.ship.teleport();
        }
        if (this.game.getGUI().isShotPressed()) {
            this.ship.fire(this.game);
        }
        if (this.game.getGUI().isShieldsPressed()) {
            this.ship.shieldOn();
        }
    }

    /**
     * the runner will run away from fighting. He will try to teleport if the closest ship is too close to
     * him.
     */
    private void actLikeRunner() {
        if (Math.abs(this.clAngle) < 0.23 && this.clDistance < 0.25) {
            this.ship.teleport();
        } else if (this.clAngle < 0) {
            this.myAngle = 1;
            this.ship.myPhysics.move(true, this.myAngle);
        } else if (clAngle > 0) {
            this.myAngle = -1;
            this.ship.myPhysics.move(true, this.myAngle);
        }
        this.ship.myPhysics.move(true, this.myAngle);
    }

    /**
     * the basher will try to collide with other ships.
     */
    private void actLikeBasher() {
        if (this.clDistance <= 0.19) {
            this.ship.shieldOn();
        }
        goAgainst();
    }

    /** this is an assist function to the basher and the agressive space ship. It makes the ship chase
     * after the closest ship.
     * */
    private void goAgainst(){
     if (this.clAngle < 0) {
         this.myAngle = -1;
         this.ship.myPhysics.move(true, this.myAngle);
    } else if (this.clAngle > 0) {
         this.myAngle = 1;
         this.ship.myPhysics.move(true, this.myAngle);
    }
        this.ship.myPhysics.move(true, this.myAngle);
    }

    /**this ship tries to harm the other ships. It tries to fire on them and get close to them.*/
    private void actLikeAggressive() {
        if (Math.abs(this.clAngle) < 0.21){
            this.ship.fire(this.game);
        }
        goAgainst();
    }

    /**
     * this ships moves randomly on the board, because the pilot fall asleep on the control panel
     */
    private void actLikeDrunken() {
        int[] angle = {-1, 0, 1};
        boolean[] acc = {true, false};
        int number = (int) Math.floor(Math.random() * 3);
        int bool = (int) Math.floor(Math.random() * 2);
        this.ship.myPhysics.move(acc[bool], angle[number]);
        if (!acc[bool]){
            this.ship.fire(game);
        }
        if (number == 0){
            this.ship.shieldOn();
        }
        if (number == 0 && !acc[bool]){
            this.ship.teleport();
        }

    }

    /**
     * this spaceship changes it's technique every 7 seconds.
     */
    private void actLikeSpecial() {
        int val = this.ship.specialCounter % 20;
        if (0 < val && val < 7) {
            actLikeAggressive();
        } else if (7 > val) {
            actLikeBasher();
        } else if (14 > val) {
            actLikeRunner();
        } else if (val == 0) {
            actLikeHuman();
        }
        this.ship.specialCounter++;
    }
}