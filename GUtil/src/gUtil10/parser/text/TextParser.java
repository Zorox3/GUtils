package gUtil10.parser.text;

import gUtil10.parser.text.dataManagement.TPArray;
import gUtil10.parser.text.dataManagement.TPData;
import gUtil10.parser.text.dataManagement.TPValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextParser {

	private String file;
	private static Map<String, TPData> data = new HashMap<>();

	public TextParser(String file) {
		this.file = file;
	}

	public void process() {

		try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
			String line;
			while ((line = br.readLine()) != null) {

				String[] splited = line.replace(" ", "").split("=");

				if (splited[1].contains("{") && splited[1].contains("}")) {
					splited[1] = splited[1].replace("{", "");
					splited[1] = splited[1].replace("}", "");

					String[] arrayVal = splited[1].split(",");

					data.put(splited[0], new TPArray(arrayVal));

				} else {
					data.put(splited[0], new TPValue(splited[1]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public TPData get(String key) {
		return data.get(key);
	}

	public Map<String, TPData> getData() {
		return data;
	}

	public ArrayList<ArrayList<TPValue>> getDataAsArrayList() {
		ArrayList<ArrayList<TPValue>> output = new ArrayList<>();

		for (TPData fData : data.values()) {

			ArrayList<TPValue> subOutput = new ArrayList<>();

			if (fData instanceof TPValue) {
				subOutput.add((TPValue) fData);
			} else if (fData instanceof TPArray) {
				for (TPValue a : ((TPArray) fData).get().values()) {
					subOutput.add(a);
				}
			}
			output.add(subOutput);
		}
		return output;
	}

}
