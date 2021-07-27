import java.util.Scanner;
import java.util.Random;

public class Game {
    Scanner sc = new Scanner( System.in );
    Random rand = new Random();

    Ship ship1 = new Ship();
    Ship ship2 = new Ship();
    Ship ship3 = new Ship();

    int cnt = 1;

    public void game() {
        
        while( Ship.checkShip() ) {
            System.out.printf( "---------[ターン%d]---------\n" , cnt++ );
            
            // 船の状態の表示
            System.out.println( "          船の状態           " );
            System.out.print( "        ------------          \n");
            ship1.showState( 1 );
            System.out.print( "            --------         \n");
            ship2.showState( 2 );
            System.out.print( "            --------         \n");
            ship3.showState( 3 );
            System.out.print( "        ------------          \n");
            
            System.out.println( "爆弾のX座標を入力してください(1-5)" );
            int choose_coorX = sc.nextInt(); 
            System.out.println( "爆弾のX座標を入力してください(1-5)" );
            int choose_coorY = sc.nextInt();

            // 爆弾を落とす
            ship1.resultSub();
            ship2.resultSub();
            ship3.resultSub();
            Ship.dropBombs( choose_coorX - 1 , choose_coorY - 1 );

            // 結果の表示
            System.out.println( "            結果             " );
            System.out.print( "        ------------          \n");
            ship1.showResult( 1 );
            System.out.print( "            --------         \n");
            ship2.showResult( 2 );
            System.out.print( "            --------         \n");
            ship3.showResult( 3 );
            System.out.print( "        ------------          \n");

        }

    }

    
    
}