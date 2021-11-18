import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*Deck deck  = new Deck();
        List<Card> listOfCards = deck.getCardsInDeck();
        //System.out.println(listOfCards);
        deck.shuffle(listOfCards);
        System.out.println(listOfCards);*/

         BlackJack game = new BlackJack();
         game.generatePlayers(3);
         game.startGame();
        //System.out.println(game.dealCards());

        }
}
