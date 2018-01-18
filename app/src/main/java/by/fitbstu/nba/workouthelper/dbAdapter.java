package by.fitbstu.nba.workouthelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class dbAdapter {
    private dbHelper dbHelper;
    private SQLiteDatabase database;

    public dbAdapter(Context context){
        dbHelper = new dbHelper(context.getApplicationContext());
    }
    public dbAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {dbHelper.COLUMN_ID, dbHelper.COLUMN_NAME, dbHelper.COLUMN_DAY,
                dbHelper.COLUMN_COUNT, dbHelper.COLUMN_REPEAT};
        return database.query(dbHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<workouts> getWorkouts(int day1){
        ArrayList<workouts> works = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_NAME));
                int day = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_DAY));
                int count = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_COUNT));
                int repeat = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_REPEAT));
                if(day1 == day)
                {   works.add(new workouts(id, name, day, count, repeat)); }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return works;
    }

    public workouts getWorkout(long id){
        workouts work = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",dbHelper.TABLE, dbHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});

        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_NAME));
            int day = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_DAY));
            int count = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_COUNT));
            int repeat = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_REPEAT));
            work = new workouts(id, name, day, count, repeat);
        }
        cursor.close();
        return work;
    }

    public long insert(workouts workout){
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.COLUMN_NAME, workout.Name);
        cv.put(dbHelper.COLUMN_DAY, workout.Day);
        cv.put(dbHelper.COLUMN_COUNT, workout.Count);
        cv.put(dbHelper.COLUMN_REPEAT, workout.Repeat);
        return database.insert(dbHelper.TABLE, null, cv);
    }

    public long delete(long Id){
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(Id)};
        return database.delete(dbHelper.TABLE, whereClause, whereArgs);
    }

    public long update(workouts workout){
        String whereClause = dbHelper.COLUMN_ID + "=" + String.valueOf(workout.id);
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.COLUMN_NAME, workout.Name);
        cv.put(dbHelper.COLUMN_DAY, workout.Day);
        cv.put(dbHelper.COLUMN_COUNT, workout.Count);
        cv.put(dbHelper.COLUMN_REPEAT, workout.Repeat);
        return database.update(dbHelper.TABLE, cv, whereClause, null);
    }
}
