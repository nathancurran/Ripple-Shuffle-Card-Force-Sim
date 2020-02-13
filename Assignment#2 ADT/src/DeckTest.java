public class DeckTest {
    public static void main(String[] args){
        // create a new object called DeckOfCards
        Deck DeckOfCards = new Deck();

        // output the initial deck of sorted cards
        System.out.println("This is a deck of cards:");
        DeckOfCards.show(DeckOfCards.getDeck());

        // perform the random shuffle operation
        DeckOfCards.shuffleCards();
        System.out.println("This is the deck of cards after it has been shuffled:");

        // retrieve and output the shuffled deck of cards
        DeckOfCards.show(DeckOfCards.getShuffledDeck());

        // output the top card of the shuffled deck
        System.out.println("This is the top card in the deck:");
        DeckOfCards.revealCard(DeckOfCards.getShuffledDeck());

        // perform the card force trick on the shuffled deck of cards
        DeckOfCards.cardForce(DeckOfCards.getShuffledDeck());

        // perform the find card operation on the final deck of cards
        DeckOfCards.findCard(DeckOfCards.getFinalDeck());

    }
}
