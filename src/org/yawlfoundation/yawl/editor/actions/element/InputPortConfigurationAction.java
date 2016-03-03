package org.yawlfoundation.yawl.editor.actions.element;

import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.yawlfoundation.yawl.editor.YAWLEditor;
import org.yawlfoundation.yawl.editor.actions.YAWLBaseAction;
import org.yawlfoundation.yawl.editor.actions.net.PreviewConfigurationProcessAction;
import org.yawlfoundation.yawl.editor.elements.model.CPort;
import org.yawlfoundation.yawl.editor.elements.model.YAWLTask;
import org.yawlfoundation.yawl.editor.net.NetGraph;
import org.yawlfoundation.yawl.editor.swing.TooltipTogglingWidget;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.util.List;

public class InputPortConfigurationAction extends YAWLBaseAction implements TooltipTogglingWidget {
 
	private static final long serialVersionUID = 1L;
	
	{
	    putValue(Action.SHORT_DESCRIPTION, getDisabledTooltipText());
	    putValue(Action.NAME, "Input Ports...");
	    putValue(Action.LONG_DESCRIPTION, "Configure the input ports for this task.");
      putValue(Action.SMALL_ICON, getPNGIcon("arrow_in"));
	    
	  }
		
	private NetGraph net;
	private YAWLTask task;
	 
	 public InputPortConfigurationAction( YAWLTask task, NetGraph net) {
		  super();
		  this.task = task;
		  this.net = net;
	  }

