package com.max2.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import java.util.*;
import com.max2.model.Venue;
import com.max2.model.Message;

@Controller
public class Max2Challenge {
	// Result creation string
	static StringBuilder q4 = new StringBuilder();
	static StringBuilder q5 = new StringBuilder();
	static 
	{
		Max2Challenge ms = new Max2Challenge();
		ms.readContent();
	}

	public static int maxId = 1;
	public static int glblId = 0;

	public enum TAG {
		VALIDATION_1, VALIDATION_2, VALIDATION_3, VALIDATION_4;
	}

	// This class is used for Question 5 API with structure :
	// "color": "<>",
	// "count": <>,
	// "names": ["<>", "<>"]
	public class ColorTag {
		private int count;
		private List<String> names;

		ColorTag(int count, List<String> names) {
			this.count = count;
			this.names = names;
		}
	}

	// Mapper for part IV where it captures count corresponding to a COLOR
	public Map<String, Integer> partIVMap = new HashMap<>();
	// Mapped for partV with more details about people liking a particular Color
	public Map<String, ColorTag> partVMap = new HashMap<>();

	

	// Phone number validator !!
	public boolean validatePhone(String ph) {
		ph = ph.trim();
		// validate phone numbers of format "1234567890"
		if (ph.matches("\\d{10}"))
			return true;
		// validating phone number with - or spaces
		else if (ph.matches("\\d{3}[-\\s]\\d{3}[-\\s]\\d{4}"))
			return true;
		// validating phone number where area code is in braces ()
		else if (ph.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
			return true;
		// return false if nothing matches the input
		else
			return false;
	}

	// Zip Code validator
	public boolean validateZip(String zip) {
		zip = zip.trim();
		// validate phone numbers of format "1234567890"
		if (zip.matches("\\d{5}"))
			return true;
		// validating phone number with - or spaces
		else if (zip.matches("\\d{5}[-\\s]\\d{4}"))
			return true;
		// return false if nothing matches the input
		else
			return false;
	}

	// Tag Specific validation starts here !!!
	public boolean validateInpTag1(String[] inp) {
		// Allowed Numbers, spaces, hyphens or Brackets for Phone Number
		String phNo = inp[2];
		if (!validatePhone(phNo))
		{
			System.out.println("** Record ["+ maxId + "] Insertion Failed [ Invalid Phone No.]  ~ "+stringOP(inp));
			return false;
		}
		// Allowed Numbers or hyphens for ZipCode
		String zipCode = inp[4];
		if (!validateZip(zipCode))
		{
			System.out.println("** Record ["+ maxId + "] Insertion Failed [ Invalid Zip Code ]  ~ "+stringOP(inp));
			return false;
		}
		return true;
	}

	public boolean validateInpTag2(String[] inp) {
		// Allowed Numbers, spaces, hyphens or Brackets for Phone Number
		String phNo = inp[3];
		if (!validatePhone(phNo))
		{
			System.out.println("** Record ["+ maxId + "] Insertion Failed [ Invalid Phone No.]  ~ "+stringOP(inp));
			return false;
		}
		// Allowed Numbers or hyphens for ZipCode
		String zipCode = inp[2];
		if (!validateZip(zipCode))
		{
			System.out.println("** Record ["+ maxId + "] Insertion Failed [ Invalid Zip Code ]  ~ "+stringOP(inp));
			return false;
		}
		return true;
	}

	public boolean validateInpTag3(String[] inp) {
		// Allowed Numbers, spaces, hyphens or Brackets for Phone Number
		String phNo = inp[3];
		if (!validatePhone(phNo))
		{
			System.out.println("** Record ["+ maxId + "] Insertion Failed [ Invalid Phone No.]  ~ "+stringOP(inp));
			return false;
		}
		// Allowed Numbers or hyphens for ZipCode
		String zipCode = inp[2];
		if (!validateZip(zipCode))
		{
			System.out.println("** Record ["+ maxId + "] Insertion Failed [ Invalid Zip Code ]  ~ "+stringOP(inp));
			return false;
		}
		return true;
	}

	public boolean validateInpTag4(String[] inp) {
		// Allowed Numbers, spaces, hyphens or Brackets for Phone Number
		String phNo = inp[3];
		if (!validatePhone(phNo))
		{
			System.out.println("** Record ["+ maxId + "] Insertion Failed [ Invalid Phone No.]  ~ "+stringOP(inp));
			return false;
		}
		// Allowed Numbers or hyphens for ZipCode
		String zipCode = inp[2];
		if (!validateZip(zipCode))
		{
			System.out.println("** Record ["+ maxId + "] Insertion Failed [ Invalid Zip Code ]  ~ "+stringOP(inp));
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
			temp.addAll(partVMap.get(popStruct.getColor()).names);
			int c = partVMap.get(popStruct.getColor()).count;
			temp.add((popStruct.getFirstName() + " " + popStruct.getLastName()));
			partVMap.put(popStruct.getColor(), new ColorTag((c + 1), temp));
		} else {
			List<String> temp = new ArrayList<>();
			temp.add((popStruct.getFirstName() + " " + popStruct.getLastName()));
			partVMap.put(popStruct.getColor(), new ColorTag(1, temp));
		}
		maxId++;
	}

	public String stringOP(String[] test)
	{
		StringBuilder sb = new StringBuilder();
		for(String s : test)
		{
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

		// Populating Object from read file content ....  >> Question 2 
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
				System.out.println("** Record ["+ maxId + "] Inserted SUCCESSFULLY [Format-1] ~ "+stringOP(inp));
			}
			else
			{
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
				System.out.println("** Record ["+ maxId + "] Inserted SUCCESSFULLY [Format-2] ~ "+stringOP(inp));
			}
			else
			{
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
				System.out.println("** Record ["+ maxId + "] Inserted SUCCESSFULLY [Format-3] ~ "+stringOP(inp));
			}
			else
			{
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
				System.out.println("** Record ["+ maxId + "] Inserted SUCCESSFULLY [Format-4] ~ "+stringOP(inp));
			}
			else
			{
				maxId++;
			}
			break;
		default:
			//System.out.println("Error Log");
			System.out.println("** Record ["+ maxId + "] Insertion Failed [ Format Check Failed]  ~ "+stringOP(inp));
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
			System.out.println(e.getKey() + "\t\t" + e.getValue());
		}
		System.out.println(" ------------ ");

		/*
		 * ***************** QUESTION - 5 ***************** This part of code
		 * cover the problem statement Question-5: Here we are using HashMap :
		 * partVMap for managing following structure: Color : Count [ List of
		 * People ]
		 */
		for (Map.Entry<String, ColorTag> e : partVMap.entrySet()) {
			System.out.println(e.getKey() + "\t\t" + e.getValue().count + "\t\t" + e.getValue().names);
		}
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
			jo2.put("count", e.getValue().count);
			JSONArray jaInt = new JSONArray();
			for (String s : e.getValue().names)
				jaInt.put(s);
			jo2.put("names", jaInt);
			ja2.put(jo2);
		}

