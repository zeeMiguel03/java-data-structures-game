/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Queues;
/**
 *
 * @author Miguel
 */
public class Codification {
    private QueueADT<Integer> queue;
    
    public Codification() {
        this.queue = new CircularArrayQueue<>();
    }
    
    public void getKey(int[] key) {
        for (int cod : key) {
            queue.enqueue(cod);
        }
    }
    
    public String codifyMessage(String message) {
        String newMessage = "";
        
        for (int i = 0; i < message.length(); i++) {
            char letter = message.charAt(i);
            
            if (letter == ' ') {
                newMessage += " ";
            } else {
                int key = queue.dequeue();
                newMessage += (char)(letter + key);
                queue.enqueue(key);
            }
        }
        
        return newMessage;
    }
    
    public String descodifyMessage(String message) {
        String newMessage = "";
        
        for (int i = 0; i < message.length(); i++) {
            char letter = message.charAt(i);
            
            if (letter == ' ') {
                newMessage += " ";
            } else {
                int key = queue.dequeue();
                newMessage += (char)(letter - key);
                queue.enqueue(key);
            }
        }
        
        return newMessage;
    }
}