    public void actionPerformed(ActionEvent event) {

        final YAWLTask task = this.task;

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputConfigurationJDialog dialog = new InputConfigurationJDialog(
                        new javax.swing.JFrame(), true, task, net);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                });
                dialog.setMinimumSize(dialog.getSize());
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(YAWLEditor.getInstance());
                dialog.setVisible(true);
            }
        });
    }

	public String getDisabledTooltipText() {
		return null;
	}


	public String getEnabledTooltipText() {
		return "Configure the input ports for this task.";
	}
	/**
	 *
	 * @author jingxin
	 */
	private class InputConfigurationJDialog extends javax.swing.JDialog {
		
		private List<CPort> inputPorts;
		
		private YAWLTask task;
		private NetGraph net;
		private int size;
		private TableColumn column = null;
		private TableColumn IDcolumn = null;
		private ActionEvent simulateEvent = new ActionEvent(this,1001,"Preview Process Configuration");
		private PreviewConfigurationProcessAction simulateAction=
            PreviewConfigurationProcessAction.getInstance();
		
		/** Creates new form NewJDialog */
	    public InputConfigurationJDialog(java.awt.Frame parent, boolean modal,YAWLTask task, NetGraph net) {
	        super(parent, modal);
	        this.task = task;
	        this.net = net;
	        size = this.task.getInputCPorts().size();
	        initInputPorts();
	        initComponents();
	        
	    }
	    
	    public void  initInputPorts(){
	    	this.inputPorts = this.task.getInputCPorts();    	
	    }
	    public Object[][] getPortsInformation(){
	    	Object[][] rowInfor = new Object[size][3];
	    	
	    	
	    	for(int i=0; i<size ; i++){	
	    			rowInfor[i][0] = this.inputPorts.get(i).getID();
	    			rowInfor[i][1] = this.inputPorts.get(i).getSourceTasksLabels();
	    			rowInfor[i][2] = this.inputPorts.get(i).getConfigurationSetting();		
	    	}
	    	return rowInfor;
	    }
	    
	    /** This method is called from within the constructor to
	     * initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is
	     * always regenerated by the Form Editor.
	     */
	    @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">
	    private void initComponents() {

	        jLabel1 = new javax.swing.JLabel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        InputPortsConfigurationTable = new javax.swing.JTable();
	        AllowButton = new javax.swing.JButton();
	        BlockButton = new javax.swing.JButton();
	        HideButton = new javax.swing.JButton();
	        DefaultButton = new javax.swing.JButton();
	        SetDefaultButton = new javax.swing.JButton();
	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        this.setTitle("Input Ports Configuration");
	        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
	        jLabel1.setText("Please select which ports you want to configure.");
	        
	        

	        InputPortsConfigurationTable.setModel(new javax.swing.table.DefaultTableModel(
	        		 this.getPortsInformation(),
	                 new String [] {
	        				 "Port ID", "Source Nodes",  "Configuration"
	                 }
	        ) {
	            Class[] types = new Class [] {
	                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
	            };
	            boolean[] canEdit = new boolean [] {
	                false, false, false
	            };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        
	        for (int i = 0; i < 3; i++) {
	            column = InputPortsConfigurationTable.getColumnModel().getColumn(i);
	            if (i == 0) {
	                column.setPreferredWidth(15); 
	                IDcolumn = column;
	            } 
	            if (i == 1) {
	                column.setPreferredWidth(160);
	            } 
	            if (i == 2) {
	                column.setPreferredWidth(50);
	                } 
	        }
	        
	      
	     
	        for(int i=0; i<this.InputPortsConfigurationTable.getRowCount(); i++){
		        column.setCellRenderer(new ConfigurationTableCellRenderer());
	        }
	        IDcolumn.setCellRenderer(new PortIDRenderer());
	       
	        jScrollPane1.setViewportView(InputPortsConfigurationTable);

	        AllowButton.setText("Activate");
	        AllowButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                AllowButtonActionPerformed(evt);
	            }
	        });

	        BlockButton.setText("Block");
	        BlockButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                BlockButtonActionPerformed(evt);
	            }
	        });

	        HideButton.setText("Hide");
	        HideButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                HideButtonActionPerformed(evt);
	            }
	        });

	        DefaultButton.setText("Use Default");
	        DefaultButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                DefaultButtonActionPerformed(evt);
	            }
	        });

	        SetDefaultButton.setText("Set Defaults");
	        SetDefaultButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                SetDefaultButtonActionPerformed(evt);
	            }
	        });
	        
	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.LEADING)
	            .add(layout.createSequentialGroup()
	                .addContainerGap()
	                .add(layout.createParallelGroup(GroupLayout.LEADING)
	                    .add(layout.createSequentialGroup()
	                        .add(AllowButton)
	                        .addPreferredGap(LayoutStyle.RELATED)
	                        .add(BlockButton)
	                        .addPreferredGap(LayoutStyle.RELATED)
	                        .add(HideButton)
	                        .addPreferredGap(LayoutStyle.RELATED)
	                        .add(DefaultButton)
	                        .addPreferredGap(LayoutStyle.RELATED, 12, Short.MAX_VALUE)
	                        .add(SetDefaultButton))
	                    .add(GroupLayout.TRAILING, jScrollPane1, GroupLayout.DEFAULT_SIZE,
                          395, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.LEADING)
	            .add(GroupLayout.TRAILING, layout.createSequentialGroup()
	                .add(33, 33, 33)
	                .add(jScrollPane1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.RELATED, 48, Short.MAX_VALUE)
	                .add(layout.createParallelGroup(GroupLayout.BASELINE)
	                    .add(SetDefaultButton)
	                    .add(AllowButton)
	                    .add(BlockButton)
	                    .add(HideButton)
	                    .add(DefaultButton))
	                .add(20, 20, 20))
	        );
	        
	        if(this.net.getConfigurationSettings().isAllowBlockingInputPorts()){
	        	this.BlockButton.setEnabled(true);
	        }else{
	        	this.BlockButton.setEnabled(false);
	        }
	        if(this.net.getConfigurationSettings().isAllowChangingDefaultConfiguration()){
	        	this.SetDefaultButton.setEnabled(true);
	        }else{
	        	this.SetDefaultButton.setEnabled(false);
	        }
	        
	        this.InputPortsConfigurationTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	        this.InputPortsConfigurationTable.addMouseListener(new java.awt.event.MouseAdapter() {
	        	public void mouseReleased(java.awt.event.MouseEvent evt) {
	                jTableMouseReleased(evt);
	            }
	        });
	        pack();
	    }// </editor-fold>
	    
	    private void jTableMouseReleased(java.awt.event.MouseEvent evt) {
	        // TODO add your handling code here:
	        boolean defaultFlag = false;
	        boolean defaultBlockFlag = true;
	        boolean defaultActivateFlag = true;
	        boolean blockFlag = true;
	        boolean activateFlag = true;
          String s = null;
	        int length = this.InputPortsConfigurationTable.getSelectedRowCount();
	        int[] selectedRows = this.InputPortsConfigurationTable.getSelectedRows();
	        for(int i=0; i<length; i++){
	        	int portId = (Integer) this.InputPortsConfigurationTable.getValueAt(selectedRows[i], 0);
	        	if(this.inputPorts.get(portId).getDefaultValue() != null){
	        		defaultFlag = true;
	        	}
	        	if(this.net.getServiceAutonomous() != null){
	        		blockFlag = blockFlag && (this.net.getServiceAutonomous().processCorrectnessCheckingForBlock(task, "INPUT", portId));
	        		if(this.inputPorts.get(portId).getConfigurationSetting().equals(CPort.BLOCKED)){
	        			activateFlag = activateFlag &&
	        							(this.net.getServiceAutonomous().processCorrectnessCheckingForActivate(task, "INPUT", portId));
	        		}
	        		
	        		if(this.inputPorts.get(portId).getDefaultValue() != null){
	        			if(this.inputPorts.get(portId).getDefaultValue().equals(CPort.BLOCKED)){
	        				defaultBlockFlag = defaultBlockFlag && (this.net.getServiceAutonomous().processCorrectnessCheckingForBlock(task, "INPUT", portId));
	        			} else if (this.inputPorts.get(portId).getDefaultValue().equals(CPort.ACTIVATED)){
	        				defaultActivateFlag = defaultActivateFlag &&
							(this.net.getServiceAutonomous().processCorrectnessCheckingForActivate(task, "INPUT", portId));
	        			}
	        		}
	        		
	        	}
            s = (String) InputPortsConfigurationTable.getValueAt(selectedRows[i], 2);
	        }
	        this.DefaultButton.setEnabled(defaultFlag && defaultBlockFlag && defaultActivateFlag);
	        this.BlockButton.setEnabled(blockFlag && ! matchText(s, "blocked"));
	        this.AllowButton.setEnabled(activateFlag && ! matchText(s, "activated"));
          HideButton.setEnabled(! matchText(s, "hidden"));
	    }

      private boolean matchText(String s, String other) {
          return (s != null) && (s.equals(other));
      }


      private void toggleEnabled(JButton btn) {
          AllowButton.setEnabled(btn != AllowButton);
          BlockButton.setEnabled(btn != BlockButton);
          HideButton.setEnabled(btn != HideButton);
      }

	    private void AllowButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
	        // TODO add your handling code here:
	        int length = this.InputPortsConfigurationTable.getSelectedRowCount();
	        int[] selectedRows = this.InputPortsConfigurationTable.getSelectedRows();
	        for(int i=0; i<length; i++){
	        	ActivateAPort(selectedRows, i);	
	        }
	        if(this.net.getConfigurationSettings().isApplyAutoGreyOut()){
	        	this.simulateAction.actionPerformed(simulateEvent);
	        }
          toggleEnabled(AllowButton);
	    }

		private void ActivateAPort(int[] selectedRows, int i) {
			int portId = (Integer) this.InputPortsConfigurationTable.getValueAt(selectedRows[i], 0);
			this.inputPorts.get(portId).setConfigurationSetting("activated");
			this.InputPortsConfigurationTable.setValueAt(this.inputPorts.get(portId).getConfigurationSetting(), selectedRows[i], 2);
			if(this.net.getServiceAutonomous() != null ){
				this.net.getServiceAutonomous().changeCurrentStateAfterActivate(task, "INPUT", portId);
			}
		}                                            

	    private void BlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        // TODO add your handling code here:
	    	int length = this.InputPortsConfigurationTable.getSelectedRowCount();
	        int[] selectedRows = this.InputPortsConfigurationTable.getSelectedRows();
	        for(int i=0; i<length; i++){
	        	BlockAPort(selectedRows, i);
	        }
	        if(this.net.getConfigurationSettings().isApplyAutoGreyOut()){
	        	this.simulateAction.actionPerformed(simulateEvent);
	        }
          toggleEnabled(BlockButton);
	    }

		private void BlockAPort(int[] selectedRows, int i) {
			int portId = (Integer) this.InputPortsConfigurationTable.getValueAt(selectedRows[i], 0);
			this.inputPorts.get(portId).setConfigurationSetting("blocked");
			this.InputPortsConfigurationTable.setValueAt("blocked", selectedRows[i], 2);
			if(this.net.getServiceAutonomous() != null ){
				this.net.getServiceAutonomous().changeCurrentStateAfterBlock(task, "INPUT", portId);
			}
		}

	    private void HideButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        // TODO add your handling code here:
	    	int length = this.InputPortsConfigurationTable.getSelectedRowCount();
	        int[] selectedRows = this.InputPortsConfigurationTable.getSelectedRows();
	        for(int i=0; i<length; i++){
	        	HideAPort(selectedRows, i);
	        }
	        if(this.net.getConfigurationSettings().isApplyAutoGreyOut()){
	        	this.simulateAction.actionPerformed(simulateEvent);
	        }
          toggleEnabled(HideButton);
	    }

		private void HideAPort(int[] selectedRows, int i) {
			int portId = (Integer) this.InputPortsConfigurationTable.getValueAt(selectedRows[i], 0);
			this.inputPorts.get(portId).setConfigurationSetting("hidden");
			this.InputPortsConfigurationTable.setValueAt("hidden", selectedRows[i], 2);
		}

	    private void DefaultButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        // TODO add your handling code here:
	    	int length = this.InputPortsConfigurationTable.getSelectedRowCount();
	        int[] selectedRows = this.InputPortsConfigurationTable.getSelectedRows();
	        for(int i=0; i<length; i++){
	        	int portId = (Integer) this.InputPortsConfigurationTable.getValueAt(selectedRows[i], 0);
	        	if(this.inputPorts.get(portId).getDefaultValue() != null){
		        	if(this.inputPorts.get(portId).getDefaultValue().equals(CPort.ACTIVATED)){
		        		ActivateAPort(selectedRows, i);	
		        	}else if(this.inputPorts.get(portId).getDefaultValue().equals(CPort.BLOCKED)){
		        		BlockAPort(selectedRows, i);
		        	}else if(this.inputPorts.get(portId).getDefaultValue().equals(CPort.HIDDEN)){
		        		HideAPort(selectedRows, i);
		        	}
	        	}
	        }
	        if(this.net.getConfigurationSettings().isApplyAutoGreyOut()){
	        	this.simulateAction.actionPerformed(simulateEvent);
	        }
	    }

	    private void SetDefaultButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        // TODO add your handling code here:
	    	 final YAWLTask task = this.task;
	    	 java.awt.EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                SetInputPortDefaultConfigurationJDialog dialog = new SetInputPortDefaultConfigurationJDialog(new javax.swing.JFrame(), true, net, task);
		                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
		                });
		                dialog.setLocationRelativeTo(YAWLEditor.getInstance());
		                dialog.setMinimumSize(dialog.getSize());
		                dialog.setVisible(true);
		            }
		        });
	    }
	    
	    
	    // Variables declaration - do not modify
	    private javax.swing.JButton AllowButton;
	    private javax.swing.JButton BlockButton;
	    private javax.swing.JButton DefaultButton;
	    private javax.swing.JButton HideButton;
	    private javax.swing.JButton SetDefaultButton;
	    private javax.swing.JTable InputPortsConfigurationTable;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JScrollPane jScrollPane1;

  }
	
	private class SetInputPortDefaultConfigurationJDialog extends javax.swing.JDialog {
	    
		private NetGraph net;
		private YAWLTask task;
		private Object[][] rowInfor;
		private List<CPort> inputPorts;
		
		public void  initInputPorts(){
	    	this.inputPorts = this.task.getInputCPorts();   
	    	System.out.println(this.inputPorts.size());
	    }
	    public Object[][] getPortsInformation(){
	    	int size = this.inputPorts.size();
			Object[][] rowInfor = new Object[size ][3];
	    	for(int i=0; i<size ; i++){	
	    			rowInfor[i][0] = this.inputPorts.get(i).getID();
	    			rowInfor[i][1] = this.inputPorts.get(i).getSourceTasksLabels();
	    			rowInfor[i][2] = this.inputPorts.get(i).getDefaultValue();		
	    	}
	    	return rowInfor;
	    }
	    
		
	    /** Creates new form NewJDialog */
	    public SetInputPortDefaultConfigurationJDialog(java.awt.Frame parent, boolean modal, NetGraph net, YAWLTask task) {
	        super(parent, modal);
	        this.net = net;
	        this.task = task;
	        initInputPorts();
	        this.rowInfor = this.getPortsInformation();
	        initComponents();
	        
	    }
	    
	    /** This method is called from within the constructor to
	     * initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is
	     * always regenerated by the Form Editor.
	     */
	    @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">
	    private void initComponents() {
	    	
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();
	        AllowButton = new javax.swing.JButton();
	        BlockButton = new javax.swing.JButton();
	        HideButton = new javax.swing.JButton();
	        EmptyButton = new javax.swing.JButton();
	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        this.setTitle("Input Ports Default Configuration Setting");

	        jTable1.setModel(new javax.swing.table.DefaultTableModel(
	            rowInfor,
	            new String [] {
	                "Port ID", "Source Nodes", "Default Configuration"
	            }
	        ) {
	            Class[] types = new Class [] {
	                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
	            };
	            boolean[] canEdit = new boolean [] {
	                false, false, false
	            };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        
	        jTable1.getColumnModel().getColumn(0).setCellRenderer(new PortIDRenderer());
	        jTable1.getColumnModel().getColumn(2).setCellRenderer(new ConfigurationTableCellRenderer());
	        jScrollPane1.setViewportView(jTable1);
	        jTable1.getColumnModel().getColumn(0).setPreferredWidth(15);
	        jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
          jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseReleased(java.awt.event.MouseEvent evt) {
                  String s = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
                  AllowButton.setEnabled(! matchText(s, "activated"));
                  BlockButton.setEnabled(! matchText(s, "blocked"));
                  HideButton.setEnabled(! matchText(s, "hidden"));
              }
          });

	        
	        AllowButton.setText("Activate");
	        AllowButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                AllowButtonActionPerformed(evt);
	            }
	        });

	        BlockButton.setText("Block ");
	        BlockButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                BlockButtonActionPerformed(evt);
	            }
	        });

	        HideButton.setText("Hide");
	        HideButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                HideButtonActionPerformed(evt);
	            }
	        });
	        
	        EmptyButton.setText("No Default");
	        EmptyButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                EmptyButtonActionPerformed(evt);
	            }
	        });

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.LEADING)
	            .add(layout.createSequentialGroup()
	                .addContainerGap()
	                .add(layout.createParallelGroup(GroupLayout.LEADING)
	                    .add(GroupLayout.TRAILING, layout.createSequentialGroup()
	                        .add(AllowButton)
	                        .add(18, 18, 18)
	                        .add(BlockButton)
	                        .add(18, 18, 18)
	                        .add(HideButton)
	                        .addPreferredGap(LayoutStyle.RELATED, 67, Short.MAX_VALUE)
	                        .add(EmptyButton))
	                    .add(GroupLayout.TRAILING, jScrollPane1, GroupLayout.DEFAULT_SIZE,
                          395, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.LEADING)
	            .add(GroupLayout.TRAILING, layout.createSequentialGroup()
	                .add(33, 33, 33)
	                .add(jScrollPane1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.RELATED, 48, Short.MAX_VALUE)
	                .add(layout.createParallelGroup(GroupLayout.BASELINE)
	                    .add(EmptyButton)
	                    .add(AllowButton)
	                    .add(BlockButton)
	                    .add(HideButton))
	                .add(20, 20, 20))
	        );
	        this.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	        pack();
	    }// </editor-fold>

	private void AllowButtonActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
		int length = jTable1.getSelectedRowCount();
	        int[] selectedRows = this.jTable1.getSelectedRows();
	        for(int i=0; i<length; i++){
	        	int portId = (Integer) this.jTable1.getValueAt(selectedRows[i], 0);
	        	this.inputPorts.get(portId).setDefaultValue(CPort.ACTIVATED);
	        	this.jTable1.setValueAt("activated", selectedRows[i], 2);	
	        }
      toggleEnabled(AllowButton);
	}

	private void BlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
		int length = jTable1.getSelectedRowCount();
        int[] selectedRows = this.jTable1.getSelectedRows();
        for(int i=0; i<length; i++){
        	int portId = (Integer) this.jTable1.getValueAt(selectedRows[i], 0);
        	this.inputPorts.get(portId).setDefaultValue(CPort.BLOCKED);
        	this.jTable1.setValueAt(CPort.BLOCKED, selectedRows[i], 2);	
        }
      toggleEnabled(BlockButton);
	}

	private void HideButtonActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
		int length = jTable1.getSelectedRowCount();
	        int[] selectedRows = this.jTable1.getSelectedRows();
	        for(int i=0; i<length; i++){
	        	int portId = (Integer) this.jTable1.getValueAt(selectedRows[i], 0);
	        	this.inputPorts.get(portId).setDefaultValue(CPort.HIDDEN);
	        	this.jTable1.setValueAt(CPort.HIDDEN, selectedRows[i], 2);	
	        }
      toggleEnabled(HideButton);
	}
	    
	private void EmptyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
		int length = jTable1.getSelectedRowCount();
        int[] selectedRows = this.jTable1.getSelectedRows();
        for(int i=0; i<length; i++){
        	int portId = (Integer) this.jTable1.getValueAt(selectedRows[i], 0);
        	this.inputPorts.get(portId).setDefaultValue(null);
        	this.jTable1.setValueAt(null, selectedRows[i], 2);	
        }
      toggleEnabled(EmptyButton);
    }

      private boolean matchText(String s, String other) {
          return (s != null) && (s.equals(other));
      }


      private void toggleEnabled(JButton btn) {
          AllowButton.setEnabled(btn != AllowButton);
          BlockButton.setEnabled(btn != BlockButton);
          HideButton.setEnabled(btn != HideButton);
      }
      
	    
	    // Variables declaration - do not modify
	    private javax.swing.JButton AllowButton;
	    private javax.swing.JButton BlockButton;
	    private javax.swing.JButton HideButton;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTable jTable1;
	    private javax.swing.JButton EmptyButton;
	    // End of variables declaration
	    
	}
	
}
