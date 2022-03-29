package com.example.proyecto.Model;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%ignorecase
%char
L=[a-zA-Z]+
espacio=[ \t\r\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%
/* Espacios en blanco */
{espacio} {/*Ignore*/}

( int ) {return new Symbol(sym.Int, yychar, yyline, yytext());}
( decimal ) {return new Symbol(sym.Decimal, yychar, yyline, yytext());}
( double ) {return new Symbol(sym.Double, yychar, yyline, yytext());}
( float ) {return new Symbol(sym.Float, yychar, yyline, yytext());}
( mediumint ) {return new Symbol(sym.Mediumint, yychar, yyline, yytext());}
( real ) {return new Symbol(sym.Real, yychar, yyline, yytext());}
( smallint ) {return new Symbol(sym.Smallint, yychar, yyline, yytext());}
( tinyint ) {return new Symbol(sym.Tinyint, yychar, yyline, yytext());}
( char ) {return new Symbol(sym.Char, yychar, yyline, yytext());}
( varchar ) {return new Symbol(sym.Varchar, yychar, yyline, yytext());}
( text ) {return new Symbol(sym.Text, yychar, yyline, yytext());}
( longtext ) {return new Symbol(sym.Longtext, yychar, yyline, yytext());}
( mediumtext ) {return new Symbol(sym.Mediumtext, yychar, yyline, yytext());}
( tinytext ) {return new Symbol(sym.Tinytext, yychar, yyline, yytext());}
( nchar ) {return new Symbol(sym.Nchar, yychar, yyline, yytext());}
( nvarchar ) {return new Symbol(sym.Nvarchar, yychar, yyline, yytext());}
( date ) {return new Symbol(sym.Date, yychar, yyline, yytext());}
( datetime ) {return new Symbol(sym.Datetime, yychar, yyline, yytext());}
( year ) {return new Symbol(sym.Year, yychar, yyline, yytext());}
( time ) {return new Symbol(sym.Time, yychar, yyline, yytext());}
( timestamp ) {return new Symbol(sym.Timestamp, yychar, yyline, yytext());}


( create ) {return new Symbol(sym.Create, yychar, yyline, yytext());}
( database ) {return new Symbol(sym.Database, yychar, yyline, yytext());}
( use ) {return new Symbol(sym.Use, yychar, yyline, yytext());}
( table ) {return new Symbol(sym.Table, yychar, yyline, yytext());}
( not ) {return new Symbol(sym.Not, yychar, yyline, yytext());}
( null ) {return new Symbol(sym.Nulo, yychar, yyline, yytext());}
( auto_increment ) {return new Symbol(sym.autoi, yychar, yyline, yytext());}
( primary ) {return new Symbol(sym.primary, yychar, yyline, yytext());}
( key ) {return new Symbol(sym.key, yychar, yyline, yytext());}
( "(" ) {return new Symbol(sym.pa, yychar, yyline, yytext());}
( ")" ) {return new Symbol(sym.pc, yychar, yyline, yytext());}
( "," ) {return new Symbol(sym.coma, yychar, yyline, yytext());}
( ";" ) {return new Symbol(sym.pcoma, yychar, yyline, yytext());}

{L}(_{L})* {return new Symbol(sym.identificador, yychar, yyline, yytext());}
(1|2|3|4|5|6|7|8|9)|((1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9))|((1|2)((0|1|2|3|4|5))((0|1|2|3|4|5))) {return new Symbol(sym.Longitud, yychar, yyline, yytext());}

/* Error de analisis */
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}