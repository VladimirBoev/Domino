public class Game {
    public static void main(String[] args) {
        Bot bot1 = new Bot("1");
        Bot bot2 = new Bot("2");
        GameState gs = new GameState(bot1, bot2);
        gs.runGame();
    }
}
