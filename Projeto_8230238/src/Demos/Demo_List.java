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
import Trees.LinkedOrderedBinarySearchTree;
import java.util.Iterator;

public class Demo_List {

    public static void main(String[] args) {
        try {
            BinarySearchTreeADT<Integer> tree = new ArrayBinarySearchTree<>();
            
            tree.addElement(4);
            tree.addElement(2);
            tree.addElement(1);
            tree.addElement(3);
            tree.addElement(6);
            tree.addElement(6);
            tree.addElement(7);
            
            tree.findMin();
    
            Iterator<Integer> iterator = tree.iteratorInOrder();
            
            
            
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
}
