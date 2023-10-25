package ludovico.ghergo.common.patients;

import ludovico.ghergo.common.Drug;
import ludovico.ghergo.common.Patient;
import ludovico.ghergo.enums.PatientState;
import ludovico.ghergo.modules.Hospital;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PhysicalPatient implements Patient
{
    private static final Logger LOGGER = LogManager.getLogger(PhysicalPatient.class);
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
        LOGGER.info("Patient has changed its state from {"+this.state+"} to {"+_newState+"}");
        this.state = _newState;
    }

    @Override
    public void takeDrug(Drug _drug)
    {
        LOGGER.info("Patient has taken the drug:{"+_drug+"}");
        this.state = _drug.apply(this);
        this.addDrugInHistory(_drug);
    }


    public void addDrugInHistory( Drug _drug)
    {
        this.history.add(_drug);
    }

    @Override
    public void eraseDrugHistory()
    {
        this.history.clear();
    }

    public boolean hasTakenDrug(Drug _drug)
    {
        return this.history.contains(_drug);
    }

}
