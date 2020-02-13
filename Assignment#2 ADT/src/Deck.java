// import java libraries
import java.util.Random;
import java.util.Scanner;

public class Deck implements DeckInterface {
    /*
    declare class variables
    create constant total num of cards
     */
    private final static int TOTCARDS = 52;

    /*
    declare instance variables
    declare card arrays for the deck at different stages
     */
    private Card[] Deck;
    private Card[] shuffledDeck;
    private Card[] deckHalfOne = new Card[TOTCARDS/2];
    private Card[] deckHalfTwo = new Card[TOTCARDS/2];
    private Card[] finalDeck = new Card[TOTCARDS];

    /**
     * This is a default constructor for the Deck of cards,
     * Creates a new deck of 52 cards with 4 suits of 13 ranks
     */
    Deck() {
        Deck = new Card[TOTCARDS];                          // create a new deck object consisting of a 52 Card array
        int i=0;                                            // initialise counter var
        for (Suit suitVal : Suit.values()) {                // iterate through the suits
            for (Rank rankVal : Rank.values()) {            // iterate through the ranks for each suit
                Card card = new Card(suitVal, rankVal);     // create a new card object
                Deck[i]=card;                               // allocate card to empty element in the Deck array
                i++;                                        // increment counter
            }
        }
    }

    /**
     * This is a getter method for the initial deck of cards before any shuffles
     * @return The card array of initial deck
     */
    public Card[] getDeck(){
        return Deck;
    }

    /**
     * This method creates the group of constants known as the Suits
     */
    public enum Suit {
        HEARTS,CLUBS,DIAMONDS,SPADES
    }

    /**
     * This method creates the group of constants known as the Ranks
     */
    public enum Rank {
        ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING
    }

    /**
     * This inner class creates an ADT called Card
     */
    static class Card{
        // declare the current suit and rank variables
        private final Suit currSuit;
        private final Rank currRank;

        /**
         * The Card constructor takes a parameter for each of the instance variables (Suit, Rank)
         * @param inputSuit The suit of the card
         * @param inputRank The rank of the card
         */
        Card(Suit inputSuit, Rank inputRank){
            this.currSuit = inputSuit;
            this.currRank = inputRank;
        }

        /**
         * This is a getter method to return the suit of a card
         * @return The suit of the card
         */
        Suit getSuit() {
            return currSuit;
        }

        /**
         * This is a getter method to return the rank of a card
         * @return The rank of the card
         */
        Rank getRank() {
            return currRank;
        }

        /**
         * This is a getter method to return a card including the suit and rank
         * @return The card as a string in the format RANK_SUIT
         */
        String getCard(){
            String card;
            card = getRank().toString() + "_" + getSuit().toString();   // concatenate the suit and rank of the card
            return card;                                                // output the card as a string variable
        }
    }

    /**
     * This method displays a deck of cards
     * @param displayDeck The deck to be output
     */
    public void show(Card[] displayDeck){
        for(int i=0;i<displayDeck.length;i++){                      // iterate through the deck
            // if-else condition outputs deck in easy-to-read format
            if(i==displayDeck.length-1){
                System.out.println(displayDeck[i].getCard());
            }
            else{
                System.out.print(displayDeck[i].getCard()+", ");
            }

        }
    }

    /**
     * This method randomly shuffles the deck of cards
     */
    public void shuffleCards(){
        boolean[] contains = new boolean[TOTCARDS];                 // initialisation of found flag
        shuffledDeck = new Card[TOTCARDS];                          // initialisation of the shuffledDeck array
        Random generator = new Random();                            // initialisation of Random data type

        int randomCard = generator.nextInt(shuffledDeck.length);    // declare & assign random card index from the deck
        for(int i=0; i< shuffledDeck.length; i++){                  // iterate through the deck
            while(contains[randomCard]){                            // validation check - if index has already been used
                randomCard = generator.nextInt(shuffledDeck.length);
            }
            contains[randomCard]=true;                              // note current card as used
            Card nextCard = Deck[randomCard];                       // declare the next card variable to be assigned
            shuffledDeck[i] = nextCard;                             // assign next card to the current empty array slot
        }
    }

