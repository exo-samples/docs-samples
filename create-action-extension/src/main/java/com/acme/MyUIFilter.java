package com.acme;
import java.util.Map;
import javax.jcr.Node;
import org.exoplatform.webui.ext.filter.UIExtensionFilter;
import org.exoplatform.webui.ext.filter.UIExtensionFilterType;
public class MyUIFilter implements UIExtensionFilter {
	/*
		* This method checks if the current node is a file.
	*/
	public boolean accept(Map<String, Object> context) throws Exception {
		//Retrieve the current node from the context
		Node currentNode = (Node) context.get(Node.class.getName());
		return currentNode.isNodeType("nt:file");
	}
	/*
		* This is the type of the filter.
	*/
	public UIExtensionFilterType getType() {
		return UIExtensionFilterType.MANDATORY;
	}
	/*
		* This is called when the filter has failed.
	*/
	public void onDeny(Map<String, Object> context) throws Exception {
		System.out.println("This node is not a file!");
	}
	}	