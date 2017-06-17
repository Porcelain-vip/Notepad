package Notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Created by Porcelain.vip on 2017/6/7.
 */

class Notepad {

    String storedContent;
    File file;

    JFrame jFrame;
    JTextArea jTextArea;
    JPopupMenu jPopupMenu;

    //文件菜单中的菜单项
    JMenuItem jMenuItemNew, jMenuItemOpen, jMenuItemSave, jMenuItemSaveAs, jMenuItemExit;

    //编辑菜单中的菜单项
    JMenuItem jMenuItemCut, jMenuItemCopy, jMenuItemPaste, jMenuItemDelete, jMenuItemSelectAll, jMenuItemTimeData;

    //弹出式菜单中的菜单项
    JMenuItem jPopupMenuItemCut, jPopupMenuItemCopy, jPopupMenuItemPaste, jPopupMenuItemDelete, jPopupMenuItemSelectAll;

    //格式菜单中的菜单项
    JCheckBoxMenuItem jCheckBoxMenuItemLineWrap;

    //帮助菜单中的菜单项
    JMenuItem jMenuItemAbout;

    Notepad() {

        storedContent = "";
        jTextArea = new JTextArea();
        jTextArea.setBorder(BorderFactory.createLineBorder(Color.CYAN, 1, true));

        //创建JFrame
        jFrame = new JFrame("记事本");
        //设置图像
        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("记事本.jpg"));

        //创建菜单栏
        JMenuBar jMenuBar = new JMenuBar();

        //创建弹出式菜单
        jPopupMenu = new JPopupMenu();

        //创建文件菜单
        JMenu jMenuFile = new JMenu("文件(F)");
        jMenuFile.setMnemonic(KeyEvent.VK_F);
        //jMenuFile.setToolTipText("打开文件");

        //创建编辑菜单
        JMenu jMenuEdit = new JMenu("编辑(E)");
        jMenuEdit.setMnemonic(KeyEvent.VK_E);
        //jMenuEdit.setToolTipText("打开编辑");

        //创建格式菜单
        JMenu jMenuFormat = new JMenu("格式(O)");
        jMenuFormat.setMnemonic(KeyEvent.VK_O);
        //jMenuFormat.setToolTipText("打开格式");

        //创建查看菜单
        JMenu jMenuView = new JMenu("查看(V)");
        jMenuView.setMnemonic(KeyEvent.VK_V);
        //jMenuView.setToolTipText("打开查看");

        //创建帮助菜单
        JMenu jMenuHelp = new JMenu("帮助(H)");
        jMenuHelp.setMnemonic(KeyEvent.VK_H);
        //jMenuHelp.setToolTipText("打开帮助");

        //向菜单栏中添加菜单
        jMenuBar.add(jMenuFile);
        jMenuBar.add(jMenuEdit);
        jMenuBar.add(jMenuFormat);
        jMenuBar.add(jMenuView);
        jMenuBar.add(jMenuHelp);

        //创建菜单项
        jMenuItemNew = new JMenuItem("新建(N)", KeyEvent.VK_N);
        jMenuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        jMenuItemOpen = new JMenuItem("打开(O)...", KeyEvent.VK_O);
        jMenuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        jMenuItemSave = new JMenuItem("保存(S)", KeyEvent.VK_S);
        jMenuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        jMenuItemSaveAs = new JMenuItem("另存为(A)...", KeyEvent.VK_A);

        jMenuItemExit = new JMenuItem("退出(X)", KeyEvent.VK_X);

        //向菜单中添加菜单项
        jMenuFile.add(jMenuItemNew);
        jMenuFile.add(jMenuItemOpen);
        jMenuFile.add(jMenuItemSave);
        jMenuFile.add(jMenuItemSaveAs);
        //添加菜单项分隔符
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuItemExit);

        //剪切，复制，粘贴
        jMenuItemCut = new JMenuItem("剪切(T)", KeyEvent.VK_T);
        jMenuItemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        //jMenuItemCut.setEnabled(false);

        jMenuItemCopy = new JMenuItem("复制(C)", KeyEvent.VK_C);
        jMenuItemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));

        jMenuItemPaste = new JMenuItem("粘贴(P)", KeyEvent.VK_P);
        jMenuItemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));

        jMenuItemDelete = new JMenuItem("删除(L)", KeyEvent.VK_L);
        jMenuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK));

        jMenuItemSelectAll = new JMenuItem("全选(A)", KeyEvent.VK_A);
        jMenuItemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));

        jMenuItemTimeData = new JMenuItem("时间/日期(D)", KeyEvent.VK_D);
        jMenuItemTimeData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_DOWN_MASK));


        //向编辑菜单中添加菜单项
        jMenuEdit.add(jMenuItemCut);
        jMenuEdit.add(jMenuItemCopy);
        jMenuEdit.add(jMenuItemPaste);
        jMenuEdit.add(jMenuItemDelete);
        jMenuEdit.addSeparator();
        jMenuEdit.add(jMenuItemSelectAll);
        jMenuEdit.add(jMenuItemTimeData);

        //弹出式菜单的剪切，复制，粘贴
        jPopupMenuItemCut = new JMenuItem("剪切(T)", KeyEvent.VK_T);
        jPopupMenuItemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        jPopupMenuItemCopy = new JMenuItem("复制(C)", KeyEvent.VK_C);
        jPopupMenuItemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));

        jPopupMenuItemPaste = new JMenuItem("粘贴(P)", KeyEvent.VK_P);
        jPopupMenuItemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));

        jPopupMenuItemDelete = new JMenuItem("删除(L)", KeyEvent.VK_L);
        jPopupMenuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK));

        jPopupMenuItemSelectAll = new JMenuItem("全选(A)", KeyEvent.VK_A);
        jPopupMenuItemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));

        //向弹出式菜单添加菜单项
        jPopupMenu.add(jPopupMenuItemCut);
        jPopupMenu.add(jPopupMenuItemCopy);
        jPopupMenu.add(jPopupMenuItemPaste);
        jPopupMenu.add(jPopupMenuItemDelete);
        jPopupMenu.addSeparator();
        jPopupMenu.add(jPopupMenuItemSelectAll);
        jPopupMenu.addSeparator();

        jCheckBoxMenuItemLineWrap = new JCheckBoxMenuItem("自动换行(W)", false);
        jCheckBoxMenuItemLineWrap.setMnemonic(KeyEvent.VK_W);
        JMenuItem jMenuItemFont = new JMenuItem("字体(F)...", KeyEvent.VK_F);
        //向菜单中添加菜单项
        jMenuFormat.add(jCheckBoxMenuItemLineWrap);
        jMenuFormat.add(jMenuItemFont);

        JMenuItem jMenuItemState = new JMenuItem("状态栏(S)", KeyEvent.VK_S);
        jMenuItemState.setEnabled(false);
        //向菜单中添加菜单项
        jMenuView.add(jMenuItemState);

        jMenuItemAbout = new JMenuItem("关于记事本(A)", KeyEvent.VK_A);
        //向菜单中添加菜单项
        jMenuHelp.add(jMenuItemAbout);

        jFrame.setJMenuBar(jMenuBar);
        jFrame.add(new JScrollPane(jTextArea));
        jFrame.setSize(1000, 700);
        jFrame.setLocation(460, 190);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
