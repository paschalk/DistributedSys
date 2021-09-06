package client;

import model.UserData;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class SocketClient {
    private JPanel rootPanel;
    private JTextField studentNo;
    private JTextField fName;
    private JTextField sName;
    private JTextField faculty;
    private JTextField course;
    private JTextField degree;
    private JButton submitBtn;

    public static void main(String[] args) throws Exception{
        JFrame frame = new JFrame("Local Client");
        frame.setContentPane(new SocketClient().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        new SocketClient(frame);
    }

    public SocketClient(){

    }

    public SocketClient(JFrame frame) throws Exception{
        Socket socket = new Socket("localhost", 9999);
        System.out.println("Connected at port 9999");

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        ClientProtocol clientProtocol = new ClientProtocol((UserData)inStream.readObject());
        System.out.println("Read object");

        submitBtn.addActionListener(e -> {
            if (fName.getText().isEmpty() ||
            sName.getText().isEmpty() ||
                    faculty.getText().isEmpty() ||
                    course.getText().isEmpty() ||
                    degree.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Please fill in all fields");
            }else{
                UserData data = clientProtocol.setData(
                        parseInt(studentNo.getText()),
                        fName.getText(),
                        sName.getText(),
                        faculty.getText(),
                        course.getText(),
                        degree.getText());
                try {
                    outStream.writeObject(data);
                    outStream.writeChars("Thank you! Innovate☺");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });

        System.out.println(inStream.readUTF());
    }
}
