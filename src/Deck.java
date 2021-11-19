import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
      private List<Card> cards;

    public Deck() {
        cards = getCardsInDeck();
    }

    public List<Card> getCardsInDeck() {
           List<Card> cards  = new ArrayList<>();
          for (Suit suit : Suit.values()) {
              for (Rank rank : Rank.values()) {
                  cards.add(new Card(suit, rank));
              }
          }
           return cards;
       }

       public void shuffle(){
           Collections.shuffle(cards);
       }

       public Card drawCard(){
         return cards.remove(0);
       }


}
