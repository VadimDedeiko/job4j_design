package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }

        final Contact contact1 = new Contact(37600, "+7 (111) 111-22-22");
        var temp = Files.createTempFile("tmp", "temp.tmp").toFile();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(temp))) {
            objectOutputStream.writeObject(contact1);
        }


        try (FileInputStream fileInputStream = new FileInputStream(temp);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Contact contactDeser = (Contact) objectInputStream.readObject();
            System.out.println(contactDeser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}