
package sintatico;
import lexico.*;
import java.io.IOException;
import java.util.*;
import static lexico.AnalisadorLexico.*;
/**
 *
 * @author guilhermeferreira
 */
public class AnalisadorSintatico {
    
    private String programaGerado = "";
    
    
    
    Stack<String> variaveis = new Stack<String>();
    
    int numVar = 0;
    
    Stack<Atributos> atributos = new Stack<Atributos>();
    
    
    
    private void ErroSemantico(Lexema token){
        System.out.println("Erro semântico na linha: " + token.getLinha() + "  com o lexema: " + token.getLexema() +
        "  do tipo: " + token.getStringTipo());
    }
    
    
    
    private String impressaoSemantico;
    
   
    
    Producao[] gramatica = 
    {   //Parametros: producao, codigo, tamanho, lado esquerdo
        new Producao("P\' -> P ", 1),
        new Producao("P -> inicio V A", 2, 3, 22),
        new Producao("V -> varinicio LV", 3, 2, 23),
        new Producao("LV -> D LV", 4, 2, 25),
        new Producao("LV -> varfim;", 5, 2, 25),
        new Producao("D -> id TIPO;", 6, 3, 26),
        new Producao("TIPO -> int", 7, 1, 27),
        new Producao("TIPO -> real", 8, 1, 27),
        new Producao("TIPO -> lit", 9, 1, 27),
        new Producao("A -> ES A", 10, 2, 24),
        new Producao("ES -> leia id;", 11, 3, 28),
        new Producao("ES -> escreva ARG;", 12, 3, 28),
        new Producao("ARG -> literal", 13, 1, 29),
        new Producao("ARG -> num", 14, 1, 29),
        new Producao("ARG -> id", 15, 1, 29),
        new Producao("A -> CMD A", 16, 2, 24),
        new Producao("CMD -> id rcb LD;", 17, 4, 30),
        new Producao("LD -> OPRM opm OPRD", 18, 3, 31),
        new Producao("LD -> OPRD", 19, 1, 31),
        new Producao("OPRD -> id", 20, 1, 32),
        new Producao("OPRD -> num", 21, 1, 32),
        new Producao("A -> COND A", 22, 2, 24),
        new Producao("COND -> CABECALHO CORPO", 23, 2, 33),
        new Producao("CABECALHO -> se (EXP_R) entao", 24, 5, 34),
        new Producao("EXP_R -> OPRD opr OPRD", 25, 3, 36),
        new Producao("CORPO -> ES CORPO", 26, 2, 35),
        new Producao("CORPO -> CMD CORPO", 27, 2, 35),
        new Producao("CORPO -> COND CORPO", 28, 2, 35),
        new Producao("CORPO -> fimse", 29, 1, 35),
        new Producao("A -> fim", 30, 1, 24),
  //      new Producao("opm -> OPM(+)",31),
  //      new Producao("opm -> OPM(-)",32),
//        new Producao("opm -> OPM(*)",33),
//        new Producao("opm -> OPM(/)",34),
//        new Producao("LD -> OPM OPRD opm OPRD",35),
//        new Producao("LD -> OPM(-) OPRD opm OPRD",36),
//        new Producao("opr -> OPR(<>)",37),
//        new Producao("opr -> OPR(=)",38),
//        new Producao("opr -> OPR(<)",39),
//        new Producao("opr -> OPR(<=)",40),
//        new Producao("opr -> OPR(>)",41, 1, 16),
//        new Producao("opr -> OPR(>=)", 42, 1, 16)
    };
    public static final int s = 0;
    public static final int t = 1;
    public static final int r = 2;
    public static final int erro = -1;
    public static final int acc = 15;
    
