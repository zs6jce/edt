/*******************************************************************************
 * Copyright © 2012, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/

//----------------------------------------------------
// The following code was generated by CUP v0.10k
// Tue Nov 20 14:32:03 EST 2012
//----------------------------------------------------

package org.eclipse.edt.compiler.core.ast;

/** CUP generated class containing symbol constants. */
public class NodeTypes {
  /* terminals */
  public static final int IMPLEMENTS = 85;
  public static final int EXIT = 115;
  public static final int BIGINTLIT = 107;
  public static final int GT = 20;
  public static final int GET = 129;
  public static final int HOLD = 168;
  public static final int BITANDEQ = 44;
  public static final int DIVEQ = 49;
  public static final int ERRORSQLSTMTLIT = 7;
  public static final int GE = 29;
  public static final int CALL = 112;
  public static final int CONCATEQ = 51;
  public static final int STACK = 157;
  public static final int ERRORBLOCKCOMMENT = 6;
  public static final int BITAND = 11;
  public static final int RPAREN = 56;
  public static final int INTO = 136;
  public static final int BYPOSITION = 159;
  public static final int DECREMENT = 138;
  public static final int WHERE = 175;
  public static final int TIMES = 35;
  public static final int TIMESEQ = 48;
  public static final int RECORD = 74;
  public static final int BLOCK_COMMENT = 69;
  public static final int GOTO = 116;
  public static final int UMINUS = 66;
  public static final int REPLACE = 132;
  public static final int EQ = 17;
  public static final int BITOREQ = 45;
  public static final int PROGRAM = 77;
  public static final int BYTESLIT = 104;
  public static final int TIMESTIMES = 37;
  public static final int ADD = 126;
  public static final int TIMESTIMESEQ = 50;
  public static final int TYPE = 87;
  public static final int RELATIVE = 151;
  public static final int DIV = 36;
  public static final int EOS = 3;
  public static final int ASSIGN = 63;
  public static final int LABEL = 164;
  public static final int NULL = 109;
  public static final int PREPARE = 131;
  public static final int PREVIOUS = 147;
  public static final int PRIVATE = 95;
  public static final int EOF = 0;
  public static final int MINUS = 33;
  public static final int LIBRARY = 78;
  public static final int LANGUAGEBUNDLE = 172;
  public static final int QUESTION = 42;
  public static final int MOVE = 118;
  public static final int IMPORT = 72;
  public static final int PACKAGE = 71;
  public static final int USING = 144;
  public static final int CONCAT = 38;
  public static final int ELSE = 167;
  public static final int BYNAME = 158;
  public static final int TRY = 122;
  public static final int MODULOEQ = 53;
  public static final int ABSOLUTE = 152;
  public static final int DELEGATE = 82;
  public static final int END = 73;
  public static final int OR = 8;
  public static final int FIRST = 148;
  public static final int RIGHTSHIFTLOGICALEQ = 26;
  public static final int HANDLER = 75;
  public static final int WHEN = 154;
  public static final int AND = 9;
  public static final int CURRENT = 150;
  public static final int THIS = 92;
  public static final int OF = 173;
  public static final int BY = 137;
  public static final int NEXT = 146;
  public static final int RETURNS = 89;
  public static final int SEMI = 61;
  public static final int GROUP = 171;
  public static final int LAST = 149;
  public static final int CLOSE = 125;
  public static final int NEW = 93;
  public static final int DELETE = 127;
  public static final int REF = 174;
  public static final int EXTERNALTYPE = 83;
  public static final int SUPER = 94;
  public static final int MINUSEQ = 47;
  public static final int INTERFACE = 81;
  public static final int RCURLY = 60;
  public static final int NE = 18;
  public static final int AT = 40;
  public static final int AS = 30;
  public static final int CASE = 113;
  public static final int PLUSEQ = 46;
  public static final int LEFTSHIFTEQ = 24;
  public static final int PLUS = 32;
  public static final int THROW = 123;
  public static final int CLASS = 76;
  public static final int LPAREN = 55;
  public static final int ALL = 161;
  public static final int RBRACKET = 58;
  public static final int WHILE = 124;
  public static final int UPLUS = 67;
  public static final int CONST = 62;
  public static final int XOR = 12;
  public static final int OPEN = 130;
  public static final int RUNUNIT = 156;
  public static final int INPARENT = 153;
  public static final int COMMA = 65;
  public static final int LT = 19;
  public static final int SMALLINTLIT = 108;
  public static final int WRAP = 176;
  public static final int RETURNING = 139;
  public static final int MODULO = 34;
  public static final int SERVICE = 80;
  public static final int ENUMERATION = 84;
  public static final int LE = 21;
  public static final int SCROLL = 169;
  public static final int NOT = 14;
  public static final int OUT = 91;
  public static final int LBRACKET = 57;
  public static final int LINEBREAKS = 70;
  public static final int RIGHTSHIFTARITHMETICEQ = 28;
  public static final int WITH = 140;
  public static final int LEFTSHIFT = 23;
  public static final int BANG = 16;
  public static final int SINGLEROW = 143;
  public static final int QUESTIONQUESTION = 43;
  public static final int FORUPDATE = 141;
  public static final int RETURN = 120;
  public static final int WS = 4;
  public static final int FLOATLIT = 105;
  public static final int LINE_COMMENT = 68;
  public static final int FOR = 133;
  public static final int FUNCTION = 79;
  public static final int TRANSACTION = 162;
  public static final int XOREQ = 54;
  public static final int INTEGER = 100;
  public static final int DOT = 41;
  public static final int WITHV60COMPAT = 160;
  public static final int USINGKEYS = 145;
  public static final int ABSTRACT = 97;
  public static final int STRING = 103;
  public static final int BOS = 2;
  public static final int INSERT = 166;
  public static final int CONSTRUCTOR = 98;
  public static final int CONTINUE = 114;
  public static final int RIGHTSHIFTLOGICAL = 25;
  public static final int USE = 88;
  public static final int FROM = 135;
  public static final int BITOR = 10;
  public static final int OTHERWISE = 155;
  public static final int FOREACH = 134;
  public static final int IS = 13;
  public static final int NULLCONCAT = 39;
  public static final int URL = 163;
  public static final int SMALLFLOATLIT = 106;
  public static final int IN = 15;
  public static final int DECIMALLIT = 101;
  public static final int error = 1;
  public static final int IF = 117;
  public static final int ID = 99;
  public static final int STATIC = 96;
  public static final int BOOLEANLIT = 102;
  public static final int RIGHTSHIFTARITHMETIC = 27;
  public static final int NEGATE = 22;
  public static final int SQLSTMTLIT = 110;
  public static final int NOCURSOR = 142;
  public static final int COLON = 64;
  public static final int SET = 121;
  public static final int ISA = 31;
  public static final int SQLCONDITION = 111;
  public static final int LCURLY = 59;
  public static final int EXECUTE = 128;
  public static final int ERRORSTRING = 5;
  public static final int NULLCONCATEQ = 52;
  public static final int EXTENDS = 86;
  public static final int ONEXCEPTION = 170;
  public static final int UPDATE = 165;
  public static final int TO = 119;
  public static final int INOUT = 90;

