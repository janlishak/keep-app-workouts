package com.janlishak.keepappworkouts.services.api;

public class Link {
    private boolean isLoading;
    private boolean failed;
    private String link;
    private String deleteHash;

    public Link(boolean isLoading, boolean failed, String link) {
        this.isLoading = isLoading;
        this.failed = failed;
        this.link = link;
    }

    public Link(boolean isLoading, boolean failed, String link, String deleteHash) {
        this.isLoading = isLoading;
        this.failed = failed;
        this.link = link;
        this.deleteHash = deleteHash;
    }

    Link(){
        isLoading = true;
        failed = false;
    }

    public String getDeleteHash() { return deleteHash; }
    public void setDeleteHash(String deleteHash) { this.deleteHash = deleteHash; }
    public boolean isLoading() {
        return isLoading;
    }
    public void setLoading(boolean loading) {
        isLoading = loading;
    }
    public boolean isFailed() {
        return failed;
    }
    public void setFailed(boolean failed) {
        this.failed = failed;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() { return "Link{" + "isLoading=" + isLoading + ", failed=" + failed + ", link='" + link + '\'' + '}'; }
}
