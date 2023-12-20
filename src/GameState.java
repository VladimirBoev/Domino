import java.util.*;

public class GameState{

    final static int MAX_VALUE = 6;

    List<Figure> figures = new ArrayList<>();
    Stack<Event>  stackOfEvents = new Stack<>();
    Bot move;
    Bot player1;
    Bot player2;
    private List<Side> activeVertex = new ArrayList<>();
    private List<Figure> figuresOnBoard = new ArrayList<>();

    public GameState(Bot player1, Bot player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    private void createFigures(){
        for(int i = 0; i <= MAX_VALUE; i++){
            for(int j = 0; j <= MAX_VALUE; j++){
                if(i == j){
                    continue;
                }
                if(j < i){
                    continue;
                }
                figures.add(new Figure(i, j));
            }
        }
        Collections.shuffle(figures);
        System.out.println("Set was created");
    }

    public List<Side> getActiveVertex() {
        return new ArrayList<>(activeVertex);
    }

    public void addFigureOnBoard(Figure figure) {
        if(figuresOnBoard.size() == 0){
            figuresOnBoard.add(figure);
            activeVertex.add(figure.firstSide);
            activeVertex.add(figure.secondSide);
            System.out.println(move.getName() + " put the figurine " + figure);
            return;
        }

        Side vertex1 = null;
        Side vertex2 = null;
        for(Side vertex: activeVertex){
            if(vertex.value == figure.firstSide.value){
                vertex1 = vertex;
            }

            if(vertex.value == figure.secondSide.value){
                vertex2 = vertex;
            }
        }
        if(vertex1 != null){
            figuresOnBoard.add(figure);
            vertex1.connect(figure.firstSide);
            figure.firstSide.connect(vertex1);
            activeVertex.add(figure.secondSide);
            activeVertex.remove(vertex1);
            stackOfEvents.push(new Event(figure, figure.firstSide, null));
        }

        if(vertex2 != null){
            figuresOnBoard.add(figure);
            vertex2.connect(figure.firstSide);
            figure.secondSide.connect(vertex2);
            activeVertex.add(figure.firstSide);
            activeVertex.remove(vertex2);
            stackOfEvents.push(new Event(figure, figure.secondSide, null));
        }
    }

    public void previousStep() {

        if(stackOfEvents.empty()){
            System.out.println("Event list is empty");
            return;
        }
        Event event = stackOfEvents.pop();



        if(event.player != null){
            event.player.getFiguresDanger().remove(event.figure);
            return;
        }
        figuresOnBoard.remove(event.figure);
        activeVertex.remove(event.figure.firstSide);
        activeVertex.remove(event.figure.secondSide);
        activeVertex.add(event.side.connectVertex);
        System.out.println("The field was rolled back a step");
    }

    public void runGame() {
        createFigures();
        initPlayers();
        play();
    }



    private void initPlayers() {
        for(int i = 0; i < 7; i++) {
            player1.getFigures(figures.remove(figures.size() - 1));
            player2.getFigures(figures.remove(figures.size() - 1));
        }
    }

    private boolean play() {
        Scanner s = new Scanner(System.in);
        move = player1;
        boolean flagPlay = true;
        while (flagPlay) {
            System.out.println("To continue, press “N”, “Roll back” “P”, “Information” “I”");
            String string = s.nextLine();
            if (Objects.equals(string, "P")) {
                previousStep();
                continue;
            }
            if (Objects.equals(string, "I")) {
                printGameInfo();
                continue;
            }
            Figure figure = move.addFigureFromPlayersFigure(this);
            if (figure != null) {
                addFigureOnBoard(figure);
                switchPlayer();
                continue;
            }
            if (figures.size() != 0) {
                Figure tmpFigure = figures.remove(figures.size() - 1);
                move.getFigures(tmpFigure);
                stackOfEvents.push(new Event(tmpFigure, null, move));
                continue;
            }
            flagPlay = checkGameOver();
            switchPlayer();
        }
        return flagPlay;
    }

    private void printGameInfo() {
        System.out.println("Free sides: " + activeVertex);
        System.out.println("Figures on table: " + figuresOnBoard);
        System.out.println("Count of figure first player: " + player1.getCountFigures());
        System.out.println("Count of figure second player: " + player2.getCountFigures());
    }

    private void switchPlayer() {
        if (move == player1) {
            move = player2;
        } else {
            move = player1;
        }
    }

    private boolean checkGameOver() {
        if (player1.getCountFigures() == 0) {
            System.out.println(player1.getName() + " wins, they have no more figures...");
            return false;
        }
        if (player2.getCountFigures() == 0) {
            System.out.println(player2.getName() + " wins, they have no more figures...");
            return false;
        }
        if (figures.size() == 0) {
            if (player1.getCountFigures() < player2.getCountFigures()) {
                System.out.println(player1.getName() + " wins, they have fewer figures");
                return false;
            } else {
                System.out.println(player2.getName() + " wins, they have fewer figures");
                return false;
            }
        }
        return true;
    }
    public List<Figure> getFiguresOnBoard() {
        return new ArrayList<>(figuresOnBoard);
    }
}
