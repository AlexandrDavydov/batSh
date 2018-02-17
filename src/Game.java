public class Game{

    private Player myPlayer;
    private Player enemyPlayer;

    public Game() {
        myPlayer = new Player();
        enemyPlayer = new Player();

        myPlayer.randomShips();
        enemyPlayer.randomShips();
        //show();
        /*for(int i=0; i<1000; i++){
            Integer x = Math.toIntExact(Math.round(Math.random()*9));
            Integer y = Math.toIntExact(Math.round(Math.random()*9));
            boolean hatted = player1.turn(new Pair<>(x,y));
            show();
            if(hatted && player1.checkPlayerLose()){
               System.out.println("Ты победил!");
               break;
            }
        }*/


    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public Player getEnemyPlayer() {
        return enemyPlayer;
    }
}
