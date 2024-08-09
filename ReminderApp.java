import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;
import java.util.Map;

public class ReminderApp extends JFrame {
    private JComboBox<String> dayComboBox;
    private JComboBox<String> timeComboBox;
    private JComboBox<String> activityComboBox;
    private JButton setReminderButton;

    private static final String[] DAYS_OF_WEEK = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    private static final String[] ACTIVITIES = {
        "Wake up", "Go to gym", "Breakfast", "Meetings", "Lunch", "Quick nap", 
        "Go to library", "Dinner", "Go to sleep"
    };

    public ReminderApp() {
        setTitle("Reminder App");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        dayComboBox = new JComboBox<>(DAYS_OF_WEEK);
        timeComboBox = new JComboBox<>(getTimes());
        activityComboBox = new JComboBox<>(ACTIVITIES);
        setReminderButton = new JButton("Set Reminder");

        add(new JLabel("Select Day:"));
        add(dayComboBox);
        add(new JLabel("Select Time:"));
        add(timeComboBox);
        add(new JLabel("Select Activity:"));
        add(activityComboBox);
        add(new JLabel());
        add(setReminderButton);

        setReminderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReminder();
            }
        });
    }

    private String[] getTimes() {
        String[] times = new String[24 * 2];
        for (int i = 0; i < times.length; i++) {
            int hour = i / 2;
            int minute = (i % 2) * 30;
            times[i] = String.format("%02d:%02d", hour, minute);
        }
        return times;
    }

    private void setReminder() {
        String selectedDay = (String) dayComboBox.getSelectedItem();
        String selectedTime = (String) timeComboBox.getSelectedItem();
        String selectedActivity = (String) activityComboBox.getSelectedItem();

        // Convert time to milliseconds
        String[] timeParts = selectedTime.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        long reminderTime = hour * 3600 * 1000 + minute * 60 * 1000;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Reminder: " + selectedActivity);
                Toolkit.getDefaultToolkit().beep(); // Play a sound
            }
        }, reminderTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReminderApp app = new ReminderApp();
            app.setVisible(true);
        });
    }
}

