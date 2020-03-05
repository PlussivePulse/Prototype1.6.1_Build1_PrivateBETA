package ccc.mainComponent.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class JTreePanelTree extends JFrame{
	
	protected DefaultTreeModel treeModel;
	protected JTree treeObj;
	protected JTextField displayObj;
	
	public JTreePanelTree() {
		super("File Explorer");
		setSize(400,300);
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(new IconData (null, null, ""));
		DefaultMutableTreeNode node;
		File[] roots = File.listRoots();
		for(int i=0; i<roots.length; i++) {
			node = new DefaultMutableTreeNode(new IconData(null,null,new FileNode(roots[i])));
			top.add(node);
			node.add(new DefaultMutableTreeNode(new Boolean(true)));
		}
		
		treeModel = new DefaultTreeModel(top);
		treeObj = new JTree(treeModel);
		
		treeObj.putClientProperty("JTree.lineStyle", "Angled");
		TreeCellRenderer renderer = new IconCellRenderer ();
		treeObj.setCellRenderer(renderer);
		
		treeObj.addTreeExpansionListener(new DirExpansionListener());
		
		treeObj.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		treeObj.setShowsRootHandles(true);
		treeObj.setEditable(false);
		
		JScrollPane s = new JScrollPane();
		s.getViewport().add(treeObj);
		getContentPane().add(s,BorderLayout.CENTER);
		
		displayObj = new JTextField();
		displayObj.setEditable(false);
		getContentPane().add(displayObj,BorderLayout.NORTH);
		
		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(wndCloser);
		setVisible(true);
	
	}

	DefaultMutableTreeNode getTreeNode(TreePath path) {
		return (DefaultMutableTreeNode) (path.getLastPathComponent());
	}

	FileNode getFileNode(DefaultMutableTreeNode node) {
		if(node == null)
			return null;
		Object obj = node.getUserObject();
		if(obj instanceof IconData) obj = ((IconData) obj).getObject();
		if(obj instanceof FileNode) return (FileNode) obj;
		else
		return null;
	}
	
	class DirExpansionListener implements TreeExpansionListener{
		public void treeExpanded(TreeExpansionEvent event) {
			final DefaultMutableTreeNode node = getTreeNode(event.getPath());
			final FileNode fnode = getFileNode(node);
			
			Thread runner = new Thread() {
				public void run() {
					if(fnode != null && fnode.expand(node)) {
						Runnable runnable = new Runnable() {
							public void run() {
								treeModel.reload(node);
							}
						};
						SwingUtilities.invokeLater(runnable);
					}
				}
			};
			runner.start();
		}

		public void treeCollapsed(TreeExpansionEvent event) {}
	}
	
	class DirSelectionListener implements TreeSelectionListener{

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			Object nodeInfo = null;
			DefaultMutableTreeNode node = getTreeNode(e.getPath());
			
			FileNode fnode = getFileNode(node);
			if(fnode != null) {
				displayObj.setText(fnode.getFile().getAbsolutePath());
				nodeInfo = node.getUserObject();
				System.out.println(nodeInfo);
				return;
			}

			else
				displayObj.setText("");
		}
		
	}


	public static void main(String argv[]) {
		new JTreePanelTree();
	}
}

