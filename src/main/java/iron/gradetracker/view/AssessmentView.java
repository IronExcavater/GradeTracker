package iron.gradetracker.view;

import iron.gradetracker.model.*;

public class AssessmentView extends DataView<AssessmentData> {

    private final DoubleTextField score;
    private final DoubleTextField maxScore;
    private final IntegerTextField weight;

    public AssessmentView(AssessmentData data) {
        super(data, new int[]{55, 15, 15, 15}, new String[]{"Assessment Name", "Score", "Max Score", "Weight"}, "Unnamed Assessment");
        score = new DoubleTextField(data.scoreProperty(), true, data.maxScoreProperty());
        maxScore = new DoubleTextField(data.maxScoreProperty(), true);
        weight = new IntegerTextField(data.weightProperty(), true, data.remainingWeightProperty());
        setColumns(name, score, maxScore, weight);
    }
}
