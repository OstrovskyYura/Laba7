import patients.Patient;
import file.FileProcessor;
import logic.Method;
import menu.Menu;
import menu.MenuItem;

import java.util.*;
import java.util.concurrent.Callable;
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Method method = new Method();
        FileProcessor fileProcessor = new FileProcessor();
        List<Patient> list1;
        list1 = FileProcessor.fillPatientsArray();
        List<Patient> list = new ArrayList<>();
        List<Patient> finalList2 = list1;
        list.addAll(list1);
        list = fileProcessor.ReadFile(list);
        List<Patient> finalList = list;
        List<Patient> finalList1 = list;
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem("Вийти з програми", () -> {
                    System.exit(0);
                }),
                new MenuItem("Додати пацієнта", () -> {

                    String patientName = null;
                    Callable<String> nameInput = () -> {
                        System.out.println("Введіть ім'я пацієнта: ");
                        String name = scanner.nextLine();
                        if (!method.inputValidate(name)) {
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
                        if (!method.inputValidate(model)) {
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
                        if (!method.inputValidate(diagnosis)) {
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
                    method.addPatient(finalList, patientName, patientAddress, patientPhone, patientMedicalCardNumber, patientDiagnosis);
                    fileProcessor.WriteFile(finalList);
                }),
                new MenuItem("Видалити пацієнта", () -> {
                    method.showAllPatients(finalList);
                    System.out.println("Введіть id пацієнта для видалення");
                    int patientToDelete = scanner.nextInt();
                    scanner.nextLine();
                    method.removePatient(patientToDelete, finalList);
                    fileProcessor.WriteFile(finalList);
                }),
                new MenuItem("Список всіх пацієнтів", () -> {
                    method.showAllPatients(finalList);

                }),
                new MenuItem("Список пацієнтів, які мають вказаний діагноз в порядку зростання номерів медичної картки;", () -> {
                    System.out.println("Введіть діагноз: ");
                    String cartaC = scanner.nextLine();
                    System.out.println(method.searchAndSortMedicalCardNumber(cartaC, finalList));
                }),
                new MenuItem("Список пацієнтів, номер медичної карти у яких знаходиться в заданому інтервалі;", () -> {
                    System.out.println("Введіть номер медичної карти першого інтервалу: ");
                    int startCardNumber = scanner.nextInt();
                    System.out.println("Введіть номер медичної карти другого інтервалу: ");
                    int endCardNumber = scanner.nextInt();
                    System.out.println(method.searchFullNameAndPhone(startCardNumber, finalList, endCardNumber));
                }),
                new MenuItem("Кількість та список пацієнтів, номер телефона яких починається з вказаної цифри;", () -> {
                    System.out.println("Введіть цифру, з якої починається номер телефону: ");
                    String phonePrefix = scanner.next();
                    System.out.println(method.foundPatients(phonePrefix, finalList));
                }),
                new MenuItem("Список діагнозів пацієнтів (без повторів) із вказанням кількості пацієнтів, що мають цей діагноз у порядку спадання цієї кількості;", () -> {

                    method.printSortMedicalCardNumberAndPhone(finalList1);

                }),
                new MenuItem("Список діагнозів пацієнтів, зареєстрованих у системі без повторів;", () -> {

                    method.showAllPatients(finalList2);
                }),
                new MenuItem("Для кожного діагнозу визначити кількість пацієнтів, яким він поставлений.", () -> {
                    method.fullNameSort(finalList1);
                })
        );
        Menu menu = new Menu(menuItems);
        menu.run();
    }
}