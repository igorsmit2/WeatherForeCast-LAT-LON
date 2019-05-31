package br.usjt;

import android.provider.BaseColumns;

public class LocationContract {
    private LocationContract (){}

    public static class Location implements BaseColumns{
        public static final String TABLE_NAME = "location";
        public static final String COLUMN_NAME_ID = "location_id";
        public static final String COLUMN_NAME_LAT = "latitude";
        public static final String COLUMN_NAME_LON = "longitude";
    }

    public static String createTableLocation(){
        return String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s REAL, $ REAL);",
                Location.TABLE_NAME,
                Location.COLUMN_NAME_ID,
                Location.COLUMN_NAME_LAT,
                Location.COLUMN_NAME_LON
        );
    }
}
