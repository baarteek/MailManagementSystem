package org.example.models;

public class Letter extends Parcel {
    private String format;
    private boolean isPriority;

    private Letter(int parcelID, Client sender, Client recipient, Address destinationAddress, String status, String format, boolean isPriority) {
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


    public static class Builder {
        private int parcelID;
        private Client sender;
        private Client recipient;
        private Address destinationAddress;
        private String status;
        private String format = "standard";
        private boolean isPriority = false;

        public Letter build() {
            return new Letter(parcelID, sender, recipient, destinationAddress, status, format, isPriority);
        }

        public Builder parcelID(int parcelID) {
            this.parcelID = parcelID;
            return this;
        }

        public Builder sender(Client sender) {
            this.sender = sender;
            return this;
        }

        public Builder recipient(Client recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder destinationAddress(Address destinationAddress) {
            this.destinationAddress = destinationAddress;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public Builder isPriority(boolean isPriority) {
            this.isPriority = isPriority;
            return this;
        }

    }
}
