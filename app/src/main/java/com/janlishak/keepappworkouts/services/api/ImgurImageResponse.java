package com.janlishak.keepappworkouts.services.api;

public class ImgurImageResponse {
    //data
    private Data data;

    //response
    private boolean success;
    private int status;

    public Link getLink(){
        return new Link(false, false, data.link, data.deletehash);
    }

    private static class Data {
        //context
        private String id;
        private String title;
        private String description;
        private String type;
        private String deletehash;

        //format
        private boolean animated;
        private boolean looping;
        private int width;
        private int height;
        private String link;
        private String mp4;

        @Override
        public String toString() {
            return "Data{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", description='" + description + '\'' + ", type='" + type + '\'' + ", deletehash='" + deletehash + '\'' +
                    ", animated=" + animated + ", looping=" + looping + ", width=" + width + ", height=" + height + ", link='" + link + '\'' + ", mp4='" + mp4 + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "ImgurImageResponse{" + "data=" + data + ", success=" + success + ", status=" + status + '}';
    }
}
