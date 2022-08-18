package softserve.com.model.day_time;

public enum DayType {
    DAY, NIGHT;

    public static String setDayTime(int value) {
        return switch (value) {
            case 1 -> DAY.name();
            case 2 -> NIGHT.name();
            default -> throw new IllegalArgumentException();
        };
    }
}
