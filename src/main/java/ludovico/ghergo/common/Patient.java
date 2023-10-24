package ludovico.ghergo.common;

import ludovico.ghergo.enums.PatientState;

public interface Patient
{
    boolean hasTakenDrug(Drug _drug);
    PatientState getState();
    void setState(PatientState _patientState);
    void addDrugInHistory( Drug _drug);
}
