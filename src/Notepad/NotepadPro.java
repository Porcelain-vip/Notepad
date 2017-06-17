package Notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Porcelain.vip on 2017/6/10.
 */

public class NotepadPro extends Notepad {

    private NotepadPro() {
        super();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NotepadPro().display();
            }
        });
    }

    private void display() {

        jMenuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!storedContent.equals(jTextArea.getText())) {
                    if (file == null) {
                        JFileChooser jFileChooser = new JFileChooser(new File(
                                "C:\\Users\\Porcelain-vip\\Desktop"));
                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "txt files", "txt");
                        jFileChooser.addChoosableFileFilter(filter);

                        int returnVal = jFileChooser.showSaveDialog(jFrame);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            file = jFileChooser.getSelectedFile();

                            try {
                                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                                        new FileOutputStream(file, false), "GBK"));
                                storedContent = jTextArea.getText();
                                bufferedWriter.write(storedContent);
                                //关闭写入流
                                bufferedWriter.close();
                                jTextArea.setText("");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
                            storedContent = jTextArea.getText();
                        }
                    } else {
                        String[] options = new String[]{"保存", "不保存", "取消"};
                        //展示对话框
                        int returnVal = JOptionPane.showOptionDialog(jFrame, "是否将更改保存到   " + file.getName(),
                                "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                options, options[0]);
                        if (returnVal == JOptionPane.YES_OPTION) {
                            try {
                                //设置流的编码方式
                                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                                        new FileOutputStream(file, false), "GBK"));
                                storedContent = jTextArea.getText();
                                bufferedWriter.write(storedContent);
                                //关闭写入流
                                bufferedWriter.close();
                                jTextArea.setText("");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (returnVal == JOptionPane.NO_OPTION) {
                            jTextArea.setText("");
                        } else {
                            jTextArea.setText(jTextArea.getText());
                        }
                    }

                } else {
                    jTextArea.setText("");
                }
            }
        });


        /*打开文件的另外一种方式
        FileDialog fileDialog = new FileDialog(jFrame, "打开", FileDialog.LOAD);
        fileDialog.setDirectory("C:\\Users\\Porcelain-vip\\Desktop");
        fileDialog.setVisible(true);
        //设置文件选择器, Microsoft Windows 的文件名过滤器在 Sun 的参考实现中不起作用
        fileDialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (dir.isDirectory()) return true;
                return name.endsWith("txt");
            }
        });
        try {
            file = new File(fileDialog.getDirectory() + fileDialog.getFile());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "GBK"));
            String line;
            storedContent = "";
            while ((line = bufferedReader.readLine()) != null) {
                storedContent += line + System.getProperty("line.separator");
            }
            //显示文本
            jTextArea.setText(storedContent);
            //设置光标位置
            jTextArea.setCaretPosition(0);
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ************************************************************************************/

        //打开文本文档
        jMenuItemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser(new File("C:\\Users\\Porcelain-vip\\Desktop"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
                jFileChooser.addChoosableFileFilter(filter);

                int returnVal = jFileChooser.showOpenDialog(jFrame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = jFileChooser.getSelectedFile();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                                new FileInputStream(file), "GBK"));
                        //开始读取文本文档中的内容
                        storedContent = "";
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            storedContent += line + System.getProperty("line.separator");
                        }
                        //关闭读取流
                        bufferedReader.close();
                        jTextArea.setText(storedContent);
                        //设置光标初始位置
                        jTextArea.setCaretPosition(0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        jMenuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser(new File("C:\\Users\\Porcelain-vip\\Desktop"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
                jFileChooser.addChoosableFileFilter(filter);

                int returnVal = jFileChooser.showSaveDialog(jFrame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = jFileChooser.getSelectedFile();

                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(file, true), "GBK"));
                        //开始向文件中写入数据
                        storedContent = jTextArea.getText();
                        bufferedWriter.write(storedContent);
                        //关闭写入流
                        bufferedWriter.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                } else if (returnVal == JFileChooser.CANCEL_OPTION) {
                    storedContent = jTextArea.getText();
                }
            }
        });

        jMenuItemSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser(new File("C:\\Users\\Porcelain-vip\\Desktop"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
                jFileChooser.addChoosableFileFilter(filter);

                int returnVal = jFileChooser.showSaveDialog(jFrame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = jFileChooser.getSelectedFile();

                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(file, true), "GBK"));
                        //开始向文件中写入数据
                        storedContent = jTextArea.getText();
                        bufferedWriter.write(storedContent);
                        //关闭写入流
                        bufferedWriter.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                } else if (returnVal == JFileChooser.CANCEL_OPTION) {
                    storedContent = jTextArea.getText();
                }
            }
        });

        //退出菜单项
        jMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        //剪切菜单项
        jMenuItemCut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTextArea.cut();
            }
        });

        //复制菜单项
        jMenuItemCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTextArea.copy();
            }
        });

        //粘贴菜单项
        jMenuItemPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTextArea.paste();
            }
        });

        //删除菜单项
        jMenuItemDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获得选定内容的偏移量
                int offs = jTextArea.getSelectionStart();
                //获得选定内容的字符数量
                int count = jTextArea.getSelectedText().length();
                try {
                    jTextArea.getDocument().remove(offs, count);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //全选菜单项
        jMenuItemSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.selectAll();
            }
        });

        //时间日期菜单项
        jMenuItemTimeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm yyyy/MM/dd E");
                String time = simpleDateFormat.format(date);
                jTextArea.append(time);
            }
        });

        //弹出式菜单剪切项
        jPopupMenuItemCut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.cut();
            }
        });

        //弹出式菜单复制项
        jPopupMenuItemCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.copy();
            }
        });

        //弹出式菜单粘贴项
        jPopupMenuItemPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.paste();
            }
        });

        //弹出式菜单删除项
        jPopupMenuItemDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获得选定内容的偏移量
                int offs = jTextArea.getSelectionStart();
                //获得选定内容的字符数量
                int count = jTextArea.getSelectedText().length();
                try {
                    jTextArea.getDocument().remove(offs, count);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //弹出式菜单全选菜单项
        jPopupMenuItemSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.selectAll();
            }
        });

        //换行菜单项
        jCheckBoxMenuItemLineWrap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jCheckBoxMenuItemLineWrap.isSelected()) {
                    jTextArea.setLineWrap(true);
                } else {
                    jTextArea.setLineWrap(false);
                }
            }
        });

        //帮助菜单项
        jMenuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jFrame, "版权所有 盗版必究\nCreated By Porcelain",
                        "关于记事本", JOptionPane.PLAIN_MESSAGE);
            }
        });

        jTextArea.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
}
