grammar ICSS;

stylesheet
    : identifier EOF
    ;

identifier
    : IDENTIFIERMARK IDENTIFIERNAME
    ;

IDENTIFIERMARK
    : '.'
    | '#'
    ;

IDENTIFIERNAME
    : [a-zA-Z]+
    ;

OPENBRACKET
    : '{'
    ;

CLOSEBRACKET
    : '}'
    ;

WS
    : [ \t\r\n]+ -> skip
    ;





//p {
//	background-color: #ffffff;
//	width: 500px;
//}
//
//a {
//	color: #ff0000;
//}
//
//#menu {
//	width: 520px;
//}
//
//.menu {
//	color: #000000;
//}
