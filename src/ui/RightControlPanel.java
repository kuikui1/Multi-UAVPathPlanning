/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import config.StaticInitConfig;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import world.World;
import world.model.KnowledgeInterface;
import world.model.WorldKnowledge;

/**
 *
 * @author boluo
 */
public class RightControlPanel extends javax.swing.JPanel implements TreeSelectionListener,MouseListener,ActionListener {

    private static KnowledgeInterface kb;
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RightControlPanel.class);
    private JPopupMenu popMenu;
    /**
     * Creates new form RightControlPanel
     */
    public RightControlPanel() {
        initComponents();
        RightControlPanel.jTree1.addMouseListener(this);
        RightControlPanel.jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        RightControlPanel.jTree1.addTreeSelectionListener(this);
        RightControlPanel.kb = World.kb;
        RightControlPanel.jTree1.setModel(kb);
        
        popMenu=new JPopupMenu();
        JMenuItem dellItem=new JMenuItem("删除");
        popMenu.add(dellItem);
        dellItem.addActionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        controlPanel1 = new ui.ControlPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(1);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setTopComponent(controlPanel1);

        jScrollPane1.setViewportView(jTree1);

        jSplitPane1.setRightComponent(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public static void setWorldKnowledge(KnowledgeInterface kb)
    {
        RightControlPanel.kb=kb;
        RightControlPanel.jTree1.setModel(kb);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ui.ControlPanel controlPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    public static javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        Object select_obj = RightControlPanel.jTree1.getLastSelectedPathComponent();
        if (select_obj!=null&&kb.isLeaf(select_obj)) {
            String node_name = select_obj.toString();
            int index = -1;
            if (node_name.contains(StaticInitConfig.OBSTACLE_NAME)) {
                int index_name_prefix = node_name.indexOf(StaticInitConfig.OBSTACLE_NAME) + StaticInitConfig.OBSTACLE_NAME.length();
                index = Integer.parseInt(node_name.substring(index_name_prefix));
                AnimationPanel.highlight_obstacle_index = index;
            } else if (node_name.contains(StaticInitConfig.THREAT_NAME)) {
                int index_name_prefix = node_name.indexOf(StaticInitConfig.THREAT_NAME) + StaticInitConfig.THREAT_NAME.length();
                index = Integer.parseInt(node_name.substring(index_name_prefix));
                AnimationPanel.highlight_threat_index = index;
            } else if (node_name.contains(StaticInitConfig.CONFLICT_NAME)) {
                int index_name_prefix = node_name.indexOf(StaticInitConfig.CONFLICT_NAME) + StaticInitConfig.CONFLICT_NAME.length();
                index = Integer.parseInt(node_name.substring(index_name_prefix));
                AnimationPanel.highlight_uav_index = index;
            }
        } else if(select_obj!=null){
            AnimationPanel.highlight_obstacle_index = -1;
            AnimationPanel.highlight_threat_index = -1;
            AnimationPanel.highlight_uav_index = -1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TreePath path=RightControlPanel.jTree1.getPathForLocation(e.getX(), e.getY());
        if(path==null)
            return;
        RightControlPanel.jTree1.setSelectionPath(path);
        boolean is_leaf_selected= RightControlPanel.kb.isLeaf(path.getLastPathComponent());
        if(e.getButton()==MouseEvent.BUTTON3 && is_leaf_selected)
        {
            popMenu.show(jTree1, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreePath path=RightControlPanel.jTree1.getSelectionPath();
        RightControlPanel.kb.deleteComponent(path,RightControlPanel.jTree1.getLastSelectedPathComponent());
        RightControlPanel.jTree1.setModel(RightControlPanel.kb);
        RightControlPanel.jTree1.removeSelectionPath(path);
    }
}
