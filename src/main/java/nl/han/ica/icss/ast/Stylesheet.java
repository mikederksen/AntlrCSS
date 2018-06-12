package nl.han.ica.icss.ast;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A stylesheet is the root node of the AST, it consists of one or more statements
 *
 */
public class Stylesheet extends ASTNode {


	public ArrayList<ASTNode> body;
	
	public Stylesheet() {
		this.body = new ArrayList<>();
	}
	public Stylesheet(ArrayList<ASTNode> body) {
		this.body = body;
	}
	@Override
	public String getNodeLabel() {
		return "Stylesheet";
	}
	@Override
	public ArrayList<ASTNode> getChildren() {
		return this.body;
	}
	@Override
	public void addChild(ASTNode child) {
	    	body.add(child);
	}
	@Override
	public void removeChild(ASTNode child) {
		body.remove(child);
	}

	@Override
	public String toString() {
		return getChildren() != null && getChildren().size() > 0
				? toStringOfChildren()
				: "";
	}

	private String toStringOfChildren() {
        return getChildren()
                .stream()
                .map(c -> c.toString() + "\n")
                .collect(Collectors.joining());
    }
}
