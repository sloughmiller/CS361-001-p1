
package fa;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author: Laura and Sterling Loughmiller
 *          Represents a state in a DFA. Each state can have a transition to
 *          another state based on input symbols.
 * 
 * @param name
 */

public class DFAState extends State {
    private boolean isFinal;
    private Map<Character, DFAState> transitions;

    /**
     * Constructs a new DFAState with the given name.
     * By default, the state is not final and has no transitions.
     * 
     * @param name //name of the state
     */
    public DFAState(String name) {
        super(name);
        this.isFinal = false; // By default, a state is not final
        this.transitions = new LinkedHashMap<>();
    }

    /**
     * Checks if the state is final
     * 
     * @return true if the stat is final, false otherwise
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Sets the state to be final or not.
     * 
     * @param isFinal true if the state is final, false otherwise
     */
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * Adds a transition from this state to another
     * 
     * @param symbol    the input symbol for the transition
     * @param nextState the state to transition to
     */
    public void addTransition(char symbol, DFAState nextState) {
        transitions.put(symbol, nextState);
    }

    /**
     * @param symbol the input symbol
     * @returns the next state given a symbol
     * @throws NoSuchElementException if no transition for the symbol
     */
    public DFAState getTransition(char symbol) {
        if (!transitions.containsKey(symbol)) {
            throw new NoSuchElementException("No transition for symbol: " + symbol);
        }
        return transitions.get(symbol);
    }

    /**
     * Checks if the DFAState is equal to another object
     * Two DFAStates are equal if they have the same final status and transitions
     * 
     * @param o the object to compare this DFAState against
     * @return true if the given object represents a DFAState equivlant to this
     *         DFAState, false otherwise
     */
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

    /**
     * Returns a hash code value for this DFAState
     * The has code is based on the name of the stat, it's final staus and it's
     * transitions
     * 
     * @return a hash code value for this DFAState
     */
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (isFinal ? 1 : 0);
        result = 31 * result + (transitions != null ? transitions.hashCode() : 0);
        return result;

    }

}
