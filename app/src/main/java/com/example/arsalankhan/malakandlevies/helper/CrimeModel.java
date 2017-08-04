package com.example.arsalankhan.malakandlevies.helper;

import java.io.Serializable;

/**
 * Created by Arsalan khan on 2/15/2017.
 */

public class CrimeModel implements Serializable {
    private String _id;
    private String criminal_id;
    private String fir_no;
    private String fir_date;
    private String fir_section;
    private String inv_officer;
    private String comp_name;
    private String acc_name;
    private String date_of_arrest;
    private String court_decision;
    private String crime;
    private String history;
    private String details;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getDate_of_arrest() {
        return date_of_arrest;
    }

    public void setDate_of_arrest(String date_of_arrest) {
        this.date_of_arrest = date_of_arrest;
    }

    public String getCourt_decision() {
        return court_decision;
    }

    public void setCourt_decision(String court_decision) {
        this.court_decision = court_decision;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getInv_officer() {
        return inv_officer;
    }

    public void setInv_officer(String inv_officer) {
        this.inv_officer = inv_officer;
    }

    public String getFir_section() {
        return fir_section;
    }

    public void setFir_section(String fir_section) {
        this.fir_section = fir_section;
    }

    public String getFir_date() {
        return fir_date;
    }

    public void setFir_date(String fir_date) {
        this.fir_date = fir_date;
    }

    public String getFir_no() {
        return fir_no;
    }

    public void setFir_no(String fir_no) {
        this.fir_no = fir_no;
    }

    public String getCriminal_id() {
        return criminal_id;
    }

    public void setCriminal_id(String criminal_id) {
        this.criminal_id = criminal_id;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = _id;
    }




}
