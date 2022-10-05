package ru.job4j.attestation;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int score = 0;
        int count = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                score = score + subject.score();
                count++;
            }
        }
        return score / (count * 1.0);
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<Label>();
        int score = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                score = score + subject.score();
            }
            double average = score / (pupil.subjects().size() * 1.0);
            labels.add(new Label(pupil.name(), average));
            score = 0;
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Integer> map = getStringIntegerMap(pupils);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            labels.add(new Label(entry.getKey(), entry.getValue() / (pupils.size() * 1.0)));
        }
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<Label>();
        int score = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                score = score + subject.score();
            }
            labels.add(new Label(pupil.name(), score));
            score = 0;
        }
        Collections.sort(labels);
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Integer> map = getStringIntegerMap(pupils);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            labels.add(new Label(entry.getKey(), entry.getValue()));
        }
        Collections.sort(labels);
        return labels.get(labels.size() - 1);
    }

    private static Map<String, Integer> getStringIntegerMap(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        int sum = 0;
        List<Subject> subjects = pupils.get(0).subjects();
        for (int j = 0; j < subjects.size(); j++) {
            for (Pupil pupil : pupils) {
                for (Subject subject : pupil.subjects()) {
                    if (subjects.get(j).name().equals(subject.name())) {
                        sum += subject.score();
                    }
                }
            }
            map.put(subjects.get(j).name(), sum);
            sum = 0;
        }
        return map;
    }
}