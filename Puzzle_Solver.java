//Created by Emre Kumas.

/*This program tests whether a two-dimensional list has a given string, either horizontally, vertically
or diagonally. But since it is a low chance of possibility, there is a second option to add it manually.*/

import java.util.Scanner;
import java.util.ArrayList;

public class Puzzle_Solver{

    //Global variables.
    //Case possibility for checking multiple cases.
    private static int[] casePossibility = new int[8];
    //These two strings hold the color information(Reset = Black && Red = Red) I'm gonna use in the program.
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    //And we need an arrayList to hold the objects which holds the indexes if there is wanted words. So we can colorize them.
    private static ArrayList<Coloring_Index> indexes = new ArrayList<>();
    //And we also need a temporary array to hold indexes, if successfully a word found, we will store it in the object.
    private static int[][] colorIndexes;


    public static void main(String[] args){

        //Scanner object.
        Scanner input = new Scanner(System.in);

        System.out.println("This program tests whether a two-dimensional list has a given string," +
                " either horizontally, vertically or diagonally. \nBut since it is a low" +
                " chance of possibility, there is a second option to add it manually.\n");

        //First, we need to decide to turn on or off the red text feature.
        boolean redFeature = redFeatureDecision(input);

        System.out.print("\nNow, you need to decide which word you will be searching in the puzzle: ");

        String word = input.next();
        word = word.toUpperCase();

        //Now, we will define the colorIndexes multi-array.
        colorIndexes = new int[word.length()][2];

        //This program will work infinitely until
        while(true){

            System.out.print("1 -> Do it with random numbers and letters. (One Time)" +
                    "\n2 -> Do it with random numbers and letters. (Until found)" +
                    "\n3 -> Do it with a predetermined matrix. (Search TAB)" +
                    "\n4 -> Do it manually. (Program will add the string manually.)" +
                    "\n5 -> Exit the program.\nYour option: ");

            int option = input.nextInt();

            switch(option){

                case 1:

                    //If the option is 1, we need to do the calculations based on random numbers and letters. (ONE TIME)
                    randomSolve(input, word, redFeature);

                    break;
                case 2:

                    //If the option is 2, we need to do the calculations based on random numbers and letters. (UNTIL FOUND)
                    loopSolve(input, word, redFeature);

                    break;
                case 3:

                    //If the option is 3, we will do it with a predetermined matrix.
                    multiArraySolve(word, redFeature);

                    break;
                case 4:

                    //If the option is 4, we need to insert the keyword in the correct place.
                    manuallySolve(input, word, redFeature);

                    break;
                case 5:

                    System.out.println("Program ends. Bye...");
                    System.exit(0);

                    break;
                default:

                    System.out.println("Please enter 1, 2, 3, 4 or 5 not any other thing.\n");
                    break;

            }
        }
    }

    private static boolean redFeatureDecision(Scanner input){

        //First, we need to decide to turn on or off the red text feature.

        System.out.print("First thing I want to ask you, which IDE are you using.\nBecause there is an option " +
                "which makes the wanted word RED. So it is easier to see it in big puzzles. " +
                "But unluckily this feature is only usable in IntelliJ IDEA IDE.\nI tried others but " +
                "this is the only one which works. Sorry if you don't have this IDE.\n" +
                "If you have Intellij IDE, press 1 to enable this feature. Else, press 0: ");

        int option;
        boolean redFeature;

        while(true){

            option = input.nextInt();

            if(option == 0) {

                redFeature = false;
                break;

            }else if(option == 1) {

                redFeature = true;
                break;
            }

            System.out.print("Hey, you can only enter 1 or 0, no other option: ");
        }

        return redFeature;
    }

    private static void randomSolve(Scanner input, String word, boolean redFeature){

        //Getting the rows and columns amount.
        System.out.print("\nPlease enter the number of rows: ");
        int rows = input.nextInt();
        System.out.print("Please enter the number of columns: ");
        int columns = input.nextInt();
        System.out.println("\n");

        //Creating the matrix with given inputs of rows and columns.
        char[][] matrix = createMatrix(rows, columns);

        //After creating the matrix, we check if the wanted word is in the matrix.
        check(word, redFeature, matrix, rows, columns);

        indexes.clear();

        System.out.println("-------------------------------------------------------------\n\n");
    }

