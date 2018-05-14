package es.codeurjc.ais.tictactoe;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class TicTacToeGamePlayTest {

    private static Player playerOne = new Player(1, "X", "player_one");
    private static Player playerTwo = new Player(2, "O", "player_two");

    @Parameters
    public static Collection<Object[]> data () {
        Object[][] values = {
                { new int[] { 0,3,1,4,2}, playerOne},
                { new int[] { 0,3,1,4,8,5}, playerTwo },
                { new int[] { 4,0,7,1,2,6,3,5,8}, null},
                { new int[] { 0,3,1,4,7,6,5,2,8}, playerTwo}
        };

        return Arrays.asList(values);
    }

    @Parameter(0) public int[] moves;
    @Parameter(1) public Player winner;


    private TicTacToeGame game;
    private Connection connectionPlayerOne;
    private Connection connectionPlayerTwo;

    @Before
    public void BeforeEach() {

        this.game = new TicTacToeGame();
        this.connectionPlayerOne = mock(Connection.class);
        this.connectionPlayerTwo = mock(Connection.class);

        game.addConnection(connectionPlayerOne);
        game.addConnection(connectionPlayerTwo);

        game.addPlayer(playerOne);
        game.addPlayer(playerTwo);

        reset(connectionPlayerOne);
        reset(connectionPlayerTwo);
    }

    @Test
    public void testGamePlay() {

        for (int move : moves) {
          game.mark(move);
        }

        connectionCheckWinner(connectionPlayerOne);
        connectionCheckWinner(connectionPlayerTwo);
    }

    private void connectionCheckWinner(Connection connection) {
        ArgumentCaptor<TicTacToeGame.WinnerValue> argument = ArgumentCaptor.forClass(TicTacToeGame.WinnerValue.class);
        verify(connection).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), argument.capture());
        TicTacToeGame.WinnerValue event = argument.getValue();

        if( null == winner) {
            assertThat(event, is(nullValue()));
        } else {
            assertThat(event, is(notNullValue()) );
            assertThat(event.player, is(winner));
        }
    }
}
