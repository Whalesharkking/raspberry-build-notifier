package ch.loewenfels.raspberrybuildnotifier;

import java.util.ArrayList;
import java.util.List;

public class ResponsilbleJobs {
    private String piNr;
    private ArrayList<String> responsibleList = new ArrayList<>();

    public String getPiNr() {
        return piNr;
    }

    public void setPiNr(final String piNr) {
        this.piNr = piNr;
    }

    public List<String> getResponsibleList() {
        return responsibleList;
    }

    public void setResponsibleList(final ArrayList<String> responsibleList) {
        this.responsibleList = responsibleList;
    }
}
