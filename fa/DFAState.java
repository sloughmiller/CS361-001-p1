package fa;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import fa.State;

public class DFAState extends State {
    private boolean isFinal;
    private Map<Character, DFAState> transitions;

    public DFAState(String name) {
        super(name);
        this.isFinal = false; // By default, a state is not final
        this.transitions = new HashMap<>();
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

    public DFAState getTransition(char symbol) {
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
