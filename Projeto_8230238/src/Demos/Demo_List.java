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
            BinarySearchTreeADT<Integer> tree = new ArrayBinarySearchTree<>();
            
            tree.addElement(1);
            tree.addElement(3);
            tree.addElement(7);
            tree.addElement(9);
            tree.addElement(17);
            
            tree.removeMin();
            tree.removeMin();
            tree.removeMax();
            tree.removeMax();
            
            tree.addElement(17);
            
            Iterator<Integer> levelOrderIterator = tree.iteratorPostOrder();
            
            while (levelOrderIterator.hasNext()) {
                System.out.print(levelOrderIterator.next() + " ");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
}
