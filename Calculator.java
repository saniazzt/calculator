import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener{

        final boolean shouldFill = true;
        final boolean shouldWeightX = true;
        final boolean RIGHT_TO_LEFT = false;
        JFrame frame;
        JPanel panel;
        JLabel textLabel;
        JButton[] button = new JButton[19];
        double num1=0, num2=0, res=0;
        char op;

        Calculator()
        {    
        frame = new JFrame("Calculator");
        Dimension frameSize = new Dimension(450,600);
        frame.setSize(frameSize);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        
        if (RIGHT_TO_LEFT) {
            panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        Color Lightblue = new Color (173, 223, 255);
        Font font = new Font("SansSerif", Font.BOLD, 26);

        frame.getContentPane().setBackground(Lightblue);
        panel.setBackground(Lightblue);
        panel.setBounds(25,0,400,600);

        textLabel = new JLabel("");
        textLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        textLabel.setOpaque(true);
        textLabel.setBackground(new Color(246, 255, 209));
        textLabel.setFont(font);

        Dimension d = textLabel.getPreferredSize();
        textLabel.setPreferredSize(new Dimension(d.width+120,d.height+20));

        button[0] = new JButton("1");
        button[1] = new JButton("2");
        button[2] = new JButton("3");
        button[3] = new JButton("+");
        button[4] = new JButton("4");
        button[5] = new JButton("5");
        button[6] = new JButton("6");
        button[7] = new JButton("-");
        button[8] = new JButton("7");
        button[9]  = new JButton("8");
        button[10] = new JButton("9");
        button[11] = new JButton("*");
        button[12] = new JButton(".");
        button[13] = new JButton("0");
        button[14] = new JButton("=");
        button[15] = new JButton("/");
        button[16] = new JButton("del");
        button[17] = new JButton("clr");
        button[18] = new JButton("_");

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        if (shouldFill) {
            constraints.fill = GridBagConstraints.HORIZONTAL;
        }

        //adding text label
        constraints.ipady = 30;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.gridwidth = 4;
        textLabel.setFont(font);
        panel.add(textLabel,constraints);

        constraints.weighty = 0;
        constraints.weightx = 1;
        constraints.ipady = 30;
        constraints.gridwidth = 1;

        if (shouldWeightX) {
            constraints.weightx = 0.5;
        }

        //adding buttons
        int t = 0;
        for (int j=1; j<=4; j++){
            for (int i=0; i<4; i++){
                constraints.gridx = i;
                constraints.gridy = j;
                JButton thisb = button[t];
                thisb.addActionListener(this);
                thisb.setFont(font);
                thisb.setOpaque(true);
                thisb.setBorderPainted(false);
                thisb.setBackground(new Color(193+i*10, 225+i*10, 193+i*10));
                panel.add(thisb,constraints);
                t++;
            }
        }
        
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 5;
        button[16].addActionListener(this);
        button[16].setFont(font);
        button[16].setOpaque(true);
        button[16].setBorderPainted(false);
        button[16].setBackground(new Color(248-20, 200-20, 220-20));
        panel.add(button[16],constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        button[17].addActionListener(this);
        button[17].setFont(font);
        button[17].setOpaque(true);
        button[17].setBorderPainted(false);
        button[17].setBackground(new Color(248-10, 200-10, 220-10));
        panel.add(button[17],constraints);

        constraints.gridx = 2;
        constraints.gridy = 5;
        button[18].addActionListener(this);
        button[18].setFont(font);
        button[18].setOpaque(true);
        button[18].setBorderPainted(false);
        button[18].setBackground(new Color(248, 200, 220));
        panel.add(button[18],constraints);

        frame.add(panel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //defining actionlistener for number buttons
        for (int i=0; i<=13; i++){
            if (i==3 || i==7 || i==11 || i==12)
                continue;
            if (e.getSource() == button[i]){
                textLabel.setText(textLabel.getText().concat(button[i].getText()));
            }
        }

        //defining actionlistener for function buttons
        //plus button
        if (e.getSource() == button[3]){
            op = '+';
            num1 = Double.parseDouble(textLabel.getText());
            textLabel.setText("");
        }
        //minus button
        if (e.getSource() == button[7]){
            op = '-';
            num1 = Double.parseDouble(textLabel.getText());
            textLabel.setText("");
        }
        //multiplication button
        if (e.getSource() == button[11]){
            op = '*';
            num1 = Double.parseDouble(textLabel.getText());
            textLabel.setText("");
        }
        //devide button
        if (e.getSource() == button[15]){
            op = '/';
            num1 = Double.parseDouble(textLabel.getText());
            textLabel.setText("");
        }
        //equal button
        if (e.getSource() == button[14]){
            num2 = Double.parseDouble(textLabel.getText());
            switch (op) {
                case '+':
                    res = num1 + num2;
                    break;

                case '-':
                res = num1 - num2;
                break;
                
                case '*':
                res = num1 * num2;
                break;

                case '/':
                res = num1 / num2;
                break;
            }
            textLabel.setText(String.valueOf(res));
            num1 = res;
        }
        //dot button
        if (e.getSource() == button[12]){
            textLabel.setText(textLabel.getText() + '.');
        }
        //delete button
        if (e.getSource() == button[16]){
            String temp = textLabel.getText();
            textLabel.setText("");
            for (int i = 0; i<temp.length()-1; i++){
                textLabel.setText(textLabel.getText() + temp.charAt(i));
            }
        }
        //clear button
        if (e.getSource() == button[17]){
            textLabel.setText("");
        }
        //negative button
        if (e.getSource() == button[18]){
            textLabel.setText(String.valueOf(Double.parseDouble(textLabel.getText())*-1));
        }
    }
}