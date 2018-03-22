grammar ICSS;

stylesheet: variableDef* block* EOF;

variableDef: 'let' variableKey 'is' variableVal CLOSE_SIGN;
block: identifier OPEN_BRACKET statement* CLOSE_BRACKET;

statement: colorStatement | widthStatement;
colorStatement: COLOR_KEY KV_SEP colorVal CLOSE_SIGN;
widthStatement: WIDTH_KW KV_SEP widthVal CLOSE_SIGN;

variableVal: hexVal | sizePx;
sizePx: AMOUNT 'px';
variableKey: '$' identifier;

colorVal: hexVal | variableKey;
widthVal: sizePx | variableKey;

hexVal: '#' HEX_AMOUNT;

identifier: IDENTIFIER_NAME | class | id;
class: '.' IDENTIFIER_NAME;
id: '#' IDENTIFIER_NAME;

HEX_AMOUNT: HEX HEX HEX HEX HEX HEX;

OPEN_BRACKET: '{';
CLOSE_BRACKET: '}';
CLOSE_SIGN: ';';
KV_SEP: ':';

WIDTH_KW: 'width';
COLOR_KEY: 'background-color' | 'color';

IDENTIFIER_NAME: [a-zA-Z] ([a-zA-Z] | '-' | '_')*;
AMOUNT: [1-9] [0-9]*;
HEX: [0-9A-Fa-f];

WS: [ \t\r\n]+ -> skip;
