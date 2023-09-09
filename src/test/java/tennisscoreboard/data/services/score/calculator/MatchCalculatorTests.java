package tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.PlayersScore;
import com.farneser.tennisscoreboard.data.services.score.calculator.MatchCalculator;
import junit.framework.TestCase;

import java.util.UUID;

public class MatchCalculatorTests extends TestCase {
    private final MatchCalculator matchCalculator = new MatchCalculator();

    public void testFirstPlayerWin() {
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 3);


        for (var i = 0; i < 2; i++) {
            var playerScore = new PlayersScore<Integer>();

            playerScore.setFirstPlayerScore(6);
            playerScore.setSecondPlayerScore(4);

            currentMatch.setCurrentSet(playerScore);
            currentMatch.refreshCurrentSet();

            var result = matchCalculator.process(currentMatch, WinnerType.FirstPlayer);

            System.out.println(result);
        }

        assert currentMatch.getWinnerPlayer() != null;
        assert currentMatch.getWinnerPlayer() == currentMatch.getFirstPlayer();
    }

    public void testSecondPlayerWin() {
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 5);


        for (var i = 0; i < 3; i++) {
            var playerScore = new PlayersScore<Integer>();

            playerScore.setFirstPlayerScore(3);
            playerScore.setSecondPlayerScore(6);

            currentMatch.setCurrentSet(playerScore);
            currentMatch.refreshCurrentSet();

            var result = matchCalculator.process(currentMatch, WinnerType.SecondPlayer);

            System.out.println(result);
        }

        assert currentMatch.getWinnerPlayer() != null;
        assert currentMatch.getWinnerPlayer() == currentMatch.getSecondPlayer();
    }
}
