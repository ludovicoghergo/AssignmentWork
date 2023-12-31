package ludovico.ghergo.common;

import ludovico.ghergo.enums.PatientState;

public interface Patient
{
    boolean hasTakenDrug(Drug _drug);
    PatientState getState();
    void setState(PatientState _patientState);
    void takeDrug(Drug _drug);
    void addDrugInHistory( Drug _drug);
    void eraseDrugHistory();
}
