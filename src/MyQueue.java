/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thanh Hai
 */
public class MyQueue {
    Node head,tail;

    public MyQueue() {
        head = tail = null;
    }
    
    public boolean isEmpty(){
        return head == null;
    }
    
    public void EnQueue(int value){
        Node node = new Node(value);
        if(isEmpty()) head = tail = node;
        else{
            tail.next = node;
            tail = node;
        }
    }
    
    public int DeQueue(){
        if(isEmpty()) return -1;
        int value = head.info;
        head = head.next;
        return value;
    }
}
class Node{
    int info;
    Node next;

    public Node(int value) {
        info = value;
        next = null;
    }

    public Node() {
    }  
}
