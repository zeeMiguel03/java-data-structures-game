/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Game.Interfaces.Division;
import Game.Interfaces.Item;
import Game.Interfaces.Person;

/**
 * @author Miguel Rocha
 */
public class DivisionImpl implements Division {
    private String name;
    private UnorderedListADT<Person> persons;
    private UnorderedListADT<Item> itens;
    private boolean entranceExit;
    
    public DivisionImpl(String name, boolean entranceExit) {
        this.name = name;
        this.entranceExit = entranceExit;
        this.persons = new LinkedUnorderedList<>();
        this.itens = new LinkedUnorderedList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public UnorderedListADT<Person> getPersonsInDivision() {
        return persons;
    }

    @Override
    public UnorderedListADT<Item> getItemsInDivision() {
        return itens;
    }  
}
