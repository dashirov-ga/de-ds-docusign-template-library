package ly.generalassemb.de.dataservices.model;

import ly.generalassemb.de.dataservices.constants.State;

public class Person {
    private State stateOfResidence;

    public State getStateOfResidence() {
        return stateOfResidence;
    }

    public void setStateOfResidence(State stateOfResidence) {
        this.stateOfResidence = stateOfResidence;
    }
}
