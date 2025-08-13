import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[][] playField = {{" "," "," "},{" "," "," "},{" "," "," "}};
        drawPlayField(playField);
        final String[][] numField = {{"1","2","3"},{"4","5","6 "},{"7","8","9"}};
        drawPlayField(numField);
        int round = 1;
        boolean gameWon = false;
        final Scanner scanner = new Scanner(System.in);
        String player ="X";
        while(round < 10 && !gameWon) {
            player ="X";
            if(round % 2 == 0) {
                player = "O";
            }
            System.out.println("Player " + player +" enter (1-9)");
            int guess = scanLetter(scanner, playField);
            int guessRow = (guess - 1)/3;
            int guessCol = (guess - 1)%3;
            if(!Objects.equals(playField[guessRow][guessCol], " ")) {
                System.out.println("That place is already filled");
                continue;
            } else {
                playField[guessRow][guessCol] = player;
            }
            drawPlayField(playField);
            gameWon = winCheck(playField);
            System.out.println(round);
            round++;
        }
        if(gameWon) {
            System.out.println("Congrats " + player + "! You win!");
        } else {
            System.out.println("Oh no! Its a tie!");
        }
    }

    public static boolean winCheck(String[][] playField){
        //horizontal check
        for (int i = 0; i < playField.length; i++){
            if(Objects.equals(playField[i][0], playField[i][1]) &&
                Objects.equals(playField[i][1], playField[i][2]) &&
                !Objects.equals(playField[i][0], " ")){
                return true;
            } else if (Objects.equals(playField[0][i], playField[1][i]) &&
                    Objects.equals(playField[1][i], playField[2][i]) &&
                    !Objects.equals(playField[0][i], " ")) {
                return true;
            }
        }
        if(Objects.equals(playField[0][0], playField[1][1]) &&
            Objects.equals(playField[1][1], playField[2][2]) &&
            !Objects.equals(playField[0][0], " ")){
            return true;
        } else if(Objects.equals(playField[2][0],playField[1][1]) &&
                Objects.equals(playField[1][1], playField[0][2]) &&
                !Objects.equals(playField[1][1], " ")){
            return true;
        } else {
            return false;
        }
    }

    public static int scanLetter(Scanner scanner, String[][] numField) {
        while (true) {
            try{
                final String letter = scanner.next();
                if (Character.isLetter(letter.charAt(0))) {
                    throw new Exception("That is not a number. Try again.");
                }
                if (letter.length() != 1) {
                    throw new Exception("Too many numbers. Give me just one");
                }
                char ch = letter.charAt(0);
                int number = ch - '0';
                if (number == 0){
                    throw new Exception("Anything else,but zero. Try again.");
                }
                return number;
            } catch (Exception e) {
                System.out.println("Invalid input:" + e.getMessage());
                System.out.println("Choose a number according to the numbered field shown");
                drawPlayField(numField);
            }
        }
    }

    public static void drawPlayField(String[][] playField) {
        for (String[] strings : playField) {
            System.out.println(strings[0] + "|" + strings[1] + "|" + strings[2]);
          /*  if(i < playField.length - 1) {
                System.out.println("-----");
            } */
        }
    }


}