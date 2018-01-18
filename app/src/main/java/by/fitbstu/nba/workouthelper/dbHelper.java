package by.fitbstu.nba.workouthelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "HELPER.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "workouts"; // название таблицы в бд
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_COUNT = "count";
    public static final String COLUMN_REPEAT = "repeat";

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE +" ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DAY + " INTEGER, "
                + COLUMN_COUNT + " INTEGER, " + COLUMN_REPEAT + ");"
        );
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME + ", " + COLUMN_DAY + ", " + COLUMN_COUNT + ", "+ COLUMN_REPEAT +") VALUES ('pushups', 1, 3, 20);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(db);
    }
}
