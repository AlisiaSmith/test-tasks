import Exeptions.TemplateNotFoundException;
import Exeptions.URLAlreadyExistException;
import Exeptions.URLNotFoundException;

import java.io.IOException;
import java.util.HashMap;

public class Main {

    private static Changes var1 (){
        return new Changes();
    }

    private static Changes var2 (){
        HashMap<String, String> y = new HashMap<>();
        HashMap<String, String> t = new HashMap<>();

        y.put("vk.com","TKUI:");
        y.put("ya.com","Tfsdukl");
        y.put("mail.ru","[;lmnvbn]");
        y.put("stackoverflow.com","fsJLursrgrsoi");
        y.put("tproger.ru","ytfsJLuoir");
        y.put("youtube.com","tysyesd");

        t.put("vk.com","TKUI:");
        t.put("ya.com",":/l.k,jgm");
        t.put("stackoverflow.com","iujhbfs");
        t.put("tproger.ru","ytfsJLuoir");
        t.put("youtube.com","tysyesd");
        t.put("habr.com","fkgkhl");

        return new Changes(y,t);
    }

    private static Changes var3 (){

        HashMap<String, String> t = new HashMap<>();

        t.put("vk.com","TKUI:");
        t.put("ya.com",":/l.k,jgm");
        t.put("stackoverflow.com","iujhbfs");
        t.put("tproger.ru","ytfsJLuoir");
        t.put("youtube.com","tysyesd");
        t.put("habr.com","fkgkhl");

        Changes changes =  new Changes(t);
        changes.newDay();

        try {
            changes.addURL("mail.ru","[;lmnvbn]");
        } catch (URLAlreadyExistException e) {
            e.printStackTrace();
        }

        try {
            changes.changeURL("youtube.com","djskLHFDJ");
        } catch (URLNotFoundException e) {
            e.printStackTrace();
        }

        return changes;

    }


    public static void main(String[] args) {

        Changes changes = var3();

       try {
            new PostGenerator(changes.getСhangesByDay());
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
