import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Random; 
import java.io.File; 
import java.io.FileNotFoundException;


class WordGame{

    public static void main(String[] args ) throws FileNotFoundException {
        boolean debug = false;
        Scanner input = new Scanner(new File("words.txt"));
        ArrayList<String> wordBank = new ArrayList<String>();

        //obtaining info from text file and filling in the wordBank 
        while(input.hasNextLine()){
            wordBank.add(input.nextLine());
        }

        Random number = new Random(); 

        //chosing a random word from the word bank 
        String chosenWord = debug ?  "elew" : wordBank.get(number.nextInt(wordBank.size()));

        if(debug) System.out.println("chosen word: " + chosenWord);

        ArrayList<Character> guessedLetters = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);  

        //Playing the game with the User 
        System.out.println("Welcome to a fun word guessing game! In this game you will have the power to grant a wish. \n"
                            + "Blowballs (white dandelions) are said to hold the wishes of those that blow on them. \n" 
                           + "Your goal is to make sure that the wind does not blow away the petal seeds." );
        System.out.println("A random word will be generated and it is your job to guess the word. The rules are simple."
                           + " Guess a letter. \nIf it is part of the word, you will see where it is located within the word. If not, you will"
                           + " lose a life and the Blowball will loose a petal (a seed). \nIt's your job to make sure some " 
                           + "petals are left so that a wish can be carried into the universe." );
        System.out.println("If you would like to see the guesses you've made so far type 'guessed'" );
        System.out.println("Enter 'quit' to stop the game at any point. Good Luck!" );

        String guess; 
        int lives = 7; 
        do{
            printCurrentState(guessedLetters, chosenWord);
            printPic(lives); 
            System.out.println("Enter another letter guess: "); 
            guess = userInput.nextLine(); //obtaining letter from user

            if(guess.equals("guessed")){
                printArray(guessedLetters); 
            }
            else{
                if(chosenWord.indexOf(guess.charAt(0)) == -1) lives -= 1; //guessed word is not part of target word --> lose a petal
                guessedLetters.add(guess.charAt(0)); //adding letter to wordbank; if more than one take first letter 
            }

            if (lives == 0){
                printPic(lives); 
                System.out.println("Oh no! The dandelion lost all its petal seeds. Someone's wish will not come true :(");
                System.out.println("The word was: " + chosenWord); 
                System.out.println("Better luck next time. :)"); 
            }
            
        }
        while(!guess.equals("quit") && !checkResults(chosenWord, guessedLetters) && lives!=0); 

        
    }


    private static boolean checkResults(String word, ArrayList guessed){
        int counter = 0; 
        for (int i = 0; i < word.length(); i++){
            if(guessed.contains(word.charAt(i))){
                counter += 1; 
            }
        }
        if(counter == word.length()){
            System.out.println("Congragulations! You have correctly gussed the word! Now someone can make a wish. :)");
            return true; 
        }
        return false; 
    }

    private static void printPic(int mistakes){ //miskates starts at 7 
        System.out.println("number of tries left: " + mistakes + "\n");
        switch(mistakes){
            case 7: 
                System.out.println("                \\ | /"); 
                System.out.println("               --(:)--");
                System.out.println("                / | \\");
                System.out.println("                  |" );
                System.out.println("                 \\|/");
                break; 
            case 6:
                System.out.println("                  | /"); 
                System.out.println("               --(:)--");
                System.out.println("                / | \\");
                System.out.println("                  |" );
                System.out.println("                 \\|/");
                break; 
            case 5: 
                System.out.println("                    /"); 
                System.out.println("               --(:)--");
                System.out.println("                / | \\");
                System.out.println("                  |" );
                System.out.println("                 \\|/");
                break; 
            case 4:
                System.out.println("               --(:)--");
                System.out.println("                / | \\");
                System.out.println("                  |" );
                System.out.println("                 \\|/");
                break; 
            case 3: 
                System.out.println("                 (:)--");
                System.out.println("                / | \\");
                System.out.println("                  |" );
                System.out.println("                 \\|/");
                break; 
            case 2:
                System.out.println("                 (:)");
                System.out.println("                / | \\");
                System.out.println("                  |" );
                System.out.println("                 \\|/");
                break; 
            case 1:
                System.out.println("                 (:)");
                System.out.println("                / | ");
                System.out.println("                  |" );
                System.out.println("                 \\|/");
                break; 
            case 0: 
                System.out.println("                 (:)");
                System.out.println("                  | ");
                System.out.println("                  |" );
                System.out.println("                 \\|/");
                break; 
        }

        return; 
    }

    private static void addLetter(ArrayList arr){
        
    }

    private static void printCurrentState(ArrayList guessed, String word){
        for (int i = 0; i < word.length(); i++){

            if(guessed.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
            }
            else{
                System.out.print("_");
            }
            System.out.print(" ");
        }
        System.out.println(); 
        System.out.println(); 
        return; 
    }

    private static void printArray(ArrayList arr){
        System.out.println("\nHere are the letters you've guessed so far: ");
        int counter = 0; 
        for(int i = 0; i < arr.size(); i++){
            if (counter == 10){
                System.out.println();
                counter = 0;  
            }
            System.out.print(arr.get(i) + " "); 
            counter += 1; 
        }
        System.out.println("\n");
        return;
    }

}

