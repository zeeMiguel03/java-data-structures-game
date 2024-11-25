/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Trees;

import java.util.Iterator;

/**
 *
 * @author Miguel
 */
public class TestHeap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HeapADT<Integer> heap = new LinkedHeap<>();
        
        heap.addElement(5);
        heap.addElement(7);
        heap.addElement(8);
        heap.addElement(10);
        heap.addElement(1);
        heap.addElement(3);
        
        Iterator<Integer> inOrder = heap.iteratorLevelOrder();
            
        while (inOrder.hasNext()) {
            System.out.print(inOrder.next() + " ");
        }    
    }
}
