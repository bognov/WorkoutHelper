package by.fitbstu.nba.workouthelper;

/**
 * Created by nowik on 1/17/2018.
 */

public class workouts {
    public long id;
    public String Name;
    public int Day;
    public int Count;
    public int Repeat;

    public workouts(long id, String name, int day, int count, int repeat)
    {
        this.id = id;
        this.Name = name;
        this.Day = day;
        this.Count = count;
        this.Repeat = repeat;
    }

    public String toString(){
        return this.Name;
    }
}
