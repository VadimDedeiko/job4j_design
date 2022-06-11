package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> listLog = new ArrayList<>();
        ConsoleChat consoleChat = new ConsoleChat(path, botAnswers);
        List<String> list = consoleChat.readPhrases();
        Scanner scanner = new Scanner(System.in);
        boolean isTrue;
        String console;
        console = scanner.nextLine();
        while (!OUT.equals(console)) {
            isTrue = true;
            if (STOP.equals(console)) {
                while (isTrue) {
                    console = scanner.nextLine();
                    listLog.add(console);
                    if (CONTINUE.equals(console) || OUT.equals(console)) {
                        isTrue = false;
                    }
                }
            }
            if (isTrue) {
                programResponse(listLog, list);
                console = scanner.nextLine();
            }
            if (CONTINUE.equals(console)) {
                while (!STOP.equals(console)) {
                    programResponse(listLog, list);
                    console = scanner.nextLine();
                    if (OUT.equals(console)) {
                        break;
                    }
                    if (CONTINUE.equals(console)) {
                        programResponse(listLog, list);
                    }
                }
            }
        }
        saveLog(listLog);
        scanner.close();
    }

    private void programResponse(List<String> listLog, List<String> list) {
        String usrText;
        usrText = list.get((int) (Math.random() * (list.size() - 1)));
        listLog.add(usrText);
        System.out.println(usrText);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader fileMemory = new BufferedReader(
                new FileReader(path, Charset.forName("WINDOWS-1251"))
        )) {
            String s;
            while ((s = fileMemory.readLine()) != null) {
                list.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter logMemory = new PrintWriter(
                new FileWriter(botAnswers, Charset.forName("WINDOWS-1251"))
        )) {
            for (String str : log) {
                logMemory.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ConsoleChat cc = new ConsoleChat("./data/Path.txt", "./data/Log.txt");
        cc.run();
    }
}