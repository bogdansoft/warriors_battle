package softserve.com.model.day_time;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sun {
    private static Lock lock = new ReentrantLock();
    private static Sun sun = null;
    private DayType dayType;

    private Sun() {
        this.dayType = DayType.valueOf(DayType.setDayTime(new Random().nextInt(2) + 1));
    }

    public static Sun getInstance() {
        lock.lock();

        if (sun == null)
            sun = new Sun();

        lock.unlock();

        return sun;
    }


    public DayType getDayType() {
        return dayType;
    }
}
