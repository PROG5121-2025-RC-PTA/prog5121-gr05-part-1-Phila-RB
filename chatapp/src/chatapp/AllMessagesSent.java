package chatapp;

import java.util.ArrayList;

import org.json.JSONObject;

public class AllMessagesSent {
	public static ArrayList<JSONObject> allSentMsgs = new ArrayList<>();
	
	public static void add(JSONObject obj) {
		allSentMsgs.add(obj);
	}
	
	public static ArrayList<JSONObject> get() {
		return allSentMsgs;
	}
}
