package com.example.eightball;

import java.util.ArrayList;
import java.util.Random;

public class AnswerGenerator {

    String getAnswers(String[] answer) {
        if(answer == null) {
            return " ";
        }


        Random randNum = new Random();
        int magicNumber = randNum.nextInt(20);

        return answer[magicNumber];
    }
}
