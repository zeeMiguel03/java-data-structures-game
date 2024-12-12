/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.Enums;

/**
 * @author Miguel Rocha
 */
public enum typeTarget {
    CHEMICAL, PERSON, GUN;
    
    @Override
    public String toString() {
        switch(this) {
            case CHEMICAL:
                return "Químico";
            case PERSON:
                return "Pessoa";
            case GUN:
                return "Arma";
            default:
                throw new AssertionError();
        }
    }
}
