package JUnitTest;

import static org.junit.Assert.fail;

import org.junit.Test;

import graphGenerator.HeavyTailGraphGenerator;

/**
 * University Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 * 
 *          _____________________________________________
 * 
 *          JUnit test case for Heavy Tail Graph Generator
 */
public class HeavyTailGraphGeneratorTest {

	@Test
	public void testBadParameters() {

		try {
			new HeavyTailGraphGenerator<>(0, 10, 100);
			fail("Bad parameter");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		try {
			new HeavyTailGraphGenerator<>(2, 0, 100);
			fail("Bad parameter");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		try {
			new HeavyTailGraphGenerator<>(2, 3, 100);
			fail("Bad parameter");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		try {
			new HeavyTailGraphGenerator<>(3, 2, 2);
			fail("Bad parameter");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}
}