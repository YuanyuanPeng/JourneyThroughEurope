package JTE.game;

import java.util.ArrayList;
import java.util.Iterator;

import JTE.ui.JourneyThroughEuropeUI;

/**
 *
 * @author peng
 */
public class JourneyThroughEuropeGameStateManager {

// Game will be in one of these 
    public enum JourneyThroughEuropeState {

        GAME_NOT_STARTED, GAME_IN_PROGRESS, GAME_OVER, GAME_LOADING,
    }
    //Store the current state of this game
    private JourneyThroughEuropeState currentState;
    //Reffernce to  the UI
    private JourneyThroughEuropeUI ui;
    //This is the game currently being played
    private JourneyThroughEuropeGameData gameInProgress;

    public ArrayList<JourneyThroughEuropeGameData> gameHistory;
    private ArrayList<String> cityInfo;

    public JourneyThroughEuropeGameStateManager(JourneyThroughEuropeUI initUI) {
        ui = initUI;
        //We haven't start a game yet
        currentState = JourneyThroughEuropeState.GAME_NOT_STARTED;
   //Init the data sturcture for the placing completed games
        //gamesHistory=new ArrayList();
        gameInProgress = null;

    }

    public JourneyThroughEuropeGameData getGameInProgress() {
        return gameInProgress;
    }

    public Iterator<JourneyThroughEuropeGameData> getGameHistoryIterator() {
        return gameHistory.iterator();

    }

    /**
     * Accessor method for testing to see if the current game is over.
     *
     * @return true if the game has not started, false otherwise.
     */
    public boolean isGameNotStarted() {
        return currentState == JourneyThroughEuropeState.GAME_NOT_STARTED;
    }

    /**
     * Accessor method for testing to see if the current game is over.
     *
     * @return true if the game in progress has completed, false otherwise.
     */
    public boolean isGameOver() {
        return currentState == JourneyThroughEuropeState.GAME_OVER;
    }

    /**
     * Accessor method for testing to see if the current game is over.
     *
     * @return true if the game is in progress, false otherwise.
     */
    public boolean isGameInProgress() {
        return currentState == JourneyThroughEuropeState.GAME_IN_PROGRESS;
    }

    /**
     * Accessor method for testing to see if the current game is over.
     *
     * @return true if the game is loading the history, false otherwise.
     */
    public boolean isGameLoading() {
        return currentState == JourneyThroughEuropeState.GAME_LOADING;
    }

    /**
     * This method starts a new game, initializing all the necessary data for
     * that new game as well as recording the current game (if it exists) in the
     * games history data structure. It also lets the user interface know about
     * this change of state such that it may reflect this change.
     */
    public void startNewGame() {
        // IS THERE A GAME ALREADY UNDERWAY?
        // YES, SO END THAT GAME AS A LOSS
        if (!isGameNotStarted() && (!gameHistory.contains(gameInProgress))) {
            gameHistory.add(gameInProgress);
        }

        makeNewGame();

        // AND MAKE SURE THE UI REFLECTS A NEW GAME
    }

    private void makeNewGame() {
        System.out.println("make New Game");

    }

}
