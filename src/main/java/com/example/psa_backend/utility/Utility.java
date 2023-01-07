package com.example.psa_backend.utility;

import com.example.psa_backend.entity.PopReport;
import com.example.psa_backend.entity.SingleEntity;
import com.example.psa_backend.repository.PopReportRepository;
import com.example.psa_backend.repository.SingleEntityRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utility {

    PopReportRepository popReportRepository;
    SingleEntityRepository singleEntityRepository;

    public Utility(PopReportRepository popReportRepository, SingleEntityRepository singleEntityRepository) {
        this.popReportRepository = popReportRepository;
        this.singleEntityRepository = singleEntityRepository;
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
        int cardName = headerList.indexOf("SubjectName");
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
        List<SingleEntity> singleEntities = new ArrayList<>();

        // Create SingleEntity objects from the data rows and add them to the list
        for (List<String> data : datas) {
            SingleEntity singleEntity = new SingleEntity();
            singleEntity.setCardName(data.get(cardName));
            singleEntity.setCardNumber(ifEmpty(data.get(cardNumberIndex)));
            singleEntity.setVariety(data.get(varietyIndex));
            singleEntity.setGrade1(ifEmpty(data.get(grade1Index)));
            singleEntity.setGrade2(ifEmpty(data.get(grade2Index)));
            singleEntity.setGrade3(ifEmpty(data.get(grade3Index)));
            singleEntity.setGrade4(ifEmpty(data.get(grade4Index)));
            singleEntity.setGrade5(ifEmpty(data.get(grade5Index)));
            singleEntity.setGrade6(ifEmpty(data.get(grade6Index)));
            singleEntity.setGrade7(ifEmpty(data.get(grade7Index)));
            singleEntity.setGrade8(ifEmpty(data.get(grade8Index)));
            singleEntity.setGrade9(ifEmpty(data.get(grade9Index)));
            singleEntity.setGrade10(ifEmpty(data.get(grade10Index)));
            singleEntity.setPopReport(popReport);

            // Add the SingleEntity object to the list
            singleEntities.add(singleEntity);
        }

        // Save the SingleEntity objects to the database in a single transaction
        singleEntityRepository.saveAll(singleEntities);
        }
}
