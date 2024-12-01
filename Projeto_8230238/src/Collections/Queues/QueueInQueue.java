/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;

/**
 *
 * @author Miguel
 */
public class QueueInQueue {
    private QueueADT<Integer> main;
    
    public QueueInQueue() {
        this.main = new LinkedQueue<>();
    }
    
    public void insertInMain(QueueADT<Integer> queue1, QueueADT<Integer> queue2) throws EmptyCollectionException {
        try {
            
            while (!queue1.isEmpty() && !queue2.isEmpty() ) {          
                if (queue1.first() < queue2.first()) {
                    main.enqueue(queue1.dequeue()); 
                } else { 
                    main.enqueue(queue2.dequeue());  
                }   
            }
            
            while (!queue1.isEmpty()) {
                main.enqueue(queue1.dequeue());
            }         
            
            while (!queue2.isEmpty()) {
                main.enqueue(queue2.dequeue());
            } 
             
        } catch (EmptyCollectionException e) {
            System.err.println(e);
        }
        
        System.out.println(main.toString());
    }
}
