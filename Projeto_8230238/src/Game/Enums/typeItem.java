/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.Enums;

/**
 * @author Miguel Rocha
 */
public enum typeItem {
    KIT_LIFE, VEST;
    
    @Override
    public String toString() {
        switch(this) {
            case KIT_LIFE:
                return "kit de vida";
            case VEST:
                return "colete";
            default:
                throw new AssertionError();
        }
    }
}
