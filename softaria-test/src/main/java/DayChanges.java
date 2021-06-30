import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;


@Getter
@AllArgsConstructor
public class DayChanges {

    private HashSet<String> added;
    private HashSet<String> deleted;
    private HashSet<String> changed;

    public DayChanges(){
        added = new HashSet<>();
        deleted = new HashSet<>();
        changed = new HashSet<>();
    }
}