    private static void loopSolve(Scanner input, String word, boolean redFeature){

        //Getting the rows and columns amount.
        System.out.print("\nPlease enter the number of rows: ");
        int rows = input.nextInt();
        System.out.print("Please enter the number of columns: ");
        int columns = input.nextInt();
        System.out.println("\n");

        int counter = 0;
        while(true){

            char[][] matrix = createMatrix(rows, columns);

            if(isThereWantedWord(matrix, word, rows, columns)){

                //If there is, we print True and show the matrix with color information if desired.
                System.out.println("\n" + "True. " + indexes.size() + " number of " + word + " found." + "\n\n");
                showMatrix(matrix, word, rows, columns, redFeature);
                indexes.clear();
                break;

            }else{
                //Means keyword is not in the matrix.
                System.out.println("\n" + "Trial: " + ++counter + " False.\n\n");
            }

            indexes.clear();
        }

        System.out.println("-------------------------------------------------------------\n\n");
    }

    private static void multiArraySolve(String word, boolean redFeature){

        char[][] matrix = {{'T', 'K', '3', 'A', 'X'},
                           {'T', 'L', 'T', '9', 'S'},
                           {'A', 'A', 'A', 'D', 'H'},
                           {'B', 'J', 'B', '6', 'T'},
                           {'E', '7', 'B', 'A', 'T'}};
        int rows = 5;
        int columns = 5;

        //After creating the matrix, we check if the wanted word is in the matrix.
        check(word, redFeature, matrix, rows, columns);

        indexes.clear();

        System.out.println("-------------------------------------------------------------\n\n");
    }

    private static void check(String word, boolean redFeature, char[][] matrix, int rows, int columns){

        if(isThereWantedWord(matrix, word, rows, columns)){

            //If there is, we print True and show the matrix with color information if desired.
            System.out.println("\n" + "True. " + indexes.size() + " number of " + word + " found." + "\n\n");
            showMatrix(matrix, word, rows, columns, redFeature);

        }else{
            //Means keyword is not in the matrix.
            System.out.println("\n" + "False.\n\n");
            showMatrix(matrix, word, rows, columns, false);
        }
    }

    private static void manuallySolve(Scanner input, String word, boolean redFeature){

        //Getting the rows and columns amount.
        System.out.print("\nPlease enter the number of rows: ");
        int rows = input.nextInt();
        System.out.print("Please enter the number of columns: ");
        int columns = input.nextInt();
        System.out.println("\n");

        //After getting the rows and columns amount, we check if the word can fit into that matrix.
        //This is needed or no placing can be done.
        if(rows >= word.length() || columns >= word.length()){

            //After that we create the matrix.
            char[][] matrix = createMatrix(rows, columns);

            //Now, we will add the keyword into matrix.
            addManually(matrix, word, rows, columns);

            //Now, to make sure, we check if the keyword is in the matrix.
            check(word, redFeature, matrix, rows, columns);

        }else{

            System.out.println("Rows or columns need to be " + word.length() + " at least for " + word + " to be inserted.");

        }

        indexes.clear();

        System.out.println("-------------------------------------------------------------\n\n");
    }

