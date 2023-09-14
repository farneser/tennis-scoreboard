package tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.calculator.MatchCalculator;
import junit.framework.TestCase;

import java.util.UUID;

public class MatchCalculatorTests extends TestCase {
    private final MatchCalculator matchCalculator = new MatchCalculator();

    public void testFirstPlayerWin() {
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 3);

        for (var n = 0; n < 2; n++) {

            for (var i = 0; i < 16; i++) {
                matchCalculator.process(currentMatch, WinnerType.FirstPlayer);
            }

            for (var i = 0; i < 16; i++) {
                matchCalculator.process(currentMatch, WinnerType.SecondPlayer);
            }

            for (var i = 0; i < 8; i++) {
                matchCalculator.process(currentMatch, WinnerType.FirstPlayer);
            }
        }


        assert currentMatch.getWinnerPlayer() != null;
        assert currentMatch.getWinnerPlayer() == currentMatch.getFirstPlayer();
    }

    public void testSecondPlayerWin() {
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 3);

        for (var n = 0; n < 2; n++) {

            for (var i = 0; i < 16; i++) {
                matchCalculator.process(currentMatch, WinnerType.SecondPlayer);
            }

            for (var i = 0; i < 16; i++) {
                matchCalculator.process(currentMatch, WinnerType.FirstPlayer);
            }

            for (var i = 0; i < 8; i++) {
                matchCalculator.process(currentMatch, WinnerType.SecondPlayer);
            }
        }

        assert currentMatch.getWinnerPlayer() != null;
        assert currentMatch.getWinnerPlayer() == currentMatch.getSecondPlayer();
    }

}
