package com.max2.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.max2.model.Message;

@Controller
public class Max2Challenge {
	public enum TAG{
		VALIDATION_1,VALIDATION_2,VALIDATION_3,VALIDATION_4;
	}
	public class ColorTag{
		private int count;
		private List<String> names;
		ColorTag(int count,List<String> names)
		{
			this.count = count;
			this.names = names;
		}
	}
	
	public  Map<String,Integer> partIVMap  = new HashMap<>();
	public  Map<String,ColorTag> partVMap  = new HashMap<>();
	StringBuilder q4 = new StringBuilder();
	StringBuilder q5 = new StringBuilder();
	
	public boolean validateInpTag1(String[] inp)
	{
		return true;
	}
	public boolean validateInpTag2(String[] inp)
	{
		return true;
	}
	public boolean validateInpTag3(String[] inp)
	{
		return true;
	}
	public boolean validateInpTag4(String[] inp)
	{
		return true;
	}
	
	public void populateDataSet(Message popStruct)
	{
		if(partIVMap.containsKey(popStruct.getColor()))
		{
			partIVMap.put(popStruct.getColor(),partIVMap.get(popStruct.getColor())+1);
		}
		else
		{
			partIVMap.put(popStruct.getColor(),1);
		}
		
		if(partVMap.containsKey(popStruct.getColor()))
		{
			List<String> temp = new ArrayList<>();
			temp.addAll(partVMap.get(popStruct.getColor()).names);
			int c = partVMap.get(popStruct.getColor()).count;
			temp.add((popStruct.getFirstName() + " " +  popStruct.getLastName()));
			partVMap.put(popStruct.getColor(), new ColorTag((c+1), temp));
		}
		else
		{
			List<String> temp = new ArrayList<>();
			temp.add((popStruct.getFirstName() + " " +  popStruct.getLastName()));
			partVMap.put(popStruct.getColor(), new ColorTag(1, temp));
		}
	}
	
	/* ***************** QUESTION - 3 *****************
	 * This part of code cover the problem statement Question-3: 
	 * Question 3 asks us to validate input file format and log error when
	 * format is not followed. Below consolidated Validation routine call specific format validation
	 * routine for every record 
	 */
	public void validateAndPopulateData(String[] inp, TAG tag) {
		
		Message popS = new Message();
		
		switch (tag) {
		// Format 1
		case VALIDATION_1:
			if (validateInpTag1(inp))
			{
				popS.setFirstName(inp[1]);
				popS.setLastName(inp[0]);
				popS.setPhoneNo(inp[2]);
				popS.setColor(inp[3]);
				popS.setZipCode(inp[4]);
				popS.setAddress("");
			}
			break;
		// Format 2
		case VALIDATION_2:
			if (validateInpTag2(inp))
			{
				popS.setFirstName(inp[0].split(" ")[0]);
				popS.setLastName(inp[0].split(" ")[1]);
				popS.setPhoneNo(inp[3]);
				popS.setColor(inp[1]);
				popS.setZipCode(inp[2]);
				popS.setAddress("");
			}
			break;
		// Format 3
		case VALIDATION_3:
			if (validateInpTag3(inp))
			{
				popS.setFirstName(inp[0]);
				popS.setLastName(inp[1]);
				popS.setPhoneNo(inp[3]);
				popS.setColor(inp[4]);
				popS.setZipCode(inp[2]);
				popS.setAddress("");
			}
			break;
		// Format 4
		case VALIDATION_4:
			if (validateInpTag4(inp))
			{
				popS.setFirstName(inp[0].split(" ")[0]);
				popS.setLastName(inp[0].split(" ")[1]);
				popS.setPhoneNo(inp[3]);
				popS.setColor(inp[4]);
				popS.setZipCode(inp[2]);
				popS.setAddress(inp[1]);
			}
			break;
		default:
			System.out.println("Error Log");
			break;
		}
		/* Once validation is successful data need to be populated in
		 * respective maps  
		 */
		populateDataSet(popS);
		return;
	}
	public void printRoutines()
	{
		/* ***************** QUESTION - 4 *****************
		 * This part of code cover the problem statement Question-4: 
		 * Here we are using HashMap : partIVMap for managing following structure: 
		 * Color : Count
		 */
		
		for(Map.Entry<String, Integer> e:partIVMap.entrySet())
		{
			System.out.println(e.getKey() + "\t\t" + e.getValue());
		}
		System.out.println(" ------------ ");
		
		/* ***************** QUESTION - 5 *****************
		 * This part of code cover the problem statement Question-5: 
		 * Here we are using HashMap : partVMap for managing following structure: 
		 * Color : Count   [ List of People ] 
		 */
		for(Map.Entry<String, ColorTag> e:partVMap.entrySet())
		{
			System.out.println(e.getKey() + "\t\t" + e.getValue().count + "\t\t" + e.getValue().names);
		}
	}
	
