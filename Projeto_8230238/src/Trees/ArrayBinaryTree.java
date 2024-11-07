/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Lists.ArrayUnorderedList;
import Lists.UnorderedListADT;
import Queues.LinkedQueue;
import Queues.QueueADT;
import java.util.Iterator;

/**
 *
 * @author Miguel
 * @param <T>
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    private final int CAPACITY = 2;
    
    protected int count;
     protected T[] tree;
    
    /**
    * Creates an empty binary tree.
    */
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[CAPACITY];
    }

    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
    }
    
    @Override
    public T getRoot() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty Collection!");
        }
        
        return tree[0];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean contains(T targetElement) {
        try {
            find(targetElement);
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree. Throws a NoSuchElementException if
     * the specified target element is not found in the binary tree.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the element is in the tree
     * @throws ElementNotFoundException if an element not found
     * exception occurs
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException{
        T temp = null;
        boolean found = false;
        
        for (int i = 0; i < count && !found; i++) {
            if (targetElement.equals(tree[i])) {
                found = true;
                temp = tree[i];
            }
        }
        
        if (!found) {
            throw new ElementNotFoundException("Element not found!");
        }
        
        return temp;
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedListADT<T> templist = new ArrayUnorderedList<>();
        
        inorder(0, templist);
        
        return templist.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    private void inorder (int node, UnorderedListADT<T> templist){
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder(node * 2 + 1, templist);
                templist.addToRear(tree[node]);
                inorder((node + 1) * 2, templist);
            }
        }
    }
    
    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedListADT<T> templist = new ArrayUnorderedList<>();
        
        preOrder(0, templist);
        
        return templist.iterator();
    }
    
    private void preOrder(int node, UnorderedListADT<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                tempList.addToRear(tree[node]);
                inorder(node * 2 + 1, tempList);
                inorder((node + 1) * 2, tempList);
            }
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedListADT<T> templist = new ArrayUnorderedList<>();
        
        postOrder(0, templist);
        
        return templist.iterator();
    }

    private void postOrder(int node, UnorderedListADT<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder(node * 2 + 1, tempList);
                inorder((node + 1) * 2, tempList);
                tempList.addToRear(tree[node]);
            }
        }
    }
    
    @Override
    public Iterator<T> iteratorLevelOrder() {
        QueueADT<Integer> tempQueue = new LinkedQueue<>();
        UnorderedListADT<T> templist = new ArrayUnorderedList<>();
        
        int next;
        
        if (tree[0] != null) {
            tempQueue.enqueue(0);
            
            while (!tempQueue.isEmpty()) {
                next = tempQueue.dequeue();
                
                templist.addToRear(tree[next]);
                
                if (tree[next * 2 + 1] != null) {
                    tempQueue.enqueue(next * 2 + 1);
                }
                
                if (tree[(next + 1) * 2] != null) {
                    tempQueue.enqueue((next + 1) * 2);
                }   
           }  
        }
        
        return templist.iterator();
    }
}
