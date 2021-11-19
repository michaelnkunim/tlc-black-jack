import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;

    private List<Card> cards = new ArrayList<>();

    public int getTotal(){
      return  cards.stream().mapToInt(card-> card.getRank().getNumber()).sum();
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cards=" + cards +
                ", total=" + getTotal() +
                '}';
    }
}
