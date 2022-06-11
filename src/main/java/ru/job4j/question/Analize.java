package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> previousMap = new HashMap<>();
        int add = 1;
        int del = 1;
        int ch = 1;

        for (User userPrevious : previous) {
            previousMap.put(userPrevious.getId(), userPrevious.getName());
        }

        for (User userCurrent : current) {
            int idCurr = userCurrent.getId();
            String nameCurr = userCurrent.getName();
            if (previousMap.get(idCurr) != null && !previousMap.get(idCurr).equals(nameCurr)) {
                info.setChanged(ch++);
            } else if (previousMap.get(idCurr) == null) {
                info.setAdded(add++);
                if (current.size() == previousMap.size()) {
                    info.setDeleted(del++);
                }
            }
            int diffSize = previousMap.size() - current.size();
            if (diffSize > 0) {
                info.setDeleted(diffSize);
            }
        }
        return info;
    }
}
