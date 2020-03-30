import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Tachograph {

    Table<Integer, String, Integer> TrainTable = HashBasedTable.create();

    public void fo(int time, int joystick, int ref){
            TrainTable.put(time, "joystick", joystick);
            TrainTable.put(time, "ref", ref);
    }

    public int getJoystick(int time){
        int speed = TrainTable.get(time, "joystick");
        return speed;
    }

    public int getRef(int time){
        int speed = TrainTable.get(time, "ref");
        return speed;
    }

}