    /**
     * This is a getter method for the shuffled deck
     * @return The card array of the shuffled deck
     */
    public Card[] getShuffledDeck(){
        return shuffledDeck;
    }

    /**
     * This method reveals the top card in the deck
     * @param inputDeck The shuffled deck
     */
    public void revealCard(Card[] inputDeck){
        String topCard;                                     // declare a topCard String variable
        topCard = inputDeck[0].getCard();                   // assign topCard to the 1st index in shuffledDeck array
        System.out.println(topCard);                        // print the top card to the user
    }

    /**
     * This method performs the card force trick
     * @param inputDeck The shuffled deck
     */
    public void cardForce(Card[] inputDeck){
        Card[] outputDeck = new Card[inputDeck.length];     // initialise the deck to be output

        // prompt for user input
        Scanner input  = new Scanner(System.in);            // call the Scanner class to read user input
        System.out.println("Please select a position you wish the top card to go to:");
        String userInput = input.nextLine();                // get user input

        int position = validateIntInput(userInput);         // call the validation method to get a positive integer

        String binRep = Integer.toBinaryString(position-1); // get binary representation of the desired position

        splitDeck(inputDeck);                               // call the splitDeck method to split the deck in half

        /*
         * This block of code reads each character of the binary representation of the desired position
         * and performs either and in or out shuffle on the deck of cards if the current character is a
         * one or a zero respectively
         */
        for(int i=0; i<binRep.length(); i++){
            int bit = Integer.parseInt(String.valueOf(binRep.charAt(i)));
            if (bit==1){
                Card[] inShuffledDeck;                              // declare a new deck of cards for the in-shuffle
                System.out.println("performing in shuffle...");
                // in-shuffle
                inShuffledDeck = inShuffle(deckHalfOne, deckHalfTwo);   // assigning the inShuffledDeck variable
                System.out.println("in shuffle: ");
                show(inShuffledDeck);                                   // display the in-shuffled deck

                splitDeck(inShuffledDeck);                              // call the splitDeck method
            }
            else{
                // out-shuffle
                Card[] outShuffledDeck;                             // declare a new deck of cards for the out-shuffle
                System.out.println("performing out shuffle...");
                outShuffledDeck = outShuffle(deckHalfOne, deckHalfTwo); // assigning the outShuffledDeck variable
                System.out.println("out shuffle: ");
                show(outShuffledDeck);                                  // display the out-shuffled deck

                splitDeck(outShuffledDeck);                             // call the splitDeck method
            }
        }

        /*
         * This block of code combines both halves of the deck into the output deck as well as set the
         * global finalDeck array equal to the outputDeck array
         */
        for(int i=0; i<26;i++){                             // iterate through the first 26 cards
            outputDeck[i] = deckHalfOne[i];                 // combine the first half of the deck
            finalDeck[i] = outputDeck[i];
        }
        for(int j=26; j<outputDeck.length;j++){             // iterate through the last 26 cards
            outputDeck[j] = deckHalfTwo[j-26];              // combine the second half of the deck
            finalDeck[j] = outputDeck[j];
        }
    }

    /**
     * This method validates the integer input for the desired card position
     * @param userInput The user input taken from the keyboard
     * @return The user input after validation, as a positive integer
     */
    private int validateIntInput(String userInput){
        int position;                                       // declare integer position variable
        Scanner input  = new Scanner(System.in);            // call the Scanner class

        // while loop continues until the user inputs a positive integer
        while(true){
            try{
                int intInput= Integer.parseInt(userInput);  // parse the string to an int value
                if (intInput>0){                            // check if the int value is positive
                    position = intInput;                    // assign the position variable
                    return position;                        // return the position
                }
            }
            catch (NumberFormatException ignored){}               // catch NumberFormatException message
            System.out.println("Please enter a positive integer:");     // prompt for new input
            userInput = input.nextLine();
        }
    }

    /**
     * This is a getter method for the final deck, once the card force trick has been performed
     * @return The card array for the final deck
     */
    public Card[] getFinalDeck(){
        return finalDeck;
    }

