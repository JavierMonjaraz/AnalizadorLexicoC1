package com.example.proyecto.Model;
import static com.example.proyecto.Model.Tokens.*;
%%
%class Lexer
%type Tokens
%ignorecase
L=[a-zA-Z]+
espacio=[ \t\r\n]+
%{
    public String lexeme;
%}
%%
create |
table |
database |
auto_increment |
null |
not |
primary |
key {lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"(" {lexeme=yytext(); return ParentesisApertura;}
")" {lexeme=yytext(); return ParentesisCierre;}
";" {lexeme=yytext(); return PCOMA;}
"," {lexeme=yytext(); return COMA;}
int |
decimal |
double |
float |
mediumint |
real |
smallint |
tinyint |
char |
varchar |
text |
Longtext |
Mediumtext |
Tinytext |
Nchar |
Nvarchar |
Date {lexeme=yytext(); return DataType;}
{L}(_{L})* {lexeme=yytext(); return Identificador;}
(1|2|3|4|5|6|7|8|9)|((1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9))|((1|2)((0|1|2|3|4|5))((0|1|2|3|4|5))) {lexeme=yytext(); return Longitud;}
 . {lexeme=yytext(); return ERROR;}