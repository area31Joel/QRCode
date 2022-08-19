import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class QRcodeSwing extends JFrame implements ActionListener {
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    JPanel jp3 = new JPanel();
    JLabel jl_catalog = new JLabel("保存目录");
    JTextField jt_catalog = new JTextField(".\\", 20);
    JTextArea jt_input = new JTextArea("仅支持英文字符，中文无法显示，最多输入600字。\n生成的二维码与“二维码生成器”在同一目录中！", 20, 30);
    JButton jb_output = new JButton("生成二维码");
    static String To_text;
    static String To_file;
    public QRcodeSwing(String title) {
        super(title);

        jp1.add(jt_input);
        jp1.add(jb_output);

        jp2.add(jl_catalog);
        jp2.add(jt_catalog);

        jp3.add(jp2);
        jp3.add(jp1);

        jb_output.addActionListener(this);

        this.add(jp3);
        this.setSize(500, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        jt_input.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // 获取焦点时执行此方法
                if (jt_input.getText().equals("仅支持英文字符，中文无法显示，最多输入600字。\n生成的二维码与“二维码生成器”在同一目录中！")) {
                    jt_input.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                // 失去焦点时执行此方法
                if (jt_input.getText().equals("")) {
                    jt_input.setText("仅支持英文字符，中文无法显示，最多输入600字。\n生成的二维码与“二维码生成器”在同一目录中！");
                }
            }
        });


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_output) {
            String text = this.jt_input.getText();
            String filePath = this.jt_catalog.getText();

            To_text = text;//将非静态变量text的值赋给静态变量To_text
            To_file =filePath;
            try {
                QRcodeUtil qRcodeUtil = new QRcodeUtil();

                //Xiaojie wants to eat eggs就是我们的生成规则，可随便填写
                qRcodeUtil.getQRCodeImage(To_text, 350, 350, To_file);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new QRcodeSwing("二维码生成器");
    }
}