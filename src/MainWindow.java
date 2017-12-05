
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tim
 */
public class MainWindow extends javax.swing.JFrame implements PropertyChangeListener
{

	/**
	 * Creates new form MainWindow
	 */
	private File[] files;
	private ImageList list;
	private Deque<File> imageStack;
	private ImageWorker task;
	private ArrayList<String> preferences;
	
	public MainWindow()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){}
		
		initComponents();
			
		imageStack = new LinkedList<File>();
		files=new File("dat").listFiles();
		directorySelect.removeAllItems();
		directorySelect.addItem("None");
		for (File file : files)
		{
			directorySelect.addItem(file.getName().substring(0, file.getName().indexOf('.')));
		}
		
		addImagePanel.setBorder(BorderFactory.createTitledBorder("Add Image"));
		sourceImagePanel.setBorder(BorderFactory.createTitledBorder("Find Image Source"));
		deleteImagePanel.setBorder(BorderFactory.createTitledBorder("Remove Duplicates"));
		sourceOutputField.setEditable(false);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setResizable(false);
	}
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTextField1 = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        directorySelect = new javax.swing.JComboBox();
        addFolderButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        addImagePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        imageUrlField = new javax.swing.JTextField();
        sourceInputField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        addImageButton = new javax.swing.JButton();
        sourceImagePanel = new javax.swing.JPanel();
        sourceOutputField = new javax.swing.JTextField();
        imagePathField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fileBrowseButton = new javax.swing.JButton();
        sourceButton = new javax.swing.JButton();
        deleteImagePanel = new javax.swing.JPanel();
        deleteAllButton = new javax.swing.JButton();
        deleteSomeButton = new javax.swing.JButton();
        configButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        directorySelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        directorySelect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                directorySelectActionPerformed(evt);
            }
        });

        addFolderButton.setText("Add Folder");
        addFolderButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addFolderButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Add Image URL:");

        imageUrlField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                imageUrlFieldActionPerformed(evt);
            }
        });

        sourceInputField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sourceInputFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Source (Optional):");

        addImageButton.setText("Add Image");
        addImageButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addImagePanelLayout = new javax.swing.GroupLayout(addImagePanel);
        addImagePanel.setLayout(addImagePanelLayout);
        addImagePanelLayout.setHorizontalGroup(
            addImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addImagePanelLayout.createSequentialGroup()
                        .addGroup(addImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sourceInputField)
                            .addComponent(imageUrlField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addImagePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addImageButton)))
                .addContainerGap())
        );
        addImagePanelLayout.setVerticalGroup(
            addImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imageUrlField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addImageButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sourceOutputField.setText("Please Select an Image to Source...");

        jLabel4.setText("Image Path:");

        fileBrowseButton.setText("Browse for a File...");

        sourceButton.setText("Find Source");
        sourceButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sourceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sourceImagePanelLayout = new javax.swing.GroupLayout(sourceImagePanel);
        sourceImagePanel.setLayout(sourceImagePanelLayout);
        sourceImagePanelLayout.setHorizontalGroup(
            sourceImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sourceImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sourceImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sourceOutputField)
                    .addGroup(sourceImagePanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imagePathField)))
                .addContainerGap())
            .addGroup(sourceImagePanelLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(fileBrowseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourceButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sourceImagePanelLayout.setVerticalGroup(
            sourceImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sourceImagePanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(sourceImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(imagePathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sourceImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileBrowseButton)
                    .addComponent(sourceButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(sourceOutputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        deleteAllButton.setText("Delete All Duplicates");
        deleteAllButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteAllButtonActionPerformed(evt);
            }
        });

        deleteSomeButton.setText("Manually Delete Duplicates");
        deleteSomeButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteSomeButtonActionPerformed(evt);
            }
        });

        configButton.setText("Configure...");
        configButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                configButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteImagePanelLayout = new javax.swing.GroupLayout(deleteImagePanel);
        deleteImagePanel.setLayout(deleteImagePanelLayout);
        deleteImagePanelLayout.setHorizontalGroup(
            deleteImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(deleteImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteImagePanelLayout.createSequentialGroup()
                        .addComponent(deleteAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSomeButton))
                    .addComponent(configButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        deleteImagePanelLayout.setVerticalGroup(
            deleteImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(deleteImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteAllButton)
                    .addComponent(deleteSomeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(deleteImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addImagePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sourceImagePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deleteImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourceImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setText("Select your directory...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(directorySelect, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addFolderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directorySelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addFolderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void directorySelectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_directorySelectActionPerformed
    {//GEN-HEADEREND:event_directorySelectActionPerformed
        if(directorySelect.getItemCount()>0)
		{
			if(directorySelect.getSelectedItem().equals("None"))
			{
				imageStack = new LinkedList<File>();
				imageUrlField.setEnabled(false);
				deleteAllButton.setEnabled(false);
				deleteSomeButton.setEnabled(false);
				addImageButton.setEnabled(false);
				sourceInputField.setEnabled(false);
				imagePathField.setEnabled(false);
				sourceButton.setEnabled(false);
				fileBrowseButton.setEnabled(false);
				configButton.setEnabled(false);
				list=null;
			}
			else
			{
				imageStack = new LinkedList<File>();
				list=ListLoader.LoadList(new File("dat/"+directorySelect.getSelectedItem()));
				generateList(list.getPath(),list.recurse);

				directorySelect.setEnabled(false);
				addFolderButton.setEnabled(false);
				task = new ImageWorker(list);
				task.addPropertyChangeListener(this);
				task.execute();

			}
		}
		

    }//GEN-LAST:event_directorySelectActionPerformed

    private void addFolderButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addFolderButtonActionPerformed
    {//GEN-HEADEREND:event_addFolderButtonActionPerformed
        FileChooser fileChooser = new FileChooser(this);
		fileChooser.setTitle("Please select a folder...");
		fileChooser.setLocationRelativeTo(null);
		fileChooser.setVisible(true);
		fileChooser.setAlwaysOnTop(true);
    }//GEN-LAST:event_addFolderButtonActionPerformed

    private void addImageButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addImageButtonActionPerformed
    {//GEN-HEADEREND:event_addImageButtonActionPerformed
        addImage();
    }//GEN-LAST:event_addImageButtonActionPerformed

    private void deleteAllButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteAllButtonActionPerformed
    {//GEN-HEADEREND:event_deleteAllButtonActionPerformed
        list.CountDupes(true);
    }//GEN-LAST:event_deleteAllButtonActionPerformed

    private void deleteSomeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteSomeButtonActionPerformed
    {//GEN-HEADEREND:event_deleteSomeButtonActionPerformed
		ImageJFrame listFrame = new ImageJFrame(list.CountDupes(false));
		listFrame.setResizable(false);
		listFrame.setLocationRelativeTo(null);
		listFrame.setVisible(true);
    }//GEN-LAST:event_deleteSomeButtonActionPerformed

    private void sourceButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_sourceButtonActionPerformed
    {//GEN-HEADEREND:event_sourceButtonActionPerformed
        String thisSource = list.getSourceFromFile(imagePathField.getText());
		if (thisSource!=null && thisSource!="")
		{
			sourceOutputField.setText(thisSource);
		}
    }//GEN-LAST:event_sourceButtonActionPerformed

    private void configButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_configButtonActionPerformed
    {//GEN-HEADEREND:event_configButtonActionPerformed
        String cool = JOptionPane.showInputDialog("Cool");
		preferences=new ArrayList<String>();
		String[] tempArray=cool.split(",");
		
		for(String item : tempArray)
		{
			preferences.add(0,item);
		}
		list.preferences=preferences;
		ListLoader.SaveList(list);
    }//GEN-LAST:event_configButtonActionPerformed

    private void imageUrlFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_imageUrlFieldActionPerformed
    {//GEN-HEADEREND:event_imageUrlFieldActionPerformed
       addImage();
    }//GEN-LAST:event_imageUrlFieldActionPerformed

    private void sourceInputFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_sourceInputFieldActionPerformed
    {//GEN-HEADEREND:event_sourceInputFieldActionPerformed
        addImage();
    }//GEN-LAST:event_sourceInputFieldActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainWindow().setVisible(true);
			}
		});
	}

	private void addImage()
	{
		/*
		Adds an image using the add image fields
		*/
		list.downloadImage(imageUrlField.getText().trim(), sourceInputField.getText().trim());
		
		imageUrlField.setText("");
		sourceInputField.setText("");
		ListLoader.SaveList(list);
	}
	
	private void generateList(File path, boolean recurse)
	{
		//Recursively generates the list of files that will be added to ImageList
		System.out.println(path);
		File[] fileList=path.listFiles();
		for(int i=0; i<fileList.length; i++)
		{
			if (fileList[i].isDirectory()&&recurse)
			{
				generateList(fileList[i],recurse);
			}
			if (fileList[i].isFile())
			{
				imageStack.add(fileList[i]);
			}
		}
	}
	
	public void takeList(ImageList list)
	{
		if(list!=null)
		{
			ListLoader.SaveList(list);
			directorySelect.removeAllItems();
			directorySelect.addItem("None");
			files=new File("dat").listFiles();
			for (File file : files)
			{
				directorySelect.addItem(file.getName().substring(0, file.getName().indexOf('.')));
			}
		}
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFolderButton;
    private javax.swing.JButton addImageButton;
    private javax.swing.JPanel addImagePanel;
    private javax.swing.JButton configButton;
    private javax.swing.JButton deleteAllButton;
    private javax.swing.JPanel deleteImagePanel;
    private javax.swing.JButton deleteSomeButton;
    private javax.swing.JComboBox directorySelect;
    private javax.swing.JButton fileBrowseButton;
    private javax.swing.JTextField imagePathField;
    private javax.swing.JTextField imageUrlField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton sourceButton;
    private javax.swing.JPanel sourceImagePanel;
    private javax.swing.JTextField sourceInputField;
    private javax.swing.JTextField sourceOutputField;
    // End of variables declaration//GEN-END:variables

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		if ("progress" == evt.getPropertyName())
		{
			jProgressBar1.setValue((Integer)evt.getNewValue());
		}
	}
	
	private class ImageWorker extends SwingWorker<Void, Void>
	{
		private ImageList list;
		public ImageWorker(ImageList list)
		{
			this.list=list;
		}
		
		@Override
		protected Void doInBackground() throws Exception
		{
			int initialSize = imageStack.size();
			jProgressBar1.setMaximum(initialSize);
			for(int i=0;i<initialSize;i++) 
			{
				try
				{
				list.addImage(imageStack.pop());
				int thisProgress = initialSize-imageStack.size();
				System.out.println(imageStack.size());
				jProgressBar1.setValue(initialSize-imageStack.size());
				this.setProgress(i*100/initialSize);
				}
				catch(Exception e)
				{
					System.out.println("problem officer");
				}
			}
			return null;
		}
		
		@Override
		public void done()
		{
			imageUrlField.setEnabled(true);
			deleteAllButton.setEnabled(true);
			deleteSomeButton.setEnabled(true);
			addImageButton.setEnabled(true);
			sourceInputField.setEnabled(true);
			imagePathField.setEnabled(true);
			sourceButton.setEnabled(true);
			fileBrowseButton.setEnabled(true);
			directorySelect.setEnabled(true);
			addFolderButton.setEnabled(true);
			configButton.setEnabled(true);
			ListLoader.SaveList(list);
		}
		
	}

}