    /**
     * This method performs the in-shuffle card trick on the two deck halves
     * @param halfOne The first half of the deck
     * @param halfTwo The second half of the deck
     * @return The combined deck after an in-shuffle has been completed
     */
    private Card[] inShuffle(Card[] halfOne, Card[] halfTwo){
        Card[] outputDeck = new Card[TOTCARDS];             // initialise the deck to be output

        // initialise counter variables for each half deck
        int h1Count=0;
        int h2Count=0;

        for(int i=0; i<outputDeck.length; i++){             // iterate through the deck
            /*
            if-else condition determines the order in which to place the cards from each deck half
            for this method, the cards from the second half are placed in the odd positions in the deck
            and the first half, in the even positions (arrays start at 0)
             */
            if((i%2==0)){
                outputDeck[i] = halfTwo[h2Count];
                h2Count++;
            }
            else{
                outputDeck[i] = halfOne[h1Count];
                h1Count++;
            }
        }
        return outputDeck;
    }

    /**
     * This method performs the in-shuffle card trick on the two deck halves
     * @param halfOne The first half of the deck
     * @param halfTwo The second half of the deck
     * @return The combined deck after an out-shuffle has been completed
     */
    private Card[] outShuffle(Card[] halfOne, Card[] halfTwo){
        Card[] outputDeck = new Card[TOTCARDS];             // initialise the deck to be output

        // initialise counter variables for each half deck
        int h1Count=0;
        int h2Count=0;

        for(int i=0; i<outputDeck.length; i++){             // iterate through the deck
            /*
            if-else condition determines the order in which to place the cards from each deck half
            for this method, the cards from the first half are placed in the odd positions in the deck
            and the second half, in the even positions (arrays start at 0)
             */
            if((i%2==0)){
                outputDeck[i] = halfOne[h1Count];
                h1Count++;
            }
            else{
                outputDeck[i] = halfTwo[h2Count];
                h2Count++;
            }
        }
        return outputDeck;
    }

    /**
     * This method takes the deck and splits it in half
     * @param inputDeck The deck to be halved
     */
    private void splitDeck(Card[] inputDeck){
        for (int i=0; i<inputDeck.length/2; i++){
            deckHalfOne[i] = inputDeck[i];
            deckHalfTwo[i] = inputDeck[i+26];
        }
    }

    /**
     * This method takes the user input card and searches the deck until it finds the given card,
     * if the card is found it will output the location of the card within the deck to the user
     */
    public void findCard(Card[] inputDeck){
        boolean found = false;                              // create a flag for when the card is found
        Scanner input = new Scanner(System.in);             // call the Scanner class to read user input

        while(!found){
            System.out.println("Please select enter a card to search for:");
            String userInput = input.nextLine();                    // get user input
            while(!userInput.matches("^[ A-Za-z_]+$")){       // check if input card is in correct format
                System.out.println("user input is: "+userInput);
                System.out.println("Please select a card in the format seen above:");
                userInput = input.nextLine();                       // get new user input
            }
            String searchCard = userInput.toUpperCase();            // convert input to UPPERCASE
            for(int i=0; i<inputDeck.length;i++){
                if(inputDeck[i].getCard().equals(searchCard)){      // execute if card is found in the deck
                    int cardLoc = i+1;                              // get card location (arrays start at 0 so +1)
                    System.out.println("Your card is in position: " + cardLoc);
                    found=true;                                     // set found flag to true
                }
            }
            if (!found){                                    // execute if loop finishes and card is still not found
                System.out.println("Sorry but that card could not be found.");
            }
            found=true;                                     // end the while loop
        }
    }
}

/*
Sources used to aid the application design(approx 10% used in creation of Card class):
Downey, A, Mayfield, C. Think Java Chapter 12. Available: https://books.trinket.io/thinkjava/chapter12.html. Last accessed 10th Dec 2019.
Available: http://www.mathcs.emory.edu/~cheung/Courses/170/Syllabus/10/deck-of-cards.html. Last accessed 15th Dec 2019.
*/