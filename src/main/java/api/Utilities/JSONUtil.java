package api.Utilities;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JSONUtil {


    public static String getSchemaAsString(String schemaFilePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(schemaFilePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read schema file at: " + schemaFilePath, e);
        }
    }

    public static String parseJson(String templateFilePath, Map<String, Object> dataModel) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDefaultEncoding("UTF-8");
        File templateFile = new File(templateFilePath);
        try {
            cfg.setDirectoryForTemplateLoading(templateFile.getParentFile());
            Template template = cfg.getTemplate(templateFile.getName());
            StringWriter writer = new StringWriter();
            template.process(dataModel, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
