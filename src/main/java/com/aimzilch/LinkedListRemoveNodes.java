package com.aimzilch;

import java.util.HashMap;
import java.util.Map;

public class LinkedListRemoveNodes {
    public static void main(String[] args) {
        // Test Case 1
        LinkedList list1 = new LinkedList();
        list1.add(3);
        list1.add(4);
        list1.add(-7);
        list1.add(5);
        list1.add(-6);
        list1.add(6);
        list1.removeZeroSums();
        list1.printList();

        System.out.println();

        // Test Case 2
        LinkedList list2 = new LinkedList();
        list2.add(1);
        list2.add(2);
        list2.add(-3);
        list2.add(4);
        list2.add(5);
        list2.add(-5);
        list2.add(-4);
        list2.add(1);
        list2.removeZeroSums();
        list2.printList();

        System.out.println();
    }
}

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public LinkedList() {
        head = null;
    }

    public void add(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }

    public void removeZeroSums() {
        Node dummy = new Node(0);
        dummy.next = head;
        Node curr = dummy;
        Map<Integer, Node> map = new HashMap<>();
        int sum = 0;
        while (curr != null) {
            sum += curr.val;
            if (map.containsKey(sum)) {
                Node prev = map.get(sum);
                prev.next = curr.next;
                curr = dummy;
                map.clear();
                sum = 0;
            } else {
                map.put(sum, curr);
                curr = curr.next;
            }
        }
        head = dummy.next;
    }

    public void printList() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.print("null");
    }
}
