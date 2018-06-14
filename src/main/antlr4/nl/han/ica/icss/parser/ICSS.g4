grammar ICSS;

stylesheet: variableDef* something* EOF;

variableDef
    : 'let' variableKey 'is' valueExpression CLOSE_SIGN
    ;

variableKey
    : '$'VARIABLE_NAME
    ;

block
    : OPEN_BRACKET declaration* CLOSE_BRACKET
    ;

switchRule
    : selector SWITCH variableKey switchCase* switchDefault
    ;

switchCase
    : CASE valueAmount block
    ;

switchDefault
    : DEFAULT block
    ;

something
    : stylerule
    | switchRule
    ;

stylerule
    : selector block
    ;

declaration
    : colorDeclaration
    | widthDeclaration
    | heightDeclaration
    ;

colorDeclaration
    : COLOR_KEY KV_SEP valueExpression CLOSE_SIGN
    ;

widthDeclaration
    : WIDTH_KW KV_SEP valueExpression CLOSE_SIGN
    ;

heightDeclaration
    : HEIGHT_KW KV_SEP valueExpression CLOSE_SIGN
    ;


valueExpression
    : valueAmount
    | variableKey
    | valueExpression '*' valueExpression
    | valueExpression '+' valueExpression
    | valueExpression '-' valueExpression
    ;

valueAmount
    : amount
    | hexVal
    ;

amount
    : amountPx
    | amountScalar
    ;

amountPx
    : NUMBER 'px'
    ;

amountScalar
    : NUMBER
    ;

hexVal
    : '#' HEX_AMOUNT
    ;


selector
    : tagSelector
    | classSelector
    | idSelector
    ;

tagSelector
    : ELEMENT
    ;

classSelector
    : '.' ELEMENT
    ;

idSelector
    : '#' ELEMENT
    ;


HEX_AMOUNT
    : HEX HEX HEX HEX HEX HEX
    ;


OPEN_BRACKET
    : '{'
    ;

CLOSE_BRACKET
    : '}'
    ;

CLOSE_SIGN
    : ';'
    ;

KV_SEP
    : ':'
    ;

WIDTH_KW
    : 'width'
    ;

HEIGHT_KW
    : 'height'
    ;

COLOR_KEY
    : 'background-color'
    | 'color'
    ;

SWITCH
    : 'switch'
    ;

CASE
    : 'case'
    ;

DEFAULT
    : 'default'
    ;


VARIABLE_NAME
    : [A-Z_]+;

ELEMENT
    : [a-zA-Z] ([a-zA-Z] | '-' | '_')*
    ;

NUMBER
    : [1-9] [0-9]*
    | [0]
    ;

HEX
    : [0-9A-Fa-f]
    ;

WS
    : [ \t\r\n]+ -> skip
    ;