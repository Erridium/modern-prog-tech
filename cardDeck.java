import java.util.*;
import java.math.*;
public class Deck extends Card {

private static Card[][]cardDeck=new Card[4][13];
int counter; 



public Deck (String suit, String cardType, int value) {
    super(suit,cardType,value);

}

public void removeCard() {
    if(counter>=40)
        generateDeck();

    int Randr=(int)(Math.random()*26);
    int Randc=(int)(Math.random()*2);

    if(cardDeck[Randr][Randc]==null) {
        removeCard();
    }
    else {        {
        cardDeck[Randr][Randc]=null;
        counter++;
        }
}
public void print2DArray() {
    for(int i=0;i<cardDeck.length;i++) {
        for(int j=0;j<cardDeck[0].length;j++) {                   
            System.out.print(super.draw(cardDeck[i][j])+" "+j);
        }
        System.out.println();
    }
}



public static void generateDeck() {

    for(int i=0; i<cardDeck.length;i++) {

        for(int j=0;j<cardDeck[0].length;j++) {

            if(i==0) {
                if(j==0) {
                    cardDeck[0][0]=new Card("Clubs","Ace",1);
                    continue;
                }
                else if(j>0&&j<10) {
                        cardDeck[i][j]=new Card("Clubs",""+(j+1),j+1);
                        continue;
                }
                else if(j==10) {
                        cardDeck[i][j]=new Card("Clubs","Jack",10);
                        continue;
                }
                else if(j==11) {
                        cardDeck[i][j]=new Card("Clubs","Queen",10);
                        continue;
                }
                else if(j==12) {
                        cardDeck[i][j]=new Card("Clubs","King",10);
                        continue;
                }
            else if(i==1) {
                if(j==0) {
                    cardDeck[1][0]=new Card("Hearts","Ace",1);
                    continue;
                }
                else if(j>0&&j<10) {
                        cardDeck[i][j]=new Card("Hearts",""+(j+1),j+1);
                        continue;
                }
                else if(j==10) {
                        cardDeck[i][j]=new Card("Hearts","Jack",10);
                        continue;
                }
                else if(j==11) {
                        cardDeck[i][j]=new Card("Hearts","Queen",10);
                        continue;
                }
                else if(j==12) {
                        cardDeck[i][j]=new Card("Hearts","King",10);
                        continue;
                }
            }
            else if(i==2) {
                if(j==0) {
                    cardDeck[2][0]=new Card("Spades","Ace",1);
                    continue;
                }
                else if(j>0&&j<10) {
                    cardDeck[i][j]=new Card("Spades",""+(j+1),j+1);
                    continue;
                }
                else if(j==10) {
                    cardDeck[i][j]=new Card("Spades","Jack",10);
                    continue;
                }
                else if(j==11) {   
                    cardDeck[i][j]=new Card("Spades","Queen",10);
                continue;
                }
                else if(j==12) {
                    cardDeck[i][j]=new Card("Spades","King",10);
                continue;
                }
            }
            else if(i==3) {
                if(j==0) {
                    cardDeck[3][0]=new Card("Diamonds","Ace",1);
                    continue;
                }
                else if(j>0&&j<10) {
                    cardDeck[i][j]=new Card("Diamonds",""+(j+1),j+1);
                    continue;
                }
                else if(j==10) {   {   
                    cardDeck[i][j]=new Card("Diamonds","Jack",10);
                    continue;
                }
                else if(j==11) {
                    cardDeck[i][j]=new Card("Diamonds","Queen",10);
                    continue;
                }

                else if(j==12) {
                    cardDeck[i][j]=new Card("Diamonds","King",10);
                    continue;
                }

               }    
            }
        }
    }
}
