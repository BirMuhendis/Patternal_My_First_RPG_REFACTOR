package com.rpggame.core;

import java.util.Scanner;

public class ConsoleInterface implements GameInterface {
    private Scanner scanner;

    public ConsoleInterface() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public String readString() {
        return scanner.next();
    }
}