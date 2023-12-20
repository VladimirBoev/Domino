public class Event {
    public Bot player;

    public Figure figure;
    public Side side;

    public Event(Figure figure, Side side, Bot player) {
        this.figure = figure;
        this.side = side;
        this.player = player;
    }

}
