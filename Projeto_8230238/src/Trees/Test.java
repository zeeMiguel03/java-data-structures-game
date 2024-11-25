/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Trees;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        BinarySearchTreeADT<Integer> tree = new LinkedBinarySearchTree<>();

        try {
            tree.addElement(4);
            tree.addElement(2);
            tree.addElement(6);
            tree.addElement(1);
            tree.addElement(3);
            tree.addElement(5);
            tree.addElement(7);
            
            Iterator<Integer> inOrder = tree.iteratorLevelOrder();
            
            while (inOrder.hasNext()) {
                System.out.print(inOrder.next() + " ");
            }
            
     
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
