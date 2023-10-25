package ludovico.ghergo.common;

import ludovico.ghergo.enums.PatientState;

public interface Drug
{
    default  PatientState apply(Patient _patient)
    {
        return PatientState.DEAD;
    }

}
