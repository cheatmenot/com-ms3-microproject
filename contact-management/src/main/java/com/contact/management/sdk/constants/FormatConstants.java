package com.contact.management.sdk.constants;

public class FormatConstants {

    private FormatConstants() {
    }

    public static final String DATE_DATE = "yyyy-MM-dd";

    public static final String DATE_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIMESTAMPWTZ = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";

    //https://stackoverflow.com/questions/54349308/java-spring-jackson-deserialization-to-zoneddatetime
    public static final String DATETIME_WTZ = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

}
