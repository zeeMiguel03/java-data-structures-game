package Game.GameImpl;

import Game.Interfaces.Division;
import Game.Interfaces.Enemy;

public class EnemyImpl extends PersonImpl implements Enemy {
    Division inicialDivision;
    public EnemyImpl(String name, int power, Division division, int life) {
        super(name, division, power, life);
        inicialDivision = division;
    }

    @Override
    public void atack() {
        getDivision().getPlayer().setLife(getDivision().getPlayer().getLife() - getPower());
    }

    @Override
    public Division getInicialDivision() {
        return inicialDivision;
    }
}
