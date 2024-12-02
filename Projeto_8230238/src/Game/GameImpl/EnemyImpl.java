package Game.GameImpl;

import Game.Interfaces.Division;
import Game.Interfaces.Enemy;
import Game.Interfaces.Person;
import Game.Interfaces.Player;

public class EnemyImpl extends PersonImpl implements Enemy {
    public EnemyImpl(String name, int power, Division division, int life) {
        super(name, division, power, life);
    }

    @Override
    public void atack() {
        for (Person person : getDivision().getPersonsInDivision()) {
            if (person instanceof Player) {
                person.setLife(person.getLife() - getPower());
            }
        }
    }
}
