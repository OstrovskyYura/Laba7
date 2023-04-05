package file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import patients.Patient;
import logic.Method;

import java.io.File;
import java.io.IOException;
import java.util.List;
public class FileProcessor {
    private final String fileName = "file";
    private final String fileExtension = ".json";
    private final String directory = "C:/Users/Юра/Desktop/gtr/ret";
    private final String filePath = directory + fileName + fileExtension;
    public void CreateFile() {
        File file = new File(filePath);
        try {
            file.createNewFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void WriteFile(List<Patient> list) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), list);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public List<Patient> ReadFile(List<Patient> list) {
        File file = new File(filePath);
        if (file.length() == 0) {
            return list;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
        }
        catch (IOException err){
            err.printStackTrace();
        }
        return list;
    }
    public static List<Patient> fillPatientsArray() {
        Patient[] patients = new Patient[]{
                new Patient("John", "Broadway", 21234, 340, "flu"),
                new Patient("Mary", "Fulton Street", 14523, 250, "cancer"),
                new Patient("Bob", "Broadway", 98465, 400, "heart disease"),
                new Patient("Alice", "Fulton Street", 54638, 670, "cancer"),
                new Patient("Tom", "Broadway", 34528, 120, "flu"),
                new Patient("Robert", "Parkway", 56784, 500, "cancer"),
                new Patient("George", "Parkway", 64738, 200, "heart disease")
        };
        List<Patient> list = Method.convertToList(patients);

        return list;
    }

}