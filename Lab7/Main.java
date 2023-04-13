package Lab7;

import Lab7.patients.Patient;
import Lab7.file.File;
import Lab7.functions.Functions;
import Lab7.info.Info;
import Lab7.info.InfoItem;

import java.util.*;
import java.util.concurrent.Callable;
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Functions functions = new Functions();
        File file = new File();
        List<Patient> list1;
        list1 = File.fillPatientsArray();
        List<Patient> list = new ArrayList<>();
        List<Patient> finalList2 = list1;
        list.addAll(list1);
        list = file.ReadFile(list);
        List<Patient> finalList = list;
        List<Patient> finalList1 = list;
        List<InfoItem> infoItems = Arrays.asList(
                new InfoItem("Вийти з програми", () -> {
                    System.out.println();
                    System.exit(0);
                }),
                new InfoItem("Додати пацієнта", () -> {

                    String patientName = null;
                    Callable<String> nameInput = () -> {
                        System.out.println("Введіть ім'я пацієнта: ");
                        String name = scanner.nextLine();
                        if (!functions.inputValidate(name)) {
                            System.out.println("Некоректно введені дані");
                            return null;
                        }
                        return name;
                    };
                    try {
                        do {
                            patientName = nameInput.call();
                        }

                        while (patientName == null);
                    }
                    catch (Exception err) {
                        err.printStackTrace();
                    }

                    String patientAddress = null;
                    Callable<String> addressInput = () -> {
                        System.out.println("Введіть адресу пацієнта: ");
                        String model = scanner.nextLine();
                        if (!functions.inputValidate(model)) {
                            System.out.println("Некоректно введені дані");
                            return null;
                        }
                        return model;
                    };
                    try {
                        do {
                            patientAddress =addressInput.call();
                        }

                        while (patientAddress == null);
                    }
                    catch (Exception err) {
                        err.printStackTrace();
                    }

                    int patientPhone = 0;
                    Callable<Integer> phoneInput = () -> {
                        System.out.println("Введіть номер телефона пацієнта: ");
                        int phone;
                        try {
                            phone = scanner.nextInt();
                            scanner.nextLine();
                        }
                        catch (InputMismatchException err) {
                            System.out.println("Некоректно введені дані");
                            scanner.next();
                            return -1;
                        }
                        return phone;
                    };
                    try {
                        do {
                            patientPhone = phoneInput.call();
                        }
                        while (patientPhone == -1);
                    }
                    catch (Exception err) {
                        err.printStackTrace();
                    }

                    int patientMedicalCardNumber = 0;
                    Callable<Integer> medicalCardNumberInput = () -> {
                        System.out.println("Введіть номер медичної картки пацієнта: ");
                        int medicalCardNumber;
                        try {
                            medicalCardNumber = scanner.nextInt();
                            scanner.nextLine();
                        }
                        catch (InputMismatchException err) {
                            System.out.println("Некоректно введені дані");
                            scanner.next();
                            return -1;
                        }
                        return medicalCardNumber;
                    };
                    try {
                        do {
                            patientMedicalCardNumber = medicalCardNumberInput.call();
                        }
                        while (patientMedicalCardNumber == -1);
                    }
                    catch (Exception err) {
                        err.printStackTrace();
                    }

                    String patientDiagnosis = null;
                    Callable<String> diagnosisInput = () -> {
                        System.out.println("Введіть діагноз пацієнта: ");
                        String diagnosis = scanner.nextLine();
                        if (!functions.inputValidate(diagnosis)) {
                            System.out.println("Некоректно введені дані");
                            return null;
                        }
                        return diagnosis;
                    };
                    try {
                        do {
                            patientDiagnosis = diagnosisInput.call();
                        }

                        while (patientDiagnosis == null);
                    }
                    catch (Exception err) {
                        err.printStackTrace();
                    }
                    functions.addPatient(finalList, patientName, patientAddress, patientPhone, patientMedicalCardNumber, patientDiagnosis);
                    file.WriteFile(finalList);
                }),
                new InfoItem("Видалити пацієнта", () -> {
                    functions.showAllPatients(finalList);
                    System.out.println("Введіть id пацієнта для видалення");
                    int patientToDelete = scanner.nextInt();
                    scanner.nextLine();
                    functions.removePatient(patientToDelete, finalList);
                    file.WriteFile(finalList);
                }),
                new InfoItem("Список всіх пацієнтів", () -> {
                    functions.showAllPatients(finalList);

                }),
                new InfoItem("Список пацієнтів, які мають вказаний діагноз в порядку зростання номерів медичної картки;", () -> {
                    System.out.println("Введіть діагноз: ");
                    String cartaC = scanner.nextLine();
                    System.out.println(functions.searchAndSortMedicalCardNumber(cartaC, finalList));
                }),
                new InfoItem("Список пацієнтів, номер медичної карти у яких знаходиться в заданому інтервалі;", () -> {
                    System.out.println("Введіть номер медичної карти першого інтервалу: ");
                    int startCardNumber = scanner.nextInt();
                    System.out.println("Введіть номер медичної карти другого інтервалу: ");
                    int endCardNumber = scanner.nextInt();
                    System.out.println(functions.searchFullNameAndPhone(startCardNumber, finalList, endCardNumber));
                }),
                new InfoItem("Кількість та список пацієнтів, номер телефона яких починається з вказаної цифри;", () -> {
                    System.out.println("Введіть цифру, з якої починається номер телефону: ");
                    String phonePrefix = scanner.next();
                    System.out.println(functions.foundPatients(phonePrefix, finalList));
                }),
                new InfoItem("Список діагнозів пацієнтів (без повторів) із вказанням кількості пацієнтів, що мають цей діагноз у порядку спадання цієї кількості;", () -> {

                    functions.ListOfPatientDiagnoses(finalList1);

                }),
                new InfoItem("Список діагнозів пацієнтів, зареєстрованих у системі без повторів;", () -> {
                    System.out.println("Список діагнозів зареєстрованих без повторів:\n");
                    functions.showAllDiagnosisPatients(finalList2);
                }),
                new InfoItem("Для кожного діагнозу визначити кількість пацієнтів, яким він поставлений.", () -> {
                    functions.fullNameSort(finalList1);
                })
        );
        Info info = new Info(infoItems);
        info.run();
    }
}
