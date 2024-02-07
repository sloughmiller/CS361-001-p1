package fa.dfa;

import java.util.*;
import fa.State;

public class DFA implements DFAInterface {

    private Set<String> states;
    private Set<Character> alphabet;
    private Map<String, Map<Character, String>> transitionFunction;
    private String initialState;
    private Set<String> finalStates;

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

    public boolean addTransition(String fromState, String toState, char onSymb) {
        return false;
    }

    public DFA swap(char symb1, char symb2) {
        return null;
    }

    @Override
    public boolean addState(String name) {

        if (states.contains(name)) {
            return false;
        } else {
            states.add(name);
            transitionFunction.put(name, new LinkedHashMap<>());
            return true;
        }
    }

    @Override
    public boolean setFinal(String name) {
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
