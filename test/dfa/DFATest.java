package test.dfa;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import dfa.DFA;

public class DFATest {

	// ------------------- dfa1 tests ----------------------//
	private DFA dfa1() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');

		assertTrue(dfa.addState("a"));
		assertTrue(dfa.addState("b"));
		assertTrue(dfa.setStart("a"));
		assertTrue(dfa.setFinal("b"));

		assertFalse(dfa.addState("a"));
		assertFalse(dfa.setStart("c"));
		assertFalse(dfa.setFinal("c"));

		assertTrue(dfa.addTransition("a", "a", '0'));
		assertTrue(dfa.addTransition("a", "b", '1'));
		assertTrue(dfa.addTransition("b", "a", '0'));
		assertTrue(dfa.addTransition("b", "b", '1'));

		assertFalse(dfa.addTransition("c", "b", '1'));
		assertFalse(dfa.addTransition("a", "c", '1'));
		assertFalse(dfa.addTransition("a", "b", '2'));

		return dfa;
	}

	// @Test
	// public void test1_1() {
	// DFA dfa = dfa1();
	// System.out.println("dfa1 instantiation pass");
	// }

	@Test
	public void test1_2() {
		DFA dfa = dfa1();
		assertNotNull(dfa.getState("a"));
		assertEquals(dfa.getState("a").getName(), "a");
		assertTrue(dfa.isStart("a"));
		assertNotNull(dfa.getState("b"));
		assertEquals(dfa.getState("b").getName(), "b");
		assertTrue(dfa.isFinal("b"));
		assertEquals(dfa.getSigma(), Set.of('0', '1'));

		System.out.println("dfa1 correctness pass");
	}

	@Test
	public void test1_3() {
		DFA dfa = dfa1();

		assertFalse(dfa.accepts("0"));
		assertTrue(dfa.accepts("1"));
		assertFalse(dfa.accepts("00"));
		assertTrue(dfa.accepts("101"));
		assertFalse(dfa.accepts("e"));

		System.out.println("dfa1 accept pass");
	}

	@Test
	public void test1_4() {
		DFA dfa = dfa1();

		String dfaStr = dfa.toString();
		String expStr = " Q = { a b }\n"
				+ "Sigma = { 0 1 }\n"
				+ "delta =\n"
				+ "		0	1\n"
				+ "	a	a	b\n"
				+ "	b	a	b\n"
				+ "q0 = a\n"
				+ "F = { b }";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));

		System.out.println("dfa1 toString pass");
	}

	@Test
	public void test1_5() {
		DFA dfa = dfa1();
		DFA dfaSwap = dfa.swap('1', '0');

		// different DFA objects
		assertTrue(dfa != dfaSwap);

		// different state objects
		assertTrue(dfa.getState("a") != dfaSwap.getState("a"));
		assertTrue(dfa.getState("b") != dfaSwap.getState("b"));
		assertEquals(dfa.isStart("a"), dfaSwap.isStart("a"));

		// the transitions of the original dfa should not change
		assertFalse(dfa.accepts("0"));
		assertTrue(dfa.accepts("1"));
		assertFalse(dfa.accepts("00"));
		assertTrue(dfa.accepts("101"));
		assertFalse(dfa.accepts("e"));

		System.out.println("dfa1Swap instantiation pass");
	}

	@Test
	public void test1_6() {
		DFA dfa = dfa1();
		DFA dfaSwap = dfa.swap('1', '0');
		assertFalse(dfaSwap.accepts("1"));
		assertTrue(dfaSwap.accepts("0"));
		assertFalse(dfaSwap.accepts("11"));
		assertTrue(dfaSwap.accepts("010"));
		assertFalse(dfaSwap.accepts("e"));

		System.out.println("dfa1Swap accept pass");
	}

	// ------------------- dfaI tests ----------------------//
	private DFA dfa2() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');

		assertTrue(dfa.addState("3"));
		assertTrue(dfa.setFinal("3"));

		assertTrue(dfa.addState("0"));
		assertTrue(dfa.setStart("0"));

		assertTrue(dfa.addState("1"));
		assertTrue(dfa.addState("2"));

		assertFalse(dfa.setFinal("c"));
		assertFalse(dfa.setStart("a"));
		assertFalse(dfa.addState("2"));

		assertTrue(dfa.addTransition("0", "1", '0'));
		assertTrue(dfa.addTransition("0", "0", '1'));
		assertTrue(dfa.addTransition("1", "3", '0'));
		assertTrue(dfa.addTransition("1", "2", '1'));
		assertTrue(dfa.addTransition("2", "1", '0'));
		assertTrue(dfa.addTransition("2", "1", '1'));
		assertTrue(dfa.addTransition("3", "3", '0'));
		assertTrue(dfa.addTransition("3", "3", '1'));

		assertFalse(dfa.addTransition("3", "a", '1'));
		assertFalse(dfa.addTransition("c", "a", '1'));
		assertFalse(dfa.addTransition("3", "a", '2'));

		return dfa;
	}

	// @Test
	// public void test2_1() {
	// DFA dfa = dfa2();
	// System.out.println("dfa2 instantiation pass");
	// }

	@Test
	public void test2_2() {
		DFA dfa = dfa2();
		assertNotNull(dfa.getState("0"));
		assertEquals(dfa.getState("1").getName(), "1");
		assertTrue(dfa.isStart("0"));
		assertNotNull(dfa.getState("3"));
		assertEquals(dfa.getState("3").getName(), "3");
		assertTrue(dfa.isFinal("3"));
		assertEquals(dfa.getSigma(), Set.of('0', '1'));

		System.out.println("dfa2 correctness pass");
	}

	@Test
	public void test2_3() {
		DFA dfa = dfa2();
		assertFalse(dfa.accepts("010"));
		assertTrue(dfa.accepts("00"));
		assertFalse(dfa.accepts("101"));
		assertTrue(dfa.accepts("111011111111110"));
		assertFalse(dfa.accepts("1110111111111010"));

		System.out.println("dfa2 accept pass");
	}

	@Test
	public void test2_4() {
		DFA dfa = dfa2();

		String dfaStr = dfa.toString();
		String expStr = "Q={3 0 1 2}\n"
				+ "Sigma = {0 1}\n"
				+ "delta =\n"
				+ "	0	1\n"
				+ "3	3	3\n"
				+ "0	1	0\n"
				+ "1	3	2\n"
				+ "2	1	1\n"
				+ "q0 = 0\n"
				+ "F={3}\n";
		// System.out.println(dfaStr);
		// System.out.println(expStr);
		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println("dfa2 toString pass");
	}

	@Test
	public void test2_5() {
		DFA dfa = dfa2();
		DFA dfaSwap = dfa.swap('1', '0');
		// different DFA objects
		assertTrue(dfa != dfaSwap);
		// different DFA states
		assertTrue(dfa.getState("0") != dfaSwap.getState("0"));
		assertTrue(dfa.getState("1") != dfaSwap.getState("1"));
		assertTrue(dfa.getState("3") != dfaSwap.getState("3"));
		assertEquals(dfa.isStart("0"), dfaSwap.isStart("0"));
		assertEquals(dfa.isFinal("3"), dfaSwap.isFinal("3"));

		// ensure that the transitions of the original DFA don't change
		assertFalse(dfa.accepts("010"));
		assertTrue(dfa.accepts("00"));
		assertFalse(dfa.accepts("101"));
		assertTrue(dfa.accepts("111011111111110"));
		assertFalse(dfa.accepts("1110111111111010"));

		System.out.println("dfa2Swap instantiation pass");
	}

	@Test
	public void test2_6() {
		DFA dfa = dfa2();
		DFA dfaSwap = dfa.swap('1', '0');
		assertFalse(dfaSwap.accepts("101"));
		assertTrue(dfaSwap.accepts("11"));
		assertFalse(dfaSwap.accepts("010"));
		assertTrue(dfaSwap.accepts("000100000000001"));
		assertFalse(dfaSwap.accepts("0001000000000101"));
		System.out.println("dfa2Swap accept pass");
	}

	// ------------------- dfa3 tests ----------------------//
	private DFA dfa3() {
		DFA dfa = new DFA();
		dfa.addSigma('2');
		dfa.addSigma('1');

		assertTrue(dfa.addState("G"));
		assertTrue(dfa.addState("D"));

		assertTrue(dfa.setFinal("G"));
		assertTrue(dfa.setFinal("D"));

		assertTrue(dfa.addState("A"));
		assertTrue(dfa.setStart("D"));
		assertTrue(dfa.setStart("A"));

		assertTrue(dfa.addState("B"));
		assertTrue(dfa.addState("C"));
		assertTrue(dfa.addState("E"));
		assertTrue(dfa.addState("F"));

		assertFalse(dfa.addState("A"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("A", "B", '1'));
		assertTrue(dfa.addTransition("A", "C", '2'));

		assertTrue(dfa.addTransition("B", "D", '1'));
		assertTrue(dfa.addTransition("B", "E", '2'));

		assertTrue(dfa.addTransition("C", "F", '1'));
		assertTrue(dfa.addTransition("C", "G", '2'));

		assertTrue(dfa.addTransition("C", "F", '1'));
		assertTrue(dfa.addTransition("C", "G", '2'));

		assertTrue(dfa.addTransition("D", "D", '1'));
		assertTrue(dfa.addTransition("D", "E", '2'));

		assertTrue(dfa.addTransition("E", "D", '1'));
		assertTrue(dfa.addTransition("E", "E", '2'));

		assertTrue(dfa.addTransition("F", "F", '1'));
		assertTrue(dfa.addTransition("F", "G", '2'));

		assertTrue(dfa.addTransition("G", "F", '1'));
		assertTrue(dfa.addTransition("G", "G", '2'));

		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	// @Test
	// public void test3_1() {
	// DFA dfa = dfa3();

	// System.out.println("dfa3 instantiation pass");
	// }

	@Test
	public void test3_2() {
		DFA dfa = dfa3();
		assertNotNull(dfa.getState("A"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("C").getName(), "C");
		assertTrue(dfa.isStart("A"));
		assertFalse(dfa.isStart("D"));
		assertNotNull(dfa.getState("G"));
		assertEquals(dfa.getState("E").getName(), "E");
		assertTrue(dfa.isFinal("G"));
		assertFalse(dfa.isFinal("B"));
		assertEquals(dfa.getSigma(), Set.of('2', '1'));

		System.out.println("dfa3 correctness pass");
	}

	@Test
	public void test3_3() {
		DFA dfa = dfa3();
		assertTrue(dfa.accepts("121212121"));
		assertTrue(dfa.accepts("12221212121"));
		assertFalse(dfa.accepts("12"));
		assertFalse(dfa.accepts("2"));
		assertFalse(dfa.accepts("1212"));

		System.out.println("dfa3 accept pass");
	}

	@Test
	public void test3_4() {
		DFA dfa = dfa3();

		String dfaStr = dfa.toString();
		String expStr = "Q={GDABCEF}\n"
				+ "Sigma = {2 1}\n"
				+ "delta =\n"
				+ "	2	1\n"
				+ "G	G	F\n"
				+ "D	E	D\n"
				+ "A	C	B\n"
				+ "B	E	D\n"
				+ "C	G	F\n"
				+ "E	E	D\n"
				+ "F	G	F\n"
				+ "q0 = A\n"
				+ "F = {G D}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println("dfa3 toString pass");
	}

	@Test
	public void test3_5() {
		DFA dfa = dfa3();
		DFA dfaSwap = dfa.swap('2', '1');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("A") != dfaSwap.getState("A"));
		assertTrue(dfa.getState("G") != dfaSwap.getState("G"));
		assertTrue(dfa.getState("E") != dfaSwap.getState("E"));
		assertEquals(dfa.isStart("D"), dfaSwap.isStart("D"));
		assertEquals(dfa.isFinal("A"), dfaSwap.isFinal("A"));

		// transitions of the original dfa should not change
		assertTrue(dfa.accepts("121212121"));
		assertTrue(dfa.accepts("12221212121"));
		assertFalse(dfa.accepts("12"));
		assertFalse(dfa.accepts("2"));
		assertFalse(dfa.accepts("1212"));

		System.out.println("df31Swap instantiation pass");
	}

	@Test
	public void test3_6() {
		DFA dfa = dfa3();
		DFA dfaSwap = dfa.swap('2', '1');
		assertTrue(dfaSwap.accepts("212121212"));
		assertTrue(dfaSwap.accepts("21112121212"));
		assertFalse(dfaSwap.accepts("21"));
		assertFalse(dfaSwap.accepts("1"));
		assertFalse(dfaSwap.accepts("2121"));

		System.out.println("dfa3Swap accept pass");
	}

	/**
	 * Our DFA
	 */
	private DFA dfaLoughmiller() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');

		assertTrue(dfa.addState("A")); // Add "A" as a state
		assertTrue(dfa.addState("B")); // Add "B" as a state

		assertTrue(dfa.setStart("A")); // Set "A" as the start state
		assertTrue(dfa.setFinal("A")); // Set "A" as a final state

		assertTrue(dfa.addTransition("A", "B", '0')); // Add transition from "A" to "B" on '0'
		assertTrue(dfa.addTransition("B", "A", '1')); // Add transition from "B" to "A" on '1'
		assertTrue(dfa.addTransition("B", "B", '0')); // Add transition from "B" to itself on '0'
		assertTrue(dfa.addTransition("A", "A", '1')); // Add transition from "A" to itself on '1'

		return dfa;
	}

	// OUR TESTS
	@Test
	public void testEmpty() {
		DFA dfa = dfaLoughmiller();
		assertTrue(dfa.accepts("")); // assuming empty string is accepted

		System.out.println("dfaEmpty testEmpty pass");
	}

	@Test
	public void testInvalidInput() {
		DFA dfa = dfaLoughmiller();

		// Test for characters not in the alphabet
		try {
			dfa.accepts("xyz");
		} catch (IllegalArgumentException e) {
			System.out.println("Passed: 'xyz' correctly threw IllegalArgumentException");
		}

		// Test for strings that don't follow the '0' followed by '1' pattern
		if (!dfa.accepts("00")) {
			System.out.println("Passed: '00' correctly rejected");
		}
		if (!dfa.accepts("11")) {
			System.out.println("Passed: '11' correctly rejected");
		}
		if (dfa.accepts("01")) {
			System.out.println("Passed: '01' correctly accepted");
		}
	}

	@Test
	public void testInvalidOperations() {
		DFA dfa = dfaLoughmiller();

		assertFalse(dfa.addTransition("nonexistent", "A", '1'));
		assertFalse(dfa.setStart("nonexistent"));
		assertFalse(dfa.setFinal("nonexistent"));
	}

	@Test
	public void testMultipleStartStates() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');
		dfa.addState("A");
		dfa.addState("B");
		dfa.setStart("A");
		dfa.setStart("B");
		// Check behavior or expect an error
		System.out.println("testMultipleStartStates pass");
	}

	@Test
	public void testMultipleFinalStates() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');
		dfa.addState("A");
		dfa.addState("B");
		dfa.setStart("A");
		dfa.setFinal("A");
		dfa.setFinal("B");
		dfa.addTransition("A", "B", '0');
		dfa.addTransition("B", "A", '1');
		assertTrue(dfa.accepts("0"));
		assertTrue(dfa.accepts("01"));
		System.out.println("testMultipleFinalStates pass");
	}

	@Test
	public void testLoopOnStartState() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');
		dfa.addState("A");
		dfa.setStart("A");
		dfa.setFinal("A");
		dfa.addTransition("A", "A", '0');

		assertTrue(dfa.accepts("")); // DFA should accept the empty string
		assertTrue(dfa.accepts("0")); // DFA should accept string with one looping symbol
		assertTrue(dfa.accepts("00")); // DFA should accept string with multiple looping symbols
		assertFalse(dfa.accepts("1")); // DFA should not accept string with non-looping symbol
		assertFalse(dfa.accepts("01")); // DFA should not accept string with non-looping symbol followed by looping
										// symbol

		System.out.println("testLoopOnStartState pass");
	}

	@Test
	public void testLoopOnFinalState() {
		DFA dfa = dfaLoughmiller();
		DFA dfa1 = dfa1();
		DFA dfa2 = dfa2();

		// DFA should accept the empty string as it starts in a final state
		assertTrue(dfa.accepts(""));

		// DFA should accept strings ending with '1' as it will be in the final state
		// "A"
		assertTrue(dfa.accepts("1")); // Stays in "A"
		assertTrue(dfa.accepts("11111")); // Goes to "B" on '0', then back to "A" on '1'
		assertTrue(dfa.accepts("000000001")); // Goes to "B" on '0', then back to "A" on '1'

		// DFA should not accept strings ending with '0' as it will be in the non-final
		// state "B"
		assertFalse(dfa.accepts("10")); // Goes to "B" on '0', then back to "A" on '1'
		assertFalse(dfa.accepts("0")); // Goes to "B" and stays there
		assertFalse(dfa.accepts("100")); // Goes to "B" on '1', back to "A" on '0', then to "B" on '0'
		assertFalse(dfa.accepts("0000")); // Goes to "B" on '0' and stays there

		// DFA should accept the empty string as it starts in a final state
		assertFalse(dfa1.accepts(""));

		// DFA should accept strings ending with '1' as it will be in the final state
		// "A"

		assertTrue(dfa1.accepts("1")); // Stays in "A"
		assertTrue(dfa1.accepts("11111")); // Goes to "B" on '0', then back to "A" on '1'
		assertTrue(dfa1.accepts("000000001")); // Goes to "B" on '0', then back to "A" on '1'

		// DFA should not accept strings ending with '0' as it will be in the non-final
		// state "B"
		assertFalse(dfa1.accepts("10")); // Goes to "B" on '0', then back to "A" on '1'
		assertFalse(dfa1.accepts("0")); // Goes to "B" and stays there
		assertFalse(dfa1.accepts("100")); // Goes to "B" on '1', back to "A" on '0', then to "B" on '0'
		assertFalse(dfa1.accepts("0000")); // Goes to "B" on '0' and stays there

		assertFalse(dfa2.accepts(""));

		assertTrue(dfa2.accepts("00")); // Stays in "A"
		assertTrue(dfa2.accepts("100")); // Goes to "B" on '0', then back to "A" on '1'
		assertTrue(dfa2.accepts("1111110100000101001")); // Goes to "B" on '0', then back to "A" on '1'

		assertFalse(dfa2.accepts("01")); // Goes to "B" on '0', then back to "A" on '1'
		assertFalse(dfa2.accepts("11111")); // Goes to "B" and stays there
		assertFalse(dfa2.accepts("01111")); // Goes to "B" on '1', back to "A" on '0', then to "B" on '0'

		System.out.println("testLoopOnFinalState pass");
	}
}
