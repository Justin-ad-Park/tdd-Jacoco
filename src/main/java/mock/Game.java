package mock;

public class Game {

    private final GameNumGen gameNumGen;

    public Game(GameNumGen gameNumGen) {
        this.gameNumGen = gameNumGen;
    }
    
    void init(GameLevel level) {
        this.gameNumGen.generate(level);
    };

    void init() {
        this.gameNumGen.generate(GameLevel.EASY);
    };
    
}