  /* non terminals */
  public static final int executeOption = 24;
  public static final int whenClause = 81;
  public static final int importDecl = 42;
  public static final int implementsOpt = 41;
  public static final int executeOption_plus = 96;
  public static final int connector = 2;
  public static final int openTarget_star = 117;
  public static final int externalTypeContent_star = 100;
  public static final int enumerationField_plus = 94;
  public static final int stmt = 77;
  public static final int UltraRoot = 1;
  public static final int useTypeOpt = 80;
  public static final int expr = 26;
  public static final int settingsBlock_plus = 127;
  public static final int getByKeyOption_plus = 105;
  public static final int handlerContent = 15;
  public static final int executeOption_star = 95;
  public static final int callSynchronizationOpt = 9;
  public static final int enumerationField_star = 93;
  public static final int expr_plus = 98;
  public static final int stepOpt = 76;
  public static final int structureContent_plus = 131;
  public static final int functionInvocation = 34;
  public static final int name_plus = 114;
  public static final int settingsBlock_star = 126;
  public static final int getByKeyOption_star = 104;
  public static final int deleteOption_plus = 92;
  public static final int classContent = 14;
  public static final int expr_star = 97;
  public static final int singleExtendsOpt = 28;
  public static final int initializerOpt = 43;
  public static final int lvalue = 51;
  public static final int addOption_plus = 84;
  public static final int openTarget = 58;
  public static final int structureContent_star = 130;
  public static final int replaceOption_plus = 122;
  public static final int settingsBlock = 71;
  public static final int enumerationField = 22;
  public static final int setTarget_plus = 113;
  public static final int eglClassContent_plus = 90;
  public static final int abstractModifierOpt = 75;
  public static final int deleteOption_star = 91;
  public static final int structureContent = 79;
  public static final int arrayAccess = 6;
  public static final int wsPair = 4;
  public static final int file = 31;
  public static final int whenClause_plus = 133;
  public static final int addOption_star = 83;
  public static final int exitModifierOpt = 25;
  public static final int classContent_plus = 86;
  public static final int replaceOption_star = 121;
  public static final int integerLiteralEnumValue = 23;
  public static final int callReturnTo = 10;
  public static final int getByPositionOption = 37;
  public static final int eglClassContent_star = 89;
  public static final int argumentsOpt = 99;
  public static final int interfaceContent = 48;
  public static final int functionParameter = 35;
  public static final int fieldAccess = 29;
  public static final int whenClause_star = 132;
  public static final int getByKeyOption = 36;
  public static final int classContent_star = 85;
  public static final int continueModifierOpt = 17;
  public static final int $START = 0;
  public static final int withNameOpt = 82;
  public static final int fromExprOpt = 33;
  public static final int getByPositionOption_plus = 107;
  public static final int defaultClauseOpt = 18;
  public static final int inlineSQLStatementOpt = 45;
  public static final int objExprEntry_plus = 125;
  public static final int objExprEntry = 70;
  public static final int settingsBlockOpt = 72;
  public static final int onException = 56;
  public static final int elseOpt = 21;
  public static final int part_plus = 120;
  public static final int getByPositionOption_star = 106;
  public static final int fieldsOpt = 30;
  public static final int withClause = 62;
  public static final int setting_plus = 124;
  public static final int eglClassContent = 16;
  public static final int part_star = 119;
  public static final int functionParameter_plus = 103;
  public static final int ID_plus = 108;
  public static final int handlerContent_plus = 88;
  public static final int callParametersOpt = 8;
  public static final int name = 53;
  public static final int setting_star = 123;
  public static final int interfaceContent_plus = 112;
  public static final int packageDeclarationOpt = 59;
  public static final int replaceOption = 66;
  public static final int onException_plus = 116;
  public static final int functionParameter_star = 102;
  public static final int callReturns = 13;
  public static final int constOpt = 55;
  public static final int handlerContent_star = 87;
  public static final int assignment = 7;
  public static final int returnsOpt = 67;
  public static final int addOption = 5;
  public static final int primary = 63;
  public static final int interfaceContent_star = 111;
  public static final int deleteOption = 19;
  public static final int intoClauseOpt = 49;
  public static final int onException_star = 115;
  public static final int IDOpt = 39;
  public static final int setting = 69;
  public static final int strItemDecl = 78;
  public static final int callOnException = 11;
  public static final int stmt_plus = 129;
  public static final int openModifierOpt = 57;
  public static final int part = 60;
  public static final int ErrorNode = 3;
  public static final int direction = 20;
  public static final int simpleNameOpt = 73;
  public static final int extendsOpt = 27;
  public static final int inparentOpt = 46;
  public static final int importDecl_plus = 110;
  public static final int moveModifierOpt = 52;
  public static final int privateAccessModifierOpt = 65;
  public static final int inlineSQLStatement = 44;
  public static final int externalTypeContent = 47;
  public static final int stmt_star = 128;
  public static final int questionOpt = 40;
  public static final int importDecl_star = 109;
  public static final int setTarget = 68;
  public static final int foreachTarget = 32;
  public static final int getByPositionSource = 38;
  public static final int partSubTypeOpt = 61;
  public static final int primaryNoNew = 64;
  public static final int literal = 50;
  public static final int openTarget_plus = 118;
  public static final int externalTypeContent_plus = 101;
  public static final int callUsingOpt = 12;
  public static final int namedType = 54;
  public static final int staticAccessModifierOpt = 74;
}

