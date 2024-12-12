/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Trees;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Lists.ArrayUnorderedList;
import Collections.Lists.UnorderedListADT;
import Collections.Queues.LinkedQueue;
import Collections.Queues.QueueADT;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 */

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    private final int CAPACITY = 10;
    
    protected int count;
    protected T[] tree;
    
    /**
    * Creates an empty binary tree.
    */
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[CAPACITY];
    }

    /**
     * Creates a binary tree with only one element.
     * @param element the first element to add
     */
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
    }

    /**
     * Returns the root of the tree.
     *
     * @return the tree root
     * @throws EmptyCollectionException if the collection was empty
     */
    @Override
    public T getRoot() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty Collection!");
        }
        
        return tree[0];
    }

    /**
     * Verify if the Tree is empty.
     *
     * @return true if it was empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of elements on the tree.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Verify if a specific element exists in the tree.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the element was found, false otherwise
     */
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
     * @return the element
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

    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with
     * the root.
     *
     * @return an in order iterator over this binary tree
     */
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

    /**
     * Performs a preorder traversal on this binary tree by calling an
     * overloaded, recursive preorder method that starts
     * with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedListADT<T> templist = new ArrayUnorderedList<>();
        
        preOrder(0, templist);
        
        return templist.iterator();
    }

    /**
     * Performs a recursive preOrder traversal.
     *
     * @param node the node to be used as the root
     * for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    private void preOrder(int node, UnorderedListADT<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                tempList.addToRear(tree[node]);
                inorder(node * 2 + 1, tempList);
                inorder((node + 1) * 2, tempList);
            }
        }
    }

    /**
     * Performs a postorder traversal on this binary tree by
     * calling an overloaded, recursive postorder
     * method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedListADT<T> templist = new ArrayUnorderedList<>();
        
        postOrder(0, templist);
        
        return templist.iterator();
    }

    /**
     * Performs a recursive postOrder traversal.
     *
     * @param node the node to be used as the root
     * for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    private void postOrder(int node, UnorderedListADT<T> tempList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder(node * 2 + 1, tempList);
                inorder((node + 1) * 2, tempList);
                tempList.addToRear(tree[node]);
            }
        }
    }

    /**
     * Performs a level order traversal on the binary tree,
     * using a queue.
     *
     * @return an iterator over the elements of this binary tree
     */
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