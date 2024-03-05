package fa.dfa;

import java.util.*;

import fa.DFAInterface;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // States (Q)
        sb.append("Q={");
        for (DFAState state : states) {
            sb.append(state.getName()).append(" ");
        }
        sb.setLength(sb.length() - 1); // Remove last space
        sb.append("}\n");

        // Alphabet (Sigma)
        sb.append("Sigma = {");
        for (char symbol : alphabet) {
            sb.append(symbol).append(" ");
        }
        sb.setLength(sb.length() - 1); // Remove last space
        sb.append("}\n");

        // Transition Function (delta)
        sb.append("delta =\n\t");
        for (char symbol : alphabet) {
            sb.append(symbol).append("\t");
        }
        sb.append("\n");
        for (DFAState state : states) {
            sb.append(state.getName()).append("\t");
            for (char symbol : alphabet) {
                DFAState nextState = state.getTransition(symbol);
                String nextStateName = nextState != null ? nextState.getName() : "null"; // Adjust based on how you want
                                                                                         // to handle null
                sb.append(nextStateName).append("\t");
            }
            sb.append("\n");
        }

        // Initial State (q0)
        sb.append("q0=").append(initialState).append("\n");

        // Final States (F)
        sb.append("F={");
        for (DFAState state : states) {
            if (state.isFinal()) {
                sb.append(state.getName()).append(" ");
            }
        }
        sb.setLength(sb.length() - 1); // Remove last space
        sb.append("}\n");

        return sb.toString().trim();
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
        DFA swappedDFA = new DFA();

        for (DFAState state : states) {
            swappedDFA.addState(state.getName());
            if (state.isFinal()) {
                swappedDFA.setFinal(state.getName());
            }
        }

        for (char symbol : alphabet) {
            swappedDFA.addSigma(symbol);
        }

        swappedDFA.setStart(initialState);
        for (DFAState state : states) {
            for (char symbol : alphabet) {
                DFAState nextState = state.getTransition(symbol);
                if (nextState != null) {
                    char swappedSymbol = symbol;
                    if (symbol == symb1) {
                        swappedSymbol = symb2;
                    } else if (symbol == symb2) {
                        swappedSymbol = symb1;
                    }
                    swappedDFA.addTransition(state.getName(), nextState.getName(), swappedSymbol);
                }
            }
        }

        return swappedDFA;
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
                finalStates.add(name);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        DFAState state = findState(name);
        if (state != null) {
            initialState = name;
            return true;
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        alphabet.add(symbol);

    }

    @Override
    public boolean accepts(String s) {
        DFAState currentState = findState(initialState);
        if (currentState == null) {
            return false; // Initial state is not set or doesn't exist
        }

        for (char symbol : s.toCharArray()) {
            if (!alphabet.contains(symbol) || currentState.getTransition(symbol) == null) {
                return false; // Symbol not in alphabet or no transition defined for this symbol
            }
            currentState = currentState.getTransition(symbol);
        }

        return currentState.isFinal();
    }

    @Override
    public Set<Character> getSigma() {
        return new LinkedHashSet<>(alphabet);
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
        DFAState state = findState(name);
        return state != null && state.isFinal();
    }

    @Override
    public boolean isStart(String name) {
        return name.equals(initialState);
    }

}
