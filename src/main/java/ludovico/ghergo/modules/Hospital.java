package ludovico.ghergo.modules;

import ludovico.ghergo.common.Patient;
import ludovico.ghergo.common.patients.PhysicalPatient;
import ludovico.ghergo.common.drugs.Medicine;
import ludovico.ghergo.enums.PatientState;

import ludovico.ghergo.utils.HospitalUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.EnumMap;


import static ludovico.ghergo.utils.HospitalUtility.initPatientByStates;

public class Hospital
{
    private static final Logger LOGGER = LogManager.getLogger(Hospital.class);
    private ArrayList<PhysicalPatient> patients;
    private ArrayList<Medicine> medicines;
    private EnumMap<PatientState,Integer> output;


    public Hospital(ArrayList<PatientState> _patients, ArrayList<Medicine> _medicines)
    {
        this.patients   = initPatientByStates(_patients);
        this.medicines  = _medicines;
        this.output     = new EnumMap<>(PatientState.class);
    }

    public void run()
    {
        for(Patient pat: patients)
        {
            for(Medicine med: medicines)
            {
                pat.takeDrug(med);
            }
        }

        for(Patient pat: patients)
        {
            if(pat.getState() == PatientState.DEAD && HospitalUtility.isFlyingSpaghettiMonsterAppeared())
            {
                pat.setState(PatientState.HEALTHY);
            }
            output.putIfAbsent(pat.getState(), 0);
            output.computeIfPresent(pat.getState(),(key,val) -> val+1);
        }

        int fever           = output.getOrDefault(PatientState.FEVER,               0);
        int healthy         = output.getOrDefault(PatientState.HEALTHY,             0);
        int diabetes        = output.getOrDefault(PatientState.DIABETES_CONTROLLED, 0);
        int tubercolosis    = output.getOrDefault(PatientState.TUBERCULOSIS,        0);
        int dead            = output.getOrDefault(PatientState.DEAD,                0) + output.getOrDefault(PatientState.DIABETES,0);

        String result = String.format("F:%d,H:%d,D:%d,T:%d,X:%d",fever,healthy,diabetes,tubercolosis,dead);
        System.out.println(result);

    }



}
