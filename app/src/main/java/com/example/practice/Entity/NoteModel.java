package com.example.practice.Entity;

public class NoteModel {
    private String pdfNotesUrl;

    public String getPdfNotesUrl() {
        return pdfNotesUrl;
    }

    public void setPdfNotesUrl(String pdfNotesUrl) {
        this.pdfNotesUrl = pdfNotesUrl;
    }

    public NoteModel(String pdfNotesUrl) {
        this.pdfNotesUrl = pdfNotesUrl;
    }
}
