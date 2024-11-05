package Demos;

import Lists.ArrayOrderedList;
import Lists.ArrayUnorderedList;
import Lists.DoubleLinkedOrderedList;
import Lists.DoubleLinkedUnorderedList;
import Lists.DoublyLinkedList;
import Lists.LinkedUnorderedList;
import Lists.OrderedListADT;
import Lists.UnorderedListADT;

public class Demo_List {

    public static void main(String[] args) {
        try {
            UnorderedListADT<Integer> list = new LinkedUnorderedList();
            
            list.addToRear(1);
            list.addToRear(2);
            list.addToRear(3);
            list.addToRear(4);
            list.addToRear(5);
            
            list.addAfter(6, 7);
               
            for (Integer e : list) {
                System.out.println(e);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
}
