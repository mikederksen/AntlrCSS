grammar ICSS;

stylesheet: variableDef* stylerule* EOF;

variableDef
    : 'let' variableKey 'is' variableVal CLOSE_SIGN
    ;

variableVal
    : hexVal
    | amount
    ;


stylerule
    : selector declaration
    | SWITCH variableKey switchBody
    ;

declaration
    : OPEN_BRACKET statement* CLOSE_BRACKET
    ;

switchBody
    : switchCase* switchDefault
    ;

switchCase
    : CASE NUMBER  declaration
    ;

switchDefault
    : DEFAULT declaration
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
    : '$' selector
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

COLOR_KEY
    : 'background-color'
    | 'color'
    ;

SWITCH
    : 'body switch'
    ;

CASE
    : 'case'
    ;

DEFAULT
    : 'default'
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
