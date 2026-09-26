package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportGenerator {

    public static void generate() {

        try {
            Path logsDir = Paths.get("logs");
            Path report = Paths.get("report.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(report.toFile()));

            long totalDeposit = 0;
            long totalWithdraw = 0;

            if (Files.exists(logsDir)) {
                DirectoryStream<Path> ds = Files.newDirectoryStream(logsDir);
                for (Path p : ds) {
                    BufferedReader br = new BufferedReader(new java.io.FileReader(p.toFile()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split("\\s+");
                        if (parts.length >= 3) {
                            if (parts[0].equals("DEPOSIT")) {
                                totalDeposit += Long.parseLong(parts[2]);
                            } else if (parts[0].equals("WITHDRAW")) {
                                totalWithdraw += Long.parseLong(parts[2]);
                            }
                        }
                    }
                    br.close();
                }
                ds.close();
            }

            bw.write("Total Deposit : " + totalDeposit);
            bw.newLine();
            bw.write("Total Withdraw : " + totalWithdraw);
            bw.newLine();
            bw.write("Net : " + (totalDeposit - totalWithdraw));
            bw.close();

        } catch (Exception e) {

        }
    }
}
