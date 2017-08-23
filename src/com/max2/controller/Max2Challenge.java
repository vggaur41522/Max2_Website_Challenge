package com.max2.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.max2.common.CommonValidations;
import com.max2.model.ColorTag;
import com.max2.model.Message;

@Controller
public class Max2Challenge {
	// Result creation string
	final static Logger LOGGER = Logger.getLogger(Max2Challenge.class);
	static StringBuilder q4 = new StringBuilder();
	static StringBuilder q5 = new StringBuilder();
	static StringBuilder initRout1 = new StringBuilder();
	static StringBuilder initRout2 = new StringBuilder();
	public static int maxId = 1;
	public static int glblId = 0;
	public JSONArray summJSONArr;
	
	// Initially reading content from XML File
	static {
		Max2Challenge ms = new Max2Challenge();
		ms.readContent();
	}

	public enum TAG {
		VALIDATION_1, VALIDATION_2, VALIDATION_3, VALIDATION_4;
	}

	// Mapper for part IV where it captures count corresponding to a COLOR
	public Map<String, Integer> partIVMap = new HashMap<>();
	// Mapped for partV with more details about people liking a particular Color
	public Map<String, ColorTag> partVMap = new HashMap<>();

	// Tag Specific validation starts here !!!
	public boolean validateInpTag1(String[] inp) {
		// Allowed Numbers, spaces, hyphens or Brackets for Phone Number
		String phNo = inp[2];
		if (!CommonValidations.validatePhone(phNo)) {
			LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Invalid Phone No.]  ~ " + stringOP(inp));
			return false;
		}
		// Allowed Numbers or hyphens for ZipCode
		String zipCode = inp[4];
		if (!CommonValidations.validateZip(zipCode)) {
			LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Invalid Zip Code ]  ~ " + stringOP(inp));
			return false;
		}
		return true;
	}

	public boolean validateInpTag2(String[] inp) {
		// Allowed Numbers, spaces, hyphens or Brackets for Phone Number
		String phNo = inp[3];
		if (!CommonValidations.validatePhone(phNo)) {
			LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Invalid Phone No.]  ~ " + stringOP(inp));
			return false;
		}
		// Allowed Numbers or hyphens for ZipCode
		String zipCode = inp[2];
		if (!CommonValidations.validateZip(zipCode)) {
			LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Invalid Zip Code ]  ~ " + stringOP(inp));
			return false;
		}
		return true;
	}

	public boolean validateInpTag3(String[] inp) {
		// Allowed Numbers, spaces, hyphens or Brackets for Phone Number
		String phNo = inp[3];
		if (!CommonValidations.validatePhone(phNo)) {
			LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Invalid Phone No.]  ~ " + stringOP(inp));
			return false;
		}
		// Allowed Numbers or hyphens for ZipCode
		String zipCode = inp[2];
		if (!CommonValidations.validateZip(zipCode)) {
			LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Invalid Zip Code ]  ~ " + stringOP(inp));
			return false;
		}
		return true;
	}

	public boolean validateInpTag4(String[] inp) {
		// Allowed Numbers, spaces, hyphens or Brackets for Phone Number
		String phNo = inp[3];
		if (!CommonValidations.validatePhone(phNo)) {
			LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Invalid Phone No.]  ~ " + stringOP(inp));
			return false;
		}
		// Allowed Numbers or hyphens for ZipCode
		String zipCode = inp[2];
		if (!CommonValidations.validateZip(zipCode)) {
			LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Invalid Zip Code ]  ~ " + stringOP(inp));
			return false;
		}
		return true;
	}

	public void populateDataSet(Message popStruct) {
		if (partIVMap.containsKey(popStruct.getColor())) {
			partIVMap.put(popStruct.getColor(), partIVMap.get(popStruct.getColor()) + 1);
		} else {
			partIVMap.put(popStruct.getColor(), 1);
		}

		if (partVMap.containsKey(popStruct.getColor())) {
			List<String> temp = new ArrayList<>();
			temp.addAll(partVMap.get(popStruct.getColor()).getNames());
			int c = partVMap.get(popStruct.getColor()).getCount();
			temp.add((popStruct.getFirstName() + " " + popStruct.getLastName()));
			partVMap.put(popStruct.getColor(), new ColorTag((c + 1), temp));
		} else {
			List<String> temp = new ArrayList<>();
			temp.add((popStruct.getFirstName() + " " + popStruct.getLastName()));
			partVMap.put(popStruct.getColor(), new ColorTag(1, temp));
		}
		maxId++;
	}

	public String stringOP(String[] test) {
		StringBuilder sb = new StringBuilder();
		for (String s : test) {
			sb.append(s + " | ");
		}
		return sb.toString();
	}

	/*
	 * ***************** QUESTION - 3 ***************** This part of code cover
	 * the problem statement Question-3: Question 3 asks us to validate input
	 * file format and log error when format is not followed. Below consolidated
	 * Validation routine call specific format validation routine for every
	 * record
	 */
	public void validateAndPopulateData(String[] inp, TAG tag) {

		// Populating Object from read file content .... >> Question 2
		Message popS = new Message();

		switch (tag) {
		// Format 1
		case VALIDATION_1:
			if (validateInpTag1(inp)) {
				popS.setFirstName(inp[1]);
				popS.setLastName(inp[0]);
				popS.setPhoneNo(inp[2]);
				popS.setColor(inp[3]);
				popS.setZipCode(inp[4]);
				popS.setAddress("");
				populateDataSet(popS);
				LOGGER.debug("** Record [" + maxId + "] Inserted SUCCESSFULLY [Format-1] ~ " + stringOP(inp));
			} else {
				maxId++;
			}
			break;
		// Format 2
		case VALIDATION_2:
			if (validateInpTag2(inp)) {
				popS.setFirstName(inp[0].split(" ")[0]);
				popS.setLastName(inp[0].split(" ")[1]);
				popS.setPhoneNo(inp[3]);
				popS.setColor(inp[1]);
				popS.setZipCode(inp[2]);
				popS.setAddress("");
				populateDataSet(popS);
				LOGGER.debug("** Record [" + maxId + "] Inserted SUCCESSFULLY [Format-2] ~ " + stringOP(inp));
			} else {
				maxId++;
			}
			break;
		// Format 3
		case VALIDATION_3:
			if (validateInpTag3(inp)) {
				popS.setFirstName(inp[0]);
				popS.setLastName(inp[1]);
				popS.setPhoneNo(inp[3]);
				popS.setColor(inp[4]);
				popS.setZipCode(inp[2]);
				popS.setAddress("");
				populateDataSet(popS);
				LOGGER.debug("** Record [" + maxId + "] Inserted SUCCESSFULLY [Format-3] ~ " + stringOP(inp));
			} else {
				maxId++;
			}
			break;
		// Format 4
		case VALIDATION_4:
			if (validateInpTag4(inp)) {
				popS.setFirstName(inp[0].split(" ")[0]);
				popS.setLastName(inp[0].split(" ")[1]);
				popS.setPhoneNo(inp[3]);
				popS.setColor(inp[4]);
				popS.setZipCode(inp[2]);
				popS.setAddress(inp[1]);
				populateDataSet(popS);
				LOGGER.debug("** Record [" + maxId + "] Inserted SUCCESSFULLY [Format-4] ~ " + stringOP(inp));
			} else {
				maxId++;
			}
			break;
		default:
			// LOGGER.debug("Error Log");
			LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Format Check Failed]  ~ " + stringOP(inp));
			maxId++;
			break;
		}
		/*
		 * Once validation is successful data need to be populated in respective
		 * maps
		 */
		return;
	}

	public void printRoutines() {
		/*
		 * ***************** QUESTION - 4 ***************** This part of code
		 * cover the problem statement Question-4: Here we are using HashMap :
		 * partIVMap for managing following structure: Color : Count
		 */

		for (Map.Entry<String, Integer> e : partIVMap.entrySet()) {
			LOGGER.debug("Accessed Value: " + e.getKey() + "\t\t" + e.getValue());
			initRout1.append("Accessed Value: " + e.getKey() + "\t\t" + e.getValue() + "\n");
		}
		LOGGER.debug(" ------------ ");

		/*
		 * ***************** QUESTION - 5 ***************** This part of code
		 * cover the problem statement Question-5: Here we are using HashMap :
		 * partVMap for managing following structure: Color : Count [ List of
		 * People ]
		 */
		for (Map.Entry<String, ColorTag> e : partVMap.entrySet()) {
			LOGGER.debug("Accessed Value: " + e.getKey() + "\t\t" + e.getValue().getCount() + "\t\t"
					+ e.getValue().getNames());
			initRout2.append("Accessed Value: " + e.getKey() + "\t\t" + e.getValue() + "\n");
		}
	}

	public void printSvc1() {
		// Logger method to log output when part 4 is called
		LOGGER.debug(initRout1.toString());
	}

	public void printSvc2() {
		// Logger method to log output when part 5 is called
		LOGGER.debug(initRout2.toString());
	}

	public void populateJsonStruct() {
		JSONArray ja1 = new JSONArray();

		for (Map.Entry<String, Integer> e : partIVMap.entrySet()) {
			JSONObject jo1 = new JSONObject();
			jo1.put("color", e.getKey());
			jo1.put("count", e.getValue());
			ja1.put(jo1);
		}

		JSONArray ja2 = new JSONArray();

		for (Map.Entry<String, ColorTag> e : partVMap.entrySet()) {
			JSONObject jo2 = new JSONObject();
			jo2.put("color", e.getKey());
			jo2.put("count", e.getValue().getCount());
			JSONArray jaInt = new JSONArray();
			for (String s : e.getValue().getNames())
				jaInt.put(s);
			jo2.put("names", jaInt);
			ja2.put(jo2);
		}

		q4.append(ja1.toString());
		q5.append(ja2.toString());

	}

	public String populateJsonObj(String[] strLst) {
		JSONObject joPart2 = new JSONObject();
		JSONArray jaPart2 = new JSONArray();
		for (String s : strLst) {
			jaPart2.put(s);
		}
		joPart2.put("places", jaPart2);
		LOGGER.debug(joPart2.toString());
		return joPart2.toString();
	}

	public void readContent() {
		/*
		 * ***************** QUESTION - 2 ***************** This part of code
		 * cover the problem statement Question-2: Here we are using simple
		 * BuffferReader operation for reading CSV file content We can't use csv
		 * parsing methods of Java as we have varied input format (4 in
		 * specific)
		 */
		BufferedReader br = null;
		try {
			String sCurrentLine;
			InputStream is = getClass().getClassLoader().getResourceAsStream("../dataSource/input.csv");
			br = new BufferedReader(new InputStreamReader(is));

			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = sCurrentLine.trim();
				String[] splitData;
				TAG validationTag;

				if (sCurrentLine.charAt(0) == '"' && sCurrentLine.charAt(sCurrentLine.length() - 1) == '"') {
					// Format 4 Validation
					validationTag = TAG.VALIDATION_4;
					sCurrentLine = sCurrentLine.substring(1, sCurrentLine.length() - 1);
					splitData = sCurrentLine.split(",");
					validateAndPopulateData(splitData, validationTag);
				} else {
					splitData = sCurrentLine.split(",");
					if (splitData.length == 4) {
						// Format 2 Validation
						validateAndPopulateData(splitData, TAG.VALIDATION_2);
					} else if (splitData.length == 5) {
						// Format 1 and 3 Validation
						if (splitData[2].trim().length() > 10)
							validateAndPopulateData(splitData, TAG.VALIDATION_1);
						else
							validateAndPopulateData(splitData, TAG.VALIDATION_3);
					} else {
						LOGGER.debug("** Record [" + maxId + "] Insertion Failed [ Format Check Failed]  ~ "
								+ stringOP(splitData));
					}
				}

			}
			/* Print Routines for Question 4 and Question 5 */
			printRoutines();

			/*
			 * ***************** QUESTION - 6 ***************** This part of
			 * code cover the problem statement Question-2: Here we are using
			 * simple BuffferReader operation for reading CSV file content We
			 * can't use csv parsing methods of Java as we have varied input
			 * format (4 in specific)
			 */
			populateJsonStruct();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/*
	 * ***************** QUESTION - 4 ***************** 
	 * Controller space for question 4 endpoint
	 */

	@RequestMapping(value = "/quest4", method = RequestMethod.GET)
	public @ResponseBody String quest4Process() {
		LOGGER.debug("[END-POINT]: /quest4 called using GET Method");
		printSvc1();
		String message = Max2Challenge.q4.toString();
		return message;
	}

	/*
	 * ***************** QUESTION - 5 ***************** 
	 * Controller space for question 5 endpoint
	 */
	@RequestMapping(value = "/quest5", method = RequestMethod.GET)
	public @ResponseBody String quest5Process() {
		LOGGER.debug("[END-POINT]: /quest5 called using GET Method");
		printSvc2();
		String message = Max2Challenge.q5.toString();
		return message;
	}

	/*
	 * ***************** QUESTION - PART II ***************** 
	 * Controller space for PART II end point
	 */
	@RequestMapping(value = "/partII", method = RequestMethod.POST)
	public @ResponseBody String partIIProcess(@RequestParam(value = "myArr[]") String[] venue) {
		LOGGER.debug("[END-POINT]: /partII.html called using POST Method");
		// Populate JSON Object to be passed back to Client !!
		return populateJsonObj(venue);
	}

	/*
	 * ***************** PART III ***************** 
	 * Controller space for PART III endpoint
	 */
	@RequestMapping(value = "/partIII", method = RequestMethod.POST)
	public @ResponseBody String partIIIProcess(@RequestParam(value = "myArr[]") String[] reqUpdate) {
		LOGGER.debug("[END-POINT]: /partIII.html called using POST Method");

		// Validation for passed Input
		if (!CommonValidations.validateZip(reqUpdate[3]))
			return "** Error: [Field Validation failed] Invalid Zip Code Passed !!!";
		if (!CommonValidations.validatePhone(reqUpdate[4]))
			return "** Error: [Field Validation failed] Invalid Phone Number Passed !!!";

		LOGGER.debug("[END-POINT]: /partIII.html called. Field Validation successfuly");
		// Fetching generic Path !!
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String resName = "";
		String filePath;
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		boolean exists = true;

		try {
			filePath = URLDecoder.decode(path, "UTF-8");

			// Capturing the WEBINF/classes path !!
			String pathArray[] = filePath.split("/WEB-INF/classes/");
			filePath = pathArray[0];

			// To read a file from Webcontent
			resName = new File(filePath).getPath() + File.separatorChar + "input.csv";
			LOGGER.debug("File name :" + resName);
			File file = new File(resName);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
				maxId = 1;
				exists = false;
			} else {
				fileReader = new FileReader(file.getAbsoluteFile());
				bufferedReader = new BufferedReader(fileReader);
				String currLine = "";
				while ((currLine = bufferedReader.readLine()) != null) {
					String[] inputArr = currLine.split(",");
					maxId = Integer.parseInt(inputArr[0]) + 1;
				}
				exists = true;
			}

			// true = append file
			fileWriter = new FileWriter(file.getAbsoluteFile(), true);
			bufferedWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < reqUpdate.length; i++) {
				if (i == 0) {
					bufferedWriter.write(String.valueOf(maxId));
					bufferedWriter.write(",");
				}
				bufferedWriter.write(reqUpdate[i]);
				if (i != reqUpdate.length - 1) {
					bufferedWriter.write(",");
				}
			}
			bufferedWriter.write('\n');
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (bufferedReader != null)
					bufferedReader.close();

				if (fileReader != null)
					fileReader.close();

				if (bufferedWriter != null)
					bufferedWriter.close();

				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		String returnMsg = "Id " + String.valueOf(maxId) + " is added";
		LOGGER.debug("[END-POINT]: /partIII.html. Unique ID created for POSTED DATA : " + maxId);
		if (!exists) {
			exists = true;
		}
		maxId++;
		return returnMsg;
	}

	/*
	 * ***************** SUMMARY SECRION ***************** 
	 * Controller space for summmary section Amendment to show current data state
	 */
	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	public @ResponseBody String summaryDisplay() {
		LOGGER.debug("[END-POINT]: /summary called using GET Method");
		summJSONArr = new JSONArray();
		// Fetching generic Path !!
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String resName = "";
		String filePath;
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;

		try {
			filePath = URLDecoder.decode(path, "UTF-8");

			// Capturing the WEBINF/classes path !!
			String pathArray[] = filePath.split("/WEB-INF/classes/");
			filePath = pathArray[0];

			// To read a file from Webcontent
			resName = new File(filePath).getPath() + File.separatorChar + "input.csv";
			File file = new File(resName);

			// if file doesn't exists, then create it
			if (file.exists()) {
				fileReader = new FileReader(file.getAbsoluteFile());
				bufferedReader = new BufferedReader(fileReader);
				String currLine = "";
				while ((currLine = bufferedReader.readLine()) != null) {
					String[] inputArr = currLine.split(",");
					populateSummaryJson(loadMessageFromText(inputArr), inputArr[0]);
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (bufferedReader != null)
					bufferedReader.close();

				if (fileReader != null)
					fileReader.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		return summJSONArr.toString();
	}

	public Message loadMessageFromText(String[] inp) {
		Message m = new Message();

		if (inp.length == 7) {
			m.setFirstName(inp[1]);
			m.setLastName(inp[2]);
			m.setAddress(inp[3]);
			m.setZipCode(inp[4]);
			m.setPhoneNo(inp[5]);
			m.setColor(inp[6]);
		}
		LOGGER.debug(m);
		return m;
	}

	/* 
	 * This will populate the Json Object required for JSON
	 */
	public void populateSummaryJson(Message m, String maxId) {
		JSONObject summJsonObj = new JSONObject();
		summJsonObj.put("MaxId", maxId);
		summJsonObj.put("FName", m.getFirstName());
		summJsonObj.put("LName", m.getLastName());
		summJsonObj.put("Address", m.getAddress());
		summJsonObj.put("ZipCode", m.getZipCode());
		summJsonObj.put("PhoneNo", m.getPhoneNo());
		summJsonObj.put("Color", m.getColor());
		summJSONArr.put(summJsonObj);
	}

}
