package lab7.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab7.patients.Patient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
public class File {
    private final String fileName = "file";
    private final String fileExtension = ".json";
    private final String directory = "C:/Users/Юра/Desktop/gtr/ret";
    private final String filePath = directory + fileName + fileExtension;
    public void WriteFile(List<Patient> list) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new java.io.File(filePath), list);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public List<Patient> ReadFile(List<Patient> list) {
        java.io.File file = new java.io.File(filePath);
        if (file.length() == 0) {
            return list;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(new java.io.File(filePath), new TypeReference<>() {});
        }
        catch (IOException err){
            err.printStackTrace();
        }
        return list;
    }
    public static List<Patient> fillPatientsArray() {
        Patient[] patients = new Patient[]{
                new Patient("John", "Broadway", 21234, 340, "flu"),
                new Patient("Mary", "Fulton Street", 14523, 250, "headache"),
                new Patient("Bob", "Broadway", 98465, 400, "broken arm"),
                new Patient("Alice", "Fulton Street", 54638, 670, "headache"),
                new Patient("Tom", "Broadway", 34528, 120, "flu"),
                new Patient("Robert", "Parkway", 56784, 500, "headache"),
                new Patient("George", "Parkway", 64738, 200, "broken arm")
        };
        List<Patient> list = Arrays.asList(patients);
        return list;
    }

}