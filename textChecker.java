package alina;
 
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class romen
{
  public static void main (String args[]){
    FrameWindow  frame;
    frame =  new FrameWindow("Text Editor"); 
    frame.setVisible(true); 
  }
}
class FrameWindow extends Frame implements ActionListener, WindowListener
{
  TextArea ta;
  MenuBar mb;  
  
  Menu mFile;  
  
  MenuItem miOpen;
  MenuItem miSave;
  MenuItem miSaveAs;
  MenuItem miExit;
  
  String PolniiPut = "";
  byte buf[];
  public FrameWindow(String szTitle)
  {
    super(szTitle);
    setSize(800, 600);
    
    mb = new MenuBar();
    mFile = new Menu("File");
    
    miOpen = new MenuItem("Open...");
    mFile.add(miOpen);
    
    miSave = new MenuItem("Save");
    mFile.add(miSave);
    
    miSaveAs = new MenuItem("Save As...");
    mFile.add(miSaveAs);
    
    mFile.add("-");
    
    miExit = new MenuItem("Exit");
    mFile.add(miExit);
    
    mb.add(mFile);
    
    
    setMenuBar(mb);
    
    ta = new TextArea(10, 30); 
    setLayout(new BorderLayout());
    add("Center", ta);         
   }
 
  
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource().equals(miOpen))
    {
      FileOpen();
    }
    
    else if(e.getSource().equals(miSave))
    {
      FileSave();
    }
    
    else if(e.getSource().equals(miSaveAs))
    {
      FileSaveAs();
    }
    
    else if(e.getSource().equals(miExit))
    {
      setVisible(false);
      System.exit(0);
    }
  }
  
 
  public void windowClosing(WindowEvent e)
  {
    setVisible(false);
    System.exit(0);
  }
  
  public void windowOpened(WindowEvent e) {}
  public void windowClosed(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
  
  void FileOpen()
  {
    FileDialog      FD; 
    FD = new FileDialog(this, "Open file",FileDialog.LOAD);
    FD.show(); 
    PolniiPut = FD.getDirectory()+FD.getFile();
 
    setTitle("Text Editor" + " - " +PolniiPut);
    
    FileInputStream FIS = null;
        
    try
    {
      FIS = new FileInputStream(PolniiPut); 
     
      buf = new byte[FIS.available()]; 
      FIS.read(buf);
    }
    catch (IOException ex)
    {
      System.out.println(ex.toString());
    }
    
    ta.selectAll();
    
    ta.replaceRange("", 0, ta.getSelectionEnd());
    
    String szStr = new String(buf);
    
    
    
    StringTokenizer st;
    st = new StringTokenizer(szStr, "\r\n");
    while(st.hasMoreElements())
    {
      szStr = new String((String)st.nextElement());
      ta.append(szStr + "\r\n");
    }
    try
    {
     FIS.close();
    }
    catch (IOException ex)
    {
      System.out.println(ex.toString());
    }
  }
  void FileSaveAs()
  {
    FileDialog FD;
    FD = new FileDialog(this, "Save file as...",  FileDialog.SAVE);
    FD.show();
 
    if(FD.getDirectory() == null || FD.getFile() == null) 
      {
       System.out.println("No File Selected!!!");
      }
     else 
      {
       PolniiPut = FD.getDirectory()+FD.getFile();
       setTitle("Text Editor" + " - " +PolniiPut);
       FileSave();
      }
  }
  void FileSave()
  {
    FileOutputStream OS = null;
    
    String sz = ta.getText();
    buf = sz.getBytes(); 
    
    if(PolniiPut=="" || PolniiPut==null)
     {
      System.out.println("No File Selected!!!");    
     }
    else 
     {
      try 
       {
        OS = new FileOutputStream(PolniiPut);
        OS.write(buf);
        OS.close();
       }
      catch (IOException ex)
       {
        System.out.println(ex.toString());
       }    
    }
  }
}
