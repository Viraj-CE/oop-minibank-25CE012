package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import model.Account;

public class StatePersister {

    public static void save(Account[] accounts, Path file) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()));
            oos.writeObject(accounts);
            oos.close();
        } catch (Exception e) {

        }
    }

    public static Account[] load(Path file) {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toFile()));
            Account[] accounts = (Account[]) ois.readObject();
            ois.close();
            return accounts;
        } catch (Exception e) {
            return null;
        }
    }
}
