package fa;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class DFAState extends State {
    private boolean isFinal;
    private Map<Character, DFAState> transitions;

    public DFAState(String name) {
        super(name);
        this.isFinal = false; // By default, a state is not final
        this.transitions = new LinkedHashMap<>();
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public void addTransition(char symbol, DFAState nextState) {
        transitions.put(symbol, nextState);
    }

    /**
     * Returns the next state given a symbol
     * throws NoSuchElementException if no transition for the symbol
     */
    public DFAState getTransition(char symbol) {
        if (!transitions.containsKey(symbol)) {
            throw new NoSuchElementException("No transition for symbol: " + symbol);
        }
        return transitions.get(symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DFAState dfaState = (DFAState) o;

        if (isFinal != dfaState.isFinal)
            return false;
        return transitions != null ? transitions.equals(dfaState.transitions) : dfaState.transitions == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (isFinal ? 1 : 0);
        result = 31 * result + (transitions != null ? transitions.hashCode() : 0);
        return result;

    }

}
