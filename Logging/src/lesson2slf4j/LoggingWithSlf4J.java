package lesson2slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LoggingWithSlf4J {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingWithSlf4J.class.getName());

	private static final String RESOURCE_LIB = "resources/txtFiles/";
	private static final String INPUT_FILE = RESOURCE_LIB + "motivationInput.txt";
	private static final String OUTPUT_FILE = RESOURCE_LIB + "motivationOutput.txt";

	public static void main(String[] args) throws IOException {
		LoggingWithSlf4J motivationWithLog = new LoggingWithSlf4J();
		motivationWithLog.getNumbersAndSaveSum();
	}
	
	public LoggingWithSlf4J() {
		super();
	}
	
	/**
	 * get numbers from text file and save their sum into a different file with a
	 * time stamp. If that file exists already - write the sum in a new line.
	 * 
	 * @throws IOException
	 */
	public void getNumbersAndSaveSum() throws IOException {
		logger.info("getNumbersAndSaveSum started");
		List<Integer> numbers = getNumbersFromFile(INPUT_FILE);
		int sum = getSum(numbers);
		saveSumIntoFile(sum);
		logger.info("getNumbersAndSaveSum finished - saved sum " + sum);		
	}

	private List<Integer> getNumbersFromFile(String fileName) throws IOException {
		File file = new File(fileName);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			logger.debug("reading from file {}", fileName);
		} catch (FileNotFoundException e) {
			logger.debug("file not found: {} -> {}", fileName, e.getMessage());
			throw e;
		}

		StringBuilder allDataAsText = new StringBuilder();
		String line;
		try {
			while ((line = br.readLine()) != null) {
				allDataAsText.append(line).append(" ");
			}
		} catch (IOException e) {
			logger.warn("failed reading from file: {}  -> {}",fileName, e.getMessage());
			throw e;
		} finally {
			if (br != null) {
				br.close();
			}
		}

		logger.debug("information from file {}", allDataAsText);
		
		StringTokenizer stringTokenizer = new StringTokenizer(allDataAsText.toString());
		
		List<Integer> numbers = new ArrayList<>();
		while (stringTokenizer.hasMoreElements()) {
			numbers.add(Integer.parseInt(stringTokenizer.nextToken()));
		}

		return numbers;
	}

	private int getSum(List<Integer> numbers) {
		int sum = 0;
		for (Integer num : numbers) {
			sum += num;
		}
		logger.debug("sum is: {}", sum);
		return sum;
	}

	private void saveSumIntoFile(int sum) throws IOException {
		StringBuilder output = new StringBuilder();
	    BufferedWriter writer = null;
	    File f = new File(OUTPUT_FILE);
	    if(f.exists() && !f.isDirectory()) { 
	    	logger.debug("output file already exists");
	    	List<Integer> numbers = getNumbersFromFile(OUTPUT_FILE);
	    	for (Integer num : numbers) {
	    		output.append(num).append("\n");
	    	}
		}
	    
	    output.append(sum);
	    
		try {
			writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));			 
	    	logger.debug("writing to output file the following data: {}", output);
			writer.append(output.toString());
		} catch (IOException e) {
			logger.warn("failed writing to file: {} -> {}", OUTPUT_FILE, e.getMessage());
			throw e;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
