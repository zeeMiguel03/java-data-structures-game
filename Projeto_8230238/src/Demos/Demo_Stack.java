/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Demos;

import Stacks.SmackStack;
import Stacks.SmackStackADT;

/**
 *
 * @author Miguel
 */
public class Demo_Stack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SmackStackADT<Integer> stack = new SmackStack<>();
        
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        
        System.out.println(stack.toString());
    }
    
}
