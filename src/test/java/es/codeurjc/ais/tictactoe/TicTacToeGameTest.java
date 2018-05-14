package es.codeurjc.ais.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

public class TicTacToeGameTest {

    private TicTacToeGame game;
    private Player playerOne;
    private Player playerTwo;
    private Connection connectionPlayerOne;
    private Connection connectionPlayerTwo;

    @Before
    public void BeforeEach() {

        this.game = new TicTacToeGame();

        this.playerOne = new Player(1, "X", "player_one");
        this.playerTwo = new Player(2, "O", "player_two");

        this.connectionPlayerOne = mock(Connection.class);
        this.connectionPlayerTwo = mock(Connection.class);

        game.addConnection(connectionPlayerOne);
        game.addConnection(connectionPlayerTwo);

        game.addPlayer(playerOne);

        reset(connectionPlayerOne);
        reset(connectionPlayerTwo);

        game.addPlayer(playerTwo);
    }

    @Test
    public void testJoinGameEvent() {
        verify(connectionPlayerOne).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(playerOne, playerTwo)));
        verify(connectionPlayerTwo).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(playerOne, playerTwo)));
    }
}
