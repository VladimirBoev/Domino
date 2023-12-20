import java.util.ArrayList;
import java.util.List;

public class Bot extends Player {
    public Bot(String name) {
        figures = new ArrayList<>();
        this.name = name;
    }


    public Figure addFigureFromPlayersFigure(GameState gameState) {
        if(gameState.getFiguresOnBoard().size() == 0){
            return figures.remove(RANDOM.nextInt(figures.size()));
        }
        List<Side> activeVertex = gameState.getActiveVertex();
        for (int i = 0; i < figures.size(); i++) {
            for(Side vertex : activeVertex){
                if(vertex.value == figures.get(i).firstSide.value ||
                vertex.value == figures.get(i).secondSide.value){
                    System.out.println("Player " + name + " add figure: " + figures.get(i) );
                    return figures.remove(i);
                };
            }
        }
        return null;
    }

    public void getFigures(Figure figure) {
        if(figures.size() > 8){
            return;
        }
        figures.add(figure);
        System.out.println("Player " + name + " get figure: " +  figure);
    }


    public List<Figure> getFiguresDanger(){
        return figures;
    }

    public String getName() {
        return name;
    }
    public int getCountFigures(){
        return figures.size();
    }

}
