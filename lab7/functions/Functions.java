package lab7.functions;

import lab7.patients.Patient;

import java.util.*;

public class Functions {
    public void addPatient(List<Patient> list, String fullName, String address, int phone, int medicalCardNumber, String diagnosis) {
        Patient patient = new Patient(fullName, address, phone, medicalCardNumber, diagnosis);
        list.add(patient);
    }
    public void removePatient(int patientToDelete, List<Patient> list) {
        boolean remove = list.removeIf(patient -> patient.getId() == patientToDelete);
        if (!remove) {}
    }

    public void showAllPatients(List<Patient> list) {
        for (Patient patient : list) {
            System.out.println(patient.toString());
        }
    }
    public void showAllDiagnosisPatients(List<Patient> list) {
        Set<String> diagnoses = new HashSet<>();
        for (Patient patient : list) {
            diagnoses.add(patient.getDiagnosis());
        }
        System.out.println(diagnoses);
    }
    public List<Patient> searchAndSortMedicalCardNumber(String diagnosis, List<Patient> list) {
        List<Patient> tempList = new ArrayList<>();
        for (Patient patient : list) {
            if (patient.getDiagnosis().equals(diagnosis)) {
                tempList.add(patient);
            }
        }
        Collections.sort(list, Comparator.comparing(Patient::getMedicalCardNumber));
        return tempList;

    }

    public List<Patient> searchFullNameAndPhone(int startCardNumber, List<Patient> list, int endCardNumber) {
        List<Patient> tempList = new ArrayList<>();
        for (Patient patient : list) {
            if (patient.getMedicalCardNumber() >= startCardNumber && patient.getMedicalCardNumber() <= endCardNumber) {
                tempList.add(patient);
            }
        }
        return tempList;
    }

    public List<Patient> foundPatients(String phonePrefix, List<lab7.patients.Patient> list) {
        List<lab7.patients.Patient> tempList = new ArrayList<>();
        for (Patient patient : list) {
            if (Integer.toString(patient.getPhone()).startsWith(phonePrefix)) {
                tempList.add(patient);
            }
        }
        return tempList;
    }

    public List<Patient> ListOfPatientDiagnoses(List<Patient> list){
        Map<String, Integer> diagnosisCount = new HashMap<>();
        for (Patient patient : list) {
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

    public static List<Patient> fullNameSort(List<Patient> list){
        Map<String, Integer> diagnosisCount = new HashMap<>();
        for (Patient patient : list) {
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
        return list;
    }
}