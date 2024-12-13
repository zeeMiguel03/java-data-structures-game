/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Stacks;

/**
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class LinearNode<T>{
    private T element;
    private LinearNode<T> next;
    
    /**
     * Creates an empty node.
     */
    public LinearNode() {
        this.element = null;
        this.next = null;
    }
    
    /**
     * Creates a node storing the specified element
     * @param element element to be stored.
     */
    public LinearNode(T element) {
        this.element = element;
        this.next = null;
    }
    
    /**
     * Returns the element stored in this node.
     * @return T element stored at this node
     */
    public T getElement() {
        return element;
    }
    
    /** 
     * Returns the node that follows this one.
     * @return LinearNode reference to next node
     */
    public LinearNode<T> getNext() {
        return next;
    }
    
    /**
     * Sets the element stored in this node.
     * @param elem element to be stored at this node
     */
    public void setElement(T elem) {
        element = elem;
    }

    /**
     * Sets the node that follows this one.
     * @param node node to follow this one
     */
    public void setNext(LinearNode<T> node) {
        next = node;
    }
}