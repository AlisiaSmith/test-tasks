import Exceptions.TemplateNotFoundException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashSet;

public class PostGenerator {

    private final String EMPTY_LIST = "изменений нет";
    private final String TEMPLATE_PATH = "src/main/resources/template.txt";
    private final String FILE_GENERATE_DIR = "generated/";

    private final String DELETE = "{deleted}";
    private final String ADD = "{added}";
    private final String CHANGE = "{changed}";

    private String set2str(HashSet<String> hashSet){
        if(hashSet.isEmpty()) return EMPTY_LIST;
        StringBuilder str = new StringBuilder();
        for (String s : hashSet){
            str.append(s).append("; ");
        }
        return str.toString();
    }

    private void replaceText(Path path, String search, String replace) throws IOException {
        Files.write(path,
                new String(Files.readAllBytes(path), StandardCharsets.UTF_8).replace(search, replace)
                        .getBytes(StandardCharsets.UTF_8));
    }

    public PostGenerator(DayChanges dayChange) throws TemplateNotFoundException, IOException {

        File tmp = new File(TEMPLATE_PATH);

        if(!tmp.exists())
            throw new TemplateNotFoundException("Template file not found");

        File folder = new File(FILE_GENERATE_DIR);
        if (!folder.exists())
            folder.mkdir();

        String fileName = "out" + (new Date()).getTime() + ".txt";
        File out = new File(FILE_GENERATE_DIR + fileName);

        Files.copy(tmp.toPath(), out.toPath());

        replaceText(out.toPath(), DELETE, set2str(dayChange.getDeleted()));
        replaceText(out.toPath(), ADD, set2str(dayChange.getAdded()));
        replaceText(out.toPath(), CHANGE, set2str(dayChange.getChanged()));

    }
}


