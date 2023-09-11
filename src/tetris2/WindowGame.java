package tetris2;

import javax.swing.JFrame;

public class WindowGame {
    public static final int WIDTH = 465, HEIGHT = 629;

    private Board board;
    private Title title;
    private JFrame window;

    public WindowGame() {

        window = new JFrame("Tetris");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);//將視窗置於屏幕中央的位置,不指定視窗的位置時啟動時視窗將以預設方式顯示在屏幕上（左上角）
        window.setResizable(false);//禁止調整視窗的大小

        board = new Board();
        title = new Title(this);

        window.addKeyListener(board);
        window.addKeyListener(title);

        window.add(title);

        window.setVisible(true);
    }

    public void startTetris() {
        window.remove(title);
        window.addMouseMotionListener(board);
        window.addMouseListener(board);
        window.add(board);
        board.startGame();
        window.revalidate();
    }

    public static void main(String[] args) {
        new WindowGame();
    }

}
