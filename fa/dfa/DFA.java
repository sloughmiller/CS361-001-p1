package fa.dfa;

import java.util.*;

import fa.DFAState;
import fa.State;

public class DFA implements DFAInterface {

    private Set<Character> alphabet;
    private Map<String, Map<Character, String>> transitionFunction;
    private String initialState;
    private Set<String> finalStates;
    private Set<DFAState> states;

    /**
     * Constructor that sets the number of states to 0, the alphabet to an empty
     * set, the initial state to null, and the set of final states to an empty set.
     */
    public DFA() {
        states = new LinkedHashSet<>();
        alphabet = new LinkedHashSet<>();
        transitionFunction = new LinkedHashMap<>();
        finalStates = new LinkedHashSet<>();
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Q = { ");
        sb.append("}\nSigma = { ");
        sb.append("}\ndelta =\n");
        sb.append("q0 = ");
        sb.append("\nF = { ");

        sb.append(" }");
        return sb.toString();
    }

    /**
     * Adds a transition to the DFA.
     * 
     */
    public boolean addTransition(String fromStateName, String toStateName, char onSymb) {
        if (!alphabet.contains(onSymb)) {
            return false;
        }

        DFAState fromStateObj = findState(fromStateName);
        DFAState toStateObj = findState(toStateName);

        if (fromStateObj == null || toStateObj == null) {
            return false;
        }

        fromStateObj.addTransition(onSymb, toStateObj);
        return true;
    }

    /**
     * Helper method to find a state by its name.
     */
    private DFAState findState(String name) {
        for (DFAState state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    public DFA swap(char symb1, char symb2) {
        return null;
    }

    @Override
    public boolean addState(String name) {
        DFAState newState = new DFAState(name);
        if (states.contains(newState)) {
            return false;
        } else {
            states.add(newState);
            transitionFunction.put(name, new LinkedHashMap<>());
            return true;
        }
    }

    @Override
    public boolean setFinal(String name) {
        for (DFAState state : states) {
            if (state.getName().equals(name)) {
                state.setFinal(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        return false;
    }

    @Override
    public void addSigma(char symbol) {

    }

    @Override
    public boolean accepts(String s) {
        return false;
    }

    @Override
    public Set<Character> getSigma() {
        return null;
    }

    @Override
    public State getState(String name) {
        for (DFAState state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        return false;
    }

    @Override
    public boolean isStart(String name) {
        return false;
    }

}
