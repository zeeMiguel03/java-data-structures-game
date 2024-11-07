package Demos;

import Lists.ArrayOrderedList;
import Lists.ArrayUnorderedList;
import Lists.DoubleLinkedOrderedList;
import Lists.DoubleLinkedUnorderedList;
import Lists.DoublyLinkedList;
import Lists.LinkedUnorderedList;
import Lists.OrderedListADT;
import Lists.UnorderedListADT;
import Trees.ArrayBinarySearchTree;
import Trees.BinarySearchTreeADT;
import Trees.LinkedBinarySearchTree;
import Trees.LinkedBinaryTree;
import java.util.Iterator;

public class Demo_List {

    public static void main(String[] args) {
        try {
            BinarySearchTreeADT<Integer> tree = new LinkedBinarySearchTree<>();
            
            tree.addElement(4);
            tree.addElement(2);
            tree.addElement(1);
            tree.addElement(3);
            tree.addElement(6);
            tree.addElement(5);
            tree.addElement(7);
    
            
            Iterator<Integer> iterator = tree.iteratorPostOrder();
            
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
}
