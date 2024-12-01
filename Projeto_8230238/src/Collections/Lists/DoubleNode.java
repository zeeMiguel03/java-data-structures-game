/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Lists;

/**
 *
 * @author Miguel Rocha
 */
public class DoubleNode<T> {
    private T element;
    private DoubleNode<T> next;
    private DoubleNode<T> previous;
    
    /**
     * Creates a empty double node.
     */
    public DoubleNode() {
        this.element = null;
        this.next = null;
        this.previous = null;
    }
    
    /**
     * Creates a new double node with specific element. 
     * @param element 
     */
    public DoubleNode(T element) {
        this.element = element;
        this.next = null;
        this.previous = null;
    }

    /**
     * Return the node element.
     * @return node element
     */
    public T getElement() {
        return element;
    }
    
    /**
     * Sets the node element.
     * @param element node element
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Return the next node.
     * @return the next node
     */
    public DoubleNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node.
     * @param next the node to set
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
    
    /**
     * Return the previous node.
     * @return the previous node
     */
    public DoubleNode<T> getPrevious() {
        return previous;
    }
    
    /**
     * Sets the previous node.
     * @param previous the node to set
     */
    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }
}
