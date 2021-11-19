import java.util.*;
import java.util.stream.Collectors;

public class BlackJack {
    boolean gameCanContinue = true;

    List<Player> players = new ArrayList<>();
    Deck deck  = new Deck();

    public BlackJack() {
        deck.shuffle();
    }

    public void startGame() {
        dealCards(2);
        System.out.println("all players above 17");
        System.out.println(players.stream().filter(player -> player.getTotal() > 17).collect(Collectors.toList()));
        while (gameCanContinue) {
           Optional<Player> optionalWinner = findWinner();

           if ( optionalWinner.isEmpty()) {
               dealCards(1);
           }
           else {
               System.out.println("There is a winner");
               System.out.println(optionalWinner.get());
           }
        }
    }

    public void dealCards(int numberOfCards){
        if(!gameCanContinue) return;
            for (Player player : players) {
                for (int i = 1; i <= numberOfCards; i++) {
                    player.getCards().add(deck.drawCard());
                }
                System.out.println("Dealing cards: ");
                System.out.println(player);
            }
     }

       public Optional<Player>  findWinner(){

           Comparator<Player> playerComparator = Comparator.comparing(Player::getTotal);
           List<Player> orderedPlayers =  players.stream().sorted(playerComparator).collect(Collectors.toList());
           Collections.reverse(orderedPlayers);

           List<Player> hitters = hitters(orderedPlayers);
           List<Player> stickers = stickers(orderedPlayers);
           List<Player> busters = busters(orderedPlayers);
           List<Player> exactly21 = exactly21(orderedPlayers);
           Optional<Player> onlyOneNotBusted = onlyOneNotBusted(orderedPlayers);

           if(stickers.size() == orderedPlayers.size()){
               System.out.println("all are stickers");
               endGame();
               return Optional.ofNullable(stickers.get(0));
           }
           if(exactly21.size() > 0){
               System.out.println("A Player has hit 21 exactly");
               endGame();
               return  Optional.ofNullable(stickers.get(0));
           }

           if(onlyOneNotBusted.isPresent()){
               System.out.println("All are busted except one who is the winner");
               endGame();
               return onlyOneNotBusted;
           }

           if(busters.size() == orderedPlayers.size()) {
               System.out.println("All are busters, game has ended with no winner");
               endGame();
           }
           if(hitters.size() > 0  ){
               System.out.println("all hitters");
               players = hitters;
           }

           return Optional.empty();
       }

       public void endGame(){
          gameCanContinue = false;
           System.out.println("Game Ended");
       }

    public void generatePlayers(int number){
        for(int i = 1; i <= number; i++){
            players.add(new Player(Integer.toString(i)));
        }
    }

    public void removePlayer(int index){
        players.remove(index);
    }

    public List<Player> hitters(List<Player> orderedPlayers){
       return orderedPlayers.stream().filter(player -> player.getTotal() < 17).collect(Collectors.toList());
    }

    public List<Player> stickers (List<Player> orderedPlayers){
        return orderedPlayers.stream().
                filter(player -> player.getTotal() >= 17 && player.getTotal() < 21).
                collect(Collectors.toList());
    }

    public List<Player> busters (List<Player> orderedPlayers){
        return orderedPlayers.stream().filter(player -> player.getTotal() > 21).collect(Collectors.toList());
    }

    public List<Player> exactly21 (List<Player> orderedPlayers){
        return orderedPlayers.stream().filter(player -> player.getTotal() == 21).collect(Collectors.toList());
    }

    public Optional<Player> onlyOneNotBusted (List<Player> orderedPlayers){
        List<Player> onlyOneNotBusted = orderedPlayers.stream().filter(player -> player.getTotal() < 21).collect(Collectors.toList());

        if(onlyOneNotBusted.size() == 1){
            return Optional.ofNullable(onlyOneNotBusted.get(0));
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "BlackJack{" +
                "players=" + players +
                ", deck=" + deck +
                '}';
    }
}
