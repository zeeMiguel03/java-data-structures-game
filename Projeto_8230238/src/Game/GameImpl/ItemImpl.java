/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Game.Enums.typeItem;
import Game.Interfaces.Division;
import Game.Interfaces.Item;

/**
 * @author Miguel Rocha
 */
public class ItemImpl implements Item {
    private typeItem type;
    private int points;
    private Division division;
    
    public ItemImpl(typeItem type, int points, Division division) {
        this.type = type;
        this.points = points;
        this.division = division;
    }

    @Override
    public Division getDivision() {
        return division;
    }

    @Override
    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public void setType(typeItem type) {
        this.type = type;
    }

    @Override
    public typeItem getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return "Type: " + type + " Points:" + points + " Division: " + division;
    }
}