    private static char[][] createMatrix(int rows, int columns){

        //Create a new 2-dimensional array.
        char[][] matrix = new char[rows][columns];

        //For every character in this array...
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){

                //First, we decide what to use; number or letter. If RNG gives us 0, we will use letters.
                //65-90 -> A-Z  &&  48-57 -> 0-9
                if((int)(Math.random() * 2) == 0)
                    matrix[i][j] = (char)((Math.random() * 26) + 65);
                else
                    matrix[i][j] = (char)((Math.random() * 10) + 48);
            }
        }

        return matrix;
    }

    private static void showMatrix(char[][] matrix, String word, int rows, int columns, boolean redFeatureEnabled){

        //If no color information is needed, we can show every element one by one.
        if(!redFeatureEnabled){

            for(int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++)
                    System.out.print(matrix[i][j] + " ");

                System.out.println();
            }

        }else{

            //Now, we need to colorize correct letters. For this we want to use coloringIndexes array.
            boolean colored = false;

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < columns; j++){
                    for(int k = 0; k < word.length(); k++){

                        for (Coloring_Index index : indexes) {

                            int[][] colorIndex = index.getIndexArray();
                            if (colorIndex[k][0] == i && colorIndex[k][1] == j) {
                                System.out.print(ANSI_RED + matrix[i][j] + ANSI_RESET + " ");
                                colored = true;
                                break;
                            }
                        }
                    }

                    if(!colored)
                        System.out.print(matrix[i][j] + " ");

                    colored = false;
                }

                System.out.println();
            }

        }
    }

    @SuppressWarnings("ConstantConditions")
    private static boolean isThereWantedWord(char[][] matrix, String word, int rows, int columns){

        //This method checks if the keyword is in the matrix.
        int[][] container = new int[8][3];
        boolean foundFirst;
        boolean firstSearch;

        //We need to split the word into characters.
        char[] letters = word.toCharArray();

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                //If the program encounters the first letter, it saves its indexes in coloringIndexes array.
                //Then it checks if there is the second letter, in any direction around the first letter.
                //If it founds the second letter in any direction, it saves this direction and continues to search
                //the remaining letters in that direction.
                int caseNumber = 0;
                foundFirst = false;
                firstSearch = true;

                for(int k = 0; k < letters.length; k++){

                    if(k == 0 && matrix[i][j] == letters[k]) {
                        //Means we found the first letter.
                        colorIndexes[k][0] = i;
                        colorIndexes[k][1] = j;
                        foundFirst = true;
                    }//Since k isn't 0, it is one of the remaining letters. We need to check if there was a first letter.
                    else if(foundFirst) {
                        //Yes, there is a first letter found.

                        //If we are looking for the second letter, we need to check every direction, so set it -1.
                        if (k == 1 && firstSearch) {

                            caseNumber = -1;
                            conditions(matrix, container, letters[k], i, j, rows, columns, caseNumber);

                        }else if(k == 1 && !firstSearch){

                            conditions(matrix, container, letters[k], i, j, rows, columns, caseNumber);

                        }else
                            conditions(matrix, container, letters[k], container[caseNumber - 1][1], container[caseNumber - 1][2], rows, columns, caseNumber);


                        if(k == 1){

                            for (int z = 0; z < 8; z++) {
                                //We will loop 8 times, because a letter has 8 letters around it.
                                if (casePossibility[z] == 1) {
                                    colorIndexes[k][0] = container[z][1];
                                    colorIndexes[k][1] = container[z][2];
                                    caseNumber = z + 1;
                                    break;
                                }
                            }

                            if(caseNumber == -1){

                                //Means, we couldn't find any second letter around the first letter.
                                foundFirst = false;

                            }
                        }else{

                            //Means we are looking for the third or fourth or fifth... etc.
                            if(casePossibility[caseNumber - 1] == 1) {
                                colorIndexes[k][0] = container[caseNumber - 1][1];
                                colorIndexes[k][1] = container[caseNumber - 1][2];

                                //The last thing we need to check is, if there is more than word whose first letter is the same.
                                if(k == letters.length - 1){

                                    //Means there is a word, but there could be other as well whose first letter is the same.
                                    //First lets, delete the casePossibilities caseNumber index value.
                                    casePossibility[caseNumber - 1] = 0;

                                    for(int z = 0; z < 8; z++){

                                        if(casePossibility[z] == 1){

                                            //Yes, there is more than one word.
                                            Coloring_Index match = new Coloring_Index(word.length());
                                            match.setIndexArray(colorIndexes);
                                            indexes.add(match);

                                            //Now, we need to look for the other case.
                                            caseNumber = z + 1;
                                            k = 0;
                                            firstSearch = false;
                                            break;

                                        }
                                    }
                                }

                            }else{
                                foundFirst = false;
                                for(int z = 0; z < 8; z++){

                                    if(casePossibility[z] == 1){
                                        caseNumber = z + 1;
                                        k = 0;
                                        foundFirst = true;
                                        firstSearch = false;
                                        break;
                                    }

                                }
                            }
                        }
                    }
                }

                if(foundFirst){

                    //If this value is true here, it means we found a match. Now, we need to create a new object.
                    Coloring_Index match = new Coloring_Index(word.length());
                    match.setIndexArray(colorIndexes);
                    indexes.add(match);

                }

                //After checking all conditions, we need to reset casePossibilities back to 0.
                for(int k = 0; k < 8; k++)
                    casePossibility[k] = 0;
            }
        }

        return !indexes.isEmpty();
    }

    @SuppressWarnings("ConstantConditions")
    private static void conditions(char[][] matrix, int[][] container, char compared, int i, int j, int rows, int columns, int caseNumber){

        //This function checks every possible condition for detecting letters around a letter.
        //Setting all values of the first column of the container from casePossibility array.
        for(int k = 0; k < 8; k++)
            container[k][0] = casePossibility[k];

        //If case number is -1, it means now we need to check every direction this letter has around it.
        //Upward, Downward, Rightward, Leftward, Right-Upper, Left-Upper, Right-Lower, Left-Lower.
        if(caseNumber == -1){

            //Case number: 1 -> Right-Upper
            //First we need to check if there is a letter in that direction. If not we cannot check it.
            if(i > 0 && j < (columns - 1)){
                //Means there is a letter in that direction. Now we check if that letter is the letter we are looking for.
                if(matrix[i-1][j+1] == compared){
                    //Yes, that is the letter we're looking for. Now we set the containers values.
                    //Then set the case number and return container.
                    container[0][0] = 1;
                    container[0][1] = i-1;
                    container[0][2] = j+1;
                }
            }
            //Case number: 2 -> Left-Upper
            if(i > 0 && j > 0){
                if(matrix[i-1][j-1] == compared){
                    container[1][0] = 1;
                    container[1][1] = i-1;
                    container[1][2] = j-1;
                }
            }
            //Case number: 3 -> Right-Lower
            if(i < (rows - 1) && j < (columns - 1)){
                if(matrix[i+1][j+1] == compared){
                    container[2][0] = 1;
                    container[2][1] = i+1;
                    container[2][2] = j+1;
                }
            }
            //Case number: 4 -> Left-Lower
            if(j > 0 && i < (rows - 1)){
                if(matrix[i+1][j-1] == compared){
                    container[3][0] = 1;
                    container[3][1] = i+1;
                    container[3][2] = j-1;
                }
            }
            //Case number: 5 -> Right
            if(j < (columns - 1)) {
                if (matrix[i][j + 1] == compared) {
                    container[4][0] = 1;
                    container[4][1] = i;
                    container[4][2] = j + 1;
                }
            }
            //Case number: 6 -> Left
            if(j > 0) {
                if(matrix[i][j - 1] == compared) {
                    container[5][0] = 1;
                    container[5][1] = i;
                    container[5][2] = j - 1;
                }
            }
            //Case number: 7 -> Downward
            if(i < (rows - 1)){
                if(matrix[i+1][j] == compared){
                    container[6][0] = 1;
                    container[6][1] = i+1;
                    container[6][2] = j;
                }
            }
            //Case number: 8 -> Upward
            if(i > 0){
                if(matrix[i-1][j] == compared){
                    container[7][0] = 1;
                    container[7][1] = i-1;
                    container[7][2] = j;
                }
            }
        }else{
            //Else means, we need to check only one direction because caseNumber is identified.
            //So we do our calculations based on the caseNumber.
            switch(caseNumber){

                case 1:

                    //The right-upper case. We check if there is a letter in that direction to check.
                    if(i > 0 && j < (columns - 1)){
                        //Means there is a letter in that direction. Now we check if that letter is the letter we are looking for.
                        if(matrix[i-1][j+1] == compared){
                            //Yes, that is the letter we're looking for. Now we set the containers values.
                            //Then set the case number and return container.
                            container[0][0] = 1;
                            container[0][1] = i-1;
                            container[0][2] = j+1;
                            return;
                        }
                    }
                    //If both of those conditions is false, we will set that condition to 0.
                    casePossibility[caseNumber - 1] = 0;

                    break;
                case 2:

                    if(i > 0 && j > 0){
                        if(matrix[i-1][j-1] == compared){
                            container[1][0] = 1;
                            container[1][1] = i-1;
                            container[1][2] = j-1;
                            return;
                        }
                    }
                    //If both of those conditions is false, we will set that condition to 0.
                    casePossibility[caseNumber - 1] = 0;

                    break;
                case 3:

                    if(i < (rows - 1) && j < (columns - 1)){
                        if(matrix[i+1][j+1] == compared){
                            container[2][0] = 1;
                            container[2][1] = i+1;
                            container[2][2] = j+1;
                            return;
                        }
                    }
                    //If both of those conditions is false, we will set that condition to 0.
                    casePossibility[caseNumber - 1] = 0;

                    break;
                case 4:

                    if(j > 0 && i < (rows - 1)){
                        if(matrix[i+1][j-1] == compared){
                            container[3][0] = 1;
                            container[3][1] = i+1;
                            container[3][2] = j-1;
                            return;
                        }
                    }
                    //If both of those conditions is false, we will set that condition to 0.
                    casePossibility[caseNumber - 1] = 0;

                    break;
                case 5:

                    if(j < (columns - 1)) {
                        if (matrix[i][j + 1] == compared) {
                            container[4][0] = 1;
                            container[4][1] = i;
                            container[4][2] = j + 1;
                            return;
                        }
                    }
                    //If both of those conditions is false, we will set that condition to 0.
                    casePossibility[caseNumber - 1] = 0;

                    break;
                case 6:

                    if(j > 0) {
                        if(matrix[i][j - 1] == compared) {
                            container[5][0] = 1;
                            container[5][1] = i;
                            container[5][2] = j - 1;
                            return;
                        }
                    }
                    //If both of those conditions is false, we will set that condition to 0.
                    casePossibility[caseNumber - 1] = 0;

                    break;
                case 7:

                    if(i < (rows - 1)){
                        if(matrix[i+1][j] == compared){
                            container[6][0] = 1;
                            container[6][1] = i+1;
                            container[6][2] = j;
                            return;
                        }
                    }
                    //If both of those conditions is false, we will set that condition to 0.
                    casePossibility[caseNumber - 1] = 0;

                    break;
                case 8:

                    if(i > 0){
                        if(matrix[i-1][j] == compared){
                            container[7][0] = 1;
                            container[7][1] = i-1;
                            container[7][2] = j;
                            return;
                        }
                    }
                    //If both of those conditions is false, we will set that condition to 0.
                    casePossibility[caseNumber - 1] = 0;

                    break;
            }
            return;
        }

        for(int k = 0; k < 8; k++)
            casePossibility[k] = container[k][0];
    }

    private static void addManually(char[][] matrix, String word, int rows, int columns){

        //First thing to do is, to determine first letters position.
        int firstLetterRow = (int)(Math.random() * rows);
        int firstLetterColumn = (int)(Math.random() * columns);

        //Lets create our char array consisting of our word.
        char[] charWord = word.toCharArray();

        //We know there are 8 cases which we can insert the following letters. These 8 cases are explained below.
        //Now, we need to determine which case to follow.
        int caseToFollow = (int)(Math.random() * 8) + 1;

        switch(caseToFollow){

            case 1:

                //Right-Upper case. First need to check if there is enough space.
                if(firstLetterRow - (word.length() - 1) >= 0 && firstLetterColumn + (word.length() - 1) < columns){

                    //Yes, there is enough space. We can fit the letter.
                    //Lets insert the letters.
                    for(int i = 0; i < word.length(); i++)
                        matrix[firstLetterRow - i][firstLetterColumn + i] = charWord[i];

                }else{

                    //Means there is not enough space. We will switch firstLetter's position and the case.
                    addManually(matrix, word, rows, columns);
                }

                break;
            case 2:

                //Left-Upper case. First need to check if there is enough space.
                if(firstLetterRow - (word.length() - 1) >= 0 && firstLetterColumn - (word.length() - 1) >= 0){

                    //Yes, there is enough space. We can fit the letter.
                    //Lets insert the letters.
                    for(int i = 0; i < word.length(); i++)
                        matrix[firstLetterRow - i][firstLetterColumn - i] = charWord[i];

                }else{

                    //Means there is not enough space. We will switch firstLetter's position and the case.
                    addManually(matrix, word, rows, columns);
                }

                break;
            case 3:

                //Right-Lower case. First need to check if there is enough space.
                if(firstLetterRow + (word.length() - 1) < rows && firstLetterColumn + (word.length() - 1) < columns){

                    //Yes, there is enough space. We can fit the letter.
                    //Lets insert the letters.
                    for(int i = 0; i < word.length(); i++)
                        matrix[firstLetterRow + i][firstLetterColumn + i] = charWord[i];

                }else{

                    //Means there is not enough space. We will switch firstLetter's position and the case.
                    addManually(matrix, word, rows, columns);
                }

                break;
            case 4:

                //Left-Lower case. First need to check if there is enough space.
                if(firstLetterRow + (word.length() - 1) < rows && firstLetterColumn - (word.length() - 1) >= 0){

                    //Yes, there is enough space. We can fit the letter.
                    //Lets insert the letters.
                    for(int i = 0; i < word.length(); i++)
                        matrix[firstLetterRow + i][firstLetterColumn - i] = charWord[i];

                }else{

                    //Means there is not enough space. We will switch firstLetter's position and the case.
                    addManually(matrix, word, rows, columns);
                }

                break;
            case 5:

                //Right case. First need to check if there is enough space.
                if(firstLetterColumn + (word.length() - 1) < columns){

                    //Yes, there is enough space. We can fit the letter.
                    //Lets insert the letters.
                    int i;
                    for(i = 0; i < word.length(); i++)
                        matrix[firstLetterRow][firstLetterColumn + i] = charWord[i];

                }else{

                    //Means there is not enough space. We will switch firstLetter's position and the case.
                    addManually(matrix, word, rows, columns);
                }

                break;
            case 6:

                //Left case. First need to check if there is enough space.
                if(firstLetterColumn - (word.length() - 1) >= 0){

                    //Yes, there is enough space. We can fit the letter.
                    //Lets insert the letters.
                    for(int i = 0; i < word.length(); i++)
                        matrix[firstLetterRow][firstLetterColumn - i] = charWord[i];

                }else{

                    //Means there is not enough space. We will switch firstLetter's position and the case.
                    addManually(matrix, word, rows, columns);
                }

                break;
            case 7:

                //Lower case. First need to check if there is enough space.
                if(firstLetterRow + (word.length() - 1) < rows){

                    //Yes, there is enough space. We can fit the letter.
                    //Lets insert the letters.
                    for(int i = 0; i < word.length(); i++)
                        matrix[firstLetterRow + i][firstLetterColumn] = charWord[i];

                }else{

                    //Means there is not enough space. We will switch firstLetter's position and the case.
                    addManually(matrix, word, rows, columns);
                }

                break;
            case 8:

                //Upper case. First need to check if there is enough space.
                if(firstLetterRow - (word.length() - 1) >= 0){

                    //Yes, there is enough space. We can fit the letter.
                    //Lets insert the letters.
                    for(int i = 0; i < word.length(); i++)
                        matrix[firstLetterRow - i][firstLetterColumn] = charWord[i];

                }else{

                    //Means there is not enough space. We will switch firstLetter's position and the case.
                    addManually(matrix, word, rows, columns);
                }

                break;
        }
    }
}