class IconCellRenderer 
extends JLabel 
implements TreeCellRenderer{

	protected Color textSelectionColor;
	protected Color textNonSelectionColor;
	protected Color bkSelectionColor;
	protected Color bkNonSelectionColor;
	protected Color borderSelectionColor;
	
	protected boolean oselected;
	
	public IconCellRenderer() {
		super();
		textSelectionColor = UIManager.getColor("Tree.selectionForeground");
		textNonSelectionColor = UIManager.getColor("Tree.textFpreground");
		bkSelectionColor = UIManager.getColor("Tree.textBackground");
		bkNonSelectionColor = UIManager.getColor("Tree.textBackground");
		borderSelectionColor = UIManager.getColor("Tree.selectionBorderColor");
		setOpaque(false);
	
	}
	
	@Override

	
	
	public Component getTreeCellRendererComponent
	(JTree tree, Object value, boolean selected, boolean expanded,
	boolean leaf, int row, boolean hasFocus) {
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		Object obj = node.getUserObject();
		setText(obj.toString());
		
		if(obj instanceof Boolean)
			setText("Loading Data...");
		if(obj instanceof IconData) {
			IconData idata = (IconData)obj;
			if(expanded)
				setIcon(idata.getExpandedIcon());
			else
				setIcon(idata.getIcon());
		}else 
			setIcon(null);

		
		setFont(tree.getFont());
		setForeground(selected ? textSelectionColor : textNonSelectionColor);
		setBackground(selected ? bkSelectionColor : bkNonSelectionColor);
		oselected = selected;
		
		return this;
	}
	
	public void paintComponent(Graphics g) {
		Color bColor = getBackground();
		Icon icon = getIcon();
		
		g.setColor(bColor);
		int offset = 0;
		if(icon != null && getText() != null)
			offset = (icon.getIconWidth() + getIconTextGap());
		g.fillRect(offset, 0, getWidth()-1-offset, getHeight()-1);
		
		if(oselected) {
			g.setColor(borderSelectionColor);
			g.drawRect(offset, 0, getWidth()-1-offset, getHeight()-1);
		}
		super.paintComponent(g);
	}
	
}
	
	class IconData{
		protected Icon icon;
		protected Icon expandedIcon;
		protected Object data;
		
		public IconData(Icon icon, Object data) {
			this.icon = icon;
			this.expandedIcon = null;
			this.data = data;
		}
		
		public IconData(Icon icon, Icon expandedIcon, Object data) {
			this.icon = icon;
			this.expandedIcon = expandedIcon;
			this.data = data;
		}
		
		public Icon getIcon() {
			return icon;
		}
		
		public Icon getExpandedIcon() {
			return expandedIcon!=null ? expandedIcon : icon;
		}
		
		public Object getObject() {
			return data;
		}
		
		public String toString() {
			return data.toString();
		}
	}
	
	class FileNode{
		
		protected File ofile;
		
		public FileNode(File file) {
			ofile = file;
		}
		
		public boolean expand(DefaultMutableTreeNode parent) {
			DefaultMutableTreeNode flag = (DefaultMutableTreeNode)parent.getFirstChild();
			if(flag==null)
				return false;
			Object obj = flag.getUserObject();
			if(!(obj instanceof Boolean))
				return false;
			parent.removeAllChildren();
			
			File[] files = listFiles();
			if(files == null)
				return true;

			Vector v = new Vector();
			
			for(int k=0; k<files.length; k++) {
				File f = files[k];
				if(!f.isDirectory())
					continue;
				
				FileNode newNode = new FileNode(f);
				
				boolean isAdded = false;
				for(int i = 0; i<v.size(); i++) {
					FileNode nd = (FileNode)v.elementAt(i);
					if(newNode.compareTo(nd) < 0) {
						v.insertElementAt(newNode, i);
						isAdded = true;
						break;
					}
				}
				if(!isAdded)
					v.addElement(newNode);
			}
			
			for(int i = 0; i<v.size(); i++) {
				FileNode nd = (FileNode)v.elementAt(i);
				IconData idata = new IconData(null,null, nd);
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(idata);
				parent.add(node);
				
				if(nd.hasSubDirs())
					node.add(new DefaultMutableTreeNode(new Boolean(true)));
			}
			
			return true;
		}

		public File getFile() {
			return ofile;
		}
		
		public String toString() {
			return ofile.getName().length() > 0 ? ofile.getName() : ofile.getPath();
		}
		
		private boolean hasSubDirs() {
			File[] files = listFiles();
			if(files==null)
			return false;
			for(int k = 0; k<files.length; k++) {
				if(files[k].isDirectory())
					return true;
			}
			return false;
		}
		
		private int compareTo(FileNode toCompare) {
			return ofile.getName().compareToIgnoreCase(toCompare.ofile.getName());
		}

		private File[] listFiles() {
			if(!ofile.isDirectory())
				return null;
			try {
				return ofile.listFiles();
			}catch(Exception ex) {
				
			}
			return null;
		}
		
}
