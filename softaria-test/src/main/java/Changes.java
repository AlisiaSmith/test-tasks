import Exeptions.URLAlreadyExistException;
import Exeptions.URLNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Changes {

    private HashMap<String, String> yesterday;
    private HashMap<String, String> today;

    public Changes(){
        yesterday = new HashMap<>();
        today = new HashMap<>();
    }

    public Changes(HashMap<String, String> today){
        yesterday = new HashMap<>();
        this.today = today;
    }

    private  HashSet<String> deletedList(){
        HashSet<String> result = new HashSet<>();

        for(Map.Entry <String, String> entry : yesterday.entrySet())
            if(!today.containsKey(entry.getKey())) result.add(entry.getKey());

        return result;
    }

    private HashSet<String> addedList(){
        HashSet<String> result = new HashSet<>();

        for(Map.Entry <String, String> entry : today.entrySet())
            if(!yesterday.containsKey(entry.getKey())) result.add(entry.getKey());

        return result;
    }

    private HashSet<String> changedList(){
        HashSet<String> result = new HashSet<>();

        for(Map.Entry <String, String> entry : today.entrySet())
            if(yesterday.containsKey(entry.getKey()))
               if(!yesterday.get(entry.getKey()).equals(entry.getValue()))
                   result.add(entry.getKey());

        return result;
    }


    // you can add only those elements that do not exist yet
    public void addURL(String url, String htmlDocument) throws URLAlreadyExistException {
        if (today.containsKey(url)) throw new URLAlreadyExistException("URL " + url + " is already in hash table");
        today.put(url, htmlDocument);
    }

    public void deleteURL(String url) throws URLNotFoundException {
        if (!today.containsKey(url)) throw new URLNotFoundException("URL " + url + " not fount in hash table");
        today.remove(url);
    }

    public void changeURL(String url, String htmlDocument) throws URLNotFoundException {
        if (!today.containsKey(url)) throw new URLNotFoundException("URL " + url + " not fount in hash table");
        today.put(url, htmlDocument);
    }

    public DayChanges getСhangesByDay(){
        if(today.equals(yesterday)) return new DayChanges();
        return new DayChanges(addedList(), deletedList(), changedList());
    }

    public void newDay(){
        yesterday.clear();
        yesterday.putAll(today);
    }

    public void clear() {
        yesterday.clear();
        today.clear();
    }
}
