package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.*;
import java.util.ArrayList;
import java.util.List;

public class Generator {

	public String generate(AST ast) {
        ASTNode root = ast.root;
       	StringBuilder builder = new StringBuilder();

        if(root != null) {
			addToBuilder(root, builder, 0);

			return builder.toString();
		}

        return "Empty...";
	}

	private void addToBuilder(ASTNode node, StringBuilder builder, int indent) {
		builder.append(node.getNodeLabel());

		for(ASTNode child : node.getChildren()) {
		    addToBuilder(child, builder, indent + 1);
        }
	}
}
