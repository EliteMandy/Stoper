import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StopwatchGUI extends JFrame implements ActionListener {
    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0;
    private boolean isRunning = false;
    
    public StopwatchGUI() {
        setTitle("Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // create components
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(timeLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        //Here the Toime is created
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                updateLabel();
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void updateLabel() {
        int hours = elapsedTime / 3600;
        int minutes = (elapsedTime % 3600) / 60;
        int seconds = elapsedTime % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(timeString);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!isRunning) {
                timer.start();
                isRunning = true;
                startButton.setText("Pause");
            } else {
                timer.stop();
                isRunning = false;
                startButton.setText("Start");
            }
        } else if (e.getSource() == stopButton) {
            timer.stop();
            isRunning = false;
            startButton.setText("Start");
            elapsedTime = 0;
            updateLabel();
        } else if (e.getSource() == resetButton) {
            elapsedTime = 0;
            updateLabel();
        }
    }
    
    public static void main(String[] args) {
        new StopwatchGUI();
    }
}
