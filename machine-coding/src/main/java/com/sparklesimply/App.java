package com.sparklesimply;

import com.sparklesimply.snakeladdergame.SnakeLadderGameDemo;

public class App {
    public static void main(String[] args) {
        String moduleToBeExecuted = System.getenv("MODULE");

        if(moduleToBeExecuted.equals("snakeladdergame")) {
            SnakeLadderGameDemo.execute();
        } else {
            System.out.println("Hello! provide module to be executed");
        }
    }
}
