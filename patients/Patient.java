package patients;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Patient implements Comparable<Patient> {
    @JsonIgnore
    private int id;
    private String fullName;
    private String address;
    private int phone;
    private int medicalCardNumber;
    private String diagnosis;
    private static int tempId;
    public Patient(String fullName, String address, int phone, int medicalCardNumber, String diagnosis) {
        tempId++;
        this.id = tempId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.medicalCardNumber = medicalCardNumber;
        this.diagnosis = diagnosis;
    }
    public Patient() {
        this("", "", 0, 0, "");
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(char phone) {
        this.phone = phone;
    }
    public int getMedicalCardNumber() {
        return medicalCardNumber;
    }
    public void setMedicalCardNumber(int medicalCardNumber) {
        this.medicalCardNumber = medicalCardNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    @Override
    public String toString() {
        return "Patient{" +
                "id = " + id + ", name = '" + fullName + '\'' + ", address = '" + address + '\'' + ", phone = " + phone +
                ", medical card number = " + medicalCardNumber + ", diagnosis = " + diagnosis + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                phone == patient.phone &&
                medicalCardNumber == patient.medicalCardNumber &&
                diagnosis == patient.diagnosis &&
                Objects.equals(fullName, patient.fullName) &&
                Objects.equals(address, patient.address);

    }
    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, address, phone, medicalCardNumber,diagnosis);
    }

    @Override
    public int compareTo(Patient o) {
        int c = Integer.compare(o.medicalCardNumber, medicalCardNumber);
        if(c==0){
            return Integer.compare(phone, o.phone);
        }
        return c;
    }

}
