package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        float strong = 33.2f;
        double length = 1.8d;
        char type = 'a';
        byte typeByte = (byte) 'b';
        boolean angry = true;
        short idea = 125;
        long year = 1990;

        LOG.debug("User info age : {}, strong : {}, length"
                + " : {}, type : {}, typeByte : {}, angry : {}, idea"
                + " : {}, year : {}", age, strong, length, type, typeByte, angry, idea, year);
    }
}