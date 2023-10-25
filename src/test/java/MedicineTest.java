import ludovico.ghergo.common.Patient;
import ludovico.ghergo.common.drugs.Medicine;
import ludovico.ghergo.common.patients.PhysicalPatient;
import ludovico.ghergo.enums.PatientState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicineTest
{

    @Test
    public void testAspirinFever()
    {
        Patient pt = new PhysicalPatient(PatientState.FEVER);
        assertEquals(PatientState.HEALTHY,Medicine.ASPIRIN.apply(pt));
    }
    @Test
    public void testAspirinParacetamol()
    {
        Patient pt = new PhysicalPatient(PatientState.HEALTHY);
        pt.takeDrug(Medicine.ASPIRIN);
        assertEquals(PatientState.DEAD,Medicine.PARACETAMOL.apply(pt));
    }
    @Test
    public void testAntibioticTubercolosis()
    {
        Patient pt = new PhysicalPatient(PatientState.TUBERCULOSIS);
        assertEquals(PatientState.HEALTHY,Medicine.ANTIBIOTIC.apply(pt));
    }
    @Test
    public void testTubercolosisWrong()
    {
        Patient pt = new PhysicalPatient(PatientState.TUBERCULOSIS);
        assertEquals(PatientState.TUBERCULOSIS,Medicine.ASPIRIN.apply(pt));
    }
    @Test
    public void testInsulinAntibiotic()
    {
        Patient pt = new PhysicalPatient(PatientState.HEALTHY);
        pt.takeDrug(Medicine.ANTIBIOTIC);
        assertEquals(PatientState.FEVER,Medicine.INSULIN.apply(pt));
    }

    @Test
    public void testInsulinAntibioticWrong()
    {
        Patient pt = new PhysicalPatient(PatientState.DIABETES);
        pt.takeDrug(Medicine.ANTIBIOTIC);
        assertEquals(PatientState.DIABETES_CONTROLLED,Medicine.INSULIN.apply(pt));
    }
    @Test
    public void testParacetamolFever()
    {
        Patient pt = new PhysicalPatient(PatientState.FEVER);
        assertEquals(PatientState.HEALTHY,Medicine.PARACETAMOL.apply(pt));
    }


}
