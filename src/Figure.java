public class Figure {
    public final Side firstSide;
    public final Side secondSide;

    public Figure(int valueFirstVertex, int valueSecondVertex) {
        this.firstSide = new Side(valueFirstVertex, this);
        this.secondSide = new Side(valueSecondVertex, this);
    }



    @Override
    public String toString() {
        return "Domino figure with meanings " +
                  firstSide.value +
                " : " + secondSide.value;
    }
}
