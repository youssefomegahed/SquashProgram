import javax.swing.*;  
import java.awt.event.*;  

public class SquashJudge {

    public static String playerOneName, playerTwoName, gameType;
    public static boolean validNames = true;
    public static JFrame frame = new JFrame();
    public static int returnedValuePAR = 0;
    public static int returnedValueHIHO = 0;
    public static int playerOnePointsPAR = 0;
    public static int playerTwoPointsPAR = 0;
    public static int currentGamePAR = 1;
    public static int playerOneGamesWonPAR = 0;
    public static int playerTwoGamesWonPAR = 0;

    

    public static int playerOnePointsHIHO = 0;
    public static int playerTwoPointsHIHO = 0;
    public static String currentServer;
    public static int currentGameHIHO = 1;
    public static int playerOneGamesWonHIHO = 0;
    public static int playerTwoGamesWonHIHO = 0;




    static void initialScreen() {
        final JTextField playerOneTF=new JTextField();
        final JTextField playerTwoTF=new JTextField();  
  
        JLabel playerOneLabel, playerTwoLabel, gameTypeLabel, errorLabel;

        playerOneLabel=new JLabel();  
        playerOneLabel.setBounds(55,48, 250,20);
        playerOneLabel.setText("Player 1 Name");    
        playerOneTF.setBounds(150,50, 150,20);  

        playerTwoLabel=new JLabel();  
        playerTwoLabel.setBounds(325,48, 250,20);
        playerTwoLabel.setText("Player 2 Name");
        playerTwoTF.setBounds(420,50, 150,20);  

        errorLabel=new JLabel();  
        errorLabel.setBounds(55,70, 250,20);
        errorLabel.setText("Invalid names, try again.");

        gameTypeLabel=new JLabel();
        gameTypeLabel.setBounds(55,100, 250,20);
        gameTypeLabel.setText("Game Type"); 
        String modes[]={"Manual", "Random"};        
        final JComboBox gameTypeCB=new JComboBox(modes); 
        gameTypeCB.setBounds(150, 100,90,20);    

        JButton button = new JButton("Confirm");
        button.setBounds(270,150,100, 40);

        frame.add(playerOneLabel);
        frame.add(playerTwoLabel);
        frame.add(playerOneTF);
        frame.add(playerTwoTF);
        frame.add(gameTypeLabel);
        frame.add(gameTypeCB);
        frame.add(button);
        frame.add(errorLabel);
        errorLabel.setVisible(false);
        frame.setSize(720,720);
        frame.setLayout(null);
        frame.setVisible(true);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                playerOneName = playerOneTF.getText();
                playerTwoName = playerTwoTF.getText();
                if(playerOneTF.getText().equals(playerTwoTF.getText())) 
                {
                    validNames = false;
                }
                gameType = "" + gameTypeCB.getItemAt(gameTypeCB.getSelectedIndex()); 
        
                if ((gameTypeCB.getItemAt(gameTypeCB.getSelectedIndex()) == gameTypeCB.getItemAt(0)) && validNames == true) {
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    getInput(1);
                } else if ((gameTypeCB.getItemAt(gameTypeCB.getSelectedIndex()) == gameTypeCB.getItemAt(1)) && validNames == true) {
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    // generateGame();
                } else {
                    errorLabel.setVisible(true);
                    validNames = true;
                }

            }
        });
    }
    static void getInput(int pointCounter) {
        final JTextField pointWinnerTF=new JTextField();  
        pointWinnerTF.setBounds(150,50, 150,20); 
        frame.add(pointWinnerTF);

        JButton button = new JButton("Next");
        button.setBounds(270,150,100, 40);
        frame.add(button);

        JLabel pointWinnerPrompt;
        pointWinnerPrompt=new JLabel();  
        pointWinnerPrompt.setBounds(55,48, 250,20);
        frame.add(pointWinnerPrompt);


        String scores[][]={ {"PAR", Integer.toString(currentGamePAR),
                            Integer.toString(playerOneGamesWonPAR) + "-" + Integer.toString(playerTwoGamesWonPAR),
                            Integer.toString(playerOnePointsPAR) + "-" + Integer.toString(playerTwoPointsPAR)
                            },   
                            {"HIHO", Integer.toString(currentGameHIHO),
                            Integer.toString(playerOneGamesWonHIHO) + "-" + Integer.toString(playerTwoGamesWonHIHO),
                            Integer.toString(playerOnePointsHIHO) + "-" + Integer.toString(playerTwoPointsHIHO)},    
                         };

        String column[]={"Scoring Method","Game #","Overall Score", "Game Score"};         
        JTable scoreTable=new JTable(scores, column);    
        scoreTable.setBounds(30,240,500,30);
        frame.add(scoreTable);    
        
        String titleColumn[]={"Scoring Method","Game #","Overall Score", "Game Score"};     
        String titles[][]={ {"Scoring Method","Game #","Overall Score", "Game Score"}, };    
        JTable titlesTable=new JTable(titles, titleColumn);    
        titlesTable.setBounds(30,225,500,15);
        frame.add(titlesTable);    
        
        JLabel errorLabel;
        errorLabel=new JLabel();
        errorLabel.setBounds(55,70, 250,20);
        errorLabel.setText("Invalid player name, try again.");
        frame.add(errorLabel);
        errorLabel.setVisible(false);

        JLabel PARJudge;
        PARJudge=new JLabel();
        PARJudge.setBounds(55,300, 700,20);
        PARJudge.setText("");
        frame.add(PARJudge);
        PARJudge.setVisible(false);

        JLabel HIHOJudge;
        HIHOJudge=new JLabel();
        HIHOJudge.setBounds(55,350, 700,20);
        HIHOJudge.setText("");
        frame.add(HIHOJudge);
        HIHOJudge.setVisible(false);


        pointWinnerPrompt.setText("Point " + Integer.toString(pointCounter) + " winner: ");

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (playerOneName.equals(pointWinnerTF.getText())) {

                    if (returnedValuePAR != -2)
                    {
                        returnedValuePAR = processPAR(playerOneName);
                        if ((returnedValuePAR == 5) || (returnedValuePAR == 6)) {
                            returnedValuePAR = -2;
                            PARJudge.setText("According to PAR Judge, " + 
                            playerOneName + " won the match with " + 
                            Integer.toString(playerOneGamesWonPAR) + "-" + 
                            Integer.toString(playerTwoGamesWonPAR) +
                            " Games");
                            PARJudge.setVisible(true);
                        }
                    }

                    if (returnedValueHIHO != -2) {
                        returnedValueHIHO = processHIHO(playerOneName);
                        if ((returnedValueHIHO == 5) || (returnedValueHIHO == 6)) {
                            returnedValueHIHO = -2;
                            HIHOJudge.setText("According to HIHO Judge, " + 
                            playerOneName + " won the match with " + 
                            Integer.toString(playerOneGamesWonHIHO) + "-" + 
                            Integer.toString(playerTwoGamesWonHIHO) +
                            " Games");
                            HIHOJudge.setVisible(true);
                        }
                    }
                } else if (playerTwoName.equals(pointWinnerTF.getText())) {
                    if (returnedValuePAR != -2)
                    {
                        returnedValuePAR = processPAR(playerTwoName);
                        if ((returnedValuePAR == 5) || (returnedValuePAR == 6)) {
                            returnedValuePAR = -2; // PAR game has ended
                            PARJudge.setText("According to PAR Judge, " + 
                            playerTwoName + " won the match with " + 
                            Integer.toString(playerTwoGamesWonPAR) + "-" + 
                            Integer.toString(playerOneGamesWonPAR) +
                            " Games");
                            PARJudge.setVisible(true);
                        }
                    }
                    if (returnedValueHIHO != -2) {
                        returnedValueHIHO = processHIHO(playerTwoName);
                        if ((returnedValueHIHO == 5) || (returnedValueHIHO == 6)) {
                            returnedValueHIHO = -2;
                            HIHOJudge.setText("According to HIHO Judge, " + 
                            playerTwoName + " won the match with " + 
                            Integer.toString(playerTwoGamesWonHIHO) + "-" + 
                            Integer.toString(playerOneGamesWonHIHO) +
                            " Games");
                            HIHOJudge.setVisible(true);
                        }
                    }
                } else {
                    if (returnedValuePAR != -2) {
                        returnedValuePAR = -1;
                    } else returnedValueHIHO = -1;
                }

                if ((returnedValuePAR == -1) || (returnedValueHIHO == -1)) {
                    errorLabel.setVisible(true);
                } else if ((returnedValuePAR == -2) && (returnedValueHIHO == -2)){
                    frame.remove(pointWinnerPrompt);
                    frame.remove(pointWinnerTF);
                    frame.remove(button);
        
                } else { //can do processing on each returned value later for printing
                    pointWinnerTF.setText("");
                    frame.remove(pointWinnerPrompt);
                    frame.remove(pointWinnerTF);
                    frame.remove(button);        
                    frame.remove(scoreTable);
                    frame.remove(titlesTable);
                    errorLabel.setVisible(false);       
                    getInput(pointCounter + 1);
                }
            }

        });


        



    }

    static int processPAR(String pointWinnerName) {
        if (pointWinnerName == playerOneName) {
            playerOnePointsPAR += 1;
            if (playerOnePointsPAR < 11)
                return 1; // player 1 won this point
        }
        else if (pointWinnerName == playerTwoName) {
            playerTwoPointsPAR += 1;
            if (playerTwoPointsPAR < 11)
                return 2; // player 2 won this point
        }

        if(playerOnePointsPAR == 11) {
            if(playerTwoPointsPAR < 10) {
                currentGamePAR += 1;
                playerOneGamesWonPAR += 1;
                if (playerOneGamesWonPAR == 3) return 5; // player one won whole match
                playerOnePointsPAR = 0;
                playerTwoPointsPAR = 0;
                return 3; // player 1 won this game
            } 
        } else if(playerTwoPointsPAR == 11) {
            if(playerOnePointsPAR < 10) {
                currentGamePAR += 1;
                playerTwoGamesWonPAR += 1;
                if (playerTwoGamesWonPAR == 3) return 6; // player two won whole match
                playerOnePointsPAR = 0;
                playerTwoPointsPAR = 0;
                return 4; // player 2 won this game
            } 
            
        } 
        
        if (playerOnePointsPAR > 11) {
            if (playerOnePointsPAR - playerTwoPointsPAR == 2) {
                currentGamePAR += 1;
                playerOneGamesWonPAR += 1;
                if (playerOneGamesWonPAR == 3) return 5; // player one won whole match
                playerOnePointsPAR = 0;
                playerTwoPointsPAR = 0;
                return 3; // player 1 won this game
            } else if (playerOnePointsPAR - playerTwoPointsPAR == 1) {
                return 1; 
            }
        }
        if (playerTwoPointsPAR > 11) {
            if (playerTwoPointsPAR - playerOnePointsPAR == 2) {
                currentGamePAR += 1;
                playerTwoGamesWonPAR += 1;
                if (playerTwoGamesWonPAR == 3) return 6; // player two won whole match
                playerOnePointsPAR = 0;
                playerTwoPointsPAR = 0;
                return 4; // player 2 won this game
            } else if (playerTwoPointsPAR - playerOnePointsPAR == 1) {
                return 2; 
            }
        }

        return 0;
        
    }

    static int processHIHO(String pointWinnerName) {
        if ((pointWinnerName == playerOneName) && (currentServer == playerOneName)) {
            playerOnePointsHIHO += 1;
            if (playerOnePointsHIHO < 9)
                return 1; // player 1 won this point
        } else if ((pointWinnerName == playerOneName) && (currentServer != playerOneName)) {
            currentServer = playerOneName;
            return 0; // no one won this point
        }
        else if ((pointWinnerName == playerTwoName) && (currentServer == playerTwoName)) {
            playerTwoPointsHIHO += 1;
            if (playerTwoPointsHIHO < 9)
                return 2; // player 2 won this point
        } else if ((pointWinnerName == playerTwoName) && (currentServer != playerTwoName)) {
            currentServer = playerTwoName;
            return 0; // no one won this point
        }

        if (playerOnePointsHIHO == 9) {
            currentGameHIHO += 1;
            playerOneGamesWonHIHO += 1;
            if (playerOneGamesWonHIHO == 3) return 5; // player one won the whole match
            playerOnePointsHIHO = 0;
            playerTwoPointsHIHO = 0;
            currentServer = "";
            return 3; // player one won this game
        } else if (playerTwoPointsHIHO == 9) {
            currentGameHIHO += 1;
            playerTwoGamesWonHIHO += 1;
            if (playerTwoGamesWonHIHO == 3) return 6; // player two won the whole match
            playerOnePointsHIHO = 0;
            playerTwoPointsHIHO = 0;
            currentServer = "";
            return 4; // player one won this game
        }

        return 0;
    }
    public static void main(String[] args) {
        
        initialScreen();
        
       
        

    }
}