package Demos;

import Lists.ArrayOrderedList;
import Lists.ArrayUnorderedList;
import Lists.DoubleLinkedUnorderedList;
import Lists.OrderedListADT;
import Lists.UnorderedListADT;

public class Demo_List {

    public static void main(String[] args) {
        try {
            UnorderedListADT<String> list = new ArrayUnorderedList<String>();
            
            list.addToRear("a");
            list.addToRear("b");
            list.addToRear("c");
            list.addToRear("d");
            
            list.remove("e");

            System.out.println(list.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }
}
