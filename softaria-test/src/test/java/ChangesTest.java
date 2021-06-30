import Exeptions.URLAlreadyExistException;
import Exeptions.URLNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ChangesTest extends Assert {

    Changes changes;
    Changes chDiff;

    @Before
    public void setUpData() {

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

        changes = new Changes(t);
        chDiff = new Changes(y, t);

    }

    @After
    public void tearDownData() {
        changes.clear();
        chDiff.clear();
    }

    @Test
    public void testToNewDay(){
        changes.newDay();
        assertEquals(changes.getYesterday(),changes.getToday());
    }

    @Test
    public void testAddURL(){
        String url = "oracle.com";
        assertFalse(changes.getToday().containsKey(url));

        try {
            changes.addURL(url, "fghjkhf");
        } catch (URLAlreadyExistException e) {
            e.printStackTrace();
            assertTrue(false);
        }

        assertTrue(changes.getToday().containsKey(url));
    }

    @Test
    public void testDeleteURL(){
        String url = "stackoverflow.com";
        assertTrue(changes.getToday().containsKey(url));

        try {
            changes.deleteURL(url);
        } catch (URLNotFoundException e) {
            e.printStackTrace();
        }

        assertFalse(changes.getToday().containsKey(url));
    }

    @Test
    public void testChangeURL(){
        String url  = "youtube.com";
        assertTrue(changes.getToday().containsKey(url));
        String str = changes.getToday().get(url);
        try {
            changes.changeURL(url,"kjahf;");
        } catch (URLNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(changes.getToday().containsKey(url));
        assertFalse(str.equals(changes.getToday().get(url)));
    }

    @Test (expected = URLAlreadyExistException.class)
    public void throwsURLAlreadyExistWhenAddURLExceptions() throws URLAlreadyExistException {
            changes.addURL("vk.com","shdgjhkgkl");
    }


    @Test (expected = URLNotFoundException.class)
    public void throwsURLNotFoundWhenDeleteURLExceptions() throws URLNotFoundException {
        changes.deleteURL("google.com");
    }


    @Test (expected = URLNotFoundException.class)
    public void throwsURLNotFoundWhenChangeURLExceptions() throws URLNotFoundException {
        changes.changeURL("google.com", "hfsjhre");
    }

    @Test
    public void testGetChangesByDay(){
        DayChanges dayChanges = new DayChanges(
            new HashSet<>(Arrays.asList("habr.com")),
            new HashSet<>(Arrays.asList("mail.ru")),
            new HashSet<>(Arrays.asList("ya.com", "stackoverflow.com"))
        );

        assertEquals( chDiff.getСhangesByDay().getAdded(), dayChanges.getAdded());
        assertEquals( chDiff.getСhangesByDay().getDeleted(), dayChanges.getDeleted());
        assertEquals( chDiff.getСhangesByDay().getChanged(), dayChanges.getChanged());
    }
}
