public class Side {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 6;
    public final int value;
    public final Figure figure;
    public Side connectVertex;
    public Side(int value, Figure figure) {
        this.figure = figure;
        this.value = validateValue(value);
    }
    private int validateValue(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException("Invalid side value: " + value);
        }
        return value;
    }
    public boolean connect(Side connectVertex) {
        if (this.connectVertex != null || connectVertex.connectVertex != null ||
                connectVertex.value != this.value) {
            return false;
        }
        this.connectVertex = connectVertex;
        connectVertex.connectVertex = this;
        System.out.println(this.figure + " joined with " + connectVertex.figure);
        return true;
    }
}