		System.out.println(ja1);
		System.out.println(ja2);

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
		System.out.println(joPart2.toString());
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
						System.out.println("** Record ["+ maxId + "] Insertion Failed [ Format Check Failed]  ~ "+stringOP(splitData));
					}
				}

			}
			/* Print Routines for Question 4 and Question 5 */
			printRoutines();

			/*
			 * ***************** QUESTION - 6 ***************** 
			 * This part of code cover the problem statement Question-2: Here we are using
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
		System.out.println("[END-POINT]: /quest5 called using POST Method");
		Max2Challenge res = new Max2Challenge();
//		res.readContent();
		String message = res.q4.toString();
		return message;
	}

	/*
	 * ***************** QUESTION - 5 ***************** 
	 * Controller space for question 5 endpoint
	 */
	@RequestMapping(value = "/quest5", method = RequestMethod.GET)
	public @ResponseBody String quest5Process() {
		System.out.println("[END-POINT]: /quest5 called using POST Method");
		Max2Challenge res = new Max2Challenge();
//		res.readContent();
		String message = res.q5.toString();
		return message;
	}

	/*
	 * ***************** QUESTION - PART II ***************** 
	 * Controller space for PART II  end point
	 */
	@RequestMapping(value = "/partII", method = RequestMethod.POST)
	public @ResponseBody String partIIProcess(@RequestParam(value = "myArr[]") String[] venue) {
		Max2Challenge res = new Max2Challenge();
		System.out.println("[END-POINT]: /partII.html called using POST Method");
		// Populate JSON Object to be passed back to Client !!
		return populateJsonObj(venue);
	}

	/*
	 * ***************** PART III ***************** 
	 * Controller space for PART III endpoint
	 */
	@RequestMapping(value = "/partIII", method = RequestMethod.POST)
	public @ResponseBody String partIIIProcess(@RequestParam(value = "myArr[]") String[] reqUpdate) {
		Max2Challenge res = new Max2Challenge();
		
		System.out.println("[END-POINT]: /partIII.html called using POST Method");
		
		// Validation for passed Input 
		if(!validateZip(reqUpdate[3]))
			return "** Error: [Field Validation failed] Invalid Zip Code Passed !!!";
		if(!validatePhone(reqUpdate[4]))
			return "** Error: [Field Validation failed] Invalid Phone Number Passed !!!";
		
		System.out.println("[END-POINT]: /partIII.html called. Field Validation successfuly");
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
		System.out.println("[END-POINT]: /partIII.html. Unique ID created for POSTED DATA : "+maxId);
		if (!exists) {
			exists = true;
		}
		maxId++;
		return returnMsg;
	}

}
