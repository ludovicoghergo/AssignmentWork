package ludovico.ghergo.common.patients;

import ludovico.ghergo.common.Drug;
import ludovico.ghergo.common.Patient;
import ludovico.ghergo.enums.PatientState;

import java.util.ArrayList;

public class PhysicalPatient implements Patient
{
    private PatientState state;
    private ArrayList<Drug> history;

    public PhysicalPatient(PatientState _state)
    {
        this.state      = _state;
        this.history    = new ArrayList<>();
    }

    public PatientState getState()
    {
        return state;
    }
    public void setState(PatientState _newState)
    {
        this.state = _newState;
    }


    public void addDrugInHistory( Drug _drug)
    {
        this.history.add(_drug);
    }
    public boolean hasTakenDrug(Drug _drug)
    {
        return this.history.contains(_drug);
    }

}
