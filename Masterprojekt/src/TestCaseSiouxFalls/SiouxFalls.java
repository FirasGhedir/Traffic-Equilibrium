package TestCaseSiouxFalls;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphModel.Graphs;
import ilog.concert.IloException;

/**
 * 
 * @author julian
 *
 */
public class SiouxFalls {
	/**
	 * Help method to create an object instance out of a given JSON file
	 * 
	 * --------------------------------------------
	 * 
	 * @param obj the object to store into JSON file
	 */
	private static Graphs createObjectInstanceFromJSON(File file) {

		Graphs graph = new Graphs(); // new instance for JSON data

		// check, if file is valide
		String fileName = file.getName().toUpperCase();
		boolean extension = fileName.endsWith(".JSON");

		switch (String.valueOf(extension)) {
		case "true":
			final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			try {
				graph = mapper.readValue(new File("./Masterprojekt/files/graphData.json"), Graphs.class); // reads
																											// object
																											// instance
																											// of
				// JSON serialization
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graph;
		default:
			System.err.println("The given file is not valid to create an object instance out of it...");
			break;
		}
		return graph;
	}

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args             the command line arguments
	 * @param adjacency_matrix
	 */
	public static void main(String[] args) throws IloException {

		try {

			/*
			 * TODO
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
