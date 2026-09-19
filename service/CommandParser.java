package service;

import model.Command;
import model.TransactionType;

public class CommandParser {

    public static Command parse(String line) {

        String command[] = line.trim().split("\\s+", 3);

        TransactionType type =
                TransactionType.valueOf(command[0]);

        String accountNumber = command[1];

        long amount = Long.parseLong(command[2]);

        return new Command(
                type,
                accountNumber,
                amount
        );
    }
}