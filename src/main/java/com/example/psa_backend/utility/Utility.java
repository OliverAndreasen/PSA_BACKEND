package com.example.psa_backend.utility;

import com.example.psa_backend.entity.Card;
import com.example.psa_backend.entity.PopReport;
import com.example.psa_backend.entity.SingleEntity;
import com.example.psa_backend.repository.CardRepository;
import com.example.psa_backend.repository.PopReportRepository;
import com.example.psa_backend.repository.SingleEntityRepository;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

public class Utility {

    PopReportRepository popReportRepository;
    SingleEntityRepository singleEntityRepository;

    CardRepository cardRepository;

    public Utility(PopReportRepository popReportRepository, SingleEntityRepository singleEntityRepository, CardRepository cardRepository) {
        this.popReportRepository = popReportRepository;
        this.singleEntityRepository = singleEntityRepository;
        this.cardRepository = cardRepository;
    }


    //read csv file and return a Single Entity object
    public List<List<String>> readCsvData(String fileName) throws FileNotFoundException {
        List<List<String>> datas = new ArrayList<>();
        List<String> data = null;

        String currentWorkingDirectory = System.getProperty("user.dir");
        String filePath = "src/main/resources/csv/";
        String fullFilePath = currentWorkingDirectory + File.separator + filePath + fileName;
        File file = new File(fullFilePath);

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();

            //get the indexs of the columns we need to create a single entity object from the csv file header row
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data = List.of(line.split(","));
                datas.add(data);
            }
        }

        return datas;
    }

    public List<String> readCsvHeader(String fileName) throws FileNotFoundException {
        String currentWorkingDirectory = System.getProperty("user.dir");
        String filePath = "src/main/resources/csv/";
        String fullFilePath = currentWorkingDirectory + File.separator + filePath + fileName;
        File file = new File(fullFilePath);
        Scanner scanner = new Scanner(file);
        String header = scanner.nextLine();
        List<String> headerList = List.of(header.split(","));
        scanner.close();
        return headerList;
    }

    public int ifEmpty(String value) {
        if (value.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }


    //create single entity object from the csv file data row with the indexes of the columns we need then save it to the database
    public void saveCsvDataToDatabase(String fileName, String setName) throws FileNotFoundException {
        // Read the CSV header and store the indexes of the columns we need
        List<String> headerList = readCsvHeader(fileName);
        int cardNameIndex = headerList.indexOf("SubjectName");
        int cardNumberIndex = headerList.indexOf("CardNumber");
        int varietyIndex = headerList.indexOf("Variety");
        int grade1Index = headerList.indexOf("Grade1");
        int grade2Index = headerList.indexOf("Grade2");
        int grade3Index = headerList.indexOf("Grade3");
        int grade4Index = headerList.indexOf("Grade4");
        int grade5Index = headerList.indexOf("Grade5");
        int grade6Index = headerList.indexOf("Grade6");
        int grade7Index = headerList.indexOf("Grade7");
        int grade8Index = headerList.indexOf("Grade8");
        int grade9Index = headerList.indexOf("Grade9");
        int grade10Index = headerList.indexOf("Grade10");

        // Read the data rows from the CSV file
        List<List<String>> datas = readCsvData(fileName);

        // Create a PopReport object and save it to the database
        PopReport popReport = new PopReport();
        popReport.setSetName(setName);
        popReportRepository.save(popReport);

        // Create a list to store SingleEntity objects
        List<SingleEntity> singleEntityList = new ArrayList<>();

        for (List<String> data : datas) {
            // Create a Card object using the values from the SubjectName, CardNumber, and Variety columns
            String cardName = data.get(cardNameIndex);
            int cardNumber =  ifEmpty((data.get(cardNumberIndex)));
            String variety = data.get(varietyIndex);
            Card card = new Card(cardName, cardNumber, variety);

            // Create a SingleEntity object using the values from the Grade1 to Grade10 columns
            int grade1 = ifEmpty(data.get(grade1Index));
            int grade2 = ifEmpty(data.get(grade2Index));
            int grade3 = ifEmpty(data.get(grade3Index));
            int grade4 = ifEmpty(data.get(grade4Index));
            int grade5 = ifEmpty(data.get(grade5Index));
            int grade6 = ifEmpty(data.get(grade6Index));
            int grade7 = ifEmpty(data.get(grade7Index));
            int grade8 = ifEmpty(data.get(grade8Index));
            int grade9 = ifEmpty(data.get(grade9Index));
            int grade10 = ifEmpty(data.get(grade10Index));

            SingleEntity singleEntity = new SingleEntity(grade1, grade2, grade3, grade4, grade5, grade6, grade7, grade8, grade9, grade10);

            // Set the SingleEntity object in the Card object
            card.setSingleEntity(singleEntity);

            // Save the Card object to the database
            cardRepository.save(card);

            // Set the Card object in the SingleEntity object
            singleEntity.setCard(card);

            // Add the SingleEntity object to the list
            singleEntityList.add(singleEntity);

            // Add the SingleEntity object to the PopReport object
            popReport.addSingleEntity(singleEntity);

            // Save the PopReport object to the database
            popReportRepository.save(popReport);

            // Add the PopReport object to the SingleEntity object
            singleEntity.setPopReport(popReport);
        }

        // Save the SingleEntity objects to the database
        singleEntityRepository.saveAll(singleEntityList);
    }


    public static String hashAndSaltPassword(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // Hash the password with the salt
        PBEKeySpec spec = new PBEKeySpec(password, salt, 10000, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = skf.generateSecret(spec);
        byte[] hashedPassword = key.getEncoded();

        // Encode the salt and hashed password as a string
        return Base64.getEncoder().encodeToString(salt) + '$' + Base64.getEncoder().encodeToString(hashedPassword);
    }


    public static boolean verifyPassword(char[] password, String hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Extract the salt and hashed password from the string
        String[] parts = hashedPassword.split("\\$");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);
        // Hash the password with the salt
        PBEKeySpec spec = new PBEKeySpec(password, salt, 10000, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = skf.generateSecret(spec);
        byte[] testHash = key.getEncoded();

        // Compare the hashed password with the test hash
        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
}