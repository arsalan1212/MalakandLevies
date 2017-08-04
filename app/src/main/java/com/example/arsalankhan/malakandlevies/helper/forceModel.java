package com.example.arsalankhan.malakandlevies.helper;

import java.io.Serializable;

/**
 * Created by Arsalan khan on 2/10/2017.
 */

public class forceModel implements Serializable{
    private String forceName;
    private String forceRegNumber;

    public String getForceRank() {
        return forceRank;
    }

    public void setForceRank(String forceRank) {
        this.forceRank = forceRank;
    }

    public String getForceName() {
        return forceName;
    }

    public void setForceName(String forceName) {
        this.forceName = forceName;
    }

    public String getForceRegNumber() {
        return forceRegNumber;
    }

    public void setForceRegNumber(String forceRegNumber) {
        this.forceRegNumber = forceRegNumber;
    }

    private String forceRank;
}
