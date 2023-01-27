package practice;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJsonObject {
public static <K> void main(String[] args) throws FileNotFoundException {
//	JSONObject jo = new JSONObject();
//	jo.put("firstName", "JOHN");
//	jo.put("lastName", "Smith");
//	jo.put("age", 27);
//	
//	Map map = new HashMap();
//	map.put("street", "chruchroad");
//	map.put("city", "Badlapur");
//	map.put("State", "Maharashtra");
//	map.put("Pincode", 421503);
//	
//	jo.put("address", map);
//	
//	JSONArray jarray = new JSONArray();
//	map = new HashMap();
//	map.put("type", "Home");
//	map.put("number", 124567);
//	jarray.add(map);
//	
//	map = new HashMap();
//	map.put("type", "fax");
//	map.put("number",9088);
//	
//	jarray.add(map);
//	
//	jo.put("phone numbers", jarray);
//	
//	
//	PrintWriter pw = new PrintWriter("D:JSONEXAMPLE.json");
//	pw.write(jo.toJSONString());
//	pw.flush();
//	pw.close();
	
	
	
	String demo=" {\"id_number\":\"AEZPI5706E\",\"mobile_number\":\"7050607080\"} ";
	
	String[] stringToSplit=demo.split(",");
	System.out.println(stringToSplit[0]);
	System.out.println(stringToSplit[1]);
	String[] stringToSplitbycolon=stringToSplit[0].split(":");
	String[] stringToSplitbycolon2=stringToSplit[1].split(":");
	System.out.println(stringToSplitbycolon[1]);
	System.out.println(stringToSplitbycolon2[1]);
	

	

	
	
}
}
