grammar ICSS;

stylesheet: variableDef* block* EOF;

variableDef
    : 'let' variableKey 'is' variableVal CLOSE_SIGN
    ;

variableVal
    : hexVal
    | amount
    ;


block
    : identifier OPEN_BRACKET statement* CLOSE_BRACKET
    ;

statement
    : colorStatement
    | widthStatement
    ;


colorStatement
    : COLOR_KEY KV_SEP colorVal CLOSE_SIGN
    ;

widthStatement
    : WIDTH_KW KV_SEP widthVal CLOSE_SIGN
    ;


widthVal
    : amount
    | variableKey
    | widthVal '+' widthVal
    | widthVal '*' widthVal
    ;

variableKey
    : '$' identifier
    ;

colorVal
    : hexVal
    | variableKey
    ;

amount
    : amountPx
    | NUMBER;

amountPx
    : NUMBER 'px'
    ;

hexVal
    : '#' HEX_AMOUNT
    ;


identifier
    : ELEMENT
    | classElement
    | idElemet
    ;

classElement
    : '.' ELEMENT
    ;

idElemet
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

COLOR_KEY
    : 'background-color'
    | 'color'
    ;


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

WS:
    [ \t\r\n]+ -> skip
    ;
