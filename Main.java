package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static ArrayList<Integer> cards = new ArrayList();
    private static ArrayList<Integer> issuedCards = new ArrayList();
    private static ArrayList<Integer> botCards = new ArrayList();
    private static Random random = new Random();

    public static void main(String[] args) {
        setCards();
        setIssuedCards();
        setBotCards();
        System.out.println("Yor Cards: "+issuedCards.toString());
        System.out.println("Bot Cards: "+botCards.toString());
        botCards();
        checkCards();
    }

    public static void setCards(){
        for (int i = 0; i < 4 ; i++) {
            for (int j = 2; j < 12; j++) {
                cards.add(j);
                if (j == 10){
                    for (int k = 0; k < 3; k++) {
                        cards.add(10);
                    }
                }
            }
        }
    }

    public static  void botCards(){
        if(sumBotCards()<16){
            botCards.add(cards.get(random.nextInt(cards.size())));
        }
    }

    public static void setIssuedCards(){
        for (int i = 0; i < 2; i++) {
            issuedCards.add(cards.get(random.nextInt(cards.size())));
        }
    }
    public static void setBotCards(){
        for (int i = 0; i < 2; i++) {
            botCards.add(cards.get(random.nextInt(cards.size())));
        }
    }

    public static void addIssuedCard(){
            issuedCards.add(cards.get(random.nextInt(cards.size())));
    }

    public static void checkCards(){
        for (int i = 0; i < issuedCards.size(); i++) {
            if((sumIssuedCards() == 21 && sumBotCards()<21) || (sumBotCards()>21 && sumIssuedCards()<21 && issuedCards.size()>2)){
                System.out.println("You Win!");
                System.exit(0);
            }else if(sumIssuedCards() > 21 && issuedCards.size()==2){
                System.out.println("Yor Change? \n :: 1 - Split :: 2 - Lose ::");
                Scanner scanner = new Scanner(System.in);
                int a = scanner.nextInt();
                switch (a) {
                    case 1:
                        splitIsCards();
                        System.out.println("Two pairs of cards: " + issuedCards.get(0) + " " + issuedCards.get(2) + " :: " + issuedCards.get(1) + " " + issuedCards.get(3));
                        break;
                    case 2:
                        System.out.println("You Cards: "+issuedCards.toString());
                        System.out.println("Bot Cards: "+botCards.toString());
                        System.out.println("You Lose!");
                        System.exit(0);
                        break;
                }
            }else if(sumIssuedCards()<21){
                System.out.println("Yor Change? \n :: 1 - Add :: 2 - Dont add ::");
                Scanner scanner = new Scanner(System.in);
                int a = scanner.nextInt();
                switch (a){
                    case 1:
                        addIssuedCard();
                        System.out.println("Your Cards:"+issuedCards.toString());
                        System.out.println("Bot Cards:"+botCards.toString());
                        botCards();
                        checkCards();
                        if(sumBotCards()==21 && sumIssuedCards()<21){
                            System.out.println("You Lose!");
                            System.exit(0);
                        }
                        break;
                    case 2:
                        botCards();
                        if(sumIssuedCards()>sumBotCards()){
                            System.out.println("You Cards: "+issuedCards.toString());
                            System.out.println("Bot Cards: "+botCards.toString());
                            System.out.println("You win!");
                            System.exit(0);
                        }else if(sumIssuedCards() == sumBotCards()){
                            System.out.println("You Cards: "+issuedCards.toString());
                            System.out.println("Bot Cards: "+botCards.toString());
                            System.out.println("Draw!");
                            System.exit(0);
                        }else{
                            System.out.println("You Cards: "+issuedCards.toString());
                            System.out.println("Bot Cards: "+botCards.toString());
                            System.out.println("You lose!");
                            System.exit(0);
                    }
                        break;
                }
            }else if((sumIssuedCards()>21 && issuedCards.size()>2) || (sumBotCards()<21 && sumIssuedCards()>21 && issuedCards.size()>2) || (sumBotCards()==21 && sumIssuedCards()!=21)){
                System.out.println("You Lose!");
                System.exit(0);
            }
        }
    }

    public static void splitIsCards(){
        addIssuedCard();
        addIssuedCard();
    }

    public static int sumIssuedCards(){
        int sum = 0;
        for (int i = 0; i < issuedCards.size(); i++) {
            sum += issuedCards.get(i);
        }
        return sum;
    }

    public static int sumBotCards(){
        int sum = 0;
        for (int i = 0; i < botCards.size(); i++) {
            sum += botCards.get(i);
        }
        return sum;
    }

}
