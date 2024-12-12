package Game.GameImpl;

import Game.Interfaces.Division;
import Game.Interfaces.Enemy;

public class EnemyImpl extends PersonImpl implements Enemy {
    private Division inicialDivision;
    
    /**
     * Constructor for the EnemyImpl class.
     * 
     * @param name the enemy name
     * @param power the enemy power
     * @param division the enemy division
     * @param life the enemy life
     */
    public EnemyImpl(String name, int power, Division division, int life) {
        super(name, division, power, life);
        inicialDivision = division;
    }

    /**
     * This method is used to atack the player.
     */
    @Override
    public void atack() {
        if (getDivision().getPlayer() != null) {
            getDivision().getPlayer().setLife(getDivision().getPlayer().getLife() - getPower());
        } 
    }

    /**
     * Returns the enemy initial division.
     * 
     * @return the initial division
     */
    @Override
    public Division getInicialDivision() {
        return inicialDivision;
    }
}
