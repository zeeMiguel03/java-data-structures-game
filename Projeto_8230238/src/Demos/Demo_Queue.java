/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Demos;

import Exceptions.EmptyCollectionException;
import Queues.CircularArrayQueue;
import Queues.Codification;
import Queues.LinkedQueue;
import Queues.QueueADT;
import Queues.QueueInQueue;
import Queues.QueueWithStack;

/**
 *
 * @author Miguel
 */
public class Demo_Queue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        QueueInQueue queue = new QueueInQueue();
        LinkedQueue<Integer> queue1 = new LinkedQueue<>();
        LinkedQueue<Integer> queue2 = new LinkedQueue<>();
        
        queue1.enqueue(1);
        queue1.enqueue(2);
        queue1.enqueue(5);
        queue1.enqueue(4);
        
        queue2.enqueue(3);
        queue2.enqueue(6);
        queue2.enqueue(7);
        queue2.enqueue(8);
        
        queue.insertInMain(queue1, queue2);
        */
        try {
            QueueWithStack<Integer> stack = new QueueWithStack<>();
        
            
            stack.enqueue(1);
            stack.enqueue(2);
            stack.enqueue(3);
            stack.enqueue(4);
        
            System.out.println(stack.first());
            System.out.println(stack.dequeue());
            System.out.println(stack.first());
            
            stack.enqueue(5);
            System.out.println(stack.dequeue());
        } catch (EmptyCollectionException e) {
            System.err.println(e);
        }
        
    }   
}
