package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Stylerule;
import nl.han.ica.icss.ast.Switchrule;

import java.util.ArrayList;
import java.util.List;

public class ReduceSwitchRules implements Transform {

    @Override
    public void apply(AST ast) {
        findSwitchParent(ast.root);
    }

    private void findSwitchParent(ASTNode currentNode) {
        if (childrenContainSwitchrule(currentNode.getChildren())) {
            solveSwitchRules(currentNode);
        } else {
            currentNode.getChildren()
                    .forEach(this::findSwitchParent);
        }
    }

    private boolean childrenContainSwitchrule(List<ASTNode> children) {
        return children
                .stream()
                .anyMatch(c -> c instanceof Switchrule);
    }

    private void solveSwitchRules(ASTNode switchParent) {
        List<ASTNode> childrenClone = new ArrayList<>(switchParent.getChildren());

        childrenClone
                .stream()
                .filter(c -> c instanceof Switchrule)
                .map(c -> (Switchrule) c)
                .forEach(c -> {
                    switchParent.removeChild(c);
                    switchParent.addChild(solveSwitchRule(c));
                });
    }

    private Stylerule solveSwitchRule(Switchrule switchrule) {
        return switchrule.valueCases
                .stream()
                .filter(r -> r.value.equals(switchrule.match))
                .findFirst()
                .map(r -> new Stylerule(switchrule.selector, r.body))
                .orElseGet(() -> new Stylerule(switchrule.selector, switchrule.defaultCase.body));
    }
}
