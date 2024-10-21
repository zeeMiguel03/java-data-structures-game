package Demos;

import Lists.ArrayOrderedList;
import Lists.ArrayUnorderedList;
import Lists.DoubleLinkedUnorderedList;
import Lists.UnorderedListADT;

public class Demo_List {

    public static void main(String[] args) {
        try {
            UnorderedListADT<String> list = new ArrayUnorderedList<String>(2);
            
            list.addToFront("a");
            list.addToFront("b");
            list.addToFront("c");
            list.addToFront("d");
            
            System.out.println(list.contains("e"));
            
            System.out.println(list.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }
}
