package logic;

import patients.Patient;

import java.util.*;

public class Method {
    public void addPatient(List<Patient> list, String fullName, String address, int phone, int medicalCardNumber, String diagnosis) {
        Patient patient = new Patient(fullName, address, phone, medicalCardNumber, diagnosis);
        list.add(patient);
    }
    public void removePatient(int patientToDelete, List<Patient> list) {
        if (list.isEmpty()) {
            return;
        }
        boolean remove = list.removeIf(patient -> patient.getId() == patientToDelete);
        if (!remove) {
            return;
        }
    }

    public void showAllPatients(List<Patient> list) {
        if (list.isEmpty()) {
            return;
        }
//        list.forEach(System.out::println);
        Set<String> diagnoses = new HashSet<>();
        diagnoses.add("flu");
        diagnoses.add("cold");
        diagnoses.add("cancer");
        diagnoses.add("flu");
        diagnoses.add("cold");
        diagnoses.add("heart disease");

        for (String diagnosis : diagnoses) {
            System.out.println(diagnosis);
        }
    }

    public List<Patient> searchAndSortMedicalCardNumber(String diagnosis, List<Patient> list) {
        if (list.isEmpty()) {
            return null;
        }
        List<Patient> tempList = new ArrayList<>();
        list.forEach(patient -> {
            if (patient.getDiagnosis().equals(diagnosis))
                tempList.add(patient);
        });
        Collections.sort(list, Comparator.comparing(Patient::getMedicalCardNumber));
        return tempList;

    }

    public List<Patient> searchFullNameAndPhone(int startCardNumber, List<Patient> list, int endCardNumber) {
        if (list.isEmpty()) {
            return null;
        }
        List<Patient> tempList = new ArrayList<>();
        list.forEach(patient -> {
            if (patient.getMedicalCardNumber() >= startCardNumber && patient.getMedicalCardNumber() <= endCardNumber) {
                    tempList.add(patient);
            }
        });
        return tempList;
    }

    public List<Patient> foundPatients(List<patients.Patient> list, String phonePrefix, String phoneNumber) {
        if (list.isEmpty()) {
            return null;
        }
        List<patients.Patient> tempList = new ArrayList<>();
        list.forEach(patient -> {
                if (phoneNumber.startsWith(phonePrefix)) {
                    tempList.add(patient);
                }
        });
        return tempList;
    }

    public boolean inputValidate(String input) {
        if (input.contains(" ") || input.contains("\n") ||
                input.contains("\t") || input.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<Patient> printSortMedicalCardNumberAndPhone(List<Patient> list){
        Map<String, Integer> diagnosisCount = new HashMap<>();
        for (Patient patient : list.toArray(new Patient[0])) {
            String diagnosis = patient.getDiagnosis();
            if (diagnosisCount.containsKey(diagnosis)) {
                diagnosisCount.put(diagnosis, diagnosisCount.get(diagnosis) + 1);
            } else {
                diagnosisCount.put(diagnosis, 1);
            }
        }

        List<Map.Entry<String, Integer>> diagnosisList = new ArrayList<>(diagnosisCount.entrySet());

        Collections.sort(diagnosisList, (a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : diagnosisList) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " patients");
        }
        return null;
    }

    public static <T> List<T> convertToList(T[] arr)
    {
        List<T> list = new ArrayList<>();
        for (T i: arr) {
            list.add(i);
        }
        return list;
    }

    public static List<Patient> fullNameSort(List<Patient> list){
        Map<String, Integer> diagnosisCount = new HashMap<>();
        for (Patient patient : list.toArray(new Patient[0])) {
            String diagnosis = patient.getDiagnosis();
            if (diagnosisCount.containsKey(diagnosis)) {
                diagnosisCount.put(diagnosis, diagnosisCount.get(diagnosis) + 1);
            } else {
                diagnosisCount.put(diagnosis, 1);
            }
        }
        for (Map.Entry<String, Integer> entry : diagnosisCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
//        Map<String, List<Patient>> patientMap = new HashMap<>();
//        for (Patient patient : list) {
//            String fullName = patient.getFullName();
//            if (!patientMap.containsKey(fullName)) {
//                patientMap.put(fullName, new ArrayList<>());
//            }
//            patientMap.get(fullName).add(patient);
//        }
//
//        for (String fullName : patientMap.keySet()) {
//            System.out.println("ПІБ: " + fullName);
//            List<Patient> patientList = patientMap.get(fullName);
//            for (Patient patient : patientList) {
//                System.out.println("Patient{" +
//                        "id = " + patient.getId() + ", address = '" + patient.getAddress() + '\'' + ", phone = " + patient.getPhone() +
//                        ", medical card number = " + patient.getMedicalCardNumber() + ", diagnosis = " + patient.getDiagnosis() + '}');
//            }
//            System.out.println();
//        }

        return list;
    }
}