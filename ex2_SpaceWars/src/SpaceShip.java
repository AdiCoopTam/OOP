import java.awt.Image;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 *
 * @author oop
 */
public class SpaceShip {
    /**
     * a boolean parameter that checkes if the sheild is on or not
     */
    public boolean sheildIsOn;

    /**
     * define what type of ship we have. That parameter defines the ship
     */
    public String shipType;

    /**
     * this number is the current health level of
     */
    public int healthLevel;

    /**
     * this parameter is the current energy level of the ship
     */
    public int energyLevel;

    /**
     * this number represents the maximal energy level the energy this ship can reach
     */
    public int maxEnergyLevel;

    /**
     * this number represents how many rounds passed since the last shot, if it was in the last 7 rounds.
     * Else, it equals 7.
     */
    public int shotTimer;

    /**
     * this is a SpaceShipPhysics object that contains information about this ship physics.
     */
    SpaceShipPhysics myPhysics;

    /**
     * this counter is relevant only for the special type. It counts how many rounds passed since the lst
     * time the special driver has change his behavior.
     */
    public int specialCounter;

    /**
     * few constants that wil be useful to have in hand
     * */
    private final int BEGIN_ENERGY_LEVEL = 190;
    private final int BEGIN_MAX_ENERGY = 210;
    private final int BEGIN_HEALTH_LEVEL = 22;

    /**
     * the constructor of SpaceShip
     *
     * @param shipType - the ship type - string that includes 1 character.
     *                   "h" for human;
     *                   "a" for aggressive;
     *                   "r" for runner;
     *                   "b" for basher;
     *                   "d" for drunk;
     *                   "s" for special.
     *the other features are set in the constructor and are the same for every new Spaceship object.
     * */
    public SpaceShip(String shipType) {
        this.myPhysics = new SpaceShipPhysics();
        this.shipType = shipType;
        this.energyLevel = BEGIN_ENERGY_LEVEL;
        this.maxEnergyLevel = BEGIN_MAX_ENERGY;
        this.sheildIsOn = false;
        this.shotTimer = 0;
        this.healthLevel = BEGIN_HEALTH_LEVEL;
        this.specialCounter = 1;

    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.sheildIsOn = false;
        if (this.energyLevel < this.maxEnergyLevel) {
            this.energyLevel++;
        }
        if (this.shotTimer > 0) {
            this.shotTimer--;
        }
        ActionZone zone = new ActionZone(this, game);
        zone.chooseFuncion();
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        if (!this.sheildIsOn){
            this.healthLevel--;
            this.maxEnergyLevel -= 18;
            updateEnergy();}
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        if (isDead()) {
            this.myPhysics = new SpaceShipPhysics();
            this.energyLevel = BEGIN_ENERGY_LEVEL;
            this.maxEnergyLevel = BEGIN_MAX_ENERGY;
            this.sheildIsOn = false;
            this.shotTimer = 0;
            this.healthLevel = BEGIN_HEALTH_LEVEL;
            this.specialCounter = 1;
        }
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (this.healthLevel <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.myPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!this.sheildIsOn) {
            this.healthLevel--;
            this.maxEnergyLevel -= 10;
            updateEnergy();
        }
        }

        /**
         * This function takes care of cases that the energy level is higher then the max energy level
         * */
    private void updateEnergy(){
        if (this.energyLevel > this.maxEnergyLevel){
            this.energyLevel = this.maxEnergyLevel;
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        if (this.shipType.equals("h")) {
            if (this.sheildIsOn) {
                return GameGUI.SPACESHIP_IMAGE_SHIELD;
            }
            else {
                return GameGUI.SPACESHIP_IMAGE;}
            }
        else {
            if (this.sheildIsOn) {
                return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
            }
            else {return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }}


    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (this.shotTimer == 0 && this.energyLevel >= 19) {
            game.addShot(this.myPhysics);
            this.energyLevel -= 19;
            this.shotTimer += 7;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (this.energyLevel >= 3){
            this.sheildIsOn = true;
            this.energyLevel -= 3;
    }}

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (this.energyLevel >= 140) {
            this.myPhysics = new SpaceShipPhysics();
            this.energyLevel -= 140;
        }
    }
}
