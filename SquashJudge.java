import javax.swing.*;  
import java.awt.event.*;  
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.max;


public class SquashJudge {


    public static String playerOneName, playerTwoName, gameType;
    public static boolean validNames = true
    ;
    public static JFrame frame = new JFrame();

    public static int returnedValuePAR = 0;
    public static int returnedValueHIHO = 0;
    public static boolean PARGameDone = false;
    public static int playerOnePointsPAR = 0;
    public static int playerTwoPointsPAR = 0;
    public static int currentGamePAR = 1;
    public static int playerOneGamesWonPAR = 0;
    public static int playerTwoGamesWonPAR = 0;

    
    public static boolean HIHOGameDone = false;
    public static int playerOnePointsHIHO = 0;
    public static int playerTwoPointsHIHO = 0;
    public static String currentServer;
    public static int currentGameHIHO = 1;
    public static int playerOneGamesWonHIHO = 0;
    public static int playerTwoGamesWonHIHO = 0;

    public static List<List<String>> rowData = new ArrayList<List<String>>();
    public static List<String> tempList = new ArrayList<String>();
    public static int counter = 1;



    static void initialScreen() {
        final JTextField playerOneTF=new JTextField();
        final JTextField playerTwoTF=new JTextField();  
  
        JLabel playerOneLabel, playerTwoLabel, gameTypeLabel, errorLabel, welcomeMessage;

        welcomeMessage=new JLabel();  
        welcomeMessage.setBounds(55,48, 600,60);
        welcomeMessage.setText("Welcome to SquashJudge!");   
        welcomeMessage.setFont(new Font("Serif", Font.BOLD, 40));



        playerOneLabel=new JLabel();  
        playerOneLabel.setBounds(55,138, 250,20);
        playerOneLabel.setText("Player 1 Name");  
        playerOneTF.setBounds(150,140, 150,20);  

        playerTwoLabel=new JLabel();  
        playerTwoLabel.setBounds(325,138, 250,20);
        playerTwoLabel.setText("Player 2 Name");
        playerTwoTF.setBounds(420,140, 150,20);  

        errorLabel=new JLabel();  
        errorLabel.setBounds(55,160, 250,20);
        errorLabel.setText("Invalid names, try again.");

        gameTypeLabel=new JLabel();
        gameTypeLabel.setBounds(55,190, 250,20);
        gameTypeLabel.setText("Game Type"); 
        String modes[]={"Manual", "Random"};       
        frame.setTitle("SquashJudge"); 
        final JComboBox gameTypeCB=new JComboBox(modes); 
        gameTypeCB.setBounds(150, 190,90,20);    

        JButton button = new JButton("Confirm");
        button.setBounds(270,240,100, 40);

        frame.add(welcomeMessage);
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
                if(playerOneTF.getText().equals(playerTwoTF.getText()) || playerOneTF.getText().equals("") || playerTwoTF.getText().equals(""))
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
                    generateGame();
                } else {
                    errorLabel.setVisible(true);
                    validNames = true;
                }

            }
        });
    }

    static void generateGame() {
        JLabel playerNames;
        playerNames=new JLabel();  
        playerNames.setBounds(55,25, 600,60);
        playerNames.setText(playerOneName + " vs " + playerTwoName);   
        playerNames.setFont(new Font("Serif", Font.BOLD, 40));
        frame.add(playerNames);

        JLabel PARJudge;
        PARJudge=new JLabel();
        PARJudge.setBounds(55,90, 650,20);
        PARJudge.setText("");
        frame.add(PARJudge);
        PARJudge.setVisible(false);

        JLabel HIHOJudge;
        HIHOJudge=new JLabel();
        HIHOJudge.setBounds(55, 130, 650,20);
        HIHOJudge.setText("");
        frame.add(HIHOJudge);
        HIHOJudge.setVisible(false);

        Random rand = new Random();
        int pointWinner;
        int currentGame;
        

        int counter = 1;
        
        while (true) {

            if ((returnedValuePAR == -2 && returnedValueHIHO == -2)) break;

            pointWinner = rand.nextInt(2) + 1; // 1 or 2


            if(pointWinner == 1) {
                if (returnedValuePAR != -2 && !PARGameDone) returnedValuePAR = processPAR(playerOneName);
                
                if (returnedValueHIHO != -2 && !HIHOGameDone) returnedValueHIHO = processHIHO(playerOneName);
                    if((returnedValuePAR == 5) || (returnedValuePAR == 6)) {
                        returnedValuePAR = -2;
                        PARGameDone = true;
                        HIHOGameDone = false;
                        PARJudge.setText("According to PAR Judge, " + 
                        playerOneName + " won the match with " + 
                        Integer.toString(playerOneGamesWonPAR) + "-" + 
                        Integer.toString(playerTwoGamesWonPAR) +
                        " Games");
                        PARJudge.setVisible(true);
                    }
                    
                    if((returnedValueHIHO == 5) || (returnedValueHIHO == 6)) {
                        returnedValueHIHO = -2;
                        HIHOGameDone = true;
                        PARGameDone = false;
                        HIHOJudge.setText("According to HIHO Judge, " + 
                        playerOneName + " won the match with " + 
                        Integer.toString(playerOneGamesWonHIHO) + "-" + 
                        Integer.toString(playerTwoGamesWonHIHO) +
                        " Games");
                        HIHOJudge.setVisible(true);
                    } 

           } else {
                if (returnedValuePAR != -2 && !PARGameDone) returnedValuePAR = processPAR(playerTwoName);
                if (returnedValueHIHO != -2 && !HIHOGameDone) returnedValueHIHO = processHIHO(playerTwoName);

                    if((returnedValuePAR == 5) || (returnedValuePAR == 6)) {
                        returnedValuePAR = -2;
                        PARGameDone = true;
                        HIHOGameDone = false;
                        PARJudge.setText("According to PAR Judge, " + 
                        playerTwoName + " won the match with " + 
                        Integer.toString(playerOneGamesWonPAR) + "-" + 
                        Integer.toString(playerTwoGamesWonPAR) +
                        " Games");
                        PARJudge.setVisible(true);
                    }

                    if((returnedValueHIHO == 5) || (returnedValueHIHO == 6)) {
                        returnedValueHIHO = -2;
                        HIHOGameDone = true;
                        PARGameDone = false;
                        HIHOJudge.setText("According to HIHO Judge, " + 
                        playerTwoName + " won the match with " + 
                        Integer.toString(playerOneGamesWonHIHO) + "-" + 
                        Integer.toString(playerTwoGamesWonHIHO) +
                        " Games");
                        HIHOJudge.setVisible(true);
                    }
              
            
           }

           currentGame = max(currentGamePAR, currentGameHIHO);
           tempList.add(Integer.toString(currentGame));
           
           tempList.add(Integer.toString(counter));


           if (pointWinner == 1) {
                tempList.add("W");
                tempList.add("L");
              
           } else {
                tempList.add("L");
                tempList.add("W");
           }

           rowData.add(tempList);
           tempList = new ArrayList<String>();
           counter++;
           

    }


        // the rows entry in the JTable element takes a regular 2D array. This is a conversion from the dynamic 2D ArrayList to a regular 2D array
        String[][] gameData = rowData.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
         
        String column[]={"Game #","Round #",playerOneName, playerTwoName};         
        JTable randomGameTable=new JTable(gameData,column);    
        JScrollPane scrollPane=new JScrollPane(randomGameTable);  
        scrollPane.setBounds(30,180,600,400);
        frame.add(scrollPane);          
        
       


    }

    static void getInput(int pointCounter) {
        final JTextField pointWinnerTF=new JTextField();  
        pointWinnerTF.setBounds(160,150, 150,20); 
        frame.add(pointWinnerTF);

        JLabel playerNames;
        playerNames=new JLabel();  
        playerNames.setBounds(55,48, 600,60);
        playerNames.setText(playerOneName + " vs " + playerTwoName);   
        playerNames.setFont(new Font("Serif", Font.BOLD, 40));
        frame.add(playerNames);


        JButton button = new JButton("Next");
        button.setBounds(270,200,100, 40);
        frame.add(button);

        JLabel pointWinnerPrompt;
        pointWinnerPrompt=new JLabel();  
        pointWinnerPrompt.setBounds(55, 148, 250,20);
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
        scoreTable.setBounds(90,290,500,30);
        frame.add(scoreTable);    
        
        String titleColumn[]={"Scoring Method","Game #","Overall Score", "Game Score"};     
        String titles[][]={ {"Scoring Method","Game #","Overall Score", "Game Score"}, };    
        JTable titlesTable=new JTable(titles, titleColumn);    
        titlesTable.setBounds(90,275,500,15);
        frame.add(titlesTable);    
        
        JLabel errorLabel;
        errorLabel=new JLabel();
        errorLabel.setBounds(55,170, 250,20);
        errorLabel.setText("Invalid player name, try again.");
        frame.add(errorLabel);
        errorLabel.setVisible(false);

        JLabel PARJudge;
        PARJudge=new JLabel();
        PARJudge.setBounds(55,350, 700,20);
        PARJudge.setText("");
        frame.add(PARJudge);
        PARJudge.setVisible(false);

        JLabel HIHOJudge;
        HIHOJudge=new JLabel();
        HIHOJudge.setBounds(55, 400, 700,20);
        HIHOJudge.setText("");
        frame.add(HIHOJudge);
        HIHOJudge.setVisible(false);


        pointWinnerPrompt.setText("Point " + Integer.toString(pointCounter) + " winner: ");

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (playerOneName.equals(pointWinnerTF.getText())) {

                    if (returnedValuePAR != -2 && !PARGameDone)
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

                    if (returnedValueHIHO != -2 && !HIHOGameDone) {
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
                    if (returnedValuePAR != -2 && !PARGameDone)
                    {
                        returnedValuePAR = processPAR(playerTwoName);
                        if ((returnedValuePAR == 5) || (returnedValuePAR == 6)) {
                            returnedValuePAR = -2; // PAR game has ended
                            PARJudge.setText("According to PAR Judge, " + 
                            playerTwoName + " won the match with " + 
                            Integer.toString(playerOneGamesWonPAR) + "-" + 
                            Integer.toString(playerTwoGamesWonPAR) +
                            " Games");
                            PARJudge.setVisible(true);
                        }
                    }
                    if (returnedValueHIHO != -2 && !HIHOGameDone) {
                        returnedValueHIHO = processHIHO(playerTwoName);
                        if ((returnedValueHIHO == 5) || (returnedValueHIHO == 6)) {
                            returnedValueHIHO = -2;
                            HIHOJudge.setText("According to HIHO Judge, " + 
                            playerTwoName + " won the match with " + 
                            Integer.toString(playerOneGamesWonHIHO) + "-" + 
                            Integer.toString(playerTwoGamesWonHIHO) +
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
                    frame.remove(playerNames);
                    errorLabel.setVisible(false);  
                    counter++;     
                    getInput(pointCounter + 1);
                }
            }

            int prevP1PointsPAR = playerOnePointsPAR;
            int prevP2PointsPAR = playerTwoGamesWonPAR;

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
                playerOneGamesWonPAR += 1;
                if (playerOneGamesWonPAR == 3) return 5; // player one won whole match
                currentGamePAR += 1;
                playerOnePointsPAR = 0;
                playerTwoPointsPAR = 0;
                if (!HIHOGameDone) PARGameDone = true;
                else {
                    PARGameDone = false;
                    HIHOGameDone = false;
                }
                return 3; // player 1 won this game
            } 
        } else if(playerTwoPointsPAR == 11) {
            if(playerOnePointsPAR < 10) {
                playerTwoGamesWonPAR += 1;
                if (playerTwoGamesWonPAR == 3) return 6; // player two won whole match
                currentGamePAR += 1;
                playerOnePointsPAR = 0;
                playerTwoPointsPAR = 0;
                if (!HIHOGameDone) PARGameDone = true;
                else {
                    PARGameDone = false;
                    HIHOGameDone = false;
                }
                return 4; // player 2 won this game
            } 
            
        } 
        
        if (playerOnePointsPAR > 11) {
            if (playerOnePointsPAR - playerTwoPointsPAR == 2) {
                playerOneGamesWonPAR += 1;
                if (playerOneGamesWonPAR == 3) return 5; // player one won whole match
                currentGamePAR += 1;
                playerOnePointsPAR = 0;
                playerTwoPointsPAR = 0;
                if (!HIHOGameDone) PARGameDone = true;
                else {
                    PARGameDone = false;
                    HIHOGameDone = false;
                }
                return 3; // player 1 won this game
            } else if (playerOnePointsPAR - playerTwoPointsPAR == 1) {
                return 1; 
            }
        }
        if (playerTwoPointsPAR > 11) {
            if (playerTwoPointsPAR - playerOnePointsPAR == 2) {
                playerTwoGamesWonPAR += 1;
                if (playerTwoGamesWonPAR == 3) return 6; // player two won whole match
                currentGamePAR += 1;
                playerOnePointsPAR = 0;
                playerTwoPointsPAR = 0;
                if (!HIHOGameDone) PARGameDone = true;
                else {
                    PARGameDone = false;
                    HIHOGameDone = false;
                }
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
            playerOneGamesWonHIHO += 1;
            if (playerOneGamesWonHIHO == 3) return 5; // player one won the whole match
            currentGameHIHO += 1;
            playerOnePointsHIHO = 0;
            playerTwoPointsHIHO = 0;
            currentServer = "";
            if (!PARGameDone) HIHOGameDone = true;
                else {
                    PARGameDone = false;
                    HIHOGameDone = false;
                }
            return 3; // player one won this game
        } else if (playerTwoPointsHIHO == 9) {
            playerTwoGamesWonHIHO += 1;
            if (playerTwoGamesWonHIHO == 3) return 6; // player two won the whole match
        
            currentGameHIHO += 1;
            playerOnePointsHIHO = 0;
            playerTwoPointsHIHO = 0;
            currentServer = "";
            if (!PARGameDone) HIHOGameDone = true;
                else {
                    PARGameDone = false;
                    HIHOGameDone = false;
                }
            return 4; // player one won this game
        }

        return 0;
    }
    public static void main(String[] args) {
        
        initialScreen();
        
       
        

    }
}