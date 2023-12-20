

import java.util.List;
import java.util.Random;

public abstract class Player {
    protected List<Figure> figures;
    protected static Random RANDOM = new Random();
    protected int points;
    protected String name;

    public int getPoints() {
        return points;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public void setPoints(int points) {
        this.points = points;
    }


}
