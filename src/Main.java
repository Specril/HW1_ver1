import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Random rnd;

    public static void main(String[] args) throws FileNotFoundException {
       /* String filePath = args[0];
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        int numberOfGames = scanner.nextInt();

        for (int i = 0; i < numberOfGames; i++) {
            int seed = scanner.nextInt();
            rnd = new Random(seed);
            scanner.nextLine();
            String player1 = scanner.nextLine();
            String player2 = scanner.nextLine();
            WarGame game = new WarGame(player1, player2);
            String winner = game.start();
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            System.out.println(winner + " won the game!\n\n"); */
        Deck deck1 = new Deck(true);
        Deck deck2 = new Deck(true);
        // Card card = new Card(Shape.SPADES, 11);
        // deck.addCard(card);
        // deck.removeTopCard();
        deck1.shuffle();
        System.out.println(deck1.deckOfCards.toString());
        System.out.println(deck2.deckOfCards.toString());
        deck2.deckOfCards=deck1.deckOfCards;
        deck1.deckOfCards.clear();
        System.out.println(deck1.deckOfCards.toString());
        System.out.println(deck2.deckOfCards.toString());
        }
    }

