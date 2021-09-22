package app.logic.logic.core.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.TreeSet;

public class EquationErrorListener extends BaseErrorListener {

    private TreeSet<Integer> errorPositions;

    public TreeSet<Integer> getErrorPositions() {
        return errorPositions;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {
        throw new ParseCancellationException();
    }

}
