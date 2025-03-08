package frc.robot.commands;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BunnyBoard {
    private static final String SERVER_IP = "10.15.57.200";
    private static final int SERVER_PORT = 5800;
    private static final int SEND_INTERVAL = 1000;

    private static Map<String, TimerTask> tasks = new HashMap<>();

    public static void sendData(String key, String value) {
        /* TimerTask task = tasks.get(key);
        if (task != null) {
            task.cancel();
        }

        task = new TimerTask() {
            @Override
            public void run() {
                try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                     OutputStream output = socket.getOutputStream();
                     PrintWriter writer = new PrintWriter(output, true)) {
                    
                    String message = key + "=" + value;
                    writer.println(message);
                
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, SEND_INTERVAL);

        tasks.put(key, task); */
    } 
}