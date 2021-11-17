package com.swp.dataweb.utils;

import lombok.ToString;

@ToString
public class MyArrayList {
    @ToString
    private class ListNode {
        private ListNode next;
        private Object data;
        public ListNode(Object data){
            this.data = data;
        }
        public Object getData(){
            return this.data;
        }
        public String toString(){
            return this.data.toString();
        }
    }
    private ListNode[] arr = new ListNode[10];
    private ListNode root = new ListNode(null);
    private static int count = 0;

    public void add(Object o){
        if(count == 0 ){
            root.data = o;
            arr[count] = root;
            count++;
        }else {
            arr[count-1].next = new ListNode(o);
            arr[count] = arr[count-1].next;
            count++;
        }
    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
    }

}
