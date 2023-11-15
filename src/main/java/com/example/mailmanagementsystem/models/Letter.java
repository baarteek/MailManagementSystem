package com.example.mailmanagementsystem.models;

public class Letter extends Parcel {
    private String format;
    private boolean isPriority;

    public Letter(int parcelID, Client sender, Client recipient, Address destinationAddress, String status, String format, boolean isPriority) {
        super(parcelID, sender, recipient, destinationAddress, status);
        this.format = format;
        this.isPriority = isPriority;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }
}
