package com.example.practice.Notes;

public class PDFResponse {
    private String pdfNotesUrl;
    private String videoUrl;

    public PDFResponse(String pdfNotesUrl, String videoUrl) {
        this.pdfNotesUrl = pdfNotesUrl;
        this.videoUrl = videoUrl;
    }

    public String getPdfNotesUrl() {
        return pdfNotesUrl;
    }

    public void setPdfNotesUrl(String pdfNotesUrl) {
        this.pdfNotesUrl = pdfNotesUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
