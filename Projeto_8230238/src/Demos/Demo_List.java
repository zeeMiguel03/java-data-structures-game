package Demos;

import Lists.CircularLinkedList;
import Lists.DoubleLinkedUnorderedList;
import Lists.DoublyLinkedList;
import Lists.LinkedList;

public class Demo_List {

    public static void main(String[] args) {
        try {
            DoubleLinkedUnorderedList<String> list = new DoubleLinkedUnorderedList<>();
            
            list.addToFront("a");
            list.addToFront("b");
            list.addToFront("c");
            list.addToFront("d");
                    
            list.tailToHead();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
}
