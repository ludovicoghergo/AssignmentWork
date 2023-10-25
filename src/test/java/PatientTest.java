import ludovico.ghergo.common.Patient;
import ludovico.ghergo.common.drugs.Medicine;
import ludovico.ghergo.common.patients.PhysicalPatient;
import ludovico.ghergo.enums.PatientState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest
{

    @Test
    public void testAddNewMedicine()
    {
        Patient pt = new PhysicalPatient(PatientState.HEALTHY);
        pt.addDrugInHistory(Medicine.INSULIN);
        assertEquals(Boolean.TRUE,pt.hasTakenDrug(Medicine.INSULIN));
    }
    @Test
    public void testClearHistory()
    {
        Patient pt = new PhysicalPatient(PatientState.HEALTHY);
        pt.addDrugInHistory(Medicine.INSULIN);
        pt.eraseDrugHistory();
        assertEquals(Boolean.FALSE,pt.hasTakenDrug(Medicine.INSULIN));
    }

}
