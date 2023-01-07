package com.example.psa_backend.SetupDev;

import com.example.psa_backend.repository.CardRepository;
import com.example.psa_backend.utility.Utility;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import com.example.psa_backend.repository.PopReportRepository;
import com.example.psa_backend.repository.SingleEntityRepository;

import java.io.File;
import java.io.FileNotFoundException;


@Controller
public class SetupDev implements ApplicationRunner {

    PopReportRepository popReportRepository;
    SingleEntityRepository singleEntityRepository;

    CardRepository cardRepository;

    SetupDev(PopReportRepository popReportRepository, SingleEntityRepository singleEntityRepository, CardRepository cardRepository) {
        this.popReportRepository = popReportRepository;
        this.singleEntityRepository = singleEntityRepository;
        this.cardRepository = cardRepository;

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Utility utility = new Utility(popReportRepository, singleEntityRepository, cardRepository);

        //create a pop report
        String csvFolder = "C:/Users/maste/Dropbox/Projects/PSA_BACKEND/src/main/resources/csv/";
        processCsvFiles(csvFolder, utility);
    }

    public void processCsvFiles(String directoryPath, Utility utility) throws FileNotFoundException {
        // Create a File object for the specified directory path
        File folder = new File(directoryPath);

        // Get a list of all files in the directory
        File[] listOfFiles = folder.listFiles();

        // Iterate through the list of files
        for (File file : listOfFiles) {
            // If the current file is a CSV file, process it
            if (file.isFile() && file.getName().endsWith(".csv")) {
                // Remove any spaces from the file name
                String fileName = file.getName().replace(" ", "");
                // Convert the hyphenated file name into a set name with spaces
                String setName = fileName.substring(0, fileName.length() - 4).replace("-", " ");
                // Save the data from the CSV file to the database using the utility
                utility.saveCsvDataToDatabase(fileName, setName);
            }
        }
    }
}



