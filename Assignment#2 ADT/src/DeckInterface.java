/**
 * An interface that describes the operations of a deck of cards
 */
public interface DeckInterface {

    /**
     * Show current state of the deck
     * @param displayDeck the deck to be displayed
     */
    void show(Deck.Card[] displayDeck);

    /**
     * Perform a random shuffle of the deck
     */
    void shuffleCards();

    /**
     * Reveal the top card of the deck
     * @param inputDeck The shuffled deck on which the top card will be revealed
     */
    void revealCard(Deck.Card[] inputDeck);

    /**
     * Perform the card force trick
     * @param inputDeck The shuffled deck on which the trick is to be performed
     */
    void cardForce(Deck.Card[] inputDeck);

    /**
     * Find the position of the desired card within the deck
     * @param inputDeck The final deck after the card force trick
     */
    void findCard(Deck.Card[] inputDeck);

    /**
     * Gets the initial deck of sorted cards
     * @return Card array of the initial deck
     */
    Deck.Card[] getDeck();

    /**
     * Gets the deck after it has been shuffled
     * @return Card array of the shuffled deck
     */
    Deck.Card[] getShuffledDeck();

    /**
     * Gets the final deck of cards after the card force trick has been completed
     * @return Card array of the final deck
     */
    Deck.Card[] getFinalDeck();
}