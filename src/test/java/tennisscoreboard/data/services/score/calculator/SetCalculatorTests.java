package tennisscoreboard.data.services.score.calculator;

import com.farneser.tennisscoreboard.data.entities.Player;
import com.farneser.tennisscoreboard.data.entities.dto.WinnerType;
import com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch;
import com.farneser.tennisscoreboard.data.services.score.State;
import com.farneser.tennisscoreboard.data.services.score.calculator.SetCalculator;
import junit.framework.TestCase;

import java.util.UUID;

public class SetCalculatorTests extends TestCase {

    private final SetCalculator setCalculator = new SetCalculator();

    public void testFirstPlayerWin() {
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 5);


        for (var i = 0; i < 5; i++) {
            var result = setCalculator.process(currentMatch, WinnerType.FirstPlayer);

            System.out.println(result);
            assert result == State.GameInProcess;
        }

        assert setCalculator.process(currentMatch, WinnerType.FirstPlayer) == State.FirstPlayer;
    }

    public void testSecondPlayerWin() {
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 5);


        for (var i = 0; i < 5; i++) {
            var result = setCalculator.process(currentMatch, WinnerType.SecondPlayer);

            System.out.println(result);
            assert result == State.GameInProcess;
        }

        assert setCalculator.process(currentMatch, WinnerType.SecondPlayer) == State.SecondPlayer;
    }

    public void testAdvancedGame(){
        var currentMatch = new CurrentMatch(UUID.randomUUID(), new Player(), new Player(), 5);


        for (var i = 0; i < 5; i++) {
            var result = setCalculator.process(currentMatch, WinnerType.FirstPlayer);
            System.out.println(result);
            assert result == State.GameInProcess;

            result = setCalculator.process(currentMatch, WinnerType.SecondPlayer);
            System.out.println(result);
            assert result == State.GameInProcess;

        }

        assert setCalculator.process(currentMatch, WinnerType.FirstPlayer) == State.GameInProcess;
        assert setCalculator.process(currentMatch, WinnerType.SecondPlayer) == State.GameInProcess;

        assert setCalculator.process(currentMatch, WinnerType.FirstPlayer) == State.FirstPlayer;
    }
}
