package com.groupNine.app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Sets up and displays the window for the game
 *  @author Milo
 *  @version 1.0
 */
public class mainWindow {

    /**
     * Window for the game
     */
    private JFrame window;
    /**
     * Displays the name of the game on the screen
     */
    private JLabel title;
    /**
     * Helps take up an empty spot on the screen
     */
    private JLabel blank;
    /**
     * Button to begin the game
     */
    private JButton start;
    /**
     * Button to close the game
     */
    private JButton exit;
    /**
     * Panel to arrange the elements on the screen
     */
    private JPanel panel;
    /**
     * Image of the instructions
     */
    private BufferedImage bfImg;
    /**
     * Logic and control of the game
     */
    private gameWindow gameWin;
    /**
     * A temporary label to be changed when needed
     */
    private JLabel endTitle;
    /**
     * A temporary space on the screen to be used when needed
     */
    private JLabel endBlank;
    /**
     * A temporary button to restart the game
     */
    private JButton endRestart;
    /**
     * A temporary button to close the game
     */
    private JButton endExit;
    /**
     * A temporary panel to arrange elements on the screen
     */
    private JPanel endPanel;

    /**
     * Constructor for the mainWindow Class
     */
    public mainWindow() { // Sets up variables
        this.window = new JFrame();
        this.title = new JLabel("Fish Road", SwingConstants.CENTER);
        this.blank = new JLabel(" ", SwingConstants.CENTER);
        this.start = new JButton("Start");
        this.exit = new JButton("Exit");
        this.panel = new JPanel();
        this.bfImg = null;
        setup();
    }

    /**
     * Sets the window to be visible and starts the application
     */
    public void start() {
        this.panel.add(title);
        this.panel.add(blank);
        this.panel.add(start);
        this.panel.add(exit);
        this.window.add(panel, BorderLayout.CENTER);
        this.window.setSize(new Dimension(1024, 768));
        this.window.setVisible(true);
    }

    /**
     * Initializes all the variables to ensure functionality
     * for when the application is started
     */
    private void setup() {
        // Setup for Window
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setTitle("Fish Road");
        // Ignoring any positions and making the window visible
        this.window.setLocationRelativeTo(null);
        this.window.requestFocus();
        // Setup for title
        this.title.setForeground(Color.white);
        this.title.setFont(new Font("Serif", Font.BOLD, 100));
        // Setup for start button
        this.start.setBackground(Color.lightGray);
        // Setup for exit button
        this.exit.setBackground(Color.lightGray);
        // Setup for background color of panel
        this.panel.setBackground(Color.gray);
        // Make buttons unfocusable and not work with keyboard shortcuts
        this.start.setFocusable(false);
        this.exit.setFocusable(false);
        this.start.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.exit.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        // Set the space for the buttons and title to appear
        this.panel.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));
        // Create a vertical single grid to layout everything
        this.panel.setLayout(new GridLayout(4, 1));
        // Setup image
        try {
            this.bfImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/rules/rules.png")));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        // Add action to start button
        try {
            setupButtonAction(this.start, 1);
            setupButtonAction(this.exit, 2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets up an action for a button
     * @param button Button to add an action to
     * @param choice What actions to add to the button
     * @throws Exception Choice is outside the scope of the function
     */
    private void setupButtonAction( JButton button, int choice) throws Exception {
        switch (choice){
            case 1: // Start Game by showing the rules
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showRules();
                    }
                });
                break;
            case 2: // Exit Game
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        quitGame();
                    }
                });
                break;
            case 3: // Restart Game for first run of game
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameWin = new gameWindow(window);
                        gameWin.startGameThread();
                        window.add(gameWin);
                        window.remove(button);
                        restartGame(window);
                    }
                });
            case 4: // Restart Game for all other restarts
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameWin = new gameWindow(window);
                        gameWin.startGameThread();
                        window.add(gameWin);
                        window.remove(endPanel);
                        restartGame(window);
                    }
                });
                break;
            default:
                throw new Exception("Unsupported Button Action");
        }
    }

    /**
     * Displays the image of the game instructions
     */
    private void showRules() { // Sets up the title screen
        // Removes the previous visuals
        this.window.remove(panel);
        this.window.invalidate();
        // Setting up the image for rules
        ImageIcon img = new ImageIcon(this.bfImg);
        JLabel endLabel = new JLabel(img);
        // Adds action for mouse click to quit the rule screen
        endLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent s) {
                startGame(endLabel);
            }

        });
        // Shows the image and the window
        window.add(endLabel);
        window.setVisible(true);
    }

    /**
     * Exits the game
     */
    public void quitGame() {
        System.exit(0);
    }

    /**
     * Displays the game on the screen and sets up buttons for restarting and quitting
     * @param infoScreen JLabel with all the components on the screen
     */
    private void startGame(JLabel infoScreen) {
        // Remove all previous visuals from the screen
        this.window.remove(infoScreen);
        this.window.revalidate();
        // Start the thread for the game
        this.gameWin = new gameWindow(window);
        this.gameWin.startGameThread();
        this.window.add(gameWin);
        // Creating buttons for the game to restart and quit when complete
        gameOverInit();
        // Restart and quit Buttons
        try {
            setupButtonAction( this.endRestart, 3);
            setupButtonAction( this.endExit, 2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // Add the buttons to the screen
        this.endPanel.add(endTitle);
        this.endPanel.add(endBlank);
        this.endPanel.add(endRestart);
        this.endPanel.add(endExit);
        window.add(endPanel, BorderLayout.CENTER);
    }

    private void gameOverInit() {
        // Set up the buttons and text for the screen when game is over
        this.endTitle = new JLabel("Game Over", SwingConstants.CENTER);
        this.endTitle.setForeground(Color.darkGray);
        this.endBlank = new JLabel(" ", SwingConstants.CENTER);
        this.endTitle.setFont(new Font("Serif", Font.BOLD, 100));
        this.endRestart = new JButton("Restart");
        this.endRestart.setBackground(Color.lightGray);
        this.endExit = new JButton("Quit");
        this.endExit.setBackground(Color.lightGray);
        this.endPanel = new JPanel();
        // Make the buttons unfocusable and unclickable
        this.endRestart.setFocusable(false);
        this.endExit.setFocusable(false);
        this.endRestart.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.endExit.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        // Set the layout to a grid
        this.endPanel.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));
        this.endPanel.setLayout(new GridLayout(4, 1));
    }

    /**
     * Sets up buttons for restarting and quitting the game.
     * Also creates and display a new run of the game
     * @param window JFrame that holds all the components on the screen
     */
    private void restartGame(JFrame window) {
        // Adding the game over buttons
        gameOverInit();
        window.setBackground(Color.white);

        // Restart Buttons
        try {
            setupButtonAction( this.endRestart, 4);
            setupButtonAction( this.endExit, 2);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        this.endPanel.add(endTitle);
        this.endPanel.add(endBlank);
        this.endPanel.add(endRestart);
        this.endPanel.add(endExit);
        window.add(endPanel, BorderLayout.CENTER);
    }


}
