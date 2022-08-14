package softserve.com.model.day_time;

public enum DayType {
    DAY, NIGHT;

   public static String getDay(int value) {
        return switch (value) {
            case 1 -> DayType.DAY.name();
            case 2 -> DayType.NIGHT.name();
            default -> throw new IllegalArgumentException();
        };
    }
}
