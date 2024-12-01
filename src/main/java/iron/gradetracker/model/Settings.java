package iron.gradetracker.model;

import com.google.gson.annotations.Expose;
import iron.gradetracker.DataManager;
import javafx.beans.property.*;

import java.util.*;

public class Settings {
    @Expose private List<Grade> gradeList = new ArrayList<>();
    @Expose private BooleanProperty lessRounding = new SimpleBooleanProperty(false);

    public static class Grade {
        @Expose public final StringProperty name = new SimpleStringProperty();
        @Expose public final DoubleProperty mark = new SimpleDoubleProperty();
        @Expose public final DoubleProperty point = new SimpleDoubleProperty();

        public Grade(String name, double mark, double point) {
            this.name.set(name);
            this.mark.set(mark);
            this.point.set(point);
        }
    }

    public Settings() { this(
            new double[]{85, 75, 65, 50, 0},
            new String[]{"HD", "D", "C", "P", "F"},
            new double[]{7, 6, 5, 4, 0});
    }
    public Settings(double[] minMarks, String[] gradeNames, double[] gradePoints) {
        for (int i = 0; i < minMarks.length; i++)
            gradeList.add(new Grade(gradeNames[i], minMarks[i], gradePoints[i]));
    }

    public void startListening() {
        lessRounding.addListener((_, _, _) -> DataManager.saveSettings());
        for (Grade grade : gradeList) {
            grade.mark.addListener((_, _, _) -> DataManager.saveSettings());
            grade.point.addListener((_, _, _) -> DataManager.saveSettings());
        }
    }

    public Grade getGrade(double mark) {
        return gradeList.stream()
                .filter(grade -> grade.mark.get() <= mark)
                .max(Comparator.comparingDouble(grade -> grade.mark.get()))
                .orElse(gradeList.getLast());
    }

    public Grade getGrade(String name) {
        return gradeList.stream()
                .filter(grade -> grade.name.get().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Grade> getGrades() { return gradeList; }

    public BooleanProperty lessRoundingProperty() { return lessRounding; }
}