
package fa;

public abstract class State {
	/**
	 * @author Laura and Sterling Loughmiller
	 *         The state label.
	 *         It should be a unique name set by
	 *         the concrete class constructor.
	 * 
	 * @author elenasherman
	 */
	private String name;

	public State() {

	}

	/**
	 * All concrete consturctors must
	 * invoke this one as super(name).
	 * This way name instance variable is
	 * correctly set.
	 */
	public State(String name) {
		this.name = name;
	}

	/**
	 * getter for the string label
	 * 
	 * @return returns the state label.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method overrides the toString method in the Object class
	 * 
	 * @return the name of this state.
	 */
	@Override
	public String toString() {
		return name;
	}

}
