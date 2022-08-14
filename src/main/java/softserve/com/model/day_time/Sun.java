package softserve.com.model.day_time;

import java.util.Random;

public class Sun {
    private static Sun sun = null;
    private DayType dayType;

    private Sun() {
        this.dayType = DayType.valueOf(DayType.getDay(new Random().nextInt(2) + 1));
    }

    public static Sun getInstance() {
        if (sun == null)
            sun = new Sun();

        return sun;
    }

    public DayType getDayType() {
        return dayType;
    }
}
