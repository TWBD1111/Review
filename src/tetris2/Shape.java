package tetris2;

import java.awt.Color;
import java.awt.Graphics;

public class Shape {

    private Color color;

    private int x, y;

    private long time, lastTime;

    private int normal = 600, fast = 50;

    private int delay;

    private int[][] coords;

    private int[][] reference;

    private int deltaX;

    private Board board;

    private boolean collision = false, moveX = false;

    private int timePassedFromCollision = -1;

    public Shape(int[][] coords, Board board, Color color) {//建構子方法coords座標,board 方塊,color顏色
        this.coords = coords;
        this.board = board;
        this.color = color;
        deltaX = 0; //x 軸上的位移量
        x = 4;//初始 x 軸位置
        y = 0;//初始 y 軸位置。
        delay = normal;
        time = 0;//方塊下落的時間
        lastTime = System.currentTimeMillis();
        reference = new int[coords.length][coords[0].length];
        //將 coords 陣列中的元素複製到 reference 陣列中，這樣做是為了在後續的操作中保留方塊的原始座標
        System.arraycopy(coords, 0, reference, 0, coords.length);//(要複製的陣列,起始索引,目標陣列,目標陣列的起始索引,要複製的元素個數)

    }

    long deltaTime;

    public void update() {
        moveX = true;
        
        deltaTime = System.currentTimeMillis() - lastTime;//上次更新與當前時間之間的時間差
        time += deltaTime;//方塊的下落時間
        lastTime = System.currentTimeMillis();//當前的系統時間

        if (collision && timePassedFromCollision > 150) {//當方塊發生碰撞並且經過一定時間（大於 50 毫秒）
            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[0].length; col++) {
                    if (coords[row][col] != 0) {
                        board.getBoard()[y + row][x + col] = color;
                    }
                }
            }
            checkLine();
            board.setCurrentShape();
            timePassedFromCollision = -1;
        }

        // 檢查水平移動
        if (!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0)) {

            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[row].length; col++) {
                    if (coords[row][col] != 0) {
                        if (board.getBoard()[y + row][x + deltaX + col] != null) {
                            moveX = false;
                        }

                    }
                }
            }

            if (moveX) {
                x += deltaX;
            }

        }

        // 檢查形狀的位置+高度（行數）
        if (timePassedFromCollision == -1) {
            if (!(y + 1 + coords.length > 20)) {

                for (int row = 0; row < coords.length; row++) {
                    for (int col = 0; col < coords[row].length; col++) {
                        if (coords[row][col] != 0) {

                            if (board.getBoard()[y + 1 + row][x + col] != null) {
                                collision();
                            }
                        }
                    }
                }
                if (time > delay) {
                	y++;
                    time = 0;
                }
            } else {
                collision();
            }
        } else {
            timePassedFromCollision += deltaTime;//用於跟踪方塊碰撞後的時間
        }

        deltaX = 0;
    }

    private void collision() {
        collision = true;
        timePassedFromCollision = 0;
    }

    public void render(Graphics g) {

        g.setColor(color);
        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[0].length; col++) {
                if (coords[row][col] != 0) {
                    g.fillRect(col * 30 + x * 30, row * 30 + y * 30, Board.blockSize, Board.blockSize);
                }
            }
        }


    }

    private void checkLine() {
        int size = board.getBoard().length - 1;
        for (int i = board.getBoard().length - 1; i > 0; i--) {//最底部开始向上遍历每一行，用于检查是否有完整的行需要消除
            int count = 0;
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                if (board.getBoard()[i][j] != null) {//计算当前行中已被填充的方块数量
                    count++;
                }
                board.getBoard()[size][j] = board.getBoard()[i][j];
            }
   
            if (count < board.getBoard()[0].length) {//用于检查当前行是否是完整的行
                size--;    //往上檢查          
            }else {
            	board.addScore();
            }          
        }       
        
    }

    public void rotateShape() {

        int[][] rotatedShape = null;

        rotatedShape = transposeMatrix(coords);

        rotatedShape = reverseRows(rotatedShape);

        if ((x + rotatedShape[0].length > 10) || (y + rotatedShape.length > 20)) {
            return;
        }

        for (int row = 0; row < rotatedShape.length; row++) {
            for (int col = 0; col < rotatedShape[row].length; col++) {
                if (rotatedShape[row][col] != 0) {
                    if (board.getBoard()[y + row][x + col] != null) {
                        return;
                    }
                }
            }
        }
        coords = rotatedShape;
    }

    private int[][] transposeMatrix(int[][] matrix) {
        int[][] temp = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][i] = matrix[i][j];
            }
        }
        return temp;
    }

    private int[][] reverseRows(int[][] matrix) {

        int middle = matrix.length / 2;

        for (int i = 0; i < middle; i++) {
            int[] temp = matrix[i];

            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = temp;
        }

        return matrix;

    }

    public Color getColor() {
        return color;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void speedUp() {
        delay = fast;
    }

    public void speedDown() {
        delay = normal;
    }

    public int[][] getCoords() {
        return coords;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}