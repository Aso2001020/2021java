import java.util.Random;

public class Ship {

    Random rand = new Random();
    
    public String state;        // 船の状態
    public String result;       // 爆弾を落とした結果
    public int hp;           // 船のHP
    public int coorX;           // 船のX座標
    public int coorY;           // 船のY座標

    public static Ship[][] area = new Ship[5][5];   // エリア
    public static boolean end = false;              // 船の数が0になるとtrue
    public static int shipCounts = 3;               // 船の数

    public Ship() {
        this.state = "生存";
        this.hp = 3;
        this.coordinate();
    }

    // 船の座標
    public void coordinate() { 
        while( true ){
            this.coorX = rand.nextInt( 5 );
            this.coorY = rand.nextInt( 5 );
            if( area[this.coorX][this.coorY] == null ) {
                placement();
                break;
            }
        }
    }

    // 船の配置
    public void placement() {
        area[this.coorX][this.coorY] = this;
    }

    // 爆弾を落とす
    static String gekichin = "撃沈!";
    static String meichu = "命中!船が移動します!";
    static String namitakasi = "波高し!";
    static String hazure = "はずれ!";

    public static void dropBombs( int X , int Y ) {
        checkMeichu( X , Y );
        checkNamitakasi( X , Y );
        checkshipCount();
    }

    // 命中の判定
    public static void checkMeichu( int X , int Y ) {
        if( area[X][Y] != null ) {
            area[X][Y].hp -= 1;                 
            area[X][Y].result = meichu;
            if( area[X][Y].hp == 0 ) {
                area[X][Y].result = gekichin;
                area[X][Y].state = "×";
                area[X][Y] = null;
                shipCounts -= 1;
            } else {
                area[X][Y].coordinate();
                area[X][Y] = null;
            }
        }
    }

    // 波高しの判定
    public static void checkNamitakasi( int X , int Y ) {
        int xLeft = X - 1;      // X座標の左
        int xRight = X + 1;     // X座標の右
        int yTop = Y - 1;      // Y座標の上
        int yDown = Y + 1;     // Y座標の下

        if( Y > 0 && area[X][yTop] != null ) {
            area[X][yTop].result = namitakasi;
        } else if( X < 4 && area[xRight][Y] != null ) {
            area[xRight][Y].result = namitakasi;
        } else if( Y < 4 && area[X][yDown] != null ) {
            area[X][yDown].result = namitakasi;
        } else if( X > 0 && area[xLeft][Y] != null ) {
            area[xLeft][Y].result = namitakasi;
        }
    }

    // 結果の代入
    public void resultSub() {
        if( !( this.state.equals( "×" ) ) ) {
            this.result = hazure;
        }
    }

    // 船の状態の表示
    public void showState( int number ) {
        System.out.printf( "　　　　船%d:%s\n" , number , this.state );
    }

    // 結果の表示
    public void showResult( int number ) {
        System.out.printf( "　　　　船%d:%s(残り%d回)\n" , number , this.result , this.hp );
    }

    // 船の数のチェック
    public static void checkshipCount() {
        if( shipCounts == 0 ) {
            end = true;
        }
    }

    // 船がまだ残っているか
    public static boolean checkShip() {
        if( end ) {
            return false;
        } else {
            return true;
        }
    }
}
