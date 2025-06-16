//Parsia Farhadnia
//Project connect 4
//computer scinece grade 11

import java.util.Scanner;

public class arrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int player1win = 0;
        int player2win = 0;
        int botWin = 0;
        int neverloseBOtwin = 0;
        char[] board = new char[42];
        for (int i = 0; i < 42; i++) { //6*7=42 so we need array with 42 spaces
            board[i] = ' '; //becouse we want it be empty
        }
        int mode = 0;
        while (true) {
            //we ask for the our mode here
            System.out.println("WHAT MODE YOU WANT TO PLAY?? \t\n(1)player vs player \t\n(2)player vs bot \t\n(3)player vs bot(cheating)");
            mode = sc.nextInt();
            if (mode == 1 || mode == 2 || mode == 3) {
                System.out.println("YOU PICKED OPTION NUMBER " + mode);
                break;
            } else {
                System.out.println("not right number brother ");
            }
        }
        char currentPlayer = 'o';
        boolean playingRn = true;
        if (mode == 1) {
            while (playingRn) {
                //we ask our two players for code each time
                System.out.println("\nchose col 1 tu 7 Player " + currentPlayer);
                int col = sc.nextInt();
                while (col < 1 || col > 7) {
                    System.out.println("not even close bro(enter again)");
                    col = sc.nextInt();
                }
                //we check if that wanted column has enough space
                int indexPut = -1;
                for (int i = (col - 1) * 6; i < col * 6; i++) {
                    if (board[i] == ' ') {
                        indexPut = i;
                        break;
                    } else {


                    }
                }
                if (indexPut == -1) {
                    System.out.println("bruh this col is full");

                } else {
                    board[indexPut] = currentPlayer;
                    // we check if anyone wins under a lot of circumcstaces
                    System.out.println("\nCurrent Board:");
                    for (int r = 5; r >= 0; r--) { // we print lines like | and the values from row 5 to row 0 |
                        for (int c = 0; c < 7; c++) {
                            System.out.print("  " + board[c * 6 + r] + " ");
                        }
                        System.out.println(" ");
                    }
                    System.out.println("  1   2   3   4   5   6   7  "); // column numbers
                    String Whowin = "";
                    if (currentPlayer == 'o') {
                        Whowin = "oooo";
                    } else {
                        Whowin = "xxxx";
                    }
                    boolean won = false;


                    //verticaly check if someone have won
                    for (int c = 0; c < 7; c++) {
                        String colStr = "";
                        for (int r = 0; r < 6; r++) {
                            colStr += board[c * 6 + r];
                        }
                        if (colStr.contains(Whowin)) {
                            won = true;
                            break;
                        }
                    }
                    // we check horizontal
                    if (!won) {
                        for (int r = 0; r < 6; r++) {
                            String rowStr = "";
                            for (int c = 0; c < 7; c++) {
                                rowStr += board[c * 6 + r];
                            }
                            if (rowStr.contains(Whowin)) {
                                won = true;
                                break;
                            }
                        }
                    }
                    // diagonal مورب
                    if (!won) {
                        for (int c = 0; c <= 3; c++) {
                            for (int r = 0; r <= 2; r++) {
                                String d = "";
                                for (int i = 0; i < 4; i++) {
                                    d += board[(c + i) * 6 + (r + i)];
                                }
                                if (d.equals(Whowin)) {
                                    won = true;
                                    break;
                                }
                            }
                        }
                    }
                    // diagonal this is like the upsind down of the other one
                    if (!won) {
                        for (int c = 0; c <= 3; c++) {
                            for (int r = 3; r < 6; r++) {
                                String d = "";
                                for (int i = 0; i < 4; i++) {
                                    d += board[(c + i) * 6 + (r - i)];
                                }
                                if (d.equals(Whowin)) {
                                    won = true;
                                    break;
                                }
                            }
                        }
                    }
                    //we give players point if they win
                    if (won) {
                        System.out.println("player " + currentPlayer + " wins!!");
                        if (currentPlayer == 'o') {
                            player1win++;
                        } else {
                            player2win++;
                        }
                        playingRn = false;
                        //we ask if they wanna play again
                        System.out.println("do u wanna play again(y/n)");
                        sc.nextLine();
                        String choice = sc.nextLine();
                        //if they wanna play again we reset our board coonect 4
                        if (choice.equals("y")) {
                            playingRn = true;
                            for (int i = 0; i < 42; i++) {
                                board[i] = ' ';
                            }
                            currentPlayer = 'o';

                        } else {
                            System.out.println("i need to farewell you ");
                        }

                    } else {
                        //we also check each time if the board is full which couses a draw
                        boolean full = true;
                        for (int i = 0; i < 42; i++) {
                            if (board[i] == ' ') {
                                full = false;
                                break;
                            }
                        }


                        if (full) {
                            System.out.println("no one won ( its a tie)");
                            playingRn = false;
                            //we its a tie and they wanna play again reset everything a current player is  o
                            System.out.println("do u wanna play again(y/n");
                            sc.nextLine();
                            String choice = sc.nextLine();
                            if (choice.equals("y")) {
                                playingRn = true;
                                for (int i = 0; i < 42; i++) {
                                    board[i] = ' ';
                                }
                                currentPlayer = 'o';
                            }else{
                                System.out.println("farewell");
                            }
                        } else {
                            System.out.println("so we go for the next round ");

                        }

                    }
                    System.out.println("boom boom");
                    if (currentPlayer == 'o') {
                        currentPlayer = 'x';
                    } else {
                        currentPlayer = 'o';
                    }
                }
            }
            //now our secound mode which is player vs bot
        } else if (mode == 2) {
            while (playingRn) {
                int col = 0;
                int indexPut = -1;
                boolean moveISok = false;
                //so here we want to make sure the move made by eather user or bot is ok
                while (!moveISok) {
                    //if its o it is  the user move
                    if (currentPlayer == 'o') {
                        System.out.println("\nchose col 1 tu 7 Player " + currentPlayer);
                        col = sc.nextInt();
                        while (col < 1 || col > 7) {
                            System.out.println("not even close bro(enter again)");
                            col = sc.nextInt();
                        }
                    } else {
                        //bot move
                        col = (int) (Math.random() * 7) + 1;
                        System.out.println("bot picks column " + col);
                    }
                     //we check if coulumn has empty spaces which makes it valid
                    indexPut = -1;
                    for (int i = (col - 1) * 6; i < col * 6; i++) {
                        if (board[i] == ' ') {
                            indexPut = i;
                            //if we had empty spaces where ever in our code it breaks
                            break;
                        }
                    }
                         //if no we going ask again
                    if (indexPut == -1) {
                        if (currentPlayer == 'o') {
                            System.out.println("bruh this col is full (pick again)");
                        }
                    } else {
                        moveISok = true;
                    }
                }
                 //we put in the thing x or o
                board[indexPut] = currentPlayer;
                System.out.println("\nCurrent Board:");
                for (int r = 5; r >= 0; r--) { // we print lines like | and the values from row 5 to row 0 |
                    for (int c = 0; c < 7; c++) {
                        System.out.print("  " + board[c * 6 + r] + " ");
                    }
                    System.out.println(" ");
                }
                System.out.println("  1   2   3   4   5   6   7  "); // column numbers

                String Whowin = "";
                if (currentPlayer == 'o') {
                    Whowin = "oooo";
                } else {
                    Whowin = "xxxx";
                }

                boolean won = false;

                // vertical check for win
                for (int c = 0; c < 7; c++) {
                    String colStr = "";
                    for (int r = 0; r < 6; r++) {
                        colStr += board[c * 6 + r];
                    }
                    if (colStr.contains(Whowin)) {
                        won = true;
                        break;
                    }
                }

                // horizontal check for win
                if (!won) {
                    for (int r = 0; r < 6; r++) {
                        String rowStr = "";
                        for (int c = 0; c < 7; c++) {
                            rowStr += board[c * 6 + r];
                        }
                        if (rowStr.contains(Whowin)) {
                            won = true;
                            break;
                        }
                    }
                }

                // diagonal chekc for win
                if (!won) {
                    for (int c = 0; c <= 3; c++) {
                        for (int r = 0; r <= 2; r++) {
                            String d = "";
                            for (int i = 0; i < 4; i++) {
                                d += board[(c + i) * 6 + (r + i)];
                            }
                            if (d.equals(Whowin)) {
                                won = true;
                                break;
                            }
                        }
                    }
                }

                // diagonal upside down
                if (!won) {
                    for (int c = 0; c <= 3; c++) {
                        for (int r = 3; r < 6; r++) {
                            String d = "";
                            for (int i = 0; i < 4; i++) {
                                d += board[(c + i) * 6 + (r - i)];
                            }
                            if (d.equals(Whowin)) {
                                won = true;
                                break;
                            }
                        }
                    }
                }
                    //if eather of them win base on checking they get point
                if (won) {
                    if (currentPlayer == 'o') {
                        System.out.println("player wins!!");
                        player1win++;
                    } else {
                        System.out.println("bot wins!!");
                        botWin++;
                    }

                    playingRn = false;
                    //after 1 won we ask if the user is willing to continue
                    System.out.println("do u wanna play again(y/n)");
                    sc.nextLine();
                    String choice = sc.nextLine();
                    if (choice.equals("y")) {
                        playingRn = true;
                        //we initialise the board
                        for (int i = 0; i < 42; i++) {
                            board[i] = ' ';
                        }
                        currentPlayer = 'o';
                    } else {
                        System.out.println("farewell");
                    }

                } else {
                    //we check if the board is full
                    boolean full = true;
                    for (int i = 0; i < 42; i++) {
                        if (board[i] == ' ') {
                            full = false;
                            break;
                        }
                    }
                      //here the same as mode 1 check if  full ask user if play again and initlise the values
                    if (full) {
                        System.out.println("no one won ( it's a tie )");
                        playingRn = false;
                        System.out.println("do u wanna play again(y/n)");
                        sc.nextLine();
                        String choice = sc.nextLine();
                        if (choice.equals("y")) {
                            playingRn = true;
                            for (int i = 0; i < 42; i++) {
                                board[i] = ' ';
                            }
                            currentPlayer = 'o';
                        } else {
                            System.out.println("farewell");
                        }
                    } else {
                        System.out.println("next round of turn");
                        if (currentPlayer == 'o') {
                            currentPlayer = 'x';
                        } else {
                            currentPlayer = 'o';
                        }
                    }
                }
            }
        }else{
            // else here would be our third option which is agains a smart robot
            while (playingRn) {
                int col = 0;
                int indexPut = -1;
                boolean moveISok1 = false;
                //we get the move only if the tergeted column is empty from bot or user
                while (!moveISok1) {
                    if (currentPlayer == 'o') {
                        System.out.println("\nchose col 1 tu 7 player " + currentPlayer);
                        col = sc.nextInt();
                        while (col < 1 || col > 7) {
                            System.out.println("not even close bro(enter again)");
                            col = sc.nextInt();
                        }
                    } else {

                        // we going to try to block player if they have 3 in line
                        boolean canWeblock = false;
                        //we check the empty column
                        for (int c = 0; c < 7; c++) {
                            int testIndex = -1;
                            for (int i = c * 6; i < (c + 1) * 6; i++) {
                                if (board[i] == ' ') {//we get the first empty index in that column
                                    testIndex = i;  //we keep it cus we wanna see what happens if user use it
                                    break;//we break cus we want the first value in each column
                                }
                            }

                            if (testIndex != -1) {//if the empty space is found we do these
                                board[testIndex] = 'o'; // we fake the other players move to if they win
                                String wintest = "";
                                for (int r = 0; r < 6; r++) {
                                    wintest += board[c * 6 + r];
                                }
                                boolean danger = false;
                                if (wintest.contains("oooo")) { // we chekc if that would make a full column if possible we block it
                                    danger = true;
                                }
                                board[testIndex] = ' '; // undo pretend
                                if (danger) {
                                    col = c + 1; // if it was dengerous we select that column
                                    canWeblock = true;
                                    break;
                                }
                            }
                        }
                        if (!canWeblock) {
                            if (board[3 * 6 + 5] == ' ') { // if it wasnt dangerous we do a normal move
                                col = 4; // we check for teh middle spot cus its a key position in connect 4 game
                            } else {
                                col = (int) (Math.random() * 7) + 1; // otherwise we do randome move
                            }
                        }
                        System.out.println("cheater bot is going for" + col);
                    }

                    indexPut = -1;
                    for (int i = (col - 1) * 6; i < col * 6; i++) {
                        if (board[i] == ' ') {
                            indexPut = i;
                            break;
                        }
                    }

                    if (indexPut == -1) {
                        if (currentPlayer == 'o') {
                            System.out.println("bruh this col is full (pick again)");
                        }
                    } else {
                        moveISok1 = true;
                    }
                }

                board[indexPut] = currentPlayer;
                System.out.println("\nCurrent Board:");
                for (int r = 5; r >= 0; r--) { // we print lines like | and the values from row 5 to row 0 |
                    for (int c = 0; c < 7; c++) {
                        System.out.print("  " + board[c * 6 + r] + " ");
                    }
                    System.out.println(" ");
                }
                System.out.println("  1   2   3   4   5   6   7  "); // column numbers
                String Whowin="";
                if(currentPlayer == 'o') {
                     Whowin = "oooo";
                }else {
                     Whowin = "xxxx";
                }
                boolean won = false;

                // vertical
                for (int c = 0; c < 7; c++) {
                    String colStr = "";
                    for (int r = 0; r < 6; r++) {
                        colStr += board[c * 6 + r];
                    }
                    if (colStr.contains(Whowin)) {
                        won = true;
                        break;
                    }
                }

                // horizontal
                if (!won) {
                    for (int r = 0; r < 6; r++) {
                        String rowStr = "";
                        for (int c = 0; c < 7; c++) {
                            rowStr += board[c * 6 + r];
                        }
                        if (rowStr.contains(Whowin)) {
                            won = true;
                            break;
                        }
                    }
                }

                // diagonal \
                if (!won) {
                    for (int c= 0; c <= 3; c++) {
                        for (int r= 0; r <= 2; r++) {
                            String d = "";
                            for (int i = 0; i < 4; i++) {
                                d += board[(c + i) * 6 + (r + i)];
                            }
                            if (d.equals(Whowin)) {
                                won = true;
                                break;
                            }
                        }
                    }
                }

                // diagonal /
                if (!won) {
                    for (int c = 0; c <= 3; c++) {
                        for (int r = 3; r < 6; r++) {
                            String d = "";
                            for (int i = 0; i < 4; i++) {
                                d += board[(c + i) * 6 + (r - i)];
                            }
                            if (d.equals(Whowin)) {
                                won = true;
                                break;
                            }
                        }
                    }
                }

                if (won) {
                    if (currentPlayer == 'o') {
                        System.out.println("player wins!!");
                        player1win++;
                    } else {
                        System.out.println("smart bot wins!!");
                        neverloseBOtwin++;
                    }

                    playingRn = false;
                    System.out.println("do u wanna play again(y/n)");
                    sc.nextLine();
                    String choice = sc.nextLine();
                    if (choice.equals("y")) {
                        playingRn = true;
                        for (int i = 0; i < 42; i++) {
                            board[i] = ' ';
                        }
                        currentPlayer = 'o';
                    } else {
                        System.out.println("farewell");
                    }

                } else {
                    boolean full = true;
                    for (int i = 0; i < 42; i++) {
                        if (board[i] == ' ') {
                            full = false;
                            break;
                        }
                    }

                    if (full) {
                        System.out.println("no one won ( it's a tie )");
                        playingRn = false;
                        System.out.println("do u wanna play again(y/n)");
                        sc.nextLine();
                        String choice = sc.nextLine();
                        if (choice.equals("y")) {
                            playingRn = true;
                            for (int i = 0; i < 42; i++) {
                                board[i] = ' ';
                            }
                            currentPlayer = 'o';
                        } else {
                            System.out.println("farewell");
                        }
                    } else {
                        System.out.println("next turn!");
                       if(currentPlayer=='o'){
                           currentPlayer='x';

                       }else{
                           currentPlayer ='o';
                       }

                    }
                }
            }

        }
        //leader board with player points
        System.out.println("leader board");
        System.out.println("player 1"+player1win+"has points");
        System.out.println("player 2"+player2win+"has points");
        System.out.println("normal bot"+botWin+"has points");
        System.out.println("super bot"+neverloseBOtwin+"has points");
    }
}