	public void populateJsonStruct()
	{
		JSONArray ja1 = new JSONArray();
		
		for(Map.Entry<String, Integer> e : partIVMap.entrySet())
		{
			JSONObject jo1 = new JSONObject();
			jo1.put("color", e.getKey());
			jo1.put("count", e.getValue());
			ja1.put(jo1);
		}
		
		JSONArray ja2 = new JSONArray();
		
		for(Map.Entry<String, ColorTag> e : partVMap.entrySet())
		{
			JSONObject jo2 = new JSONObject();
			jo2.put("color", e.getKey());
			jo2.put("count", e.getValue().count);
			JSONArray jaInt = new JSONArray();
			for(String s: e.getValue().names)
				jaInt.put(s);
			jo2.put("names", jaInt);
			ja2.put(jo2);
		}
		
		System.out.println( ja1);
		System.out.println( ja2);
		
		q4.append(ja1.toString());
		q5.append(ja2.toString());
		
	}
	public void readContent() 
	{
		/* ***************** QUESTION - 2 *****************
		 * This part of code cover the problem statement Question-2: 
		 * Here we are using simple BuffferReader operation for reading CSV file content
		 * We can't use csv parsing methods of Java as we have varied input format (4 in specific)
		 */
		BufferedReader br = null;
		try {
			String sCurrentLine;
			//br = new BufferedReader(new FileReader("C:\\Users\\Varun_Gaur\\workspace\\Max2WebProject\\dataSource\\input.csv"));
			InputStream is = getClass().getClassLoader().getResourceAsStream("../dataSource/input.csv");
			br = new BufferedReader(new InputStreamReader(is));
			
			System.out.print(br);
			//C:\Users\Varun_Gaur\workspace\Max2WebProject\WebContent\dataSource\input.csv
			
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = sCurrentLine.trim();
				String[] splitData ;
				TAG validationTag ;
				
				if(sCurrentLine.charAt(0) == '"' && sCurrentLine.charAt(sCurrentLine.length()-1) == '"')
				{
					//Format 4 Validation
					validationTag = TAG.VALIDATION_4;
					sCurrentLine = sCurrentLine.substring(1, sCurrentLine.length()-1);
					splitData = sCurrentLine.split(",");
					validateAndPopulateData(splitData,validationTag);
				}
				else{
					splitData = sCurrentLine.split(",");
					if(splitData.length == 4)
					{
						// Format 2 Validation
						validateAndPopulateData(splitData,TAG.VALIDATION_2);
					}
					else if (splitData.length == 5)
					{
						// Format 1 and 3 Validation
						if(splitData[2].trim().length() > 10)
							validateAndPopulateData(splitData,TAG.VALIDATION_1);
						else
							validateAndPopulateData(splitData,TAG.VALIDATION_3);
					}
					else
					{
						System.out.println("Error Log !!!");
					}
				}
				
			}
			/* Print Routines for Question 4 and Question 5 */
			printRoutines();
		
			/* ***************** QUESTION - 6 *****************
			 * This part of code cover the problem statement Question-2: 
			 * Here we are using simple BuffferReader operation for reading CSV file content
			 * We can't use csv parsing methods of Java as we have varied input format (4 in specific)
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
	
	//@RequestMapping (value="/quest4" , method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/quest4", method = RequestMethod.GET)
	public @ResponseBody String quest4Process() 
	{
		Max2Challenge res = new Max2Challenge();
		res.readContent();	
		String message = res.q4.toString();
		System.out.println("FINAL DATA ----"+message);
		return message;
	}
	
	@RequestMapping(value="/quest5", method = RequestMethod.GET)
	public @ResponseBody  String quest5Process() 
	{
		Max2Challenge res = new Max2Challenge();
		res.readContent();	
		String message = res.q5.toString();
		System.out.println("FINAL 5 DATA ----"+message);
		return message;
	}
}
