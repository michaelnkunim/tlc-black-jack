import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    List<Player> players = new ArrayList<>();
    Deck deck  = new Deck();
    List<Card> listOfCards = new ArrayList<>();

    public BlackJack() {
        listOfCards =   deck.getCardsInDeck();
        deck.shuffle(listOfCards);
       // System.out.println("all Shuffled Cards");
       // System.out.println(listOfCards);
    }

    public void startGame(){
        ///get cards from top of deck
        //System.out.println(listOfCards);
        int currentPlayerIndex = 0;
        for(Player player: players){
            player.setCards(dealCards(currentPlayerIndex,2));
            currentPlayerIndex += 1;
            System.out.println(player);
        }
    }

    public void generatePlayers(int number){
        for(int i = 1; i <= number; i++){
            players.add(new Player(Integer.toString(i)));
        }
    }

      public List<Card> dealCards(int playerIndex,int limit){
        int skip = playerIndex * limit;
       return listOfCards.stream().skip(skip).limit(limit).toList();
      }

    @Override
    public String toString() {
        return "BlackJack{" +
                "players=" + players +
                ", deck=" + deck +
                ", listOfCards=" + listOfCards +
                '}';
    }
}
