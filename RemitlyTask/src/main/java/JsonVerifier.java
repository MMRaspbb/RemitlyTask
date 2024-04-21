import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

//I assumed that the following: Version, Statement, Sid, Effect, Action, Resource are required as they were presented in the given example
//I am also checking for optional elements: Principal, Condition

public class JsonVerifier {
    public boolean isLegit(String absolutePathToJson){
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(absolutePathToJson);
            Object object = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject)object;
            //Checking if json contains PolicyName space and if it is a String format and if it is longer than 0 characters
            if(!jsonObject.containsKey("PolicyName")){
                return false;
            }
            Object policyNameObj = jsonObject.get("PolicyName");
            if(isString(policyNameObj)) {
                String policyName = (String) policyNameObj;
                if(policyName.length() < 1) {
                    return false;
                }
            } else {
                return false;
            }
            //Checking if json contains PolicyDocument and if it is a JsonObject type
            if(!jsonObject.containsKey("PolicyDocument")){
                return false;
            }
            if(jsonObject.get("PolicyDocument") instanceof JSONObject){
                //checking the contents of PolicyDocument
                if(!documentConstentsLegit((JSONObject) jsonObject.get("PolicyDocument"))){
                    return false;
                }
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    private boolean documentConstentsLegit(JSONObject jsonObject){
        //Checking the date validity
        if(!jsonObject.containsKey("Version")){
            return false;
        }
        if(!(jsonObject.get("Version") instanceof String)){
            return false;
        }
        String date = (String)jsonObject.get("Version");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try{
            dateFormat.parse(date);
        } catch(java.text.ParseException e){
            return false;
        }
        //Checking the Statement content
        if(!jsonObject.containsKey("Statement")){
            return false;
        }
        if(!(jsonObject.get("Statement") instanceof JSONArray)){
            return false;
        }
        JSONArray statement = (JSONArray) jsonObject.get("Statement");
        HashSet<String> sidSet = new HashSet<>();
        for(int i = 0;i < statement.size();i++){
            //Checking if part of the statement is a jsonObject
            if(!(statement.get(i) instanceof JSONObject)){
                return false;
            }
            JSONObject currentStatement = (JSONObject)statement.get(i);
            //Checking if current sid is a String and is unique compared to the rest
            if(!existsAndIsNotEmptyString(currentStatement, "Sid")){
                return false;
            }
            String sid = (String)currentStatement.get("Sid");
            if(sidSet.contains(sid)){
                return false;
            }
            sidSet.add(sid);
            //Checking if current Effect is a String and if it is either "Allow" or "Deny"
            if(!existsAndIsNotEmptyString(currentStatement, "Effect")){
                return false;
            }
            String effect = (String)currentStatement.get("Effect");
            if(!(effect.equals("Allow") || effect.equals("Deny"))){
                return false;
            }
            //Checking if Action and Resource are either a not empty String or array of not empty Strings
            if(!isStringOrArrayOfStrings(currentStatement, "Action")){
                return false;
            }
            if(!isStringOrArrayOfStrings(currentStatement, "Resource")){
                return false;
            }
            //Checking if Principal element exists, if so if it is correct
            if(currentStatement.containsKey("Principal")){
                if(!isStringOrArrayOfStrings(currentStatement, "Principal")){
                    return false;
                }
            }
            //Checking if condition element structure is correct
            if(currentStatement.containsKey("Condition")){
                JSONObject condition = (JSONObject) currentStatement.get("Condition");
                for(Object key: condition.keySet()){
                    if(!(condition.get(key) instanceof JSONObject)){
                        return false;
                    }
                    JSONObject insideCondition = (JSONObject)condition.get(key);
                    for(Object insideKey: insideCondition.keySet()){
                        if(!isString(insideCondition.get(insideKey))){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    private boolean isStringOrArrayOfStrings(JSONObject object, String key){
        if(!object.containsKey(key)){
            return false;
        }
        if(!isString(object.get(key))){
            if((object.get(key) instanceof JSONArray)){
                JSONArray actionArray = (JSONArray) object.get(key);
                for(int j = 0;j < actionArray.size();j++){
                    if(!isString(actionArray.get(j))){
                        return false;
                    }
                    if(((String)actionArray.get(j)).length() < 1){
                        return false;
                    }
                }
            } else{
                return false;
            }
        } else if(((String)object.get(key)).length() < 1){
            return false;
        }
        return true;
    }
    private boolean existsAndIsNotEmptyString(JSONObject object, Object key){
        if(!object.containsKey(key)){
            return false;
        }
        if(!isString(object.get(key))){
            return false;
        }
        if(((String)object.get(key)).length() < 1){
            return false;
        }
        return true;
    }
    private boolean isString(Object object){
        return object instanceof String;
    }
}