    public int[][][] tabelaSintatica = {
//          0           1                   2              3                4               5               6               7               8               9               10              11              12              13              14              15              16              17              18              19              20              21              22              23              24              25              26              27              28              29               30           31                32              33              34            35                36
//      inicio          varinicio	varfim	          id	          int	         real	           lit          literal           leia	        escreva             ;	           (	            )	            num	           rcb	           opm	           oPr	           se	          entao	          fim	         fimse	            $	           P	           V	           A                LV              D               TIPO            ES              ARG             CMD           LD	          OPRD	          COND	       CABECALHO	CORPO	          EXP_R
/*00*/	{{s,2},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,1},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*01*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{acc,},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*02*/	{{erro,-1},	{s,4},  	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,3},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*03*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,10},         {s,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,14},         {erro,-1},	{s,9},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,5},          {erro,-1},	{erro,-1},	{erro,-1},	{t,6},          {erro,-1},	{t,7},          {erro,-1},	{erro,-1},	{t,8},          {t,13},         {erro,-1},	{erro,-1}},
/*04*/	{{erro,-1},	{erro,-1},	{s,17},         {s,18},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,19},         {t,15},         {t,16},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*05*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,2},          {erro,-1},	{erro,-1},	{t,19},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*06*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,10},         {s,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,14},         {erro,-1},	{s,9},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,19},         {erro,-1},	{erro,-1},	{erro,-1},	{t,6},          {erro,-1},	{t,7},          {erro,-1},	{erro,-1},	{t,8},          {t,13},         {erro,-1},	{erro,-1}},
/*07*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,10},         {s,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,14},         {erro,-1},	{s,9},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,20},         {erro,-1},	{erro,-1},	{erro,-1},	{t,6},          {erro,-1},	{t,7},          {erro,-1},	{erro,-1},	{t,8},          {t,13},         {erro,-1},	{erro,-1}},
/*08*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,10},         {s,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,14},         {erro,-1},	{s,9},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,21},         {erro,-1},	{erro,-1},	{erro,-1},	{t,6},          {erro,-1},	{t,7},          {erro,-1},	{erro,-1},	{t,8},          {t,13},         {erro,-1},	{erro,-1}},
/*09*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,30},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*10*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,22},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*11*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,26},         {erro,-1},	{erro,-1},	{erro,-1},	{s,24},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,25},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,23},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*12*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,27},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*13*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,10},         {s,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,14},         {erro,-1},	{erro,-1},	{s,32},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,29},         {erro,-1},	{t,30},         {erro,-1},	{erro,-1},	{t,31},         {t,13},         {t,28},         {erro,-1}},
/*14*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,33},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*15*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,3},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,3},          {r,3},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,3},          {erro,-1},	{r,3},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*16*/	{{erro,-1},	{erro,-1},	{s,17},         {s,18},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,34},         {t,16},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*17*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,35},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*18*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,37},         {s,38},         {s,39},         {erro,-1},	{erro,-1},      {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,36},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*19*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,10},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*20*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,16},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*21*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,22},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
//	inicio          varinicio	varfim          id              int             real            lit             literal         leia            escreva         ;               (               )               num             rcb             opm             oPr             se              entao           fim             fimse           $                P              V               A               LV              D               TIPO            ES              ARG             CMD             LD              OPRD            COND            CABECALHO        CORPO          EXP_R
/*22*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},      {s,40},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*23*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,41},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*24*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,13},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*25*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,14},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*26*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,15},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*27*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,44},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,45},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,42},	{t,43},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*28*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,23},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,23},         {r,23},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,23},         {erro,-1},	{r,23},         {r,23},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*29*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,10},         {s,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,14},         {erro,-1},	{erro,-1},	{s,32},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,29},         {erro,-1},	{t,30},         {erro,-1},	{erro,-1},	{t,31},         {t,13},         {t,46},         {erro,-1}},
/*30*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,10},         {s,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,14},         {erro,-1},	{erro,-1},	{s,32},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,29},         {erro,-1},	{t,30},         {erro,-1},	{erro,-1},	{t,31},         {t,13},         {t,47},         {erro,-1}},
/*31*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,10},         {s,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,14},         {erro,-1},	{erro,-1},	{s,32},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,29},         {erro,-1},	{t,30},         {erro,-1},	{erro,-1},	{t,31},         {t,13},         {t,48},         {erro,-1}},
/*32*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,29},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,29},         {r,29},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,29},         {erro,-1},	{r,29},         {r,29},     	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*33*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,44},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,45},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,50},         {erro,-1},	{erro,-1},	{erro,-1},	{t,49}},
/*34*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,4},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,4},          {r,4},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,4},          {erro,-1},	{r,4},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*35*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,5},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,5},          {r,5},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,5},          {erro,-1},	{r,5},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*36*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,51},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*37*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,7},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*38*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,8},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*39*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,9},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*40*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,11},         {r,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,11},         {erro,-1},	{r,11},         {r,11},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*41*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,12},         {r,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,12},         {erro,-1},	{r,12},         {r,12},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*42*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,52},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*43*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,19},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,53},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
//	inicio          varinicio       varfim          id              int             real            lit             literal         leia            escreva         ;               (               )               num             rcb             opm             oPr             se              entao           fim             fimse           $               P               V               A               LV              D               TIPO            ES              ARG             CMD             LD              OPRD            COND            CABECALHO	CORPO           EXP_R
/*44*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,20},         {erro,-1},	{r,20},         {erro,-1},	{erro,-1},	{r,20},         {r,20},         {erro,-1},      {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*45*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,21},         {erro,-1},	{r,21},         {erro,-1},	{erro,-1},	{r,21},         {r,21},         {erro,-1},      {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*46*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,26},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,26},         {r,26},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,26},         {erro,-1},	{r,26},         {r,26},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*47*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,27},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,27},         {r,27},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,27},         {erro,-1},	{r,27},         {r,27},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*48*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,28},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,28},         {r,28},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,28},         {erro,-1},	{r,28},         {r,28},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*49*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},         {s,54}, 	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*50*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,55},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*51*/	{{erro,-1},	{erro,-1},	{r,6},          {r,6},          {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*52*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,17},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,17},         {r,17},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,17},         {erro,-1},	{r,17},         {r,17},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*53*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,44},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,45},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,56},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*54*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,57},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*55*/	{{erro,-1},	{erro,-1},	{erro,-1},	{s,44},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{s,45},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{t,58},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*56*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,18},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*57*/	{{erro,-1},	{erro,-1},	{erro,-1},	{r,24},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,24},         {r,24},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,24},         {erro,-1},	{erro,-1},	{r,24},         {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},
/*58*/	{{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{r,25},         {erro,-1},      {erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1},	{erro,-1}},

  
    };
    
    public Stack pilha = new Stack();
    
    public int estado = 0;
    public int error = 0;
    
    public boolean idDeclarado(String id){
        if(TabelaSimbolos.simbolos.containsKey(id)){
            if(TabelaSimbolos.simbolos.get(id).getTipo() != 0){
                return true;
            }else{
                return false;
            }
        }else{
            System.out.println("Variavel inexistente: "+id+".\nTerminando execução do compilador.");
            System.exit(0);
        }
        return false;
    }
    
    public int mapeiaToken(int token){//Mapeia token de acordo com a coluna da tabela
        //if (Main.getC() == -1) return 21;
        if(token == AnalisadorLexico.tokenInicio)
            return 0;
        if(token == AnalisadorLexico.tokenVarInicio)
            return 1;
        if(token == AnalisadorLexico.tokenVarFim)
            return 2;
        if(token == AnalisadorLexico.tokenId)
            return 3;
        if(token == AnalisadorLexico.palavraReservadaInteiro)
            return 4;
        if(token == AnalisadorLexico.palavraReservadaReal)
            return 5;
        if(token == AnalisadorLexico.palavraReservadaLiteral)
            return 6;
        if(token == AnalisadorLexico.tokenLiteral)
            return 7;
        if(token == AnalisadorLexico.tokenLeia)
            return 8;
        if(token == AnalisadorLexico.tokenEscreva)
            return 9;
        if(token == AnalisadorLexico.tokenPontoVirgula)
            return 10;
        if(token == AnalisadorLexico.tokenAbreParenteses)
            return 11;
        if(token == AnalisadorLexico.tokenFechaParenteses)
            return 12;
        if(token == AnalisadorLexico.tokenNumero)
            return 13;
        if(token == AnalisadorLexico.tokenDeclaracao)
            return 14;
        if(token == AnalisadorLexico.tokenMais || token == AnalisadorLexico.tokenMenos 
                || token == AnalisadorLexico.tokenMultiplicacao || token == AnalisadorLexico.tokenDivisao )
            return 15;
        if(token == AnalisadorLexico.tokenMenor || token == AnalisadorLexico.tokenMenorIgual 
                || token == AnalisadorLexico.tokenDiferente || token == AnalisadorLexico.tokenMaior 
                || token == AnalisadorLexico.tokenMaiorIgual || token == AnalisadorLexico.tokenIgual)
            return 16;
        if(token == AnalisadorLexico.tokenSe)
            return 17;
        if(token == AnalisadorLexico.tokenEntao)
            return 18;
        if(token == AnalisadorLexico.tokenFim)
            return 19;
        if(token == AnalisadorLexico.tokenFimSe)
            return 20;
        if(token == AnalisadorLexico.tokenCifrao)
            return 21;
        
        return -1;
    }
    public void erro(Lexema token){
        System.out.println("Erro na linha: " + token.getLinha()+ " com o lexema: " + token.getLexema());
    }
    
    
    
    public void analisadorSintatico() throws IOException{
        
        pilha.push(estado);
        Lexema token = Main.obterLexemas();
        System.out.println("token lido: " + token.getStringToken() + " q e " + token.getLexema() + "  do tipo " + token.getTipo());
        //while (error == -1 || (token.getToken() != AnalisadorLexico.tokenFim && token.getLexema() != "fim") ){
        while (true){  
            
            if(!pilha.isEmpty()){
                estado = (int)pilha.peek();// novo s'  --> primeiro ciclo --> s' = s = 0.
            }else{
                erro(token);
            }
            
            int acao = tabelaSintatica[estado][mapeiaToken(token.getToken())][0];
            //System.out.println("token: "+ token.getStringToken() + " linha: " + token.getLinha()+ " acao: [" + estado+ "],[" + mapeiaToken(token.getToken()) + "]" + " acao: " + acao);
            
            switch(acao){
                case s:
                    //System.out.println("estado no topo: " + estado);
                    pilha.push(mapeiaToken(token.getToken()));
                    atributos.push(new Atributos(token.getStringToken(),  token));
                    //                          Terminal ou ñ terminal,   atributos
                    System.out.println("empilhei o token " + token.getStringToken() + " q e " + token.getLexema() + "  do tipo " + token.getTipo() );
                    estado = tabelaSintatica[estado][mapeiaToken(token.getToken())][1];

                    pilha.push(estado);
                    
                    
                    token = Main.obterLexemas();
                    if(!token.getLexema().equals("fim") && token.getToken() == AnalisadorLexico.tokenFim && Main.getC() == -1){
                        token = new Lexema("FIM DO ARQUIVO", AnalisadorLexico.tokenCifrao, 0, token.getLinha());
                    }
                    break;

                case r:
                    int numProducao = tabelaSintatica[estado][mapeiaToken(token.getToken())][1];
 //                   System.out.println("token:" + token.getStringToken());
                    regraSemantica(numProducao, token);
                    
                    for(int i = 0; i < gramatica[numProducao-1].getTamanho() * 2; i++){
                        pilha.pop();
                    }
                    
                    estado = (int)pilha.peek();
                    
                    int ladoEsq = gramatica[numProducao-1].getLadoEsquerdo();
                    pilha.push(ladoEsq);
                    pilha.push(tabelaSintatica[estado][ladoEsq][1]);
//                    System.out.println("token:" + token.getStringToken()+" "+ token.getLexema()+" linha: "+ token.getLinha()   + " reduz prod: " + numProducao + " Esq " + ladoEsq);
//                    System.out.println("pilha depois de r: " + pilha.toString());
                    System.out.println("Producao: " + gramatica[numProducao-1].getProducao());
                    break;

                case acc:
//                    System.out.println(pilha.toString());
//                    System.out.println("Aceitooooooo Uhuuuuuuuuuuuuuuuu!");
                      System.out.println(programaGerado);
                    return;


                default:
                    System.out.println("Estado:      "+estado);
                    erro(token);
                    return;
                               
    }
    }
    
    
    }
    
     private void regraSemantica(int numeroProducao, Lexema token){
        Atributos aux;
        Atributos aux2;
        Lexema lex;
        String exp;
        switch (numeroProducao){
            case 5: 
                programaGerado += "\n\n\n";
                break;
            case 6:
                atributos.pop(); // remove ;
                aux = atributos.pop();// TIPO
                aux2 =atributos.pop(); //id
                if(idDeclarado(aux2.getLexema())){
                   System.out.println("Variável já declarada anteriormente! Variável: " + aux2.getLexema() + "\nSegunda tentativa de declaração na linha " + aux2.getLinha());
                   System.exit(0);
                }
                aux2.setTipo(aux.getTipo());
                lex = TabelaSimbolos.simbolos.get(aux2.getLexema());
                lex.setTipo(aux2.getTipo());
                TabelaSimbolos.simbolos.put(lex.getLexema(),lex);
                System.out.println("Tipo: "+lex.getTipo());
                programaGerado += lex.getStringTipo() + " " + lex.getLexema() + ";\n";
                
                break;
                
                
            case 7:        case 8:          case 9:
                System.out.println("\n\n"+atributos.peek().getTipo()+"\n\n");
                atributos.peek().setVariavel("TIPO");
               
                
                break;
                
           
                
            case 11:
                break;
                
            case 12:
                break;
                
            case 13:        case 14:
                aux = atributos.pop();
                aux.setVariavel("ARG");
                atributos.push(aux);
                break;
                
            case 15:
                aux = atributos.pop();
                if(TabelaSimbolos.simbolos.containsKey(aux.getLexema())     &&
                   TabelaSimbolos.simbolos.get(aux.getLexema()).getTipo() != 0)
                    aux.setVariavel("ARG");
                else 
                    System.out.println("Erro: Variável não declarada!");
                    ErroSemantico(token);
                break;
                
            case 17:
                exp = atributos.pop().getLexema();// ;
                aux2 = atributos.pop();// LD
                aux = atributos.pop(); // rcb
                if((TabelaSimbolos.simbolos.get(atributos.peek().getLexema())) == null ||
                    (TabelaSimbolos.simbolos.get(atributos.peek().getLexema())).getTipo() == 0){
                    
                }
                if(atributos.peek().getTipo() == aux2.getTipo()){
                    exp = atributos.peek().getLexema() + " " + aux.getLexema() + " " + aux2.getLexema() + ";";
                    programaGerado += exp;
                    
                }else{
                    System.out.println("Erro: Operandos com tipos incompatíveis!");
                    ErroSemantico(token);
                }
                
                break;
                
            case 18:
               /* aux = atributos.pop(); //OPRD 2
                aux2 = atributos.pop();// opm
                if(TabelaSimbolos.simbolos.containsKey(aux.getLexema())     &&
                   TabelaSimbolos.simbolos.get(aux.getLexema()).getTipo() != 0  &&
                   TabelaSimbolos.simbolos.containsKey((atributos.peek()).getLexema())     &&
                   TabelaSimbolos.simbolos.get((atributos.peek()).getLexema()).getTipo() != 0  &&
                   aux.getTipo() == atributos.peek().getTipo()      &&
                   aux.getTipo() != "LITERAL"                       &&
                   atributos.peek().getTipo() != "LITERAL"){
                    exp = aux2.getLexema() + " " + aux.getLexema();
                    aux = atributos.pop();
                    exp = aux.getLexema() + " " + exp;
                    aux.setLexema("T"+Integer.toString(numVar));
                    programaGerado += aux.getLexema() + " = " + exp;
                    aux.setVariavel("EXP_R");
                    variaveis.push(aux.getLexema());
                    atributos.push(aux);
                    numVar++;
                }else{
                    System.out.println("Erro: Operandos com tipos incompatíveis!");
                    ErroSemantico(token);
                }
                break;
                */
            case 19:
                aux = atributos.pop();
                aux.setVariavel("LD");
                atributos.push(aux);
                break;
                
            case 20:
                aux = atributos.pop();
                if(TabelaSimbolos.simbolos.containsKey(aux.getLexema())     &&
                   TabelaSimbolos.simbolos.get(aux.getLexema()).getTipo() != 0){
                    aux.setVariavel("OPRD");
                }
                else
                    System.out.println("Erro: Variável nao declarada!");
                    ErroSemantico(token);
                break;
                
            case 21:
                aux = atributos.pop();
                aux.setVariavel("OPRD");
                atributos.push(aux);
                break;
                
            case 23:
                programaGerado += "}";
                break;
                
            case 24:
                atributos.remove(atributos.size() - 4);
                atributos.remove(atributos.size() - 2);
                programaGerado += "if(" + atributos.get(atributos.size() - 2).getLexema() + "){";
                break;
                
            case 25:
                aux = atributos.pop(); //OPRD 2
                aux2 = atributos.pop();// opr
                if(aux.getTipo() == atributos.peek().getTipo()){
                    exp = aux2.getLexema() + " " + aux.getLexema();
                    aux = atributos.pop();
                    exp = aux.getLexema() + " " + exp;
                    aux.setLexema("T"+Integer.toString(numVar));
                    programaGerado += aux.getLexema() + " = " + exp;
                    aux.setVariavel("EXP_R");
                    variaveis.push(aux.getLexema());
                    atributos.push(aux);
                    numVar++;
                }else{
                    System.out.println("Erro: Operandos com tipos incompatíveis!");
                    ErroSemantico(token);
                }
                
                
                break;
                
            default:
                break;
        }
        
    }
}

