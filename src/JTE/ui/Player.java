/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTE.ui;

/**
 *
 * @author peng
 */
public class Player {
    private String PlayerName;
    private String StartCity;
    private String EndCity;
    private String PlayerColor;
    
    private int DiceMoves;
    private int CardMoves;
    private int totalMoves;
    //Player(player1 , random.math to get a city ,red,0,0,0)
    public Player(String PlayerName,String StartCity,String EndCity,String PlayerColor,int DiceMoves, int CardMoves, int totalMoves){
    this.PlayerName=PlayerName;
    this.StartCity=StartCity;
    this.EndCity=EndCity;
    this.PlayerColor=PlayerColor;
    this.DiceMoves=DiceMoves;
    this.CardMoves=CardMoves;
    this.totalMoves=totalMoves;
    
    }
}
