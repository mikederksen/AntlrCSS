package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.Stylerule;

import java.util.List;
import java.util.stream.Collectors;

public class MergeRules implements Transform {

    @Override
    public void apply(AST ast) {
        ast.root.getChildren().stream()
                .filter(c -> c instanceof Stylerule)
                .map(c -> (Stylerule) c)
                .collect(Collectors.groupingBy(c -> c.selector.toString()))
                .forEach((k, stylerules) -> unionStylerules(ast, stylerules));
    }

    private void unionStylerules(AST ast, List<Stylerule> stylerules) {
        if (stylerules.size() > 1) {
            for (Stylerule stylerule : stylerules.subList(1, stylerules.size())) {
                stylerule.getChildren().forEach(stylerules.get(0)::addChild);
                ast.root.removeChild(stylerule);
            }
        }
    }
}
