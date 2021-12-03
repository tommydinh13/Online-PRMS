package Database;

public class PropertyFee {
    private int period = 60;
    private int fee;
    private String periodStartDate;
    private String periodEndDate;

    // Constructors
    public PropertyFee() {}
    public PropertyFee(int p, int f, String p_start, String p_end) {
        period = p;
        fee = f;
        periodStartDate = p_start;
        periodEndDate = p_end;
    }

    // Getters and Setters
    public int getPeriod() {
        return period;
    }
    public int getFee() {
        return fee;
    }
    public String getPeriodStart() {
        return periodStartDate;
    }
    public String getPeriodEnd() {
        return periodEndDate;
    }
}
