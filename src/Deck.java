import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
      private List<Card> cards  = new ArrayList<>();

      public List<Card> getCardsInDeck() {
          for (Suit suit : Suit.values()) {
              for (Rank rank : Rank.values()) {
                  cards.add(new Card(suit,rank));
              }
          }
           return cards;
       }

       public void shuffle(List<Card> listOfCards){
           Collections.shuffle(listOfCards);
       }


}
