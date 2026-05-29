package context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    //Central store - key is a string, value is an object(of any type)
    private Map<String,Object> contextData = new HashMap<>();

    //store any value
    public void set(String key, Object value){
        contextData.put(key,value);
    }

    //retrieve any value
    public Object get(String key){
        return contextData.get(key);
    }
}
