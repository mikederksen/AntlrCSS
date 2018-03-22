grammar ICSS;

stylesheet: block* EOF;

block: IDENTIFIER OPENBRACKET statement* CLOSEBRACKET;
statement: colorStatement | widthStatement;
colorStatement: COLORKEY KVSEP COLORVALUE CLOSESIGN;
widthStatement: WIDTHKEY KVSEP SIZEPX CLOSESIGN;

COLORKEY: 'background-color' | 'color';
COLORVALUE: '#' HEX HEX HEX HEX HEX HEX;

WIDTHKEY: 'width';
SIZEPX: [1-9] [0-9]* 'px';

IDENTIFIER: IDENTIFIERNAME | CLASS | ID;
CLASS: '.' IDENTIFIERNAME;
ID: '#' IDENTIFIERNAME;
IDENTIFIERNAME: [a-zA-Z]+;

HEX: [0-9A-Fa-f];
OPENBRACKET: '{';
CLOSEBRACKET: '}';
CLOSESIGN: ';';
KVSEP: ':';

WS: [ \t\r\n]+ -> skip;