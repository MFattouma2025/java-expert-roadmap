package com.expertjava.threads.virtual;

public class VirtualThreadBasicDemo {
    public static void main(String[] args) {
        Thread.startVirtualThread(() -> System.out.println("Virtual thread lancé"));
    }
}
