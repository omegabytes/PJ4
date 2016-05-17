package PJ4_PokerGame.PJ4;

import javafx.scene.control.ListView;

import java.util.*;


//=================================================================================

/** class PlayingCardException: It is used for errors related to Card and Deck objects
 *  This is a checked exception!
 *  Do not modify this class!
 */
class PlayingCardException extends Exception {

    /* Constructor to create a PlayingCardException object */
    PlayingCardException (){
		super ();
    }

    PlayingCardException ( String reason ){
		super ( reason );
    }
}


//=================================================================================

/** class Card : for creating playing card objects
 *  it is an immutable class.
 *  Rank - valid values are 1 to 13
 *  Suit - valid values are 1 to 4
 *  Do not modify this class!
 */
class Card {
    /* constant suits and ranks */
    static final String[] Suit = {"","Clubs", "Diamonds", "Hearts", "Spades" };
    static final String[] Rank = {"","A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    /* Data field of a card: rank and suit */
    private int cardRank;   /* values: 1-13 (see Rank[] above) */
    private int cardSuit;   /* values: 1-4  (see Suit[] above) */

    /* Constructor to create a card */
    /* throw PlayingCardException if rank or suit is invalid */
    public Card(int suit, int rank) throws PlayingCardException {
        if ((rank < 1) || (rank > 13))
            throw new PlayingCardException("Invalid rank:"+rank);
        else
            cardRank = rank;
        if ((suit < 1) || (suit > 4))
            throw new PlayingCardException("Invalid suit:"+suit);
        else
            cardSuit = suit;
    }

    /* Accessor and toString */
    /* You may implement equals(), but it will not be used */
    public int getRank() { return cardRank; }
    public int getSuit() { return cardSuit; }
    public String toString() { return Rank[cardRank] + " " + Suit[cardSuit]; }


    /* Quick tests */
    public static void main(String args[]) {
        try {
            Card c1 = new Card(4,1);    // A Spades
            System.out.println(c1);
            c1 = new Card(1,10);        // 10 Clubs
            System.out.println(c1);
            c1 = new Card(5,10);        // generate exception here
        }
        catch (PlayingCardException e) {
            System.out.println("PlayingCardException: "+e.getMessage());
        }
    }
}


//=================================================================================

/** class Decks represents : n decks of 52 playing cards
 *  Use class Card to construct n * 52 playing cards!
 *
 *  Do not add new data fields!
 *  Do not modify any methods
 *  You may add private methods
 */

class Decks {

    /* this is used to keep track of original n*52 cards */
    private List<Card> originalDecks;

    /* this starts by copying all cards from originalDecks   */
    /* it holds remaining cards during games             */
    private List<Card> currentDecks;

    /* number of 52-card decks in this object */
    private int numOfDecks;

    /* number of cards in a deck */
    public static final int CARDS_PER_DECK = 52;


    /**
     * Constructor: Creates default one deck of 52 playing cards in originalDecks and
     *              copy them to currentDecks.
     *              initialize numOfDecks=1
     * Note: You need to catch PlayingCardException from Card constructor
     *       Use ArrayList for both originalDecks & currentDecks
     */
    public Decks()
    {
        // implement this method!
        numOfDecks = 1;
    }


    /**
     * Constructor: Creates n 52-card decks of playing cards in
     *              originalDecks and copy them to currentDecks.
     *              initialize numOfDecks=n
     * Note: You need to catch PlayingCardException from Card constructor
     *       Use ArrayList for both originalDecks & currentDecks
     */
    public Decks(int n)
    {
        // implement this method!
        originalDecks = new ArrayList<Card>();
        numOfDecks = n;
        for(int numDecks = 0; numDecks < numOfDecks; numDecks++) {
            for (int suit = 0; suit < 0; suit++) {
                for (int rank = 1; rank <= 13; rank++) {
                 try {
                     originalDecks.add(new Card(rank,suit));
                 }catch (PlayingCardException pce){
                     System.out.println(pce.getMessage());
                 }
                }
            }
        }
        currentDecks = new ArrayList<>(originalDecks);

    }


    /**
     * Task: Shuffles cards in currentDecks.
     * Hint: Look at java.util.Collections
     */
    public void shuffle()
    {
        // implement this method!
        Collections.shuffle(currentDecks);
    }


    /**
     * Task: Deals cards from the deal deck.
     *
     * @param numOfCards number of cards to deal
     * @return a list containing cards that were dealt
     * @throw PlayingCardException if numberCards > number of remaining cards
     *
     * Note: You need to create ArrayList to store dealt cards
     *       and remove dealt cards from currentDecks
     *
     */
    public List<Card> deal(int numOfCards) throws PlayingCardException
    {
        // implement this method!
        List<Card> dealtCards = new ArrayList<>();
        if (numOfCards > dealtCards.size()) {
            throw new PlayingCardException("There aren't enough cards left to deal.");
        }
        for (int i=0;i<numOfCards;i++){
            dealtCards.add(currentDecks.remove(0));
        }
        return dealtCards;
    }


    /**
     * Task: Resets playedDeck by copying all cards from the resetDeck.
     */
    public void reset()
    {
        // implement this method!
        currentDecks.clear();
        currentDecks.addAll(originalDecks);

    }


    /**
     * Task: Return number of remaining cards in deal deck.
     */
    public int numOfRemainingCards()
    {
	return currentDecks.size();
    }

    /**
     * Task: Returns a string representing cards in the deal deck
     */
    public String toString()
    {
	return ""+ currentDecks;
    }


    /* Quick test                   */
    /*                              */
    /* Do not modify these tests    */
    /* Generate 2 decks of cards    */
    /* Loop 2 times:                */
    /*   Deal 30 cards for 4 times  */
    /*   Expect exception last time */
    /*   reset()                    */

    public static void main(String args[]) {

        System.out.println("*******    Create 2 decks of cards      *********\n\n");
        Decks decks  = new Decks(2);

        for (int j=0; j < 2; j++) {
            System.out.println("\n************************************************\n");
            System.out.println("Loop # " + j + "\n");
            System.out.println("Before shuffle:"+decks.numOfRemainingCards()+" cards");
            System.out.println("\n\t"+decks);
            System.out.println("\n==============================================\n");

            int numHands = 4;
            int cardsPerHand = 30;

            for (int i=0; i < numHands; i++) {
                decks.shuffle();
                System.out.println("After shuffle:"+decks.numOfRemainingCards()+" cards");
                System.out.println("\n\t"+decks);

                try {
                    System.out.println("\n\nHand "+i+":"+cardsPerHand+" cards");
                    System.out.println("\n\t"+decks.deal(cardsPerHand));
                    System.out.println("\n\nRemain:"+decks.numOfRemainingCards()+" cards");
                    System.out.println("\n\t"+decks);
                    System.out.println("\n==============================================\n");
                }
                catch (PlayingCardException e) {
                    System.out.println("*** In catch block:PlayingCardException:Error Msg: "+e.getMessage());
                }
            }

        decks.reset();
        }
    }

}
