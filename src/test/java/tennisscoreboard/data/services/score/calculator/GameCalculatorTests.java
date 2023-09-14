package tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;
import com.farneser.tennisscoreboard.data.services.score.calculator.GameCalculator;
import junit.framework.TestCase;

import java.util.UUID;

public class GameCalculatorTests extends TestCase {

    private final GameCalculator gameCalculator = new GameCalculator();

    public void testFirstPlayerWin() {
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 3);

        State result;

        for (var i = 0; i < 3; i++) {
            result = gameCalculator.process(currentMatch, WinnerType.FirstPlayer);
            assertSame("score calculates", result, State.GameInProcess);
        }

        result = gameCalculator.process(currentMatch, WinnerType.FirstPlayer);

        assertSame("FirstPlayer wins", result, State.FirstPlayer);
    }

    public void testSecondPlayerWin() {
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 3);

        State result;

        for (var i = 0; i < 3; i++) {
            result = gameCalculator.process(currentMatch, WinnerType.SecondPlayer);
            assertSame("score calculates", result, State.GameInProcess);
        }

        result = gameCalculator.process(currentMatch, WinnerType.SecondPlayer);

        assertSame("SecondPlayer wins", result, State.SecondPlayer);
    }

    public void testAdvancedPlayersWin() {
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 3);

        State result;

        for (var i = 0; i < 3; i++) {
            result = gameCalculator.process(currentMatch, WinnerType.FirstPlayer);
            assertSame("score calculates", result, State.GameInProcess);

            result = gameCalculator.process(currentMatch, WinnerType.SecondPlayer);
            assertSame("score calculates", result, State.GameInProcess);
        }

        System.out.println("40:40");

        result = gameCalculator.process(currentMatch, WinnerType.FirstPlayer);
        assertSame("score wins", result, State.GameInProcess);

        System.out.println("AD:40");

        result = gameCalculator.process(currentMatch, WinnerType.SecondPlayer);
        assertSame("score wins", result, State.GameInProcess);

        System.out.println("40:40");

        assertSame("score tha same",
                currentMatch.getCurrentGame().getGameScore().getFirstPlayerScore(),
                currentMatch.getCurrentGame().getGameScore().getSecondPlayerScore());

        result = gameCalculator.process(currentMatch, WinnerType.SecondPlayer);
        assertSame("score wins", result, State.GameInProcess);

        System.out.println("40:AD");

        result = gameCalculator.process(currentMatch, WinnerType.FirstPlayer);
        assertSame("score wins", result, State.GameInProcess);

        System.out.println("40:40");

        assertSame("score tha same",
                currentMatch.getCurrentGame().getGameScore().getFirstPlayerScore(),
                currentMatch.getCurrentGame().getGameScore().getSecondPlayerScore());

        result = gameCalculator.process(currentMatch, WinnerType.FirstPlayer);
        assertSame("score wins", result, State.GameInProcess);

        System.out.println("AD:40");

        result = gameCalculator.process(currentMatch, WinnerType.FirstPlayer);
        assertSame("FirstPlayer wins", result, State.FirstPlayer);

        System.out.println("WIN:40");
    }
}
