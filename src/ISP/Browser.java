package ISP;

/**
 *
 * @editor Aziz
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.*;


public class Browser extends JFrame
{

    Container konten = getContentPane();
    private JTextField txtUrl = new JTextField(20);
    private JButton btnCari = new JButton("Search");
    private JButton btnKembali = new JButton("Back");
    private JToolBar toolbar = new JToolBar();
    private Stack stack = new Stack();
    private JEditorPane editPane = new JEditorPane();
    private JScrollPane pane = new JScrollPane();

    //Konstruktor
    public Browser()
    {
        setTitle("@Warkop.id - Mini Browser");
        setSize(700,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        toolbar.add(txtUrl);
        toolbar.add(btnCari);
        toolbar.add(btnKembali);
        txtUrl.setToolTipText("Masukkan alamat URL");
        txtUrl.setText("https://");

        //Melakukan aksi pencarian ketika tombol enter ditekan
        txtUrl.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent act)
            {
              try
              {
                 stack.push(txtUrl.getText());
                 editPane.setPage(txtUrl.getText());
              }
              catch(Exception ex)
              {
                  System.out.println(ex);
              }
            }
        });

        //Melakukan aksi pencarian ketika tombol cari di klik
        btnCari.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent act)
            {
               try
               {
                   stack.push(txtUrl.getText());
                   editPane.setPage(txtUrl.getText());
               }
               catch(Exception ex)
               {
                   System.out.println(ex);
               }
            }
        });

        //Kembali ke halaman web sebelumnya
        btnKembali.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent act)
           {
              if(stack.size()<=1)
                   return;
               try
               {
                stack.pop();
                String url = (String)stack.peek();
                txtUrl.setText(url);
                editPane.setPage(url);
               }
               catch(Exception ex)
               {
                   System.out.println(ex);
               }
           }
        });

        editPane.setEditable(false);
        editPane.addHyperlinkListener(new HyperlinkListener()
        {
            public void hyperlinkUpdate(HyperlinkEvent hyper)
            {
                if(hyper.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                {
                    try
                    {
                    stack.push(hyper.getURL().toString());
                    txtUrl.setText(hyper.getURL().toString());
                    editPane.setPage(hyper.getURL());
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex);
                    }
                }
            }
        });

        pane.getViewport().add(editPane);

        konten.setLayout(new BorderLayout());
        konten.add(pane,BorderLayout.CENTER);
        konten.add(toolbar,BorderLayout.NORTH);


    }//Akhir Konstruktor
    public void enable(){
        new Browser();
    }
}